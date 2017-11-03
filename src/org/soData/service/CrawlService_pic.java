package org.soData.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.soData.pojo.Enity;
import org.soData.pojo.Rule;


public class CrawlService_pic extends BaseCrawl implements IModel{

	@Override
	public  List<Enity> crawl(Rule rule) {
		// TODO Auto-generated method stub
		validatesourl(rule);
		List<Enity> enitys = new ArrayList<Enity>();
		
		//获取链接参数
		String url = rule.getUrl();  
        String[] params = rule.getParams();  
        String[] values = rule.getValues();  
        String selectModel = rule.getSelectModel();  
        int type = rule.getType();  
        int requestType = rule.getRequestMoethod();
        //设置链接
        Connection con = Jsoup.connect(url);		
        con.header("Accept", "text/html");
        con.header("Accept-Charset", "utf-8");
        con.header("Accept-Encoding", "gzip");
        con.header("Accept-Language", "en-US,en");
        con.userAgent("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.22 (KHTML, like Gecko) Chrome/25.0.1364.160 Safari/537.22");

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
        try{
	         switch (requestType)  
	         {  
	         case Rule.GET:  
	             doc = con.timeout(100000).get();  
	             break;  
	         case Rule.POST:  
	             doc = con.timeout(100000).post();  
	             break;  
	         }  
        }catch(IOException e){
       	 e.printStackTrace();
        }
        Elements results = new Elements(); 
        switch (type)  
        {  
        case Rule.CLASS:  
            results = doc.getElementsByClass(selectModel);  
            break;  
        case Rule.ID:  
            Element result = doc.getElementById(selectModel);  
            results.add(result);  
            break;  
        case Rule.SELECTION:  
            results = doc.select(selectModel);  
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

            Enity linkData = new Enity();  
            linkData.setHref(linkHref);  
            linkData.setText(linkText);  

            enitys.add(linkData);  
        }  
        
		return enitys;
	}

}
