package com.radcliffe.exception;
import java.util.*;
import java.lang.*;
public class LoggerException extends Exception {
	private String ExceptionMSG;
	private String MethodName;
	private String ClassName;
	
	
	public LoggerException(String ExceptionMessage, String MethodName, String ClassName){
		super(ExceptionMessage + " " + ClassName + " " + MethodName);
		this.ExceptionMSG=ExceptionMessage;
		this.MethodName=MethodName;
		this.ClassName= ClassName;
	}
	public String getMessage(){
		
		return "Exception Occured in " + this.ClassName + "at " +this.MethodName + " with Exception " + this.ExceptionMSG;
	}
	
}
