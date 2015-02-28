package GUI;

import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Logic.FourPictures;

public class GameWindow extends JFrame{
	
	public static int counter = 0;
	private ArrayList<JButton> buttonArray;
	
	
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
		JPanel main = new JPanel(new BorderLayout());
		JPanel north = new JPanel(new FlowLayout());
		JPanel center = new JPanel(new GridLayout(2,2));
		JPanel south = new JPanel(new FlowLayout());
		north.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		south.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		
		main.add(north,BorderLayout.NORTH); main.add(center,BorderLayout.CENTER); main.add(south,BorderLayout.SOUTH);
		
		JLabel guessedLabel = new JLabel("Guess: ");
		JLabel revealed = new JLabel(game.getRevealed());
		guessedLabel.setFont(new Font("Sans-Serif", Font.PLAIN, 20));
		revealed.setFont(new Font("Sans-Serif", Font.PLAIN, 20));
		north.add(guessedLabel); north.add(revealed);
		
		for (int i=0;i<4;i++) {
			String path = "";
			path+=game.getFilename(i);
			JLabel image=new JLabel(new ImageIcon(path));
			System.out.println(path);
			center.add(image);
		}
		
		for(int i=0;i < game.getSelection().length();++i){
			String text="";
            text+=game.getSelectionLetters(i);
            char chr=text.charAt(0);
            JButton btn=new JButton(text);
            this.buttonArray.add(btn);
            btn.addActionListener(new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent ae) {
            	   
            	   checkButton(btn,revealed,game);
                   
               } 
            });
		}
		
		for (int i=0;i<this.buttonArray.size();i++) {
            south.add(this.buttonArray.get(i));
        }
		
		add(main);
	}
	
	public void checkButton(JButton button, JLabel label, FourPictures game){
		char d = button.getText().charAt(0);
		
		for(int j = 0; j < game.getSelection().length();++j)
		{
			if(game.getAnswer().charAt(counter)==d){
				++counter;
				game.setCharacter(game.getCorrectPosition(counter)-1, d);
				label.setText(game.getRevealed());
				button.setEnabled(false);
				break;
			}	
		}	
	}
	
}