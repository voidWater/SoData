package org.soData.view;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.soData.pojo.Enity;
import org.soData.pojo.Rule;
import org.soData.service.CrawlService_pic;
import org.soData.service.CrawlService_title;
import org.soData.service.CrawlService_title;
import org.soData.service.DownloadPic_jsoup;

public class DownloadPicture extends JFrame implements ActionListener,Runnable{
	boolean flag = false;
	String type = "mmtt";
	Logger log = LoggerFactory.getLogger(DownloadPicture.class);
	//SOURL url = new SOURL();
	Rule rule = new Rule();
	List<Enity> links;
	String path;
	Thread thread = new Thread(this);
	CrawlService_pic service = new CrawlService_pic();
	CrawlService_title service_tit = new CrawlService_title();
	JButton button,update,traverse;
	JTextField text;
	
	JButton fileButton;
	JTextField fileText;
	
	JButton searchButton;
	JTextField searchText;
	JLabel html_tit;
	
	JTextArea area = new JTextArea();
	JPanel p = new JPanel();
	FlowLayout fl = new FlowLayout(FlowLayout.LEFT);
	JPanel subp1 = new JPanel(fl);
	JPanel subp2 = new JPanel(fl);
	JPanel subp3 = new JPanel(fl);
	GridLayout gl=new GridLayout(2,1);
	JPanel sub = new JPanel(gl);
	JScrollPane jsp;
	
	Thread t;
	public DownloadPicture(){
		super("图片批量下载");
		Container c = this.getContentPane();
		
		button = new JButton("开始");
		update = new JButton("update");
		text  = new JTextField("http://www.mmtt33.link/",30);
		
		fileButton = new JButton("path");
		fileText  = new JTextField("E:\\csexe\\",30);
		
		searchButton = new JButton("search");
		searchText = new JTextField(30);
		traverse = new JButton("traverse");
		html_tit = new JLabel("mmtt33");
		
		subp1.add(text);subp1.add(button);subp1.add(update);
		subp2.add(fileText);subp2.add(fileButton);
		sub.add(subp1);sub.add(subp2);
		subp3.add(searchText);subp3.add(searchButton);subp3.add(traverse);subp3.add(html_tit);
		
		p.setLayout(new GridLayout(2,1));
		p.add(sub,"North");
		//p.add(subp2,"Center");
		p.add(subp3,"Center");
		
		button.addActionListener(this);
		update.addActionListener(this);
		traverse.addActionListener(this);
		fileButton.addActionListener(this);
		searchButton.addActionListener(this);
		jsp = new JScrollPane(area);
		c.add(p,"North");
		c.add(jsp,"Center");
		setSize(500,400);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==button){
			if(!flag){
				flag=true;
				rule.setUrl(text.getText());
				rule.setSelectModel("img");
				rule.setType(Rule.SELECTION);
				rule.setRequestMoethod(Rule.GET);
				links = service.crawl(rule);
				path = fileText.getText();
				area.append(links.size()+"\n");
				if(links!=null&&path!=null){
					//download(links,fileText.getText());
					t = new Thread(this);
					button.setText("停止");
					t.start();
				}else{
					System.out.println("url and path is null");
				}
			}else{
				t.stop();
				flag=false;
				button.setText("开始");
			}
		}else if(e.getSource() == update){
			area.setText("updata...");
			rule.setUrl(text.getText());
			rule.setSelectModel("li");
			rule.setType(Rule.SELECTION);
			rule.setRequestMoethod(Rule.GET);
			
			service_tit.writeTitle(rule,fileText.getText());
			area.append("done"+"\n");
		}else if(e.getSource() == fileButton){
			JFileChooser fd;
			if(fileText.getText()!=null){
				 fd = new JFileChooser(fileText.getText());
			}else{
				 fd = new JFileChooser("E:\\csexe\\");
			}
			  
        	fd.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        	fd.showOpenDialog(null);  
        	File f = fd.getSelectedFile();  
        	if(f != null){
        		fileText.setText(f.getPath());
        	}
		}else if(e.getSource()==traverse){
			if(type=="mmtt"){
				type="0000XX";
				text.setText("https://3344su.com/tupianqu/katong/");
				html_tit.setText(type);
			}else if(type=="0000XX"){
				type="siwa";
				text.setText("http://www.256bp.com/html/article/");
				html_tit.setText(type);
			}else if(type == "siwa"){
				type ="mmtt";
				text.setText("http://www.mmtt33.link/");
				html_tit.setText(type);
			}
		}else if(e.getSource()==searchButton){
			area.setText("");
			search(searchText.getText());
		}
	}
	private void search(String s) {
		// TODO Auto-generated method stub
		log.debug("search");
		try {
			File f = new File("E:/csexe/source/"+type+".txt");
			InputStreamReader isr = new InputStreamReader(new FileInputStream(f),"utf-8");
        	BufferedReader bw = new BufferedReader(isr);
			String line;
			while((line=bw.readLine())!=null){
				if(match(s,line)){
					area.append(line+"\n");
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.debug(e.getMessage());
			e.printStackTrace();
		}
	}
	public boolean match(String s,String line){
		if(line.indexOf(s)>0){
			return true;
		}else{
			return false;
		}
		
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		area.append("begin\n");
		File  file = new File(path);
		if(!file.exists()){
			file.mkdirs();
		}
		int i= file.list().length;
		try {
			DownloadPic_jsoup.downloadByLists(links,path,area,i);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//thread.stop();
		area.append("done"+"\n");
	}
}
