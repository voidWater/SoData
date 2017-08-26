package org.soData.view;

import java.awt.Container;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class DownloadPane extends JPanel implements ActionListener{
	private TextField urlField = new TextField(30);
	private TextField pathField = new TextField(30);
	private JButton download = new JButton("download");
	private JButton path = new JButton("path");;
	private TextArea area = new TextArea();
	private JScrollPane jsp = new JScrollPane(area);
	
	String url=null;
	String file = null;
	public DownloadPane(){
		download.addActionListener(this);
		path.addActionListener(this);
		add(urlField);add(download);
		add(pathField);add(path);
		add(jsp);
		setSize(500,400);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==download){
			download(urlField.getText(),pathField.getText());
		}else if(e.getSource()==path){
			JFileChooser fd = new JFileChooser();  
        	fd.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        	fd.showOpenDialog(null);  
        	File f = fd.getSelectedFile();  
        	if(f != null){
        		pathField.setText(f.getPath());
        	}
		}
	}
	private void download(String url,String file){
		System.out.println(url+":"+file);
	}

}
