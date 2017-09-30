package com.radcliffe.utilities;

public abstract class RandomAccess {

		abstract void getString(int loc, int offset);
		abstract void writeString(String data);
		abstract void seek();
		
}
