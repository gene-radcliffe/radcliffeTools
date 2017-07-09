package com.radcliffe.exception;
import java.io.*;
import java.util.regex.*;
import javax.swing.*;


import java.util.*;
public class Logger implements LoggerMethods {

	private String Directory_Path;
	private String FileName;
	private File file;
	private File Dir;
	private FileWriter writer;
	private PrintWriter pWriter;
	private JOptionPane dialog;
	private BufferedWriter bf;
	private File Directory_file;
	public Logger(String path, String FileName){
		this.Directory_Path= path;
		this.FileName=FileName;
		
		LoadFileAndDirectory();
		LoadPrintWriter();
		
	}
	public Logger(File path, String FileName){
		this.Directory_file= path;
		this.FileName=FileName;
		
		LoadFileAndDirectory();
		LoadPrintWriter();
		
	}
	private void LoadFileAndDirectory(){
		if(Directory_Path =="" || Directory_Path == null){
			Dir = Directory_file;
		}else{
		Dir = new File(this.Directory_Path);
		}
		file = new File(Dir, this.FileName);
	
		if(Dir.mkdir()){
			try{
			file.createNewFile();
			}catch(IOException ioe){
				dialog = new JOptionPane();
				dialog.showMessageDialog(null, ioe.getMessage());
			}
		}
	}
	private void LoadPrintWriter(){
		try{
			writer = new FileWriter(file, true);
			bf = new BufferedWriter(writer);
			pWriter = new PrintWriter(bf);
			
			}catch(IOException ioe){
				dialog = new JOptionPane();
				dialog.showMessageDialog(null, ioe.getMessage());
			}
		
		
	}
	@Override
	public void logAppend(boolean append){
		
	}
	@Override
	public void logError(String msg) {
		// TODO Auto-generated method stub
	
		pWriter.println(msg+System.getProperty("line.separator"));
		pWriter.println("DDDD");
		
	}

	public void logDone(){
		pWriter.flush();
		pWriter.close();
	}
	@Override
	public void setDirectory(String path) {
		// TODO Auto-generated method stub
		this.Directory_Path=path;
	}

	@Override
	public void setFileName(String File) {
		// TODO Auto-generated method stub
		this.FileName=File;
	}

	@Override
	public String getDirectory() {
		// TODO Auto-generated method stub
		return this.Directory_Path;
	}

	@Override
	public String getFileName() {
		// TODO Auto-generated method stub
		return this.FileName;
	}

	
}
