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
import org.soData.pojo.SOURL;
import org.soData.utils.TextUtil;

public class CrawlPicService {

	public static List<LinkContent> CrawlPage(SOURL sourl){
		validatesourl(sourl);
		List<LinkContent> linkDatas = new ArrayList<LinkContent>();
		LinkContent linkData = null;
		//获取链接参数
		String url = sourl.getUrl();  
        String[] params = sourl.getParams();  
        String[] values = sourl.getValues();  
        String resultTagName = sourl.getResultTagName();  
        int type = sourl.getType();  
        int requestType = sourl.getRequestMoethod();
        //设置链接
        Connection con = Jsoup.connect(url);
        //设置参数
        if (params != null)  
        {  
            for (int i = 0; i < params.length; i++)  
            {  
                con.data(params[i], values[i]);  
            }  
        }
        //解析html
        Document doc = null;
//        try{
//	         switch (requestType)  
//	         {  
//	         case SOURL.GET:  
//	             doc = con.timeout(100000).get();  
//	             break;  
//	         case SOURL.POST:  
//	             doc = con.timeout(100000).post();  
//	             break;  
//	         }  
//        }catch(IOException e){
//       	 e.printStackTrace();
//        }
        //过滤
        try {
			doc = con.timeout(100000).get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
        default:  break;
        }
      
        for (Element link : results)  
        {  
            //
        	
    		String linkHref = null;
          	if((linkHref = link.attr("src")).equals("")){
          		linkHref = link.attr("data-original");
          	}
            String linkText = link.text();  

            linkData = new LinkContent();  
            linkData.setLinkHref(linkHref);  
            linkData.setLinkText(linkText);  

            linkDatas.add(linkData);  
        }  
        
		return linkDatas;
	}
	 private static  void validatesourl(SOURL sourl)  
	    {  
	        String url = sourl.getUrl();  
	        if (url==null||url.equals(""))  
	        {  
	            throw new UrlRuleException("url不能为空");  
	        }  
	        if (!url.startsWith("http://")&&!url.startsWith("https://"))  
	        {  
	            throw new UrlRuleException("url格式不正确");  
	        }  
	  
	        if (sourl.getParams() != null && sourl.getValues() != null)  
	        {  
	            if (sourl.getParams().length != sourl.getValues().length)  
	            {  
	                throw new UrlRuleException("参数和值数量不匹配");  
	            }  
	        }  
	  
	    }  
}
