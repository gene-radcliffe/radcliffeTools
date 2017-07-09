package com.radcliffe.Forms;
import java.awt.Graphics;
import java.io.*;
import javax.imageio.ImageIO;

import javax.swing.JComponent;
import java.awt.Graphics2D;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
public class Banner extends JComponent {
private String title = "";
private String title2 = "";
private Color bkColor;
private Color foreGroundColor;
private Color txtBkColor;
private Color txtForeGroundColor;
private int x, y;
private int x2, y2;
private Font font = new Font("Arial", Font.BOLD, 32);


	public Banner(){
		this.txtForeGroundColor= new Color(255,255,255);
		this.txtBkColor= new Color(50,50,50);
	}
	public void setString(String str){
		title = str;
	}
	public Banner(String fontName, int fontStyle, int size){
			this();
			font= new Font(fontName, fontStyle, size);	
	}
	public void setFont(String fontName, int fontStyle, int size){
		font = new Font(fontName, fontStyle, size);
	}
	public void setTextForeGroundColor(Color color){
		
	}
	public void setTextBackGroundColor(Color color){
		
	}
	public void setBkColor(Color color){
		this.bkColor=color;
		
	}
	public void setForeGroundColor(Color color){
		this.foreGroundColor=color;
	}
	
	public void setSampleImage(File file){
		
	}
	public void setSampleImage(String file){
		
	}
	
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		//create a blank canvas
	
		BufferedImage img = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_RGB);
		Graphics2D imgcanvas = img.createGraphics();
		 imgcanvas.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		 imgcanvas.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);		 
		FontRenderContext frc = imgcanvas.getFontRenderContext();
		
		
		//fill the canvas with a orange filled rectangle
		imgcanvas.setColor(new Color(255,200,60));
		imgcanvas.fillRect(1, 1,this.getWidth(), this.getHeight());
		
		//draw a string on the canvas
		imgcanvas.setFont(font);
		TextLayout tl = new TextLayout(title,font,frc);
		
	//	TextLayout tl2 = new TextLayout(title2,font,frc);
		//position our title center
		x = (int) ((this.getWidth()/2) - (tl.getBounds().getWidth()/2));
		y= (int) ((this.getHeight()/2) - (tl.getBounds().getHeight()/2));
		/*
		x2 = (int) ((this.getWidth()/2) - (tl2.getBounds().getWidth()/2));
		y2= (int) ((this.getHeight()/2) - (tl.getBounds().getHeight()/2));
		*/
		imgcanvas.setColor(this.txtBkColor);
	
		tl.draw(imgcanvas, x+2, y+2);
	//	tl2.draw(imgcanvas, x2+2, y2+37);
		imgcanvas.setColor(this.txtForeGroundColor);
		tl.draw(imgcanvas, x, y);
	//	tl2.draw(imgcanvas, x2, y2+35);
		
		
		// create the graphics output
		Graphics2D outPut = (Graphics2D) g;
		//draw the rendered image on the control
		
		outPut.drawImage(img,1,1,this.getWidth(),this.getHeight(),null);
		outPut.dispose();
		

	}
	



}
