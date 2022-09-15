package frontend;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Abilities {
Font rigor;
	
	public Abilities() {
		try {
			rigor=Font.createFont(Font.TRUETYPE_FONT,new File("RigorMortis.otf")).deriveFont(30f);
			GraphicsEnvironment g=GraphicsEnvironment.getLocalGraphicsEnvironment();
			g.registerFont(Font.createFont(Font.TRUETYPE_FONT,new File("RigorMortis.otf")));
		}
		catch(IOException | FontFormatException e) {
		}
		ImageIcon logo=new ImageIcon("logoM.png");
	
		ImageIcon icon1=new ImageIcon("cc.png");
		ImageIcon icon2=new ImageIcon("dmg.png");
		ImageIcon icon3=new ImageIcon("heal.png");
		
		JLabel label1=new JLabel();
		label1.setIcon(icon1);
		label1.setBounds(50,0,500,600);
		
		JLabel label2=new JLabel();
		label2.setIcon(icon2);
		label2.setBounds(520,0,500,600);
		JLabel label3=new JLabel();
		label3.setIcon(icon3);
		label3.setBounds(970,0,500,450);
		JFrame frame=new JFrame();
		
		
		
		frame.add(label1);
		frame.add(label2);
		frame.add(label3);
		frame.setIconImage(logo.getImage());
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLayout(null);
		frame.setSize(1500,600);
		frame.setResizable(false);
		frame.getContentPane().setBackground(Color.white);
		frame.setTitle("Marvel Ultimate War");
	
		
		frame.setVisible(true);
	}
	
	
}
