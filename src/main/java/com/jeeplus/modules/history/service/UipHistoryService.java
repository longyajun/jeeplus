/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.history.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeeplus.common.persistence.Page;
import com.jeeplus.common.service.CrudService;
import com.jeeplus.modules.history.entity.UipHistory;
import com.jeeplus.modules.history.dao.UipHistoryDao;

/**
 * historyService
 * @author 李焱
 * @version 2018-06-28
 */
@Service
@Transactional(readOnly = true)
public class UipHistoryService extends CrudService<UipHistoryDao, UipHistory> {

	public UipHistory get(String id) {
		return super.get(id);
	}
	
	public List<UipHistory> findList(UipHistory uipHistory) {
		return super.findList(uipHistory);
	}
	
	public Page<UipHistory> findPage(Page<UipHistory> page, UipHistory uipHistory) {
		return super.findPage(page, uipHistory);
	}
	
	@Transactional(readOnly = false)
	public void save(UipHistory uipHistory) {
		super.save(uipHistory);
	}
	
	@Transactional(readOnly = false)
	public void delete(UipHistory uipHistory) {
		super.delete(uipHistory);
	}
	
	
	
	
}