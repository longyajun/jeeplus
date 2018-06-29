/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.agent.entity;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.NotNull;

import com.jeeplus.common.persistence.DataEntity;
import com.jeeplus.common.utils.excel.annotation.ExcelField;

/**
 * agentEntity
 * @author 李焱
 * @version 2018-06-12
 */
public class UipAgent extends DataEntity<UipAgent> {
	
	private static final long serialVersionUID = 1L;
	private String telephone;		// 手机号
	private String name;		// 代理名称
	private Date createTime;		// 创建日期
	private String agentId;		// 代理ID
	
	public UipAgent() {
		super();
	}

	public UipAgent(String id){
		super(id);
	}

	@ExcelField(title="手机号", align=2, sort=1)
	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	@ExcelField(title="代理名称", align=2, sort=2)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="创建日期不能为空")
	@ExcelField(title="创建日期", align=2, sort=3)
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@ExcelField(title="代理ID", align=2, sort=4)
	public String getAgentId() {
		return agentId;
	}

	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}
	
}