package Logic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;

public class Games {
	
	private String path;
	
	public Games(String file_path){
		path = file_path;		
	}
	
	public String[] OpenFile() throws Exception{
		FileReader fr = new  FileReader(path);
		BufferedReader br = new BufferedReader(fr);
		
		int lines = 6;
		String[ ] textData = new String[lines];
		
		for (int i =0; i < lines; i++) 
			{
				textData[i] = br.readLine();
			}
	
		br.close();
		
		return textData;		
	}
	
	public String returnAnswer(String[] textData){
		return textData[0];
	}
	
	public String returnSelection(String[] textData){
		return textData[1];
	}
	
	
	public int readLines() throws Exception{
		FileReader fileToRead = new  FileReader(path);
		BufferedReader bufferToRead = new BufferedReader(fileToRead);
		
		String eachLine;
		int numberOfLines = 0;
		
		while ( ( eachLine = bufferToRead.readLine( ) ) != null ) {
			numberOfLines++;
			}
		
		bufferToRead.close();
		
		return numberOfLines;
	}
	
	public String printLines(Games g, int i) throws Exception{
		String[ ] aryLines = g.OpenFile();

		return aryLines[i];	
	}
	
}
