package frontend;

import java.awt.GraphicsEnvironment;
import java.awt.FontFormatException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Window;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class SignUpScreen implements ActionListener{
	JFrame frame = new JFrame();
	JButton button1=new JButton("Submit");
	JButton button2=new JButton("Submit");
	JButton enter=new JButton("Enter Game");
	JTextField field1=new JTextField();
	JTextField field2=new JTextField();
	private String player1Name;
	private String player2Name;
	Font rigor;
	
	public SignUpScreen() {
		try {
			rigor=Font.createFont(Font.TRUETYPE_FONT,new File("RigorMortis.otf")).deriveFont(25f);
			GraphicsEnvironment g=GraphicsEnvironment.getLocalGraphicsEnvironment();
			g.registerFont(Font.createFont(Font.TRUETYPE_FONT,new File("RigorMortis.otf")));
		}
		catch(IOException | FontFormatException e) {
		}
		try {
		    UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName());
		 } catch (Exception e) {
		          //  e.printStackTrace();
		 }
		
		enter.setBounds(150,290,120,20);
		enter.setBackground(Color.red);
		enter.setForeground(Color.yellow);
		enter.setFont(new Font("Comic Sans", Font.PLAIN, 15));
		enter.setOpaque(true);
		enter.addActionListener(this);
		enter.setEnabled(false);
		
		JLabel label=new JLabel("Welcome to Ulitame War");
		JLabel label2=new JLabel("Please Sign Up");
		label.setBackground(Color.black);
		label2.setBackground(Color.black);
		label.setForeground(Color.yellow);
		label2.setForeground(Color.yellow);
		label.setFont(rigor);
		label2.setFont(rigor);
		label.setBounds(0,0,150,75);
		label2.setBounds(0,75,150,75);
		JPanel textPanel=new JPanel();
		textPanel.setBounds(60,70,300,150);
		textPanel.setBackground(Color.black);
		textPanel.add(label);
		textPanel.add(label2);
		
		field1.setBounds(0,0,150,40);
		field1.setFont(rigor);
		field1.setText("User 1");
		field1.setForeground(Color.blue);
		button1.setBounds(35,50,80,20);
		button1.setBackground(Color.red);
		button1.setForeground(Color.yellow);
		button1.setFont(new Font("Comic Sans", Font.PLAIN, 12));
		button1.setOpaque(true);
		button1.addActionListener(this);
		JPanel panel=new JPanel();
		panel.setSize(150,70);
		panel.setLayout(null);
		panel.setBounds(30,145,150,70);
		
		panel.add(field1);
		panel.add(button1);
		panel.setBackground(Color.black);
		
		
		field2.setBounds(0,0,150,40);
		field2.setFont(rigor);
		field2.setText("User 2");
		field2.setForeground(Color.blue);
		field2.setEditable(false);
		button2.setBounds(35,50,80,20);
		button2.setBackground(Color.red);
		button2.setForeground(Color.yellow);
		button2.setFont(new Font("Comic Sans", Font.PLAIN, 12));
		button2.setOpaque(true);
		button2.addActionListener(this);
		button2.setEnabled(false);
		JPanel panel2=new JPanel();
		panel2.setSize(150,70);
		panel2.setLayout(null);
		panel2.setBounds(240,145,150,70);
		
		panel2.add(field2);
		panel2.add(button2);
		panel2.setBackground(Color.black);
		ImageIcon logo=new ImageIcon("logoM.png");
		frame.setIconImage(logo.getImage());
		frame.add(enter);
		frame.add(panel);
		frame.add(panel2);
		frame.add(textPanel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		frame.setSize(420,420);
		frame.setResizable(false);
		frame.getContentPane().setBackground(Color.black);
		frame.setTitle("Marvel Ultimate War");
		frame.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==button1) {
			player1Name=field1.getText();
			field1.setEditable(false);
			field2.setEditable(true);
			button1.setEnabled(false);
			button2.setEnabled(true);
		}
		if(e.getSource()==button2) {
			player2Name=field2.getText();
			field2.setEditable(false);
			button2.setEnabled(false);
			enter.setEnabled(true);
		}
		if(e.getSource()==enter) {
			try {
				PlayMode s=new PlayMode(player1Name,player2Name);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				//e1.printStackTrace();
			}
			frame.dispose();
		}
		
	}
	public String getPlayer1Name() {
		return player1Name;
	}
	public String getPlayer2Name() {
		return player2Name;
	}
	
}
