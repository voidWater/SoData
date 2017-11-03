package org.soData.service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.JTextArea;

import org.jsoup.Connection.Response;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.soData.pojo.Enity;



public class DownloadPic_jsoup{
	public static Map<String,String> map= new HashMap<String,String>();

	public static void round(JTextArea area){
		System.out.println("round:"+map.size());
		area.append("round:"+map.size()+"\n");
		List<String> list = new ArrayList<String>();
		Set s = map.keySet();
		Iterator i = s.iterator();
		while(i.hasNext()){
			String file = (String) i.next();
			String path = map.get(file);
			System.out.println(file+":"+path);
			try {
				list.add(file);
				download(path,file);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				list.remove(list.size()-1);
			}
			
		}
		for(String i1:list){
			map.remove(i1);
		}
		if(map.size()!=0){
			round(area);
		}else{
			return;
		}
	}
	public static  void download(String args,String file) throws IOException {
		// TODO Auto-generated method stub
		Response resultImageResponse;
		
		// output here
		FileOutputStream out;
		try {
//			resultImageResponse = Jsoup.connect(args)
//					.userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.64 Safari/537.31")
//					//.userAgent("Opera/9.80 (Android 2.3.4; Linux; Opera Mobi/build-1107180945; U; en-GB) Presto/2.8.149 Version/11.10")
//					.header("Accept", "text/html")
//		.header("Accept-Charset", "utf-8")
//		.header("Accept-Encoding", "gzip")
//		.header("Accept-Language", "en-US,en")
//					.ignoreContentType(true)
//					.execute();
			Connection conn = Jsoup.connect(args);
			
			conn.header("Accept", "text/html");
			conn.header("Accept-Charset", "utf-8");
			conn.header("Accept-Encoding", "gzip");
			conn.header("Accept-Language", "en-US,en");
			conn.userAgent("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.22 (KHTML, like Gecko) Chrome/25.0.1364.160 Safari/537.22");
			conn.ignoreContentType(true);
			resultImageResponse = conn.execute();
			out = (new FileOutputStream(new java.io.File(file)));
			out.write(resultImageResponse.bodyAsBytes());           
			// resultImageResponse.body() is where the image's contents are.
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}
	

	public static void downloadByLists(List<Enity> list, String path, JTextArea area, int i) {
		// TODO Auto-generated method stub
		
		if(i>1){
			i = 1+i;
		}else{
			i = 1;
		}
		String result;
		for(Enity l : list){
			//System.out.println(i+":"+l.getHref());
			area.append(i+":"+l.getHref()+"\n");
			try {
				download(l.getHref(),path+"\\"+i+".jpg");
			} catch(UnknownHostException e){
				map.put(path+"\\"+i+".jpg", l.getHref());
				area.append("put:"+i+"\n");
				//return;
			}catch (IOException e) {
				// TODO Auto-generated catch block
				map.put(path+"\\"+i+".jpg", l.getHref());
				area.append("put:"+i+"\n");
				//e.printStackTrace();
			}
			i++;
		}
		
		if(!map.isEmpty()){
			area.append("round:"+map.size()+"\n");
			round(area);
		}
	}

}
