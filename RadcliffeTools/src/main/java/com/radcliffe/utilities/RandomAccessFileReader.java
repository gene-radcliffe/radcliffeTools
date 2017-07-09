package com.radcliffe.utilities;

import java.io.File;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.io.FileNotFoundException;

enum Mode{
	readAndWrite("rw"), read("r"), readAndWriteSync("rws"), rwd("rwd");
	String _mode;
	private Mode(String mode){
	 _mode = mode;	
	}
	public String toString(){
		return _mode;
	}
	
}
public class RandomAccessFileReader {
	
	private RandomAccessFile _ras;
	private boolean _msb_lsb=false;
	private int _currentLoc;
	private File _file;
	
	public RandomAccessFileReader(){
		
	}
	public RandomAccessFileReader(File file, Mode mode) throws FileNotFoundException
	{
		_file = file;
		_ras = new RandomAccessFile(_file, mode.toString());
	}
	public void setRandomAccessFile(RandomAccessFile file){
		_ras = file;
	}
	public void setRandomAccessFile(File file, Mode mode)throws FileNotFoundException{
		_ras = new RandomAccessFile(file, mode.toString());
	}
	private boolean validateIndex(int index) throws IOException{
		boolean rtn=false;
		
		
			
			rtn =(index<=_ras.length())? true:false;
			
		
			
			
		
		return rtn;
	}
	public void seek(final int loc) throws IOException{
		
		if(this.validateIndex(loc)== true)
			_currentLoc=loc;
	}
	
	public byte getByte()throws IOException{
	
	
		   
		    _ras.seek(_currentLoc);
		    
			final byte data = (byte)_ras.read();
			return data;
		    
		
	
	}
	
	public void closeFile() throws IOException{
		_ras.close();
	}
	public byte[] getBytes(int loc, int length)throws IOException{
		
		if(validateIndex(loc)== true)
			_ras.seek(loc);
		
		byte[] data = new byte[length];
		int bytesRead = _ras.read(data);
		
			
		return data;
	}
	public void checkMostSignificantByte(int loc) throws IOException{
		
			
		byte[] data =this.getBytes(loc,2);
		
		short msbMarker = (short) ( (data[0] << 8 ) | (data[1] &0xFF));
		
		if(msbMarker == 0x4949)
			this._msb_lsb=false;
		
		if(msbMarker == 0x4D4D)
			this._msb_lsb=true;
	}
	
	public boolean isMostSignificantByte(){
		return this._msb_lsb;
	}
	
	public byte getByte(final int location)throws IOException{
		
		    if (location == _currentLoc)
		    	_ras.seek(location);
		    
			final byte data = (byte)_ras.read();
			return data;
	
	}
	
	public String readAscii(int location, int length) throws IOException{
		String str=null;
	
		byte[] data = getBytes(location, length);
		
		try {
			str = new String(data,"ASCII");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str;
	}
}
