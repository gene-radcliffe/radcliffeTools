package com.radcliffe.exception;

public interface LoggerMethods {
	void logError(String msg);
	void logAppend(boolean append);
	void setDirectory(String path);
	void setFileName(String File);
	String getDirectory();
	String getFileName();
}
