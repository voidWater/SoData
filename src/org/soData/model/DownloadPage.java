package org.soData.model;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class DownloadPage {
	 /* ʵ����һ��HttpClient�ͻ��� */
	public static String getContentFormUrl(String url){ 
	    HttpClient client = new DefaultHttpClient();  
	    HttpGet getHttp = new HttpGet(url); 
	    String content = null;
	    HttpResponse response;
	   
        
	    return null;
	}
}
