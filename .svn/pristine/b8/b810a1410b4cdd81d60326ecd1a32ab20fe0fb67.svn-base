/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.apilist.entity;

import javax.validation.constraints.NotNull;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.jeeplus.common.persistence.DataEntity;
import com.jeeplus.common.utils.excel.annotation.ExcelField;

/**
 * apilistEntity
 * @author 李焱
 * @version 2018-06-12
 */
public class UipInterface extends DataEntity<UipInterface> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 接口名称
	private Double cost;		// 接口费用
	private Date createTime;		// 创建日期
	private String type;		// 接口认证类型
	
	public UipInterface() {
		super();
	}

	public UipInterface(String id){
		super(id);
	}

	@ExcelField(title="接口名称", align=2, sort=1)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@NotNull(message="接口费用不能为空")
	@ExcelField(title="接口费用", align=2, sort=2)
	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
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
	
	@ExcelField(title="接口认证类型", align=2, sort=4)
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}