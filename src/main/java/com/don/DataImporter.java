package com.don;

import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.don.domain.Stock;
import com.don.domain.dao.StockDao;

import au.com.bytecode.opencsv.CSVReader;

public class DataImporter {
    public static void main(String [] args) throws ClassNotFoundException, SQLException, IOException, ParseException {
//        Class.forName(MyConstants.DATABASE_DRIVER);
//        String db = "stock8";
//        String url = MyConstants.DATABASE_JDBC + db;
//        Connection conn = java.sql.DriverManager.getConnection(url, "root", "");
//        Statement statement = conn.createStatement();
//
//        String filename = "/Users/lydonchandra/Documents/workspace-sts-carbon/QuotePub3/src/com/don/test/data/test.csv";        
//        CSVReader reader = new CSVReader(new FileReader(filename));
//        
//        
//        String[] aLine = new String[10];
//        String lastSymbol = "";
//        DataImporter dataImporter = new DataImporter();
//        
//        dataImporter.importDataHibernate(filename, db);
//        while( (aLine = reader.readNext()) != null  ) { // for each line in csv file
        	
//            String symbol = aLine[0];
//            if( !symbol.equals(lastSymbol) && !symbol.equalsIgnoreCase("Symbol")) {            	
//                if( !dataImporter.isTable(symbol, db, statement) ) // symbol/table does not exist, create one
//                	dataImporter.createTable2(symbol, statement);
//            }
//
//            lastSymbol = symbol;
//
//            if( !symbol.equalsIgnoreCase("Symbol") ) {
//            	dataImporter.insertLine2(aLine, symbol, statement);
//            }
//        }
       
        System.out.println("done");
    }
    
    private StockDao stockDao;
	public void setStockDao(StockDao stockDao) {
		this.stockDao = stockDao;
	}
	
    public void importDataHibernate(String filename, String db) throws ClassNotFoundException, SQLException, IOException, ParseException {
    	CSVReader reader = new CSVReader(new FileReader(filename));
        
        String[] aLine = new String[10];
//        String lastSymbol = "";
        while( (aLine = reader.readNext()) != null  ) { // for each line in csv file
            String symbol = aLine[0];

//            lastSymbol = symbol;

            if( !symbol.equalsIgnoreCase("Symbol") ) {
            	insertLineHibernate(aLine, symbol);
            }
        }
        System.out.println("done");

    }

	public boolean insertLineHibernate(String[] aLine, String symbol ) throws ParseException {
	
		Stock stock = new Stock();
		stock.setSymbol(aLine[0]);
		stock.setOpen(new BigDecimal(aLine[2]));
		stock.setHigh(new BigDecimal(aLine[3]));
		stock.setLow(new BigDecimal(aLine[4]));
		stock.setClose(new BigDecimal(aLine[5]));
		stock.setVol(Long.parseLong(aLine[6]));
		//stock.setEv(new BigDecimal("111.11"));
		String sDate = aLine[1];//"07-May-2010 09:00";
		DateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy HH:mm");
		Date date = formatter.parse(sDate);
		stock.setDate(date);
		
		this.stockDao.saveStock(stock);
		
		return true;
	}
    
}
