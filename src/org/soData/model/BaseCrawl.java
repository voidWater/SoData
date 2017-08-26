package org.soData.model;

import java.util.List;

import org.soData.Exception.UrlRuleException;
import org.soData.pojo.Enity;
import org.soData.pojo.Rule;

public class BaseCrawl {
	protected static  void validatesourl(Rule rule)  
    {  
        String url = rule.getUrl();  
        if (url==null||url.equals(""))  
        {  
            throw new UrlRuleException("url不能为空");  
        }  
        if (!url.startsWith("http://")&&!url.startsWith("https://"))  
        {  
            throw new UrlRuleException("格式错误");  
        }  
  
        if (rule.getParams() != null && rule.getValues() != null)  
        {  
            if (rule.getParams().length != rule.getValues().length)  
            {  
                throw new UrlRuleException("参数数量不匹配");  
            }  
        }  
    }
	protected static void printEnity(List<Enity> list){
		for(Enity e : list){
			System.out.println();
		}
	}
}
