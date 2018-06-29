/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.history.web;

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
import com.jeeplus.modules.history.entity.UipHistory;
import com.jeeplus.modules.history.service.UipHistoryService;

/**
 * historyController
 * @author 李焱
 * @version 2018-06-28
 */
@Controller
@RequestMapping(value = "${adminPath}/history/uipHistory")
public class UipHistoryController extends BaseController {

	@Autowired
	private UipHistoryService uipHistoryService;
	
	@ModelAttribute
	public UipHistory get(@RequestParam(required=false) String id) {
		UipHistory entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = uipHistoryService.get(id);
		}
		if (entity == null){
			entity = new UipHistory();
		}
		return entity;
	}
	
	/**
	 * history列表页面
	 */
	@RequiresPermissions("history:uipHistory:list")
	@RequestMapping(value = {"list", ""})
	public String list(UipHistory uipHistory, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<UipHistory> page = uipHistoryService.findPage(new Page<UipHistory>(request, response), uipHistory); 
		model.addAttribute("page", page);
		return "modules/history/uipHistoryList";
	}

	/**
	 * 查看，增加，编辑history表单页面
	 */
	@RequiresPermissions(value={"history:uipHistory:view","history:uipHistory:add","history:uipHistory:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(UipHistory uipHistory, Model model) {
		model.addAttribute("uipHistory", uipHistory);
		return "modules/history/uipHistoryForm";
	}

	/**
	 * 保存history
	 */
	@RequiresPermissions(value={"history:uipHistory:add","history:uipHistory:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public String save(UipHistory uipHistory, Model model, RedirectAttributes redirectAttributes) throws Exception{
		if (!beanValidator(model, uipHistory)){
			return form(uipHistory, model);
		}
		if(!uipHistory.getIsNewRecord()){//编辑表单保存
			UipHistory t = uipHistoryService.get(uipHistory.getId());//从数据库取出记录的值
			MyBeanUtils.copyBeanNotNull2Bean(uipHistory, t);//将编辑表单中的非NULL值覆盖数据库记录中的值
			uipHistoryService.save(t);//保存
		}else{//新增表单保存
			uipHistoryService.save(uipHistory);//保存
		}
		addMessage(redirectAttributes, "保存history成功");
		return "redirect:"+Global.getAdminPath()+"/history/uipHistory/?repage";
	}
	
	/**
	 * 删除history
	 */
	@RequiresPermissions("history:uipHistory:del")
	@RequestMapping(value = "delete")
	public String delete(UipHistory uipHistory, RedirectAttributes redirectAttributes) {
		uipHistoryService.delete(uipHistory);
		addMessage(redirectAttributes, "删除history成功");
		return "redirect:"+Global.getAdminPath()+"/history/uipHistory/?repage";
	}
	
	/**
	 * 批量删除history
	 */
	@RequiresPermissions("history:uipHistory:del")
	@RequestMapping(value = "deleteAll")
	public String deleteAll(String ids, RedirectAttributes redirectAttributes) {
		String idArray[] =ids.split(",");
		for(String id : idArray){
			uipHistoryService.delete(uipHistoryService.get(id));
		}
		addMessage(redirectAttributes, "删除history成功");
		return "redirect:"+Global.getAdminPath()+"/history/uipHistory/?repage";
	}
	
	/**
	 * 导出excel文件
	 */
	@RequiresPermissions("history:uipHistory:export")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(UipHistory uipHistory, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "history"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<UipHistory> page = uipHistoryService.findPage(new Page<UipHistory>(request, response, -1), uipHistory);
    		new ExportExcel("history", UipHistory.class).setDataList(page.getList()).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出history记录失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/history/uipHistory/?repage";
    }

	/**
	 * 导入Excel数据

	 */
	@RequiresPermissions("history:uipHistory:import")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<UipHistory> list = ei.getDataList(UipHistory.class);
			for (UipHistory uipHistory : list){
				try{
					uipHistoryService.save(uipHistory);
					successNum++;
				}catch(ConstraintViolationException ex){
					failureNum++;
				}catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条history记录。");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条history记录"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入history失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/history/uipHistory/?repage";
    }
	
	/**
	 * 下载导入history数据模板
	 */
	@RequiresPermissions("history:uipHistory:import")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "history数据导入模板.xlsx";
    		List<UipHistory> list = Lists.newArrayList(); 
    		new ExportExcel("history数据", UipHistory.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/history/uipHistory/?repage";
    }
	
	
	

}