package com.radcliffe.utilities;
import java.io.*;
class JpegCheck {
	private static SequentialFileReader _sfr;
	
	private static boolean isMostSignificantByte;
	private static boolean isJpeg;
	private static boolean isExif;
	public static boolean isExif() {
		return isExif;
	}


	public static void setExif(boolean isExif) {
		JpegCheck.isExif = isExif;
	}


	public static boolean isJpeg() {
		return isJpeg;
	}


	public static void setJpeg(boolean isJpeg) {
		isJpeg = isJpeg;
	}


	static boolean isMostSignificantByte() {
		return isMostSignificantByte;
	}


	static void setMostSignificantByte(boolean isMostSignificantByte) {
		JpegCheck.isMostSignificantByte = isMostSignificantByte;
	}


	public JpegCheck() throws IOException{
		File file[] = new File[3];
		file[0] = new File("c://test/pics3/DSC_0687.jpg");//true
		file[1] = new File("c://test/pics3/20160512_055801.jpg");//false
		file[2] = new File("c://test/pics3/20160512_055318dated.jpg");//fals
		
		for(File f: file){
			System.out.println(f.toString());
			checkExif(f);
			checkMSB(f);
			checkJpegMarker(f);
		}
		
		/*
		File file = new File("c://test/pics3/DSC_0687.jpg");//true
		File file2 = new File("c://test/pics3/20160512_055801.jpg");//false
		File file3 = new File("c://test/pics3/20160512_055318dated.jpg");//false
		*/
		
	}
	
	static void checkExif(File file) throws IOException{

		RandomAccessFileReader _rfr= new RandomAccessFileReader(file, Mode.read);
		String exifTag = _rfr.readAscii(6,4);
		
		if(exifTag.equals("Exif")){
			
			isExif=true;
			System.out.println(file.toString() + " exif");
			
		}else{
			System.out.println(file.toString() + "no exif");
			isExif =false;
		}
	
	}
	static void checkMSB(File file) throws IOException{
		
		RandomAccessFileReader _rfr= new RandomAccessFileReader(file, Mode.read);
			
		
		_rfr.checkMostSignificantByte(12);
		 isMostSignificantByte = _rfr.isMostSignificantByte();
		
		_rfr.closeFile();
		_rfr=null;
	}
		
			/*
			 * old checkMSB(File file){
		short bytesread=0;
		do{
			short msb= _sfr.getInt16();
			
			
			if(msb==0x4949){
				isMostSignificantByte=false;
				System.out.println("msb is false");
				_sfr.closeFile();
				_sfr=null;
				break;
			}else if(msb==0x4D4D){
				isMostSignificantByte=true;
				System.out.println("msb is true");
				_sfr.closeFile();
				
				_sfr=null;
				break;
			}
			
			
			bytesread +=2;
		}while(bytesread <= 16);
		}
		*/
	
	
	
	static void checkJpegMarker(File file)throws IOException{
		
		
		
		RandomAccessFileReader _rfr= new RandomAccessFileReader(file, Mode.read);
		
		byte[] jpgmarker = _rfr.getBytes(0, 2);

		
		int marker =  ( (jpgmarker[0] << 8 & 0xFF00)  |  (jpgmarker[1] & 0xFF) );
		
		if(marker == 0xFFD8){
			isJpeg = true;
			
			_rfr.closeFile();
		}
		
		
	}

	public static void main(String[] args){
		System.out.println("Hello");
		try {
			new JpegCheck();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
