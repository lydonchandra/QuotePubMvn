package com.don;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.don.domain.Stock;
import com.don.domain.dao.StockDao;


public class StockDistribution {
    public static void main(String [] args ) throws ClassNotFoundException, SQLException {
//        Class.forName("com.mysql.jdbc.Driver");
//        String db = "stock5";
//        String url = "jdbc:mysql://localhost:3306/" + db;
//        Connection conn = java.sql.DriverManager.getConnection(url, "root", "");
//        Statement stat = conn.createStatement();
//
//        ResultSet rs = stat.executeQuery("select table_name from information_schema.tables where table_schema = '" + db + "'");
//        // store all stock names in arrStock, so we can use it later to calculate the EV
//        List<String> arrStock = new ArrayList<String>();
//
//        while( rs.next() ) {
//           String tableName = rs.getString("table_name");
//           arrStock.add(tableName);
//        }
//    	StockDistribution stockDist = new StockDistribution();
//    	List<String> arrStock = stockDist.getAllSymbols("stock5");
//        String theDate = "07-May-2010";        
//        for( int idx=0; idx< arrStock.size(); idx++ ) {
//            stockDist.popEv( arrStock.get(idx), theDate); 
//        }
    }
    
    public void calculateAllEv() {
    	List<String> allSymbols = stockDao.findAllSymbols();
    	for( String symbol: allSymbols ) {
    		popEv(symbol);
    	}
    }
    
    public void popEv(String symbol) {
    	List<Stock> stocks = stockDao.findStock(symbol);
    	BigDecimal prevClose = null;
    	for( Stock stock: stocks ) {
    		
    		if( prevClose != null ) {
    			long ev = getEv(prevClose, stock.getOpen(),
    							stock.getHigh(),
    							stock.getLow(),
    							stock.getClose(),
    							stock.getVol()
    			);
    			stock.setEv(new BigDecimal(ev));
    			stockDao.saveStock(stock);
    		}
    		
    		prevClose = stock.getClose();
    	}
    }
        
    public long getEv(BigDecimal prevClose, BigDecimal open, BigDecimal high, BigDecimal low, BigDecimal close, long vol) {
    	if(prevClose.compareTo(close) == 0) { 
    		return 0L; 
    	}
    		
        final BigDecimal PI = new BigDecimal("0.01");
        BigDecimal evHigh = high.max(prevClose);
        BigDecimal evLow = low.min(prevClose);
        BigDecimal evDiff = prevClose.subtract(close).add(PI);
        BigDecimal evSpread = evHigh.subtract(evLow).add(PI);
        BigDecimal effVol = evDiff.divide( evSpread, 5, RoundingMode.HALF_EVEN )  .multiply( new BigDecimal(vol));
        
        if( open.compareTo(close) == 1)
        	effVol = effVol.multiply(new BigDecimal("-1"));
        
        return effVol.longValue();        
    }

    
    private String db;
    public void setDb(String db) {
    	this.db = db;
    }

    private StockDao stockDao;
	public void setStockDao(StockDao stockDao) {
		this.stockDao = stockDao;
	}
}
