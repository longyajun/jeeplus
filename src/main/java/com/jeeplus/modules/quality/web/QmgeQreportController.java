/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.quality.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.jeeplus.common.utils.DateUtils;
import com.jeeplus.common.utils.MyBeanUtils;
import com.jeeplus.common.config.Global;
import com.jeeplus.common.persistence.Page;
import com.jeeplus.common.web.BaseController;
import com.jeeplus.common.utils.StringUtils;
import com.jeeplus.common.utils.excel.ExportExcel;
import com.jeeplus.common.utils.excel.ImportExcel;
import com.jeeplus.modules.quality.entity.QmgeQreport;
import com.jeeplus.modules.quality.service.QmgeQreportService;

/**
 * 质量报告Controller
 * @author LONGYAJUN
 * @version 2018-05-15
 */
@Controller
@RequestMapping(value = "${adminPath}/quality/qmgeQreport")
public class QmgeQreportController extends BaseController {

	@Autowired
	private QmgeQreportService qmgeQreportService;
	
	@ModelAttribute
	public QmgeQreport get(@RequestParam(required=false) String id) {
		QmgeQreport entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = qmgeQreportService.get(id);
		}
		if (entity == null){
			entity = new QmgeQreport();
		}
		return entity;
	}
	
	/**
	 * 质量报告列表页面
	 */
	@RequiresPermissions("quality:qmgeQreport:list")
	@RequestMapping(value = {"list", ""})
	public String list(QmgeQreport qmgeQreport, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<QmgeQreport> page = qmgeQreportService.findPage(new Page<QmgeQreport>(request, response), qmgeQreport); 
		model.addAttribute("page", page);
		return "modules/quality/qmgeQreportList";
	}

	/**
	 * 查看，增加，编辑质量报告表单页面
	 */
	@RequiresPermissions(value={"quality:qmgeQreport:view","quality:qmgeQreport:add","quality:qmgeQreport:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(QmgeQreport qmgeQreport, Model model) {
		model.addAttribute("qmgeQreport", qmgeQreport);
		return "modules/quality/qmgeQreportForm";
	}

	/**
	 * 保存质量报告
	 */
	@RequiresPermissions(value={"quality:qmgeQreport:add","quality:qmgeQreport:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public String save(QmgeQreport qmgeQreport, Model model, RedirectAttributes redirectAttributes) throws Exception{
		if (!beanValidator(model, qmgeQreport)){
			return form(qmgeQreport, model);
		}
		if(!qmgeQreport.getIsNewRecord()){//编辑表单保存
			QmgeQreport t = qmgeQreportService.get(qmgeQreport.getId());//从数据库取出记录的值
			MyBeanUtils.copyBeanNotNull2Bean(qmgeQreport, t);//将编辑表单中的非NULL值覆盖数据库记录中的值
			qmgeQreportService.save(t);//保存
		}else{//新增表单保存
			qmgeQreportService.save(qmgeQreport);//保存
		}
		addMessage(redirectAttributes, "保存质量报告成功");
		return "redirect:"+Global.getAdminPath()+"/quality/qmgeQreport/?repage";
	}
	
	/**
	 * 删除质量报告
	 */
	@RequiresPermissions("quality:qmgeQreport:del")
	@RequestMapping(value = "delete")
	public String delete(QmgeQreport qmgeQreport, RedirectAttributes redirectAttributes) {
		qmgeQreportService.delete(qmgeQreport);
		addMessage(redirectAttributes, "删除质量报告成功");
		return "redirect:"+Global.getAdminPath()+"/quality/qmgeQreport/?repage";
	}
	
	/**
	 * 批量删除质量报告
	 */
	@RequiresPermissions("quality:qmgeQreport:del")
	@RequestMapping(value = "deleteAll")
	public String deleteAll(String ids, RedirectAttributes redirectAttributes) {
		String idArray[] =ids.split(",");
		for(String id : idArray){
			qmgeQreportService.delete(qmgeQreportService.get(id));
		}
		addMessage(redirectAttributes, "删除质量报告成功");
		return "redirect:"+Global.getAdminPath()+"/quality/qmgeQreport/?repage";
	}
	
	/**
	 * 导出excel文件
	 */
	@RequiresPermissions("quality:qmgeQreport:export")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(QmgeQreport qmgeQreport, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "质量报告"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<QmgeQreport> page = qmgeQreportService.findPage(new Page<QmgeQreport>(request, response, -1), qmgeQreport);
    		new ExportExcel("质量报告", QmgeQreport.class).setDataList(page.getList()).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出质量报告记录失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/quality/qmgeQreport/?repage";
    }

	/**
	 * 导入Excel数据

	 */
	@RequiresPermissions("quality:qmgeQreport:import")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<QmgeQreport> list = ei.getDataList(QmgeQreport.class);
			for (QmgeQreport qmgeQreport : list){
				try{
					qmgeQreportService.save(qmgeQreport);
					successNum++;
				}catch(ConstraintViolationException ex){
					failureNum++;
				}catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条质量报告记录。");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条质量报告记录"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入质量报告失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/quality/qmgeQreport/?repage";
    }
	
	/**
	 * 下载导入质量报告数据模板
	 */
	@RequiresPermissions("quality:qmgeQreport:import")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "质量报告数据导入模板.xlsx";
    		List<QmgeQreport> list = Lists.newArrayList(); 
    		new ExportExcel("质量报告数据", QmgeQreport.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/quality/qmgeQreport/?repage";
    }
	
	
	

}