package org.soData.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.soData.Exception.UrlRuleException;
import org.soData.pojo.Enity;
import org.soData.pojo.Rule;

public class CrawlService_douban extends BaseCrawl implements IModel{

	@Override
	public List<Enity> crawl(Rule rule) {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
//	public List<Enity> crawl(Rule rule) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//	private String stic = "https://movie.douban.com/subject/25823277/comments";
//	private String url;
//	private SOURL URL;
//	public Set<String> link =new HashSet<String>();
//	public HashSet<HashMap<String, String>> user = new HashSet<HashMap<String,String>>();
//	public CrawlService_douban(String url,String[] params,String[] values,String element,int type,int requestType){
//		URL = new SOURL( url,  
//				params, values,  
//				element, type, requestType);  
//	}
//	public boolean crawlPage(String url){
//
//		Connection conn = Jsoup.connect(URL.getUrl()+url);
//		Document doc = null; 
//        try{
//	         switch (URL.getRequestMoethod())  
//	         {  
//	         case SOURL.GET:  
//	             doc = conn.timeout(100000).get();  
//	             break;  
//	         case SOURL.POST:  
//	             doc = conn.timeout(100000).post();  
//	             break;  
//	         }  
//        }catch(IOException e){
//       	 e.printStackTrace();
//        }
//      
//        Elements userResults = new Elements();
//        Elements rtlResults = new Elements();
//        userResults = doc.getElementsByClass("comment-info"); 
//        
//        for(Element es:userResults){
//        	Elements e = es.getElementsByTag("a");
//        	for(Element result : e){
//        		HashMap<String,String> m = new HashMap<String,String>();
//        		m.put(result.text(), result.attr("href"));
//        		user.add(m);
//        	}
//        }
//        Element linkResult= doc.getElementById("paginator");
//        Elements e = linkResult.getElementsByTag("a");
//        for(Element result:e){
//        	if(result.text().equals("��ҳ >")){
//        		link.add(result.attr("href"));
//        		crawlPage(result.attr("href"));
//        	}
//        }
//         return true;  
//	}
}
