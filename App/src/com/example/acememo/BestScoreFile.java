package com.example.acememo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import android.content.Context;
import android.os.Environment;

public class BestScoreFile {
	
	public void writeToFile(int bestScore) throws FileNotFoundException{
		
		File myFile = new File("/sdcard/acememo/BestScore.txt");
		PrintWriter writer = new PrintWriter(myFile);
		writer.print(bestScore);
		writer.close();
		
	}
	
	public void clearFolder(){
	    File newFolder = new File(Environment.getExternalStorageDirectory(), "acememo");
	    File myFile = new File("/sdcard/acememo/BestScore.txt");	    
	    if (newFolder.exists()) {
	    	myFile.delete();
	        newFolder.delete();
	    }
	}
	
	public int readFile(){
		checkOrCreateFolder();
		
		try {
			File myFile = new File("/sdcard/acememo/BestScore.txt");
			FileInputStream fIn = new FileInputStream(myFile);
			BufferedReader myReader = new BufferedReader(
					new InputStreamReader(fIn));
			String aDataRow = "";
			String aBuffer = "";
			while ((aDataRow = myReader.readLine()) != null) {
				aBuffer += aDataRow;
			}
			myReader.close();
			
			int score = Integer.parseInt(aBuffer);
			return score;
		} catch (Exception e) {
			return 0;
		}
		
		
	}
	
	
	private void checkOrCreateFolder(){
		try {
		    File newFolder = new File(Environment.getExternalStorageDirectory(), "acememo");
		    if (!newFolder.exists()) {
		        newFolder.mkdir();
			    
				try {
					File myFile = new File("/sdcard/acememo/BestScore.txt");
					myFile.createNewFile();
					FileOutputStream fOut = new FileOutputStream(myFile);
					OutputStreamWriter myOutWriter = new OutputStreamWriter(fOut);
					myOutWriter.append("0");
					myOutWriter.close();
					fOut.close();
	
				} catch (Exception e) {
				}
		  
		    }
		} catch (Exception e) {
		    System.out.println("e: " + e);
		}
	}

}
