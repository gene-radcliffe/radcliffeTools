package com.radcliffe.utilities;

import java.io.*;
import java.awt.font.TextLayout;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.awt.font.FontRenderContext;

public class ImageDater {
	private RandomAccessFileReader randomFileReader;
	private SequentialFileReader _sfr;
	private File _file;
	private File _fileDir;
	private File _directory;
	private int _progess;
	private boolean _readDateOnFile;
	private boolean _isMSB;
	private boolean _isJPEG;
	private boolean _isExif;
	public ImageDater(){
		
		
	}

	
	public void setDirectory(File directory){
		_directory = directory;
		
	}
	public void setFile(File file){
		_file = file;
	}
	
	public String readDateOnFile(File file) throws IOException{
		String rtn = "";
		int loc=0;
		System.out.println(file.toString());
		JpegCheck.checkJpegMarker(file);
		
		if(JpegCheck.isJpeg()==true){
			_isJPEG =true;}
	
		
		JpegCheck.checkMSB(file);
		_isMSB= JpegCheck.isMostSignificantByte();
		JpegCheck.checkExif(file);
		_isExif=JpegCheck.isExif();
		
		_sfr= new SequentialFileReader(file);
		_sfr.is_MSB(_isMSB);
		
		
		do{
			if(_isJPEG==false)
				break;
			
			if(_isExif==false){
				System.out.println("No Exif Tag");
				rtn = "";
				break;
			}
			
			if(_sfr.getInt16() == 0x132){
				
				_sfr.getInt16();
				_sfr.readInt32();
				loc = _sfr.readInt32();
				
				_sfr.closeFile();

				randomFileReader = new RandomAccessFileReader();
				randomFileReader.setRandomAccessFile(file, Mode.read);	
				//Add 12 to the offset location. The Tiff header starts at offset 12
				
				rtn = randomFileReader.readAscii(loc+12, 19);
			
				break;
			}
			
		}while(!_sfr.isEndOfFile());
		_isMSB=false;
		_isJPEG=false;
		
	
		return rtn;
	}
	/*
	 * @param file - the file to load
	 * @param foreColor - the foreground color of the text
	 * @param bkColor - the background color of the text
	 * @return BufferedImage - returns an image with a date stamp.
	 */

	public BufferedImage dateStampImage(BufferedImage oImage, String date, Color foreColor, Color bkColor, int fontSize){
		
		BufferedImage nImage = null;
		FontRenderContext frc = null;
		TextLayout textLayout = null;
		Font font = new Font("Arial", Font.PLAIN, fontSize);
		Graphics2D g2= null;
		
		
		nImage = new BufferedImage(oImage.getWidth(), oImage.getHeight(), oImage.getType());
		
		g2=nImage.createGraphics();
		
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);		 

		frc = g2.getFontRenderContext();
		
		g2.drawImage(oImage, 1, 1, oImage.getWidth(), oImage.getHeight(), null);
		
		
		g2.setColor(bkColor);
		textLayout = new TextLayout(date, font,frc);
		textLayout.draw(g2, 50, oImage.getHeight()-50);
		g2.setColor(foreColor);
		textLayout.draw(g2, 50+2, (oImage.getHeight()-50)+2);
		g2.dispose();
		return nImage;
		
	}

	/*
	 * @param file - the file to load
	 * @return BufferedImage - returns an image with a date stamp.
	 */
	
	public BufferedImage dateStampImage(BufferedImage oImage, String date){
		
		BufferedImage nImage = null;
		FontRenderContext frc = null;
		TextLayout textLayout = null;
		Font font = new Font("Arial", Font.PLAIN, 24);
		Graphics2D g2= null;
		
		
		nImage = new BufferedImage(oImage.getWidth(), oImage.getHeight(), oImage.getType());
		
		g2=nImage.createGraphics();
		
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);		 

		frc = g2.getFontRenderContext();
		
		g2.drawImage(oImage, 1, 1, oImage.getWidth(), oImage.getHeight(), null);
		
		
		g2.setColor(Color.darkGray);
		textLayout = new TextLayout(date, font,frc);
		textLayout.draw(g2, 50, oImage.getHeight()-50);
		g2.setColor(Color.white);
		textLayout.draw(g2, 50+2, (oImage.getHeight()-50)+2);
		g2.dispose();
		return nImage;
		
	}
	/*
	 * @param file - the file to load
	 * @param fncolor - the foreground color of the text
	 * @param bkcolor - the background color of the text
	 */

	public void dateStampImageFromFile(File file, String date, int fontSize, Color fncolor, Color bkcolor){
		BufferedImage oImage = null;
		BufferedImage nImage = null;
		FontRenderContext frc = null;
		TextLayout textLayout = null;
		Font font = new Font("Arial", Font.PLAIN, fontSize);
		Graphics2D g2= null;
		try{
			oImage = ImageIO.read(file);
		}catch(IOException ioe){
			ioe.printStackTrace();
		}
		
		
		nImage = new BufferedImage(oImage.getWidth(), oImage.getHeight(), oImage.getType());
		
		g2=nImage.createGraphics();
		
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);		 

		frc = g2.getFontRenderContext();
		
		g2.drawImage(oImage, 1, 1, oImage.getWidth(), oImage.getHeight(), null);
		
		
		g2.setColor(bkcolor);
		textLayout = new TextLayout(date, font,frc);
		textLayout.draw(g2, 50, oImage.getHeight()-50);
		g2.setColor(fncolor);
		textLayout.draw(g2, 50+2, (oImage.getHeight()-50)+2);
		g2.dispose();
		try{
			ImageIO.write(nImage, "jpg", _fileDir);
		}catch(IOException ioe){
			ioe.getMessage();
		}
		
	}
	public void dateStampImageFromFile(File file, String date){
		BufferedImage oImage = null;
		BufferedImage nImage = null;
		FontRenderContext frc = null;
		TextLayout textLayout = null;
		Font font = new Font("Arial", Font.PLAIN, 24);
		Graphics2D g2= null;
		try{
			oImage = ImageIO.read(file);
		}catch(IOException ioe){
			ioe.printStackTrace();
		}
		
		
		nImage = new BufferedImage(oImage.getWidth(), oImage.getHeight(), oImage.getType());
		
		g2=nImage.createGraphics();
		
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);		 

		frc = g2.getFontRenderContext();
		
		g2.drawImage(oImage, 1, 1, oImage.getWidth(), oImage.getHeight(), null);
		
		
		g2.setColor(Color.darkGray);
		textLayout = new TextLayout(date, font,frc);
		textLayout.draw(g2, 50, oImage.getHeight()-50);
		g2.setColor(Color.white);
		textLayout.draw(g2, 50+2, (oImage.getHeight()-50)+2);
		g2.dispose();
		try{
			ImageIO.write(nImage, "jpg", _fileDir);
		}catch(IOException ioe){
			ioe.getMessage();
		}
		
	}
		
}