
package com.radcliffe.utilities;
import java.io.*;
import java.awt.Graphics2D;
import java.awt.image.*;
import javax.imageio.*;
import com.radcliffe.utilities.*;
public class ImageResizer {
private File Directory;

private File file;

private static int _newWidth;
private static int _newHeight;
private static int _progress;

	public void setNewHeight(int Height) {
	this._newHeight = Height;
	}
	public void setNewWidth(int Width){
		this._newWidth=Width;
	}
	
	public ImageResizer(){
		
		String str = null;
		
	}
	public static void main(String[] args){
		new ImageResizer();
	}
	public void setWorkingDirectory(File directory){
		
	}
	public int getProgress(){
		return _progress;
	}
	
	
	public static void filesToResize(File directory){
		File[] files = directory.listFiles();
		BufferedImage nImage=null;
		BufferedImage oImage = null;
		File filename=null;
		String strFile=null;
		float count =0;
		float len =files.length;
		for(File file: files){
			try{
				oImage = ImageIO.read(file);
				filename =file;
			}catch(IOException ioe){
				ioe.getMessage();
			}
			nImage  = new BufferedImage(_newWidth, _newHeight, oImage.getType());
			Graphics2D g2 = nImage.createGraphics();
			g2.drawImage(oImage, 1, 1,_newWidth,_newHeight, null);
			try{
				
			strFile = filename.getName();
			strFile = strFile.substring(0, strFile.indexOf('.'));
			filename = new File(directory.getCanonicalPath()+ "\\" + strFile + " dated" + ".jpg");
			ImageIO.write(nImage, "jpg", filename);
			
			}catch(IOException ioe){
				ioe.getMessage();
			}
			count++;
			float tot = ((count / len) * 100.0f);
			_progress = (int) tot;
		}
	}


	public static BufferedImage fileToResize(File file, int newWidth, int newHeight){

		BufferedImage nImage =null;
		BufferedImage oImage=null;
		
		try{
			oImage = ImageIO.read(file);
		}catch(IOException ioe){
			ioe.getMessage();
		}
		nImage = new BufferedImage(newWidth, newHeight, oImage.getType());
		Graphics2D g2 = nImage.createGraphics();
		g2.drawImage(oImage, 1, 1,newWidth,newHeight, null);
		g2.dispose();
		return nImage;
	}
	/*
	 * 
	 */
	public static BufferedImage fileToResize(String file, int newWidth, int newHeight) throws IOException
	{
		BufferedImage nImage=null;
		BufferedImage oImage=null;
		
		try{
			oImage = ImageIO.read(new File(file));
		}catch(IOException ioe){
			ioe.getMessage();
		}
		nImage = new BufferedImage(newWidth, newHeight, oImage.getType());
		Graphics2D g2 = nImage.createGraphics();
		g2.drawImage(oImage, 1, 1,newWidth,newHeight, null);
		g2.dispose();
		return nImage;
		
	}

	

}