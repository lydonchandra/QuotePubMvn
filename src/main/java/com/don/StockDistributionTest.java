package com.don;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

public class StockDistributionTest extends TestCase {
	
	public static void main( String [] args)  {
		StockDistributionTest stockTest = new StockDistributionTest();
		stockTest.testPopEv();
	}
	
	public void testGetAllSymbols() {
//		assertEquals(true, true);
	}


	public void testPopEv() {
		List<EvItem> items = getEvItems();
		StockDistribution stockdist = new StockDistribution();
		BigDecimal NEG_ONE = new BigDecimal("-1");
		BigDecimal prevClose = new BigDecimal("-1");
		List<Long> evLists = new ArrayList<Long>();
		for( EvItem item: items ) {
			long ev = 0L;
			if( prevClose.compareTo(NEG_ONE) != 0 ) {
				ev = stockdist.getEv(prevClose, item.getOpen(), item.getHigh(), item.getLow(), item.getClose(), item.getVol() );
			}
			prevClose = item.getClose();
			System.out.println(ev);
			evLists.add(ev);
		}
		assertEquals( (long)evLists.get(0), 0L);
		assertEquals( (long)evLists.get(1), -10932L);
		assertEquals( (long)evLists.get(2), 0L);
		assertEquals( (long)evLists.get(3), -13802L);
		assertEquals( (long)evLists.get(4), -10511L);
		assertEquals( (long)evLists.get(5), 0L);
	}
	
	public List<EvItem> getEvItems() {
		
		StockDistribution stockdist = new StockDistribution();
		List<EvItem> evItems = new ArrayList<EvItem>(10);
//			1 Symbol 				open 	high 	low 	close 	vol		
		EvItem item1 = new EvItem(	"11.07","11.08","11.07","11.07", 20041L	);
		evItems.add(item1);
		EvItem item2 = new EvItem(	"11.07","11.08","11.06","11.06", 16399L	);
		evItems.add(item2);
		EvItem item3 = new EvItem(	"11.06","11.06","11.05","11.06", 32536L	);
		evItems.add(item3); 
		EvItem item5 = new EvItem(	"11.05","11.06","11.04","11.04", 13802L	);
		evItems.add(item5);
		EvItem item6 = new EvItem(	"11.04","11.04","11.01","11.02", 14015L	);
		evItems.add(item6);
		EvItem item7 = new EvItem(	"11.02","11.02","11.02","11.02", 40889L	);
		evItems.add(item7);
		EvItem item8 = new EvItem(	"11.01","11.02","11.01","11.02", 11351L	);
		evItems.add(item8);
		
		return evItems;
	}
	
	
	class EvItem {
		private final BigDecimal open;
		private final BigDecimal high;
		private final BigDecimal low;
		private final BigDecimal close;
		private final long vol;

		public EvItem(String open, String high, String low, String close, long vol) {
			this.open = new BigDecimal(open);
			this.high = new BigDecimal(high);
			this.low = new BigDecimal(low);
			this.close = new BigDecimal(close);
			this.vol = vol;
		}

		public BigDecimal getOpen() {
			return open;
		}

		public BigDecimal getHigh() {
			return high;
		}

		public BigDecimal getLow() {
			return low;
		}

		public BigDecimal getClose() {
			return close;
		}

		public long getVol() {
			return vol;
		}
		
	}
	
}
