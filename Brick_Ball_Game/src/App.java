
import java.awt.Image;
import java.awt.Label;

import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class App {

	public static void main(String[] args) { 
		JFrame f=new JFrame();
		f.setTitle("Breaking Ball");
		f.setSize(707,630);
		f.setLocation(400,100);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setResizable(false);
		GamePlay gameP=new GamePlay();
		f.add(gameP);
		f.setVisible(true);
	}

}