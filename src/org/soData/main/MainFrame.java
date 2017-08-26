package org.soData.main;

import java.awt.Container;

import javax.swing.JFrame;

import org.soData.view.DownloadPane;

public class MainFrame extends JFrame{

	public MainFrame(){
		Container container = this.getContentPane();
		DownloadPane dp = new DownloadPane();
		container.add(dp);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}
