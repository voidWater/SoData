package org.soData.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.soData.pojo.Enity;
import org.soData.pojo.LinkContent;
import org.soData.pojo.Rule;
import org.soData.pojo.SOURL;
import org.soData.utils.TextUtil;

public class Comm_CrawlService extends BaseCrawl {
//	Document doc = Jsoup.connect("http://www.oschina.net/")   
//	 .data("query", "Java")   // 设置参数 
//	 .userAgent("I �� m jsoup") // 设置 User-Agent   
//	 .cookie("auth", "token") // 设置cookie   
//	 .timeout(3000)           // 相应时间 
//	 .post().get();           //使用get或post  
	public List<Enity> crawl(Rule rule){
		validatesourl(rule);
		List<Enity> Enitys = new ArrayList<Enity>();
		String url = rule.getUrl();  
		String[] params = rule.getParams();  
		String[] values = rule.getValues();  
		String selectModel = rule.getSelectModel();  
		int type = rule.getType();  
		int requestType = rule.getRequestMoethod();
		
		//开始设置连接参数
		Connection conn = Jsoup.connect(url);
		Document doc = null;
		//请求参数
		if (params != null)  
        {  
            for (int i = 0; i < params.length; i++)  
            {  
                conn.data(params[i], values[i]);  
            }  
        }
		//请求方式
        try{
	         switch (requestType)  
	         {  
	         case Rule.GET:  
	             doc = conn.timeout(rule.getTime_out()).get();  
	             break;  
	         case Rule.POST:  
	             doc = conn.timeout(rule.getTime_out()).post();  
	             break;  
	         }  
        }catch(IOException e){
       	 e.printStackTrace();
        }
        //筛选数据
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
        default:  
            //selectModel为空则选取body
            if (TextUtil.isEmpty(selectModel))  
            {  
                results = doc.getElementsByTag("body");  
            }  
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

                Enitys.add(enity);  
            }  
        }
		return Enitys;
	} 
}
