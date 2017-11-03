package org.soData.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.soData.pojo.Enity;
import org.soData.pojo.Rule;

public class CrawlService_title extends BaseCrawl implements IModel{
//	Document doc = Jsoup.connect("http://www.oschina.net/")   
//	 .data("query", "Java")   // 设置参数 
//	 .userAgent("") // 设置 User-Agent   
//	 .cookie("auth", "token") // 设置cookie   
//	 .timeout(3000)           // 相应时间 
//	 .post().get();           //使用get或post
	Logger log = LoggerFactory.getLogger(getClass());
	public void writeTitle(Rule rule,String path){
		log.info(rule.getUrl());
		validatesourl(rule);
		List<Enity>enitys = new ArrayList<Enity>();
		Connection conn = Jsoup.connect(rule.getUrl());
		
		conn.header("Accept", "text/html");
		conn.header("Accept-Charset", "utf-8");
		conn.header("Accept-Encoding", "gzip");
		conn.header("Accept-Language", "en-US,en");
		conn.userAgent("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.22 (KHTML, like Gecko) Chrome/25.0.1364.160 Safari/537.22");
		Document doc = null;
		System.out.println(rule.getUrl());
		try{
			switch(rule.getRequestMoethod()){
				case Rule.GET: 
					doc = conn.timeout(rule.getTime_out()).get();break;
				case Rule.POST:	
					doc = conn.timeout(rule.getTime_out()).post();break;
				default:
					break;	
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			log.debug(e.getMessage());
		}
		if(doc!=null){
			Elements results = new Elements();
			switch(rule.getType()){
				case Rule.CLASS:
					results = doc.getElementsByClass(rule.getSelectModel());break;
				case Rule.SELECTION:
					results = doc.select(rule.getSelectModel());break;
				default:
					results = doc.getElementsByTag("<a>");
			}
			
			//装载Enity
	        for (Element result : results)  
	        {  
	            Elements links = result.getElementsByTag("a");  

	            for (Element link : links)  
	            {  
	                String linkHref = link.attr("href");  
	                String linkText = link.text();  

	                Enity enity= new Enity();  
	                enity.setHref(linkHref);  
	                enity.setText(linkText);  

	                enitys.add(enity);  
	            }  
	        }
	        
	        writeFile(path,enitys);
	        Elements links = doc.getElementsByClass("page");
	        String linkHref = null;
	        //装载page
	        for (Element result : links)  
	        {  
	            Elements link = result.getElementsByTag("a");  

	            for (Element e : link)  
	            {  
	            	if(e.text().equals("下一页")){
	            		linkHref = e.attr("href"); 
	            	} 
	            }  
	        }
	        
	        if(linkHref!=null){
	        	rule.setUrl("https://3344su.com"+linkHref);
	        	writeTitle(rule,path);
	        	
	        }else{
	        	return;
	        }
	        
		}else{
			log.debug("页面为空");
			writeTitle( rule, path);
		}
		
		
	}
	public void writeFile(String path,List<Enity>enitys){
		File f = new File(path);
		if(!f.exists()){
			try {
				f.createNewFile();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		FileWriter fw;
		try {
			fw = new FileWriter(f,true);
			BufferedWriter bw = new BufferedWriter(fw);
			
			for(Enity e:enitys){
				bw.write(e.getText()+":"+e.getHref());
				bw.newLine();
			}
			bw.flush();
			bw.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	@Override
	public List<Enity> crawl(Rule rule) {
		// TODO Auto-generated method stub
		validatesourl(rule);
		List<Enity> enitys = new ArrayList<Enity>();
		return enitys;
	}

}
