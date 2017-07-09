package com.radcliffe.exception;
import java.io.*;
import java.util.*;
public class LoggerMain {

	static Date d;
	public static void main(String[] args) throws ArrayIndexOutOfBoundsException{
		d= new Date();
		int[] a = {1,2};
		// TODO Auto-generated method stub
		Logger lg = new Logger("c:/loggersamples/","Logger.txt");
		try{
		System.out.println(a[2]);
		}catch(ArrayIndexOutOfBoundsException e){
			e.printStackTrace();
		}
		try{
			throw new LoggerException("Error in main", LoggerMain.class.toString(), "Main method");
		}catch(LoggerException le){
			System.out.println(le.getMessage());
			lg.logError("Excepton,"+"Error in main Method," + LoggerMain.class.toString()+ ","+ d);
			lg.logDone();
		}
		 
	}

}
