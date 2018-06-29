/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.history.entity;

import javax.validation.constraints.NotNull;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.jeeplus.common.persistence.DataEntity;
import com.jeeplus.common.utils.excel.annotation.ExcelField;

/**
 * historyEntity
 * @author 李焱
 * @version 2018-06-29
 */
public class UipHistory extends DataEntity<UipHistory> {
	
	private static final long serialVersionUID = 1L;
	private Double je;		// 计费
	private String interType;		// 接口认证类型
	private String khzh;		// 代理ID
	private String ip;		// IP
	private Date createTime;		// 创建时间
	private String itype;		// 接口名称
	
	public UipHistory() {
		super();
	}

	public UipHistory(String id){
		super(id);
	}

	@NotNull(message="计费不能为空")
	@ExcelField(title="计费", align=2, sort=1)
	public Double getJe() {
		return je;
	}

	public void setJe(Double je) {
		this.je = je;
	}
	
	@ExcelField(title="接口认证类型", align=2, sort=2)
	public String getInterType() {
		return interType;
	}

	public void setInterType(String interType) {
		this.interType = interType;
	}
	
	@ExcelField(title="代理ID", align=2, sort=3)
	public String getKhzh() {
		return khzh;
	}

	public void setKhzh(String khzh) {
		this.khzh = khzh;
	}
	
	@ExcelField(title="IP", align=2, sort=4)
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="创建时间不能为空")
	@ExcelField(title="创建时间", align=2, sort=5)
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@ExcelField(title="接口名称", align=2, sort=6)
	public String getItype() {
		return itype;
	}

	public void setItype(String itype) {
		this.itype = itype;
	}
	
}