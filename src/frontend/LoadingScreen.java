package frontend;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.sound.sampled.*;


public class LoadingScreen implements ActionListener{
	JButton button=new JButton("Sign Up");
	JLabel label=new JLabel();
	JFrame frame=new JFrame();
	public LoadingScreen() {
		try {
		    UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName());
		 } catch (Exception e) {
		          //  e.printStackTrace();
		 }
		
		
		try {
			File file=new File("song.wav");
			AudioInputStream audio=AudioSystem.getAudioInputStream(file);
			Clip clip=AudioSystem.getClip();
			clip.open(audio);
			clip.start();
		} catch (UnsupportedAudioFileException | IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		
		
		
		
		ImageIcon icon=new ImageIcon("logo.png");
		label.setIcon(icon);
		//label.setBackground(Color.red);
		//label.setOpaque(true);
		label.setBounds(35,60,350,200);
		//button.setBounds(185,200,50,20);
		button.setSize(80,20);
		button.setForeground(Color.yellow);
		button.setBackground(Color.red);
		button.setOpaque(true);
		button.setFont(new Font("Comic Sans", Font.PLAIN, 12));
		button.addActionListener(this);
		
		
		JPanel panel=new JPanel();
		panel.setLayout(new BorderLayout());
		panel.setBounds(170,270,80,20);
		panel.add(button,BorderLayout.CENTER);
		
		ImageIcon logo=new ImageIcon("logoM.png");
		frame.setIconImage(logo.getImage());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		frame.setSize(420,420);
		frame.setResizable(false);
		frame.getContentPane().setBackground(Color.black);
		frame.setTitle("Marvel Ultimate War");
		frame.add(panel);
		frame.add(label);
		
		frame.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==button) {
			SignUpScreen s=new SignUpScreen();
			frame.dispose();
		}
		
	}
	
	public static void main(String[] args) {
		new LoadingScreen();
	}
}
