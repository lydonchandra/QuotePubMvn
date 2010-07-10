package com.don.domain.dao;

import org.springframework.orm.hibernate3.HibernateTemplate;

import com.don.domain.Stock;

public class StockDao {
	
	public StockDao() {}
	
	private HibernateTemplate hibernateTemplate;
	
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
	public void saveStock(Stock stock) {
		hibernateTemplate.save(stock);
	}
	

}
