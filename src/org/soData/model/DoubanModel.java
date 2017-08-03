package org.soData.model;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.soData.Exception.ElementNotFoundException;
import org.soData.Exception.UrlRuleException;
import org.soData.pojo.LinkContent;
import org.soData.pojo.SOURL;
import org.soData.utils.TextUtil;

public class DoubanModel {
	
//	Document doc = Jsoup.connect("http://www.oschina.net/")   
//			 .data("query", "Java")   // �������  
//			 .userAgent("I �� m jsoup") // ���� User-Agent   
//			 .cookie("auth", "token") // ���� cookie   
//			 .timeout(3000)           // �������ӳ�ʱʱ��  
//			 .post();                 // ʹ�� POST �������� URL 
	private String stic = "https://movie.douban.com/subject/25823277/comments";
	private String url;
	private SOURL URL;
	public Set<String> link =new HashSet<String>();
	public HashSet<HashMap<String, String>> user = new HashSet<HashMap<String,String>>();
	public DoubanModel(String url,String[] params,String[] values,String element,int type,int requestType){
		URL = new SOURL( url,  
				params, values,  
				element, type, requestType);  
	}
	public boolean crawlPage(String url){
		validatesourl(URL);
		//List<LinkContent> content = CrawlService.CrawlPage(URL);
		//�������
		Connection conn = Jsoup.connect(URL.getUrl()+url);
		//��ʼ������
//		for(int i=0;i<URL.getParams().length;i++){
//			conn.data(URL.getParams()[i], URL.getValues()[i]);
//		}
		//��type����doc
		Document doc = null; 
        try{
	         switch (URL.getRequestMoethod())  
	         {  
	         case SOURL.GET:  
	             doc = conn.timeout(100000).get();  
	             break;  
	         case SOURL.POST:  
	             doc = conn.timeout(100000).post();  
	             break;  
	         }  
        }catch(IOException e){
       	 e.printStackTrace();
        }
        //����elements
        Elements userResults = new Elements();
        Elements rtlResults = new Elements();
        userResults = doc.getElementsByClass("comment-info"); 
        //��������û�
        for(Element es:userResults){
        	Elements e = es.getElementsByTag("a");
        	for(Element result : e){
        		HashMap<String,String> m = new HashMap<String,String>();
        		m.put(result.text(), result.attr("href"));
        		user.add(m);
        	}
        }
        Element linkResult= doc.getElementById("paginator");
        Elements e = linkResult.getElementsByTag("a");
        for(Element result:e){
        	if(result.text().equals("��ҳ >")){
        		link.add(result.attr("href"));
        		crawlPage(result.attr("href"));
        	}
        }
         return true;  
	}
	
	 private static  void validatesourl(SOURL sourl)  
	    {  
	        String url = sourl.getUrl();  
	        if (url==null||url.equals(""))  
	        {  
	            throw new UrlRuleException("url����Ϊ�գ�");  
	        }  
	        if (!url.startsWith("http://")&&!url.startsWith("https://"))  
	        {  
	            throw new UrlRuleException("url�ĸ�ʽ����ȷ��");  
	        }  
	  
	        if (sourl.getParams() != null && sourl.getValues() != null)  
	        {  
	            if (sourl.getParams().length != sourl.getValues().length)  
	            {  
	                throw new UrlRuleException("�����ļ�ֵ�Ը�����ƥ�䣡");  
	            }  
	        }  
	  
	    }  

}
