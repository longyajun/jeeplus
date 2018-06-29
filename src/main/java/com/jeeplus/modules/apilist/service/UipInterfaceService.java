/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.apilist.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeeplus.common.persistence.Page;
import com.jeeplus.common.service.CrudService;
import com.jeeplus.modules.apilist.entity.UipInterface;
import com.jeeplus.modules.apilist.dao.UipInterfaceDao;

/**
 * apilistService
 * @author 李焱
 * @version 2018-06-12
 */
@Service
@Transactional(readOnly = true)
public class UipInterfaceService extends CrudService<UipInterfaceDao, UipInterface> {

	public UipInterface get(String id) {
		return super.get(id);
	}
	
	public List<UipInterface> findList(UipInterface uipInterface) {
		return super.findList(uipInterface);
	}
	
	public Page<UipInterface> findPage(Page<UipInterface> page, UipInterface uipInterface) {
		return super.findPage(page, uipInterface);
	}
	
	@Transactional(readOnly = false)
	public void save(UipInterface uipInterface) {
		super.save(uipInterface);
	}
	
	@Transactional(readOnly = false)
	public void delete(UipInterface uipInterface) {
		super.delete(uipInterface);
	}
	
	
	
	
}