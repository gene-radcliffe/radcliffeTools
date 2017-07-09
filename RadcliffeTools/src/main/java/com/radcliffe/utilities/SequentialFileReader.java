package com.radcliffe.utilities;

import java.io.*;

public class SequentialFileReader {
	//private Logger log;
	private FileInputStream _fs;
	private boolean _msb_lsb=false;
	private File Directory;
	private boolean endOfFile;
	
	

	public boolean isEndOfFile() {
		return endOfFile;
	}
	public void setEndOfFile(boolean endOfFile) {
		this.endOfFile = endOfFile;
	}
	public SequentialFileReader(){
		
	}
	/*
	 * @param FileInputStream fs - input the file to load
	 */
	public SequentialFileReader(FileInputStream fileInputStream){
		
		_fs = fileInputStream;
	}
	
	public SequentialFileReader(File file){
		//Directory =new File(System.getProperty("user.home")+"/Desktop");
		
		try {
			_fs = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/*
	 * @param FileInputStream fs - input the file to load
	 */
	public void setFileInput(FileInputStream fs){
		_fs = fs;
	}
	public void setFileInput(File file){
		try {
			_fs = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void setDirectory(String path){
		Directory = new File(path);
	}
	public void is_MSB(boolean msb){
		_msb_lsb = msb;
	}
	
	public byte[] getBytes(int len){
		byte[] data = new byte[len];
		int bytesRead=0;
		while(bytesRead != len){
			try{
		final int readData = _fs.read(data, bytesRead, len-bytesRead) ;
		if(readData == -1){
			//log.logError("public byte[] getBytes(int len): End of File");
		}
		bytesRead += readData;
			
			}catch(IOException ioe){
				//log.logError("public byte[] getBytes(int len): Error while readind file/data");
			}
		
		
		}
		return data;
	}
	public byte getByte(){
		
		int read =0;
		
		try{
			read = _fs.read();
		}catch(IOException ioe){
			System.out.println(ioe.getMessage());
			
		}
		if (read<0){
			endOfFile=true;
			System.out.println("File Ended");
			//log.logError("End of File");
		}
		return (byte) read;
	}
	public byte getInt8(){
		return getByte();
	}
	public short getUint8(){
		return (short) ( getByte() & 0xFF);
	}
	public int getUint16(){
		if(_msb_lsb){
			
			return (getByte() <<8 & 0xFF00) | (getByte() & 0xFF);
		}else{
			return (getByte() & 0xFF) | 
					   (getByte() <<8 & 0xFF00);
		
		}
	}
	public short getInt16(){
		 if(_msb_lsb){
			 //File is lsb.. convert to MSB
			 return (short)((getByte() <<8 & 0xFF00) | 
					        (getByte() & 0xFF)) ;	
			
		 }else{

			 return (short)((getByte() & 0xFF)       |  
					        (getByte() <<8 & 0xFF00)) ;
		 }
		
	}
	public long readUint32(){
		if(_msb_lsb){
			return (long)( (getByte() <<24 & 0xFF000000L) | 
					       (getByte() << 16 & 0xFF0000L)  | 
					       (getByte() << 8 & 0xFF00L)     | 
					       (getByte() & 0xFFL) );
		}else{
			return (long)( (getByte() & 0xFFL)            | 
					       (getByte() << 8 & 0xFF00L)     | 
					       (getByte() << 16 & 0xFF0000L)  | 
					       (getByte() << 24 & 0xFF000000L) );
		}
	}
	
	public int readInt32(){
		if(_msb_lsb){
			return ( (getByte() << 24 & 0xFF000000) | 
					 (getByte() << 16 & 0xFF0000)   | 
					 (getByte() << 8 & 0xFF00)      | 
					 (getByte() & 0xFF) );
		}else{
			return ( (getByte() & 0xFF)             | 
					 (getByte() << 8 & 0xFF00)      | 
					 (getByte() <<16 & 0xFF0000)    |
					 (getByte() << 24 & 0xFF000000) );
		}
	}
	
	public long readInt64(){
		if(_msb_lsb){
			return ( (getByte() << 56 & 0xFF00000000000000L)      |
					 (getByte() << 48 & 0xFF00000000000L)         |
					 (getByte() << 40 & 0xFF0000000000L)          |
					 (getByte() << 32 & 0xFF00000000L)            |
					 (getByte() << 24 & 0xFF000000L)              | 
					 (getByte() << 16 & 0xFF0000L)                | 
					 (getByte() << 8  & 0xFF00L)                  |
					 (getByte()       & 0xFFL) );
		}else{
			return ( (getByte()       & 0xFFL)                    | 
					 (getByte() << 8  & 0xFF00L)                  | 
					 (getByte() << 16 & 0xFF0000L)                | 
					 (getByte() << 24 & 0xFF000000L)              |
					 (getByte() << 32 & 0xFF00000000L)            |
					 (getByte() << 40 & 0xFF0000000000L)          |
					 (getByte() << 48 & 0xFF000000000000L)        |
					 (getByte() << 56 & 0xFF00000000000000L)     
					 );
		}
	}
	
	public void closeFile(){
		try {
			this._fs.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}