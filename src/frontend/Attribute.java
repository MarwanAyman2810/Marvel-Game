package frontend;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Attribute {
	Font rigor;
	
	public Attribute() {
		try {
			rigor=Font.createFont(Font.TRUETYPE_FONT,new File("RigorMortis.otf")).deriveFont(30f);
			GraphicsEnvironment g=GraphicsEnvironment.getLocalGraphicsEnvironment();
			g.registerFont(Font.createFont(Font.TRUETYPE_FONT,new File("RigorMortis.otf")));
		}
		catch(IOException | FontFormatException e) {
		}
		
		
		ImageIcon icon1=new ImageIcon("AntiHeroes.png");
		ImageIcon icon2=new ImageIcon("heroes.png");
		ImageIcon icon3=new ImageIcon("Villains.png");
		JLabel text1=new JLabel("Anti-Heroes");
		text1.setFont(rigor);
		text1.setBounds(150,30,200,40);
		JLabel text2=new JLabel("Heroes");
		text2.setFont(rigor);
		text2.setBounds(600,30,200,40);
		JLabel text3=new JLabel("Villains");
		text3.setFont(rigor);
		text3.setBounds(1100,30,200,40);
		
		JLabel label1=new JLabel();
		label1.setIcon(icon1);
		label1.setBounds(50,0,300,600);
		
		JLabel label2=new JLabel();
		label2.setIcon(icon2);
		label2.setBounds(450,0,500,600);
		JLabel label3=new JLabel();
		label3.setIcon(icon3);
		label3.setBounds(960,0,500,450);
		JFrame frame=new JFrame();
		ImageIcon logo=new ImageIcon("logoM.png");
		frame.setIconImage(logo.getImage());
		frame.add(text1);
		frame.add(text2);
		frame.add(text3);
		frame.add(label1);
		frame.add(label2);
		frame.add(label3);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLayout(null);
		frame.setSize(1500,600);
		frame.setResizable(false);
		frame.getContentPane().setBackground(Color.white);
		frame.setTitle("Marvel Ultimate War");
	
		
		frame.setVisible(true);
	}
	
	
}
