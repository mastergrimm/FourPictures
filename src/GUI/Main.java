package GUI;

import java.util.Random;

import Logic.FourPictures;
import Logic.Games;

public class Main {
	
	public static void main(String [] args) throws Exception{
		FourPictures level = new FourPictures();
		GameWindow display = new GameWindow(level);
		
	}
}
