package Logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class FourPictures {

	private String selection;
	private String[] filename = new String[4];
	private String answer;
	private String revealed;	
	private ArrayList<Integer> charList;
	
	public FourPictures() throws Exception{
		Games loadList = new Games("src\\Files\\GameList.txt");
		
		this.answer = loadList.printLines(loadList,0);;
		this.selection = loadList.printLines(loadList,1);
		this.filename[0] =loadList.printLines(loadList,2);
		this.filename[1] =loadList.printLines(loadList,3);
		this.filename[2] =loadList.printLines(loadList,4);
		this.filename[3] =loadList.printLines(loadList,5);
		this.charList=new ArrayList<Integer>();
		this.revealed = "";
		CreateGuessLabel();
		addPosition();
	}
	
	public void CreateGuessLabel(){
		for(int i = 0; i < this.answer.length(); ++i){
			this.revealed += "_ ";
		}
	}
	
	public char randomVowel()
	{
	    char[] vowels = {'a','e','i','o','u'};
	    Random r=new Random();
	    return vowels[r.nextInt(vowels.length)];
	}
	
	public String getSelection(){
		return this.selection;
	}
	
	public char getSelectionLetters(int i) {
		return this.selection.charAt(i);
	}
	
	public String getFilename(int filenameNumber){
		return this.filename[filenameNumber];
	}
	
	public String getAnswer(){
		return this.answer;
	}
	
	public char getAnswerCharacters(int characterLocation){
		char c = ' ';
		
		for (int i = 0;i < this.answer.length(); ++i){
			
			c = this.answer.charAt(i);
			return c;
		}
		
		return ' ';
	}
	
	public String getRevealed(){
		return this.revealed;
	}
	
	public String removeCharAt(String s, int pos) {
	      return s.substring(0, pos) + s.substring(pos + 1);
	   }
	
	public void setCharacter(int i,char c) {
		String temp=this.revealed.substring(0,i)+c+this.revealed.substring(i+1);
		this.revealed=temp;
	}
	
	public int getCorrectPosition(int i) {
		return this.charList.get(i);
	}
	
	public void addPosition(){
		int c=0,i=1;
		while (c<this.answer.length()) {
			if (i%2!=0) {
				this.charList.add(i); 
				++c;
			}
			++i;
		}
	}
	
	public static String chooseGame(){
		Random rand = new Random();
		String[] game = {"src\\Files\\GameList.txt","src\\Files\\GameList2.txt","src\\Files\\GameList3.txt"};
		int gameNumber = rand.nextInt(game.length);
		
		return game[gameNumber];
	}
	
}
