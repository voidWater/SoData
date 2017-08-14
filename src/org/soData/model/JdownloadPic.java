package org.soData.model;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.soData.pojo.LinkContent;


public class JdownloadPic implements Runnable{
	public static Map<String,String> map= new HashMap<String,String>();
	/**
	 * @param args
	 */
	public static void main(String[] args){
		String s ="http://i.imagseur.com/uploads/2017-08/13/37db2afb2374f49613bfee11d653dfbf.jpg";
		List<String> l = new ArrayList<String>();
		l.add("http://i.imagseur.com/uploads/2017-08/13/2ab95f5599ada9c184b8f42d0091a1de.jpg");
		l.add("http://i.imagseur.com/uploads/2017-08/13/f4696a12ad43978b0a6482760508ed9d.jpg");
		l.add("http://i.imagseur.com/uploads/2017-08/13/e73cb97afa33343227377c917461c390.jpg");
		l.add("http://i.imagseur.com/uploads/2017-08/13/6dee4e98f998ffc37f912f9e385a60ce.jpg");
		l.add("http://i.imagseur.com/uploads/2017-08/13/1564f7a26d40ea05782de863cc45816b.jpg");
		l.add("http://i.imagseur.com/uploads/2017-08/13/fe0a2acb3e0acb1b86331ff12de9db04.jpg");
		l.add("http://i.imagseur.com/uploads/2017-08/13/00bf1574a535483ddb1d108d59836313.jpg");
		for(int i=0;i<l.size();i++){
			System.out.println(i);
			download(l.get(i),"E:\\csexe\\"+i+".jpg");
		}
	}
	public static void download(List<LinkContent> list){
		int i = 1;
		for(LinkContent l : list){
			System.out.println(l.getLinkHref());
			download(l.getLinkHref(),"E:\\csexe\\"+i+".jpg");
			i++;
		}
	} 
	public static  void download(String args,String file) {
		// TODO Auto-generated method stub
		Response resultImageResponse;
		
		// output here
		FileOutputStream out;
		try {
			resultImageResponse = Jsoup.connect(args)
					.userAgent("Mozilla/5.0 (Windows NT 5.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.75 Safari/537.36")
					.ignoreContentType(true)
					.execute();
			out = (new FileOutputStream(new java.io.File(file)));
			out.write(resultImageResponse.bodyAsBytes());           
			// resultImageResponse.body() is where the image's contents are.
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			if(e.getMessage().equals("Read timed out")){
				map.put("file", "args");
			}
			e.printStackTrace();
		}
		if(!map.isEmpty()){
			round();
		}
		
	}
	public static void round(){
		Set s = map.keySet();
		Iterator i = s.iterator();
		while(i.hasNext()){
			String file = (String) i.next();
			String path = map.get(file);
			download(path,file);
		}
		if(map.size()!=0){
			round();
		}
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}
