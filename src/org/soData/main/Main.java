package org.soData.main;

import java.util.List;

import org.apache.commons.codec.language.bm.Rule;
import org.soData.model.CrawlService;
import org.soData.pojo.LinkContent;
import org.soData.pojo.SOURL;

public class Main {
	public static void main(String[] args){
//		URL rule = new URL(  
//                "http://www1.sxcredit.gov.cn/public/infocomquery.do?method=publicIndexQuery",  
//        new String[] { "query.enterprisename","query.registationnumber" }, new String[] { "ÐËÍø","" },  
//                "cont_right", URL.CLASS, URL.POST);  
		SOURL rule = new SOURL("http://news.baidu.com/ns",  
	            new String[] { "word" }, new String[] { "Ö§¸¶±¦" },  
	            null, -1, SOURL.GET); 
        List<LinkContent> extracts = CrawlService.CrawlPage(rule);  
        printf(extracts);  
	}
	public static void printf(List<LinkContent> datas)  
    {  
        for (LinkContent data : datas)  
        {  
            System.out.println(data.getLinkText());  
            System.out.println(data.getLinkHref());  
            System.out.println("***********************************");  
        }  
  
    }  
}
