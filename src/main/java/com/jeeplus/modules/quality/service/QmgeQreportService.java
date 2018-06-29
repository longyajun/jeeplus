/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.quality.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeeplus.common.persistence.Page;
import com.jeeplus.common.service.CrudService;
import com.jeeplus.modules.quality.entity.QmgeQreport;
import com.jeeplus.modules.quality.dao.QmgeQreportDao;

/**
 * 质量报告Service
 * @author LONGYAJUN
 * @version 2018-05-15
 */
@Service
@Transactional(readOnly = true)
public class QmgeQreportService extends CrudService<QmgeQreportDao, QmgeQreport> {

	public QmgeQreport get(String id) {
		return super.get(id);
	}
	
	public List<QmgeQreport> findList(QmgeQreport qmgeQreport) {
		return super.findList(qmgeQreport);
	}
	
	public Page<QmgeQreport> findPage(Page<QmgeQreport> page, QmgeQreport qmgeQreport) {
		return super.findPage(page, qmgeQreport);
	}
	
	@Transactional(readOnly = false)
	public void save(QmgeQreport qmgeQreport) {
		super.save(qmgeQreport);
	}
	
	@Transactional(readOnly = false)
	public void delete(QmgeQreport qmgeQreport) {
		super.delete(qmgeQreport);
	}
	
	
	
	
}