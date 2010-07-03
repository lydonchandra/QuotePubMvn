package com.don.test;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;

import org.quartz.SchedulerException;
import org.quartz.impl.StdScheduler;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.FileSystemResource;

public class Play {

	public static void main(String[] args) throws IOException, ParseException, InterruptedException, SchedulerException {
		
		final BeanFactory factory = new XmlBeanFactory(new FileSystemResource("/Users/lydonchandra/Documents/workspace-sts-carbon/QuotePubMvn/src/main/java/com/don/test/quartz.xml"));
		StdScheduler sched = (StdScheduler)factory.getBean("scheduler");
		sched.start();
		
		//		while(true) {
//			String tmpdir = "/tmp/test";
//			File file = new File(tmpdir);
//			File[] children = file.listFiles();
//			for(File tmpFile: children) {
//				System.out.println(tmpFile.getAbsolutePath());
//				//send msg to jms to process
//				// delete file or archive file
//			}
//			
//			Thread.sleep(10000);
//		}
//		
		
		
//		String sDate = "07-May-2010 10:05";
//		DateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy HH:mm");
//		Date date = formatter.parse(sDate);
//		date = null;
//		OHLCDataItem[] items = new OHLCDataItem[10];
//		
//		int idx = 1;
//		for( int i=0; i<10; i++) {
//			items[i] = new OHLCDataItem(new Date(110, 2, idx), 1, 2, 1, 1.5, 1000);
//			idx++;
//		}
//		OHLCDataset dataset = new DefaultOHLCDataset("data1", items);	
//		
//		JFreeChart chart = ChartFactory.createCandlestickChart("stock", "datetime", "price", dataset, true);
//		ChartUtilities.saveChartAsPNG(new File("/Users/lydonchandra/chartdemo.png"), chart, 500, 500);
	
	}

}
