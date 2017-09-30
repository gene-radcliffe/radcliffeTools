/**
 *	Banner Class
 *
 * 	@author Gene Radcliffe
 * 
 * 
 * <p>
 * 
 */
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
private Color txtBkColor= new Color(50,50,50);
private Color txtForeGroundColor= new Color(255,255,255);
private boolean cShadow=true;
private int x, y;
private int x2, y2;
private String fontName = "Arial";
private int fontStyle = Font.BOLD;
private int fontSize = 32;
private Font font = new Font(fontName, fontStyle, fontSize);


	private Banner(){
		this.txtForeGroundColor= new Color(255,255,255);
		this.txtBkColor= new Color(50,50,50);
	}
	/**
	 * Constructor. 
	 * 
	 * @param fontName - Name of the font
	 * @param fontStyle - Style of the font
	 * @param size - Font size
	 * @param textColor - Color of the Text
	 * @param shadow - Shadow Color
	 */
	public Banner(String fontName, int fontStyle, int size, Color textColor, Color shadow){
		this.txtForeGroundColor= textColor;
		this.txtBkColor= shadow;
	}
	/**
	 * 
	 * @param fontName - Name of the Font
	 * @param fontStyle - Style of the font
	 * @param size  - Font size
	 */
	public Banner(String fontName, int fontStyle, int size){
		this();
		font= new Font(fontName, fontStyle, size);	
}
	
	/**
	 *  Sets the String
	 * @param str. Enter the string to style
	 * @return void.
	 */
	public void setString(String str){
		title = str;
	}
	/**
	 *  Sets the Font
	 * @param fontName. Font name of the text (Font Family)
	 * @param fontStyle. Font Sytle of the text
	 * @param size. Size of the text;
	 * @return void.
	 */
	public void setFont(String fontName, int fontStyle, int size){
		font = new Font(fontName, fontStyle, size);
	}
	
	/**
	 * Sets a new font type, and repaints
	 * @param name - Name of the font type
	 */
	public void setFont(String name){
		fontName = name;
		font = new Font(fontName, fontStyle,fontSize);
		
		repaint();
	}
	/**
	 * Sets a new font style, and repaints
	 * @param style - Style of font [int]
	 */
	public void setFontStyle(int style){
		fontStyle = style;
		font = new Font(fontName, fontStyle,fontSize);
		repaint();
	}
	/**
	 * Sets a new font size, and repaints
	 * 
	 * @param size - new size of font
	 */
	public void setFontSize(int size){
		fontSize = size;
		font = new Font(fontName, fontStyle, fontSize);
		repaint();
	}
	/**
	 *  Sets the Foreground color
	 * @deprecated. This was mean to set shadow cffolor. Use setShadowColor(color) instead.
	 * @param color. Color for the Foreground text
	 * @return void.
	 */
	public void setTextForeGroundColor(Color color){
		
		this.txtForeGroundColor=color;
		this.repaint();
	}
	/**
	 *  Sets the set Text Background color
	 * @param color. Color for the Background text
	 * @return void.
	 */
	public void setTextBackGroundColor(Color color){
		
	}
		
	/**
	 *  Sets the Shadow Color
	 * @param color. Color for the shadow
	 * @return void.
	 */
	public void setShadowColor(Color color){
		this.bkColor = color;
	}
	
	
	/**
	 *  Sets the Shadow Color
	 * @deprecated. This was mean to set shadow color. Use setShadowColor(color) instead.
	 * @param color. Color for the background text
	 * @return void.
	 */
	public void setBkColor(Color color){
		this.bkColor=color;
		
	}
	
	/** 
	 * Sets the Foreground Color
	 * @deprecated Use setTextForegroundColor(color)
	 * @param color. Color for the foreground text
	 * @return void.
	 */
	public void setForeGroundColor(Color color){
		this.foreGroundColor=color;
	}
	
	/**
	 * Set background Image
	 * @param file.  Location of the JPEG or Image file to use. Use File class.
	 * @return void.
	 */
	public void setSampleImage(File file){
		
	}
	/**
	 * Set background Image
	 * @param file. [String]. Location of the JPEG or Image file to use. 
	 * @return void.
	 */

	public void setSampleImage(String file){
		
	}
	
	/**
	 * Turn on Shadow rendering
	 * @param shadow. Boolean. true turns on shadow, and false turns off. This is set to true as default. 
	 * @return void.
	 */
	public void setShadow(boolean shadow){
		cShadow = shadow;
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
		// Draw the shadow
				if(cShadow==true){
					imgcanvas.setColor(this.txtBkColor);
					
					tl.draw(imgcanvas, x+2, y+2);
					//	tl2.draw(imgcanvas, x2+2, y2+37);
					
				}
					
				//draw the foreground
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
