/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.account.entity;

import javax.validation.constraints.NotNull;

import com.jeeplus.common.persistence.DataEntity;
import com.jeeplus.common.utils.excel.annotation.ExcelField;

/**
 * accountEntity
 * @author 李焱
 * @version 2018-06-12
 */
public class UipAccount extends DataEntity<UipAccount> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 账户名称
	private Double amount;		// 账户金额
	private Double balance;		// 账户余额
	private String certifId;		// 证书ID
	private String agentId;		// 代理ID
	
	public UipAccount() {
		super();
	}

	public UipAccount(String id){
		super(id);
	}

	@ExcelField(title="账户名称", align=2, sort=1)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@NotNull(message="账户金额不能为空")
	@ExcelField(title="账户金额", align=2, sort=2)
	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
	@NotNull(message="账户余额不能为空")
	@ExcelField(title="账户余额", align=2, sort=3)
	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}
	
	@ExcelField(title="证书ID", align=2, sort=4)
	public String getCertifId() {
		return certifId;
	}

	public void setCertifId(String certifId) {
		this.certifId = certifId;
	}
	
	@ExcelField(title="代理ID", align=2, sort=5)
	public String getAgentId() {
		return agentId;
	}

	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}
	
}