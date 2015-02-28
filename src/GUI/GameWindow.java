package GUI;

import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Logic.FourPictures;

public class GameWindow extends JFrame{
	
	public static int counter = 0;
	public static int loss_counter = 0;
	private ArrayList<JButton> buttonArray;
	private JLabel revealed;
	private JLabel guessedLabel;
	
	public GameWindow(FourPictures game){
		super("FOUR PICTURES");
		buttonArray = new ArrayList<JButton>();
		createWidgets(game);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600,600);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public void createWidgets(FourPictures game){
		createPanels(game);		
	}
	
	public void createPanels(FourPictures game){
		JPanel main = new JPanel(new BorderLayout());
		JPanel north = new JPanel(new FlowLayout());
		JPanel center = new JPanel(new GridLayout(2,2));
		JPanel south = new JPanel(new FlowLayout());
		north.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		south.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		
		main.add(north,BorderLayout.NORTH); main.add(center,BorderLayout.CENTER); main.add(south,BorderLayout.SOUTH);
		createLabels(game, north);
		createImages(game, center);
		createButtons(south, game, main, revealed);
	
		add(main);
	}
	
	public void createLabels(FourPictures game, JPanel north){
		revealed = new JLabel(game.getRevealed());
		guessedLabel = new JLabel("Guess: ");
		guessedLabel.setFont(new Font("Sans-Serif", Font.PLAIN, 20));	
		revealed.setFont(new Font("Sans-Serif", Font.PLAIN, 20));
		north.add(guessedLabel);  north.add(revealed);
	}
	
	
	public void createImages(FourPictures game, JPanel center){
		for (int i=0;i<4;i++) {
			String path = "";
			path+=game.getFilename(i);
			ImageIcon fourpics = new ImageIcon(path);
			JLabel image=new JLabel(fourpics);
			center.add(image);
		}
	}
	
	public void createButtons(JPanel south, FourPictures game, JPanel main, JLabel label){
		for(int i=0;i < game.getSelection().length();++i){
			String text="";
            text+=game.getSelectionLetters(i);
            char chr=text.charAt(0);
            JButton btn=new JButton(text);
            this.buttonArray.add(btn);
            clickButton(game, btn, label);
		}
		
		for (int i=0;i<this.buttonArray.size();i++) {
            south.add(this.buttonArray.get(i));
        }
		
	}
	
	public void clickButton(FourPictures game, JButton btn, JLabel revealed){
		btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
            	char d = btn.getText().charAt(0);
        		
        		for(int j = 0; j < game.getSelection().length();++j)
        		{
        			if(game.getAnswer().charAt(counter)==d){
        				game.setCharacter(game.getCorrectPosition(counter)-1, d);
        				updateRevealed(game, revealed);
        				btn.setEnabled(false);
        				System.out.println(game.getRevealed());
        				CheckVictory(btn, game);
        				++counter;	
        				
        				break;
        			}
        			else if(j == 0){
        				++loss_counter;
        				if(loss_counter==5){
        					CheckLoss();
        					dispose();
        					new GameWindow(game);
        				}
        			}
        		}
            } 
         });
	}
	
	public void updateRevealed(FourPictures game, JLabel label){
		revealed.setText(game.getRevealed());
	}
	
	public void CheckVictory(JButton b, FourPictures game){
		String temp = "";
		temp+= game.getAnswer().charAt((game.getAnswer().length()-1));
		
		if(b.getText().equals(temp)){
			System.out.println("Victory");
			JOptionPane.showMessageDialog(this,"YOU WON! The word was: " + game.getAnswer(), "SUCCESS",JOptionPane.PLAIN_MESSAGE);
			dispose();
		}
	}
	
	public void CheckLoss(){
		System.out.println("Loss");
		JOptionPane.showMessageDialog(this,"You lost ;( Too many attempts", "GAME OVER!",JOptionPane.PLAIN_MESSAGE);
		dispose();
	}	
}