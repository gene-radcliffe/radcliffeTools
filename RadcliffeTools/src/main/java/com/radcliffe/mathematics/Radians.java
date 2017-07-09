package com.radcliffe.mathematics;
import java.math.*;
public class Radians {

	private static double deg_to_rad[] = new double[360];
	static{
		createDegToRadTable();
	}
	public Radians(){
		
	}
	
	/*
	 * create Radian table
	 * 
	 */
	private static void createDegToRadTable(){
		for(int x =0; x<360; x++){
			deg_to_rad[x] = Math.toRadians(x);
		}
	}
	public static double getDegToRad(int index){
		return deg_to_rad[index];
	}
}
