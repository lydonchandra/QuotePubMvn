package com.don.web;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.don.domain.Stock;
import com.don.domain.dao.StockDao;

public class StockController extends AbstractController {

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		Stock stock = new Stock();
		stock.setSymbol("dondo");
		stock.setId(1L);
		stock.setOpen(new BigDecimal("111.11"));
		stock.setHigh(new BigDecimal("111.11"));
		stock.setLow(new BigDecimal("111.11"));
		stock.setClose(new BigDecimal("111.11"));
		stock.setVol(111L);
		stock.setEv(new BigDecimal("111.11"));
		String sDate = "07-May-2010 09:00";
		DateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy HH:mm");
		Date date = formatter.parse(sDate);
		stock.setDate(date);
		
		stockDao.saveStock(stock);
		response.getWriter().print("StockController");
		response.getWriter().flush();
		return null;
	}

	private StockDao stockDao;
	public void setStockDao(StockDao stockDao) {
		this.stockDao = stockDao;
	}
	
	

}
