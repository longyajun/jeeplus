/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.quality.entity;

import javax.validation.constraints.NotNull;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.jeeplus.common.persistence.DataEntity;
import com.jeeplus.common.utils.excel.annotation.ExcelField;

/**
 * 质量报告Entity
 * @author LONGYAJUN
 * @version 2018-05-15
 */
public class QmgeQreport extends DataEntity<QmgeQreport> {
	
	private static final long serialVersionUID = 1L;
	private Integer projectId;		// 项目id
	private String qreportName;		// 报告名称
	private Date createTime;		// 创建时间
	private String createWriter;		// 创建人
	private Integer isDisplay;		// 是否显示
	private String qreportDes;		// 描述
	private Date insertTime;		// 新增时间
	private Date updateTime;		// 修改时间
	private String express1;		// 扩展字段
	private String express2;		// 扩展字段
	private String express3;		// 扩展字段
	private String express4;		// 扩展字段
	
	public QmgeQreport() {
		super();
	}

	public QmgeQreport(String id){
		super(id);
	}

	@NotNull(message="项目id不能为空")
	@ExcelField(title="项目id", align=2, sort=1)
	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}
	
	@ExcelField(title="报告名称", align=2, sort=2)
	public String getQreportName() {
		return qreportName;
	}

	public void setQreportName(String qreportName) {
		this.qreportName = qreportName;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="创建时间不能为空")
	@ExcelField(title="创建时间", align=2, sort=3)
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@ExcelField(title="创建人", align=2, sort=4)
	public String getCreateWriter() {
		return createWriter;
	}

	public void setCreateWriter(String createWriter) {
		this.createWriter = createWriter;
	}
	
	@ExcelField(title="是否显示", align=2, sort=5)
	public Integer getIsDisplay() {
		return isDisplay;
	}

	public void setIsDisplay(Integer isDisplay) {
		this.isDisplay = isDisplay;
	}
	
	@ExcelField(title="描述", dictType="del_flag", align=2, sort=6)
	public String getQreportDes() {
		return qreportDes;
	}

	public void setQreportDes(String qreportDes) {
		this.qreportDes = qreportDes;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="新增时间", align=2, sort=7)
	public Date getInsertTime() {
		return insertTime;
	}

	public void setInsertTime(Date insertTime) {
		this.insertTime = insertTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="修改时间", align=2, sort=8)
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	@ExcelField(title="扩展字段", align=2, sort=9)
	public String getExpress1() {
		return express1;
	}

	public void setExpress1(String express1) {
		this.express1 = express1;
	}
	
	@ExcelField(title="扩展字段", align=2, sort=10)
	public String getExpress2() {
		return express2;
	}

	public void setExpress2(String express2) {
		this.express2 = express2;
	}
	
	@ExcelField(title="扩展字段", align=2, sort=11)
	public String getExpress3() {
		return express3;
	}

	public void setExpress3(String express3) {
		this.express3 = express3;
	}
	
	@ExcelField(title="扩展字段", align=2, sort=12)
	public String getExpress4() {
		return express4;
	}

	public void setExpress4(String express4) {
		this.express4 = express4;
	}
	
}