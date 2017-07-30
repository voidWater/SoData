package org.soData.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.soData.Exception.UrlRuleException;
import org.soData.pojo.LinkContent;
import org.soData.pojo.Rule;
import org.soData.pojo.SOURL;
import org.soData.utils.TextUtil;

public class CrawlService {
	public static List<LinkContent> CrawlPage(SOURL sourl){
		validatesourl(sourl);
		List<LinkContent> linkDatas = new ArrayList<LinkContent>();
		LinkContent linkData = null;
		
		 String url = sourl.getUrl();  
         String[] params = sourl.getParams();  
         String[] values = sourl.getValues();  
         String resultTagName = sourl.getResultTagName();  
         int type = sourl.getType();  
         int requestType = sourl.getRequestMoethod();  

         Connection conn = Jsoup.connect(url);
         // 设置查询参数  
         
         if (params != null)  
         {  
             for (int i = 0; i < params.length; i++)  
             {  
                 conn.data(params[i], values[i]);  
             }  
         }  

         // 设置请求类型  
         Document doc = null; 
         try{
	         switch (requestType)  
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
         //处理返回数据  
         Elements results = new Elements(); 
         switch (type)  
         {  
         case SOURL.CLASS:  
             results = doc.getElementsByClass(resultTagName);  
             break;  
         case SOURL.ID:  
             Element result = doc.getElementById(resultTagName);  
             results.add(result);  
             break;  
         case SOURL.SELECTION:  
             results = doc.select(resultTagName);  
             break;  
         default:  
             //当resultTagName为空时默认去body标签  
             if (TextUtil.isEmpty(resultTagName))  
             {  
                 results = doc.getElementsByTag("body");  
             }  
         }
         for (Element result : results)  
         {  
             Elements links = result.getElementsByTag("a");  

             for (Element link : links)  
             {  
                 //必要的筛选  
                 String linkHref = link.attr("href");  
                 String linkText = link.text();  

                 linkData = new LinkContent();  
                 linkData.setLinkHref(linkHref);  
                 linkData.setLinkText(linkText);  

                 linkDatas.add(linkData);  
             }  
         }  
		
		return linkDatas;
	}
	
	public static List<LinkContent> CraelPage(SOURL sourl,Rule rule){
		validatesourl(sourl);
		List<LinkContent> linkDatas = new ArrayList<LinkContent>();
		LinkContent linkData = null;
		
		 String url = sourl.getUrl();  
         String[] params = sourl.getParams();  
         String[] values = sourl.getValues();  
         String resultTagName = sourl.getResultTagName();  
         int type = sourl.getType();  
         int requestType = sourl.getRequestMoethod();  

         Connection conn = Jsoup.connect(url);
         // 设置查询参数  
         
         if (params != null)  
         {  
             for (int i = 0; i < params.length; i++)  
             {  
                 conn.data(params[i], values[i]);  
             }  
         }  

         // 设置请求类型  
         Document doc = null; 
         try{
	         switch (requestType)  
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
         //处理返回数据  
         Elements results = new Elements(); 
         switch (type)  
         {  
         case SOURL.CLASS:  
             results = doc.getElementsByClass(resultTagName);  
             break;  
         case SOURL.ID:  
             Element result = doc.getElementById(resultTagName);  
             results.add(result);  
             break;  
         case SOURL.SELECTION:  
             results = doc.select(resultTagName);  
             break;  
         default:  
             //当resultTagName为空时默认去body标签  
             if (TextUtil.isEmpty(resultTagName))  
             {  
                 results = doc.getElementsByTag("body");  
             }  
         }
         for (Element result : results)  
         {  
             Elements links = result.getElementsByTag("a");  

             for (Element link : links)  
             {  
                 //必要的筛选  
                 String linkHref = link.attr("href");  
                 String linkText = link.text();  

                 linkData = new LinkContent();  
                 linkData.setLinkHref(linkHref);  
                 linkData.setLinkText(linkText);  

                 linkDatas.add(linkData);  
             }  
         }  
		
		return linkDatas;
	}
	/** 
     * 对传入的参数进行必要的校验 
     */  
    private static  void validatesourl(SOURL sourl)  
    {  
        String url = sourl.getUrl();  
        if (url==null||url.equals(""))  
        {  
            throw new UrlRuleException("url不能为空！");  
        }  
        if (!url.startsWith("http://")&&!url.startsWith("https://"))  
        {  
            throw new UrlRuleException("url的格式不正确！");  
        }  
  
        if (sourl.getParams() != null && sourl.getValues() != null)  
        {  
            if (sourl.getParams().length != sourl.getValues().length)  
            {  
                throw new UrlRuleException("参数的键值对个数不匹配！");  
            }  
        }  
  
    }  
}
