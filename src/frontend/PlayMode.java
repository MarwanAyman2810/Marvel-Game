package frontend;

import java.awt.GraphicsEnvironment;
import java.awt.FontFormatException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Window;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.Border;

import engine.Game;
import engine.Player;
import model.world.Champion;

public class PlayMode implements ActionListener , MouseListener {
	JFrame menuFrame =new JFrame();
	JFrame gameFrame=new JFrame();
	JButton play;
	JButton showAbilities;
	Player player1;
	Player player2;
	Game game;
	Font rigor;
	JLabel spiderman;
	JLabel captain;
	JLabel deadpool;
	JLabel strange;
	JLabel electro;
	JLabel rider;
	JLabel hela;
	JLabel hulk;
	JLabel iceman;
	JLabel ironman;
	JLabel loki;
	JLabel quicksilver;
	JLabel thor;
	JLabel venom;
	JLabel yellow;
	JLabel atr;
	JLabel title;
	JLabel label1=new JLabel();
	JLabel label2=new JLabel();
	JLabel label3=new JLabel();
	JLabel label4=new JLabel();
	JLabel label5=new JLabel();
	JLabel label6=new JLabel();
	ArrayList<JLabel> list=new ArrayList<JLabel>();
	JButton submit1=new JButton("Submit Team");
	JButton remove1=new JButton("remove");
	JButton submit2=new JButton("Submit Team");
	JButton remove2=new JButton("remove");
	JButton leader1=new JButton("Set Leader");
	JButton leader2=new JButton("Set Leader");
	public PlayMode(String name1, String name2) throws IOException {
		try {
			rigor=Font.createFont(Font.TRUETYPE_FONT,new File("RigorMortis.otf")).deriveFont(30f);
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
		
		ImageIcon spider=new ImageIcon("spiderman.png");
		spiderman=new JLabel();
		spiderman.setIcon(spider);
		spiderman.setBounds(0,50,150,140);
		spiderman.addMouseListener(this);
		spiderman.setText("Spiderman");
		spiderman.setForeground(Color.black);
		list.add(spiderman);
		
		ImageIcon cap=new ImageIcon("captain-america.png");
		captain=new JLabel();
		captain.setIcon(cap);
		captain.setBounds(200,50,150,140);
		captain.addMouseListener(this);
		captain.setText("Captain America");
		captain.setForeground(Color.black);
		list.add(captain);
		
		ImageIcon pool=new ImageIcon("deadpool.png");
		deadpool=new JLabel();
		deadpool.setIcon(pool);
		deadpool.setBounds(420,50,150,140);
		deadpool.addMouseListener(this);
		deadpool.setText("Deadpool");
		deadpool.setForeground(Color.black);
		list.add(deadpool);
		
		ImageIcon dr=new ImageIcon("drstrange.png");
		strange=new JLabel();
		strange.setIcon(dr);
		strange.setBounds(590,50,180,140);
		strange.addMouseListener(this);
		strange.setText("Dr Strange");
		strange.setForeground(Color.black);
		list.add(strange);
		
		ImageIcon elec=new ImageIcon("electro.png");
		electro=new JLabel();
		electro.setIcon(elec);
		electro.setBounds(820,50,150,140);
		electro.addMouseListener(this);
		electro.setText("Electro");
		electro.setForeground(Color.black);
		list.add(electro);
		
		ImageIcon ghost=new ImageIcon("ghostrider.png");
		rider=new JLabel();
		rider.setIcon(ghost);
		rider.setBounds(0,220,150,160);
		rider.addMouseListener(this);
		rider.setText("Ghost Rider");
		rider.setForeground(Color.black);
		list.add(rider);
		
		ImageIcon he=new ImageIcon("hela.png");
		hela=new JLabel();
		hela.setIcon(he);
		hela.setBounds(200,240,150,140);
		hela.addMouseListener(this);
		hela.setText("Hela");
		hela.setForeground(Color.black);
		list.add(hela);
		
		ImageIcon hu=new ImageIcon("hulk.png");
		hulk=new JLabel();
		hulk.setIcon(hu);
		hulk.setBounds(420,240,150,140);
		hulk.addMouseListener(this);
		hulk.setText("Hulk");
		hulk.setForeground(Color.black);
		list.add(hulk);
		
		ImageIcon ice=new ImageIcon("iceman.png");
		iceman=new JLabel();
		iceman.setIcon(ice);
		iceman.setBounds(610,260,150,140);
		iceman.addMouseListener(this);
		iceman.setText("Iceman");
		iceman.setForeground(Color.black);
		list.add(iceman);
		
		ImageIcon iron=new ImageIcon("ironman.png");
		ironman=new JLabel();
		ironman.setIcon(iron);
		ironman.setBounds(820,240,150,140);
		ironman.addMouseListener(this);
		ironman.setText("Ironman");
		ironman.setForeground(Color.black);
		list.add(ironman);
		
		ImageIcon lo=new ImageIcon("loki.png");
		loki=new JLabel();
		loki.setIcon(lo);
		loki.setBounds(25,420,150,140);
		loki.addMouseListener(this);
		loki.setText("Loki");
		loki.setForeground(Color.black);
		list.add(loki);
		
		ImageIcon quick=new ImageIcon("quicksilver.png");
		quicksilver=new JLabel();
		quicksilver.setIcon(quick);
		quicksilver.setBounds(200,425,150,140);
		quicksilver.addMouseListener(this);
		quicksilver.setText("Quicksilver");
		quicksilver.setForeground(Color.black);
		list.add(quicksilver);
		
		ImageIcon th=new ImageIcon("thor.png");
		thor=new JLabel();
		thor.setIcon(th);
		thor.setBounds(420,420,150,140);
		thor.addMouseListener(this);
		thor.setText("Thor");
		thor.setForeground(Color.black);
		list.add(thor);
		
		
		ImageIcon ve=new ImageIcon("venom.png");
		venom=new JLabel();
		venom.setIcon(ve);
		venom.setBounds(610,420,150,140);
		venom.addMouseListener(this);
		venom.setText("Venom");
		venom.setForeground(Color.black);
		list.add(venom);
		
		ImageIcon ye=new ImageIcon("yellow.png");
		yellow=new JLabel();
		yellow.setIcon(ye);
		yellow.setBounds(800,405,150,170);
		yellow.addMouseListener(this);
		yellow.setText("Yellow Jacket");
		yellow.setForeground(Color.black);
		list.add(yellow);
		
		
		atr=new JLabel();
		atr.setOpaque(true);
		atr.setBackground(Color.black);
		atr.setBounds(30,580,300,170);
		
		title=new JLabel();
		title.setOpaque(true);
		title.setText("title");
		title.setFont(rigor);
		title.setBackground(Color.black);
		title.setForeground(Color.yellow);
		title.setBounds(350,650,300,30);
		
		showAbilities=new JButton("Show Abilities");
		showAbilities.setOpaque(true);
		showAbilities.setBackground(Color.blue);
		showAbilities.setForeground(Color.yellow);
		showAbilities.setBounds(330,700,150,20);
		showAbilities.addActionListener(this);
		showAbilities.setFocusable(false);
		
		player1 = new Player(name1);
		player2 = new Player(name2);
		game=new Game(player1,player2);
		game.loadAbilities("Abilities.csv");
		game.loadChampions("Champions.csv");

		
		JLabel plr2=new JLabel(name2);
		JLabel plr2sett=new JLabel("Player 2");
		plr2sett.setBackground(Color.black);
		plr2sett.setForeground(Color.white);
		plr2sett.setFont(rigor);
		plr2sett.setBounds(990,350,120,30);
		
		plr2.setBackground(Color.black);
		plr2.setForeground(Color.white);
		plr2.setFont(rigor);
		plr2.setBounds(1120,350,250,30);
		
		JLabel plr1=new JLabel(name1);
		JLabel plr1sett=new JLabel("Player 1");
		
		plr1sett.setBackground(Color.black);
		plr1sett.setForeground(Color.white);
		plr1sett.setFont(rigor);
		plr1sett.setBounds(990,30,120,30);
		
		plr1.setBackground(Color.black);
		plr1.setForeground(Color.white);
		plr1.setFont(rigor);
		plr1.setBounds(1120,30,250,30);
		
		JLabel leader=new JLabel("Leader");
		leader.setFont(rigor);
		leader.setForeground(Color.yellow);
		leader.setBounds(1000,250,150,40);
		
		
		
		JLabel leader2=new JLabel("Leader");
		leader2.setFont(rigor);
		leader2.setForeground(Color.yellow);
		leader2.setBounds(1000,577,150,40);
		
		Border border=BorderFactory.createLineBorder(Color.red, 7);
		
		label1.setBounds(990,70,150,170);
		label1.setBorder(border);
		label2.setBounds(1150,70,150,170);
		label3.setBounds(1300,70,150,170);
		
		label4.setBounds(990,390,150,170);
		label4.setBorder(border);
		label5.setBounds(1150,390,150,170);
		label6.setBounds(1300,390,150,170);
		
		
		
		
		play=new JButton("Play -->");
		play.setOpaque(true);
		play.setBackground(Color.red);
		play.setForeground(Color.yellow);
		play.setBounds(1280,730,100,20);
		play.setFocusable(false);
		play.addActionListener(this);
		play.setEnabled(false);
		
		submit1.setFocusable(false);
		submit1.setForeground(Color.yellow);
		submit1.setBackground(Color.red);
		submit1.setOpaque(true);
		submit1.setFont(new Font("Ariel",Font.PLAIN,12));
		submit1.setBounds(1310,255,110,20);
		submit1.setEnabled(false);
		submit1.addActionListener(this);
		
		remove1.setFocusable(false);
		remove1.setForeground(Color.yellow);
		remove1.setBackground(Color.blue);
		remove1.setOpaque(true);
		remove1.setBounds(1165,255,110,20);
		remove1.setEnabled(true);
		remove1.addActionListener(this);
		
		
		
		
		
		submit2.setFocusable(false);
		submit2.setForeground(Color.yellow);
		submit2.setBackground(Color.red);
		submit2.setOpaque(true);
		submit2.setBounds(1310,585,110,20);
		submit2.setEnabled(false);
		submit2.setFont(new Font("Ariel",Font.PLAIN,12));
		submit2.addActionListener(this);
		
		remove2.setFocusable(false);
		remove2.setForeground(Color.yellow);
		remove2.setBackground(Color.blue);
		remove2.setOpaque(true);
		remove2.setBounds(1165,585,110,20);
		remove2.setEnabled(false);
		remove2.addActionListener(this);
		
		
		ImageIcon logo=new ImageIcon("logoM.png");
		menuFrame.setIconImage(logo.getImage());
		menuFrame.add(leader2);
		menuFrame.add(leader);
		menuFrame.add(label4);
		menuFrame.add(label5);
		menuFrame.add(label6);
		menuFrame.add(remove2);
		menuFrame.add(remove1);
		menuFrame.add(submit2);
		menuFrame.add(submit1);
		menuFrame.add(label1);
		menuFrame.add(label2);
		menuFrame.add(label3);
		menuFrame.add(yellow);
		menuFrame.add(venom);
		menuFrame.add(thor);
		menuFrame.add(quicksilver);
		menuFrame.add(loki);
		menuFrame.add(ironman);
		menuFrame.add(hela);
		menuFrame.add(iceman);
		menuFrame.add(hulk);
		menuFrame.add(rider);
		menuFrame.add(electro);
		menuFrame.add(strange);
		menuFrame.add(deadpool);
		menuFrame.add(captain);
		menuFrame.add(spiderman);
		menuFrame.add(atr);
		menuFrame.add(title);
		menuFrame.add(play);
		menuFrame.add(showAbilities);
		menuFrame.add(plr2sett);
		menuFrame.add(plr2);
		menuFrame.add(plr1sett);
		menuFrame.add(plr1);
		menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menuFrame.setLayout(null);
		menuFrame.pack();
		menuFrame.setSize(1450,800);
		menuFrame.setResizable(false);
		menuFrame.getContentPane().setBackground(Color.black);
		menuFrame.setTitle("Marvel Ultimate War");
		menuFrame.setVisible(true);
		
		

		
	}
	
	
	
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		new PlayMode(null,null);
		
	}




	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource()!=label1 && e.getSource()!=label2 && e.getSource()!=label3  && label4.getIcon()==null) {
			for(int i=0;i<list.size();i++) {
				JLabel x=list.get(i);
				if(e.getSource()==x) {
					if(label1.getIcon()==null) {
						label1.setIcon(x.getIcon());
						label1.setText(x.getText());
						x.setIcon(null); 
						x.setEnabled(false);
					}
					else if(label2.getIcon()==null){
						label2.setIcon(x.getIcon());
						label2.setText(x.getText());
						x.setIcon(null); 
						x.setEnabled(false);
					}
					else if(label3.getIcon()==null) {
						label3.setIcon(x.getIcon());
						label3.setText(x.getText());
						x.setIcon(null); 
						x.setEnabled(false);
						submit1.setEnabled(true);
						
					}
				}
				
			}
		}
		if(e.getSource()!=label4 && e.getSource()!=label5 && e.getSource()!=label6  && label3.getIcon()!=null) {
			for(int i=0;i<list.size();i++) {
				JLabel x=list.get(i);
				if(e.getSource()==x) {
					if(label4.getIcon()==null) {
						label4.setIcon(x.getIcon());
						label4.setText(x.getText());
						x.setIcon(null); 
						x.setEnabled(false);
					}
					else if(label5.getIcon()==null){
						label5.setIcon(x.getIcon());
						label5.setText(x.getText());
						x.setIcon(null); 
						x.setEnabled(false);
					}
					else if(label6.getIcon()==null) {
						label6.setIcon(x.getIcon());
						label6.setText(x.getText());
						x.setIcon(null); 
						x.setEnabled(false);
						submit2.setEnabled(true);
						
					}
				}
				
			}
		}
		
		
	}




	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
		
	}




	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}




	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==spiderman) {
			ImageIcon icon=new ImageIcon("spiderat.png");
			atr.setIcon(icon);
			title.setText("Hero");
		}
		if(e.getSource()==captain) {
			ImageIcon icon=new ImageIcon("capat.png");
			atr.setIcon(icon);
			title.setText("Hero");
		}
		if(e.getSource()==ironman) {
			ImageIcon icon=new ImageIcon("ironat.png");
			atr.setIcon(icon);
			title.setText("Hero");
		}
		if(e.getSource()==strange) {
			ImageIcon icon=new ImageIcon("strangeat.png");
			atr.setIcon(icon);
			title.setText("Hero");
		}
		if(e.getSource()==hulk) {
			ImageIcon icon=new ImageIcon("hulkat.png");
			atr.setIcon(icon);
			title.setText("Hero");
		}
		if(e.getSource()==thor) {
			ImageIcon icon=new ImageIcon("thorat.png");
			atr.setIcon(icon);
			title.setText("Hero");
		}
		if(e.getSource()==iceman) {
			ImageIcon icon=new ImageIcon("iceat.png");
			atr.setIcon(icon);
			title.setText("Hero");
		}
		if(e.getSource()==quicksilver) {
			ImageIcon icon=new ImageIcon("quickat.png");
			atr.setIcon(icon);
			title.setText("Villain");
		}
		if(e.getSource()==loki) {
			ImageIcon icon=new ImageIcon("lokiat.png");
			atr.setIcon(icon);
			title.setText("Villain");
		}
		if(e.getSource()==electro) {
			ImageIcon icon=new ImageIcon("elcat.png");
			atr.setIcon(icon);
			title.setText("Villain");
		}
		if(e.getSource()==hela) {
			ImageIcon icon=new ImageIcon("helat.png");
			atr.setIcon(icon);
			title.setText("Villain");
		}
		if(e.getSource()==yellow) {
			ImageIcon icon=new ImageIcon("yelat.png");
			atr.setIcon(icon);
			title.setText("Villain");
		}
		if(e.getSource()==venom) {
			ImageIcon icon=new ImageIcon("venomat.png");
			atr.setIcon(icon);
			title.setText("Anti hero");
		}
		if(e.getSource()==deadpool) {
			ImageIcon icon=new ImageIcon("deadat.png");
			atr.setIcon(icon);
			title.setText("Anti hero");
		}
		if(e.getSource()==rider) {
			ImageIcon icon=new ImageIcon("rideat.png");
			atr.setIcon(icon);
			title.setText("Anti hero");
		}
		
	}




	@Override
	public void mouseExited(MouseEvent e) {
		atr.setIcon(null);
		title.setText("Title");
		
	}




	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==showAbilities) {
			new Abilities();
		}
		if(e.getSource()==submit1) {
			for(int i=0;i<game.getAvailableChampions().size();i++) {
				Champion x=game.getAvailableChampions().get(i);
				if(x.getName().equals(label1.getText())) {
					player1.getTeam().add(x);
					//remove1.setEnabled(true);
				}
			}
			for(int i=0;i<game.getAvailableChampions().size();i++) {
				Champion x=game.getAvailableChampions().get(i);
				if(x.getName().equals(label2.getText())) {
					player1.getTeam().add(x);
					//remove1.setEnabled(true);
				}
			}
			for(int i=0;i<game.getAvailableChampions().size();i++) {
				Champion x=game.getAvailableChampions().get(i);
				if(x.getName().equals(label3.getText())) {
					player1.getTeam().add(x);
					//remove1.setEnabled(true);
				}
			}
			/*game.getTurnOrder().insert(player1.getTeam().get(0));
			game.getTurnOrder().insert(player1.getTeam().get(1));
			game.getTurnOrder().insert(player1.getTeam().get(2));*/
			submit1.setEnabled(false);
			remove1.setEnabled(false);
			remove2.setEnabled(true);
			submit2.setEnabled(true);
			leader1.setEnabled(true);
			for(int i=0;i<game.getAvailableChampions().size();i++) {
				Champion x=game.getAvailableChampions().get(i);
				if(x.getName().equals(label1.getText())) {
					player1.setLeader(x);
				}
			}

		}
		
		if(e.getSource()==remove1) {
			submit1.setEnabled(false);
			if(label3.getIcon()!=null) {
				for(int i=0;i<game.getAvailableChampions().size();i++) {
					Champion x=game.getAvailableChampions().get(i);
					if(x.getName().equals(label3.getText())) {
						player1.getTeam().remove(x);
					}
				}
				for(int i=0;i<list.size();i++) {
					JLabel x=list.get(i);
					if(x.getIcon()==null && x.getText().equals(label3.getText())) {
						x.setEnabled(true);
						x.setIcon(label3.getIcon());
						
					}
				}
				label3.setIcon(null);
				label3.setText(null);
			}
			else if(label2.getIcon()!=null) {
				for(int i=0;i<game.getAvailableChampions().size();i++) {
					Champion x=game.getAvailableChampions().get(i);
					if(x.getName().equals(label2.getText())) {
						player1.getTeam().remove(x);
					}
				}
				for(int i=0;i<list.size();i++) {
					JLabel x=list.get(i);
					if(x.getIcon()==null && x.getText().equals(label2.getText())) {
						x.setEnabled(true);
						x.setIcon(label2.getIcon());
						
					}
				}
				label2.setIcon(null);
				label2.setText(null);
			}
			else if(label1.getIcon()!=null) {
				for(int i=0;i<game.getAvailableChampions().size();i++) {
					Champion x=game.getAvailableChampions().get(i);
					if(x.getName().equals(label1.getText())) {
						player1.getTeam().remove(x);
					}
				}
				for(int i=0;i<list.size();i++) {
					JLabel x=list.get(i);
					if(x.getIcon()==null && x.getText().equals(label1.getText())) {
						x.setEnabled(true);
						x.setIcon(label1.getIcon());
						
					}
				}
				label1.setIcon(null);
				label1.setText(null);
			}
		}
		if(e.getSource()==submit2) {
			for(int i=0;i<game.getAvailableChampions().size();i++) {
				Champion x=game.getAvailableChampions().get(i);
				if(x.getName().equals(label4.getText())) {
					player2.getTeam().add(x);
					//remove1.setEnabled(true);
				}
			}
			for(int i=0;i<game.getAvailableChampions().size();i++) {
				Champion x=game.getAvailableChampions().get(i);
				if(x.getName().equals(label5.getText())) {
					player2.getTeam().add(x);
					//remove1.setEnabled(true);
				}
			}
			for(int i=0;i<game.getAvailableChampions().size();i++) {
				Champion x=game.getAvailableChampions().get(i);
				if(x.getName().equals(label6.getText())) {
					player2.getTeam().add(x);
					//remove1.setEnabled(true);
				}
			}
			/*game.getTurnOrder().insert(player2.getTeam().get(0));
			game.getTurnOrder().insert(player2.getTeam().get(1));
			game.getTurnOrder().insert(player2.getTeam().get(2));*/
			remove2.setEnabled(false);
			submit2.setEnabled(false);
			leader2.setEnabled(true);
			for(int i=0;i<game.getAvailableChampions().size();i++) {
				Champion x=game.getAvailableChampions().get(i);
				if(x.getName().equals(label4.getText())) {
					player2.setLeader(x);
				}
			}

			play.setEnabled(true);
		}
		if(e.getSource()==remove2) {
			submit2.setEnabled(false);
			if(label6.getIcon()!=null) {
				for(int i=0;i<game.getAvailableChampions().size();i++) {
					Champion x=game.getAvailableChampions().get(i);
					if(x.getName().equals(label6.getText())) {
						player2.getTeam().remove(x);
					}
				}
				for(int i=0;i<list.size();i++) {
					JLabel x=list.get(i);
					if(x.getIcon()==null && x.getText().equals(label6.getText())) {
						x.setEnabled(true);
						x.setIcon(label6.getIcon());
						
					}
				}
				label6.setIcon(null);
				label6.setText(null);
			}
			else if(label5.getIcon()!=null) {
				for(int i=0;i<game.getAvailableChampions().size();i++) {
					Champion x=game.getAvailableChampions().get(i);
					if(x.getName().equals(label5.getText())) {
						player2.getTeam().remove(x);
					}
				}
				for(int i=0;i<list.size();i++) {
					JLabel x=list.get(i);
					if(x.getIcon()==null && x.getText().equals(label5.getText())) {
						x.setEnabled(true);
						x.setIcon(label5.getIcon());
						
					}
				}
				label5.setIcon(null);
				label5.setText(null);
			}
			else if(label4.getIcon()!=null) {
				for(int i=0;i<game.getAvailableChampions().size();i++) {
					Champion x=game.getAvailableChampions().get(i);
					if(x.getName().equals(label4.getText())) {
						player2.getTeam().remove(x);
					}
				}
				for(int i=0;i<list.size();i++) {
					JLabel x=list.get(i);
					if(x.getIcon()==null && x.getText().equals(label4.getText())) {
						x.setEnabled(true);
						x.setIcon(label4.getIcon());
						
					}
				}
				label4.setIcon(null);
				label4.setText(null);
			}
		}
		if(e.getSource()==play) {
		
			game=new Game(player1,player2);
			try {
				game.loadAbilities("Abilities.csv");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				game.loadChampions("Champions.csv");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			new GameBoard(game);
			menuFrame.dispose();
			
		}
		

		
	}
	
	
	
}
