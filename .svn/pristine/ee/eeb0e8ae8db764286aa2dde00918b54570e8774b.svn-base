/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.agent.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeeplus.common.persistence.Page;
import com.jeeplus.common.service.CrudService;
import com.jeeplus.modules.agent.entity.UipAgent;
import com.jeeplus.modules.agent.dao.UipAgentDao;

/**
 * agentService
 * @author 李焱
 * @version 2018-06-12
 */
@Service
@Transactional(readOnly = true)
public class UipAgentService extends CrudService<UipAgentDao, UipAgent> {

	public UipAgent get(String id) {
		return super.get(id);
	}
	
	public List<UipAgent> findList(UipAgent uipAgent) {
		return super.findList(uipAgent);
	}
	
	public Page<UipAgent> findPage(Page<UipAgent> page, UipAgent uipAgent) {
		return super.findPage(page, uipAgent);
	}
	
	@Transactional(readOnly = false)
	public void save(UipAgent uipAgent) {
		super.save(uipAgent);
	}
	
	@Transactional(readOnly = false)
	public void delete(UipAgent uipAgent) {
		super.delete(uipAgent);
	}
	
	
	
	
}