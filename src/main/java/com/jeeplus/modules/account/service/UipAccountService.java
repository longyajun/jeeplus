/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.account.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeeplus.common.persistence.Page;
import com.jeeplus.common.service.CrudService;
import com.jeeplus.modules.account.entity.UipAccount;
import com.jeeplus.modules.account.dao.UipAccountDao;

/**
 * accountService
 * @author 李焱
 * @version 2018-06-12
 */
@Service
@Transactional(readOnly = true)
public class UipAccountService extends CrudService<UipAccountDao, UipAccount> {

	public UipAccount get(String id) {
		return super.get(id);
	}
	
	public List<UipAccount> findList(UipAccount uipAccount) {
		return super.findList(uipAccount);
	}
	
	public Page<UipAccount> findPage(Page<UipAccount> page, UipAccount uipAccount) {
		return super.findPage(page, uipAccount);
	}
	
	@Transactional(readOnly = false)
	public void save(UipAccount uipAccount) {
		super.save(uipAccount);
	}
	
	@Transactional(readOnly = false)
	public void delete(UipAccount uipAccount) {
		super.delete(uipAccount);
	}
	
	
	
	
}