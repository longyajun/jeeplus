/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.history.dao;

import com.jeeplus.common.persistence.CrudDao;
import com.jeeplus.common.persistence.annotation.MyBatisDao;
import com.jeeplus.modules.history.entity.UipHistory;

/**
 * historyDAO接口
 * @author 李焱
 * @version 2018-06-28
 */
@MyBatisDao
public interface UipHistoryDao extends CrudDao<UipHistory> {

	
}