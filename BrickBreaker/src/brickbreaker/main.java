package brickbreaker;
import javax.swing.*;

public class main {

	public static void main(String[] args) {
		JFrame obj=new JFrame();
		obj.setBounds(10,10,700,600);
		obj.setResizable(false);
		obj.setTitle("Brick Breaker");
		obj.setVisible(true);
		obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Gameplay gameplay=new Gameplay();
		obj.add(gameplay); 

	}

}
