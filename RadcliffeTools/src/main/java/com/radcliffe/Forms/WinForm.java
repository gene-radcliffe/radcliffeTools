package com.radcliffe.Forms;
import javax.swing.*;
import java.awt.*;
public class WinForm extends JFrame{
	private String Title;
	
	public WinForm(){
		
	}
	public WinForm(String Title, Dimension dim){
		super();
		this.setSize(dim);
		this.setTitle(Title);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	
	public void showWindow(boolean show){
		this.setVisible(show);
	}
}
