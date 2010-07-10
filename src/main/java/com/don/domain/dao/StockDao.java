package com.don.domain.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.support.DataAccessUtils;
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
	
	public List<Stock> findStock(String symbol) {
		DetachedCriteria detached = DetachedCriteria.forClass(Stock.class)
										.add( Property.forName("symbol").eq(symbol));
		
		return hibernateTemplate.findByCriteria( detached );
	}
	
	public List<String> findAllSymbols() {
		DetachedCriteria detached = DetachedCriteria.forClass(Stock.class)
//			.add( Property.forName("symbol").eq("%"));
			.setProjection(Projections.projectionList()
				.add(Projections.property("symbol"))
			);
		
		//List<String> results = hibernateTemplate.findByCriteria(detached);
		List<String> results = hibernateTemplate.find("select distinct symbol from Stock");
		//return DataAccessUtils.uniqueResult(results);
		
		return results;
	}
	
}
