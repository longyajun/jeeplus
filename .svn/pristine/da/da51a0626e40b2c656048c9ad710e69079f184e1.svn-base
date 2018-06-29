/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.agent.web;

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
import com.jeeplus.modules.agent.entity.UipAgent;
import com.jeeplus.modules.agent.service.UipAgentService;

/**
 * agentController
 * @author 李焱
 * @version 2018-06-12
 */
@Controller
@RequestMapping(value = "${adminPath}/agent/uipAgent")
public class UipAgentController extends BaseController {

	@Autowired
	private UipAgentService uipAgentService;
	
	@ModelAttribute
	public UipAgent get(@RequestParam(required=false) String id) {
		UipAgent entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = uipAgentService.get(id);
		}
		if (entity == null){
			entity = new UipAgent();
		}
		return entity;
	}
	
	/**
	 * agent列表页面
	 */
	@RequiresPermissions("agent:uipAgent:list")
	@RequestMapping(value = {"list", ""})
	public String list(UipAgent uipAgent, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<UipAgent> page = uipAgentService.findPage(new Page<UipAgent>(request, response), uipAgent); 
		model.addAttribute("page", page);
		return "modules/agent/uipAgentList";
	}

	/**
	 * 查看，增加，编辑agent表单页面
	 */
	@RequiresPermissions(value={"agent:uipAgent:view","agent:uipAgent:add","agent:uipAgent:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(UipAgent uipAgent, Model model) {
		model.addAttribute("uipAgent", uipAgent);
		return "modules/agent/uipAgentForm";
	}

	/**
	 * 保存agent
	 */
	@RequiresPermissions(value={"agent:uipAgent:add","agent:uipAgent:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public String save(UipAgent uipAgent, Model model, RedirectAttributes redirectAttributes) throws Exception{
		if (!beanValidator(model, uipAgent)){
			return form(uipAgent, model);
		}
		if(!uipAgent.getIsNewRecord()){//编辑表单保存
			UipAgent t = uipAgentService.get(uipAgent.getId());//从数据库取出记录的值
			MyBeanUtils.copyBeanNotNull2Bean(uipAgent, t);//将编辑表单中的非NULL值覆盖数据库记录中的值
			uipAgentService.save(t);//保存
		}else{//新增表单保存
			uipAgentService.save(uipAgent);//保存
		}
		addMessage(redirectAttributes, "保存agent成功");
		return "redirect:"+Global.getAdminPath()+"/agent/uipAgent/?repage";
	}
	
	/**
	 * 删除agent
	 */
	@RequiresPermissions("agent:uipAgent:del")
	@RequestMapping(value = "delete")
	public String delete(UipAgent uipAgent, RedirectAttributes redirectAttributes) {
		uipAgentService.delete(uipAgent);
		addMessage(redirectAttributes, "删除agent成功");
		return "redirect:"+Global.getAdminPath()+"/agent/uipAgent/?repage";
	}
	
	/**
	 * 批量删除agent
	 */
	@RequiresPermissions("agent:uipAgent:del")
	@RequestMapping(value = "deleteAll")
	public String deleteAll(String ids, RedirectAttributes redirectAttributes) {
		String idArray[] =ids.split(",");
		for(String id : idArray){
			uipAgentService.delete(uipAgentService.get(id));
		}
		addMessage(redirectAttributes, "删除agent成功");
		return "redirect:"+Global.getAdminPath()+"/agent/uipAgent/?repage";
	}
	
	/**
	 * 导出excel文件
	 */
	@RequiresPermissions("agent:uipAgent:export")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(UipAgent uipAgent, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "agent"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<UipAgent> page = uipAgentService.findPage(new Page<UipAgent>(request, response, -1), uipAgent);
    		new ExportExcel("agent", UipAgent.class).setDataList(page.getList()).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出agent记录失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/agent/uipAgent/?repage";
    }

	/**
	 * 导入Excel数据

	 */
	@RequiresPermissions("agent:uipAgent:import")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<UipAgent> list = ei.getDataList(UipAgent.class);
			for (UipAgent uipAgent : list){
				try{
					uipAgentService.save(uipAgent);
					successNum++;
				}catch(ConstraintViolationException ex){
					failureNum++;
				}catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条agent记录。");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条agent记录"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入agent失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/agent/uipAgent/?repage";
    }
	
	/**
	 * 下载导入agent数据模板
	 */
	@RequiresPermissions("agent:uipAgent:import")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "agent数据导入模板.xlsx";
    		List<UipAgent> list = Lists.newArrayList(); 
    		new ExportExcel("agent数据", UipAgent.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/agent/uipAgent/?repage";
    }
	
	
	

}