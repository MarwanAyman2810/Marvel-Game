package frontend;

import engine.Game;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.Border;

import engine.Game;
import engine.Player;
import engine.PriorityQueue;
import exceptions.AbilityUseException;
import exceptions.ChampionDisarmedException;
import exceptions.InvalidTargetException;
import exceptions.LeaderAbilityAlreadyUsedException;
import exceptions.LeaderNotCurrentException;
import exceptions.NotEnoughResourcesException;
import exceptions.UnallowedMovementException;
import model.abilities.Ability;
import model.abilities.AreaOfEffect;
import model.abilities.CrowdControlAbility;
import model.abilities.DamagingAbility;
import model.abilities.HealingAbility;
import model.effects.Effect;
import model.world.AntiHero;
import model.world.Champion;
import model.world.Cover;
import model.world.Damageable;
import model.world.Direction;
import model.world.Hero;
import model.world.Villain;
public class GameBoard implements ActionListener , MouseListener{
	JFrame frame=new JFrame();
	Object[][] board;
	JButton up=new JButton("UP");
	JButton down=new JButton("DOWN");
	JButton right=new JButton("RIGHT");
	JButton left=new JButton("LEFT");
	JButton attack=new JButton("Attack");
	JButton move=new JButton("Move");
	JButton cast1=new JButton("Cast Ability");
	JButton endTurn=new JButton("End Turn");
	JButton useLeaderAbility=new JButton("Use Leader Ability");
	Font rigor;
	JLabel used;
	JLabel used2;
	JLabel cur1;
	JLabel cur2;
	JLabel test;
	JLabel lab=new JLabel();
	JPanel pane=new JPanel();
	JLabel[][] cells=new JLabel[5][5];
	Game game;
	boolean movebool=false;
	boolean attackbool=false;
	boolean castbool=false;
	boolean endbool=false;
	boolean leaderbool=false;
	String[] abilities;
	String[] effects;
	JPanel c1;
	ArrayList<JLabel> list=new ArrayList<JLabel>();
	ArrayList<JLabel> list1=new ArrayList<JLabel>();
	JPanel panel=new JPanel();
	JLabel rem1=new JLabel();
	JLabel rem2=new JLabel();
	JLabel remx=new JLabel();
	JLabel rem3=new JLabel();
	JLabel rem4=new JLabel();
	JLabel rem5=new JLabel();
	String[] names=new String[6];
	JPanel info=new JPanel();
	boolean r1=true;
	boolean r2=true;
	boolean rx=true;
	boolean r3=true;
	boolean r4=true;
	boolean r5=true;
	Champion ch1;
	Champion ch2;
	Champion chx;
	Champion ch3;
	Champion ch4;
	Champion ch5;
	public GameBoard(Game game)  {
		
		ch1=game.getFirstPlayer().getTeam().get(0);
		ch2=game.getFirstPlayer().getTeam().get(1);
		chx=game.getFirstPlayer().getTeam().get(2);
		ch3=game.getSecondPlayer().getTeam().get(0);
		ch4=game.getSecondPlayer().getTeam().get(1);
		ch5=game.getSecondPlayer().getTeam().get(2);;
		
		this.game=game;
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
		try {
			game.loadAbilities("Abilities.csv");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			game.loadChampions("Champions.csv");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Border brdr=BorderFactory.createLineBorder(Color.red, 2);
		
		panel.setBounds(20,630,430,150);
		panel.setBackground(Color.black);
		panel.setOpaque(true);
		panel.setBorder(brdr);
		
		//panel.setLayout(null);
		frame.add(panel);

		String[] abs=new String[game.getCurrentChampion().getAbilities().size()];
		
		for(int i=0;i<game.getCurrentChampion().getAbilities().size();i++) {
			Ability ab=game.getCurrentChampion().getAbilities().get(i);
			if(ab instanceof HealingAbility ) {
				abs[i]="Name: "+ab.getName()+" Type: Heal"+" Area of Effect: "+ab.getCastArea()+" Range: "+ab.getCastRange()+" Cost: "+ab.getManaCost()+
						" Reequired points: "+ab.getRequiredActionPoints()+" Base Cooldown: "+ab.getBaseCooldown()+"\n"+" Current Cooldown: "+ab.getCurrentCooldown()+
						" Heal Amount: "+((HealingAbility) ab).getHealAmount();
			}
			if(ab instanceof DamagingAbility ) {
				abs[i]="Name: "+ab.getName()+" Type: Damage"+" Area of Effect: "+ab.getCastArea()+" Range: "+ab.getCastRange()+" Cost: "+ab.getManaCost()+
						" Reequired points: "+ab.getRequiredActionPoints()+" Base Cooldown: "+ab.getBaseCooldown()+"\n"+" Current Cooldown: "+ab.getCurrentCooldown()+
						" Damage Amount: "+((DamagingAbility) ab).getDamageAmount();
			}
			if(ab instanceof CrowdControlAbility ) {
				abs[i]="Name: "+ab.getName()+" Type: Crowd Control"+" Area of Effect: "+ab.getCastArea()+" Range: "+ab.getCastRange()+" Cost: "+ab.getManaCost()+
						" Reequired points: "+ab.getRequiredActionPoints()+" Base Cooldown: "+ab.getBaseCooldown()+"\n"+" Current Cooldown: "+ab.getCurrentCooldown()+
						" Effect "+((CrowdControlAbility) ab).getEffect()+" Duration: "+((CrowdControlAbility) ab).getEffect().getDuration();
			}
		}
		int plus=0;
		for(int i=0;i<abs.length;i++) {
			JLabel label=new JLabel(abs[i]);
			label.setFont(new Font("Arial",Font.PLAIN,5));
			label.setBounds(10,10+plus,200,40);
			label.setForeground(Color.yellow);
			plus+=50;
			panel.add(label);
		}
		
		String[] effects=new String[game.getCurrentChampion().getAppliedEffects().size()];
		for(int i=0;i<effects.length;i++) {
			Effect e=game.getCurrentChampion().getAppliedEffects().get(i);
			effects[i]="Effect: "+e.getName()+" Duration: "+e.getDuration();
		}
		JLabel applied=new JLabel("Applied Effects");
		applied.setBounds(10,80,200,30);
		applied.setForeground(Color.yellow);
		panel.add(applied);
		plus=0;
		for(int i=0;i<effects.length;i++) {
			JLabel label=new JLabel(effects[i]);
			label.setFont(new Font("Arial",Font.PLAIN,8));
			label.setBounds(10,10+plus,200,40);
			label.setForeground(Color.yellow);
			plus+=50;
			panel.add(label);
		}
		
		

		info.setBounds(1000,630,430,150);
		info.setBorder(brdr);
		info.setBackground(Color.black);
		info.setLayout(new FlowLayout());
		info.setOpaque(true);
		JLabel text=new JLabel("Name: "+game.getCurrentChampion().getName());
		text.setBounds(10,10,180,30);
		text.setFont(new Font("Arial",Font.BOLD,20));
		text.setForeground(Color.yellow);
		
		String types="";
		if(game.getCurrentChampion() instanceof Hero) {
			types="Hero";
		}
		if(game.getCurrentChampion() instanceof AntiHero) {
			types="Anti Hero";
		}
		if(game.getCurrentChampion() instanceof Villain) {
			types="Villain";
		}
		
		JLabel text2=new JLabel("--Type: "+types);
		text2.setBounds(220,10,180,30);
		text2.setFont(new Font("Arial",Font.BOLD,20));
		text2.setForeground(Color.yellow);
		
		JLabel text3=new JLabel("HP: "+game.getCurrentChampion().getCurrentHP()+"     Mana: "+game.getCurrentChampion().getMana()+"      Actions: "+game.getCurrentChampion().getCurrentActionPoints());
		text3.setBounds(10,80,300,30);
		text3.setFont(new Font("Arial",Font.BOLD,20));
		text3.setForeground(Color.yellow);
		
		JLabel text4=new JLabel("Damage: "+game.getCurrentChampion().getAttackDamage()+"      Range: "+game.getCurrentChampion().getAttackRange());
		text4.setBounds(10,120,300,30);
		text4.setFont(new Font("Arial",Font.BOLD,20));
		text4.setForeground(Color.yellow);
		
		info.add(text);
		info.add(text2);
		info.add(text3);
		info.add(text4);
		frame.add(info);

		up.setBounds(590,540,80,20);
		up.setBackground(Color.blue);
		up.setOpaque(true);
		up.setForeground(Color.yellow);
		up.setFocusable(false);
		up.addActionListener(this);
		
		down.setBounds(590,610,80,20);
		down.setBackground(Color.blue);
		down.setOpaque(true);
		down.setForeground(Color.yellow);
		down.setFocusable(false);
		down.addActionListener(this);
		
		right.setBounds(670,575,80,20);
		right.setBackground(Color.blue);
		right.setOpaque(true);
		right.setForeground(Color.yellow);
		right.setFocusable(false);
		right.addActionListener(this);
		
		left.setBounds(510,575,80,20);
		left.setBackground(Color.blue);
		left.setOpaque(true);
		left.setForeground(Color.yellow);
		left.setFocusable(false);
		left.addActionListener(this);
		
		move.setBounds(760,520,160,20);
		move.setBackground(Color.red);
		move.setOpaque(true);
		move.setForeground(Color.yellow);
		move.setFocusable(false);
		move.addActionListener(this);
		
		attack.setBounds(760,550,160,20);
		attack.setBackground(Color.red);
		attack.setOpaque(true);
		attack.setForeground(Color.yellow);
		attack.setFocusable(false);
		attack.addActionListener(this);
		
		endTurn.setBounds(760,580,160,20);
		endTurn.setBackground(Color.red);
		endTurn.setOpaque(true);
		endTurn.setForeground(Color.yellow);
		endTurn.setFocusable(false);
		endTurn.addActionListener(this);
		
		cast1.setBounds(760,610,160,20);
		cast1.setFocusable(false);
		cast1.setOpaque(true);
		cast1.setBackground(Color.red);
		cast1.setForeground(Color.yellow);
		cast1.addActionListener(this);
		
		useLeaderAbility.setBounds(760,640,160,20);
		useLeaderAbility.setBackground(Color.red);
		useLeaderAbility.setOpaque(true);
		useLeaderAbility.setForeground(Color.yellow);
		useLeaderAbility.setFocusable(false);
		useLeaderAbility.addActionListener(this);
		
		JLabel plr1=new JLabel("Player 1");
		plr1.setFont(rigor);
		plr1.setForeground(Color.yellow);
		plr1.setBounds(20,65,180,30);
		JLabel plr2=new JLabel("Player 2");
		plr2.setFont(rigor);
		plr2.setForeground(Color.yellow);
		plr2.setBounds(1100,65,180,30);
		JLabel name1=new JLabel(game.getFirstPlayer().getName());
		name1.setBounds(140,65,200,30);
		name1.setFont(rigor);
		name1.setForeground(Color.yellow);
		JLabel name2=new JLabel(game.getSecondPlayer().getName());
		name2.setBounds(1225,65,200,30);
		name2.setFont(rigor);
		name2.setForeground(Color.yellow);
		
		JLabel remain1=new JLabel("Remaining");
		remain1.setFont(rigor);
		remain1.setForeground(Color.red);
		remain1.setBounds(20,130,230,30);
		
		rem1.setBackground(Color.black);
		rem1.setOpaque(true);
		rem1.setBounds(20,170,280,130);
		rem1.addMouseListener(this);
		
		rem2.setBackground(Color.black);
		rem2.setOpaque(true);
		rem2.setBounds(20,310,280,130);
		rem2.addMouseListener(this);
		
		remx.setBackground(Color.black);
		remx.setOpaque(true);
		remx.setBounds(20,450,280,130);
		remx.addMouseListener(this);
	
		JLabel ab1=new JLabel("Leader Ability");
		ab1.setFont(rigor);
		ab1.setForeground(Color.blue);
		ab1.setBounds(20,590,270,30);
		used=new JLabel();
		ImageIcon icon=new ImageIcon("check.png");
		used.setIcon(icon);
		used.setBounds(220,575,50,50);
		
		JLabel remain2=new JLabel("Remaining");
		remain2.setFont(rigor);
		remain2.setForeground(Color.red);
		remain2.setBounds(1100,130,230,30);
		frame.add(remain2);
		
		rem3.setBackground(Color.black);
		rem3.setOpaque(true);
		rem3.setBounds(1100,170,280,130);
		rem3.addMouseListener(this);
		
		rem4.setBackground(Color.black);
		rem4.setOpaque(true);
		rem4.setBounds(1100,310,280,130);
		rem4.addMouseListener(this);
		
		rem5.setBackground(Color.black);
		rem5.setOpaque(true);
		rem5.setBounds(1100,450,280,130);
		rem5.addMouseListener(this);
		
		JLabel ab2=new JLabel("Leader Ability");
		ab2.setFont(rigor);
		ab2.setForeground(Color.blue);
		ab2.setBounds(1100,590,270,30);
		used2=new JLabel();
		ImageIcon icon1=new ImageIcon("check.png");
		used2.setIcon(icon1);
		used2.setBounds(1300,575,50,50);
		
			Champion a=game.getFirstPlayer().getTeam().get(0);
			Champion b=game.getFirstPlayer().getTeam().get(1);
			Champion c=game.getFirstPlayer().getTeam().get(2);
			if(a.getCurrentHP()>0) {
				if(a.equals(game.getFirstPlayer().getLeader())) {
					Border border=BorderFactory.createLineBorder(Color.red, 2);
					rem1.setBorder(border);
				}
				if(a.getName().equals("Thor")) {
					rem1.setIcon(new ImageIcon("thorat.png"));
						}
				if(a.getName().equals("Spiderman")) {
					rem1.setIcon(new ImageIcon("spiderat.png"));
							}
				if(a.getName().equals("Captain America")) {
					rem1.setIcon(new ImageIcon("capat.png"));
							}
				if(a.getName().equals("Deadpool")) {
					rem1.setIcon(new ImageIcon("deadat.png"));
							}
				if(a.getName().equals("Dr Strange")) {
					rem1.setIcon(new ImageIcon("strangeat.png"));
							}
				if(a.getName().equals("Electro")) {
					rem1.setIcon(new ImageIcon("elcat.png"));
							}
				if(a.getName().equals("Ghost Rider")) {
					rem1.setIcon(new ImageIcon("rideat.png"));
							}
				if(a.getName().equals("Hela")) {
					rem1.setIcon(new ImageIcon("helat.png"));
							}
				if(a.getName().equals("Hulk")) {
					rem1.setIcon(new ImageIcon("hulkat.png"));
							}
				if(a.getName().equals("Iceman")) {
					rem1.setIcon(new ImageIcon("iceat.png"));
							}
				if(a.getName().equals("Ironman")) {
					rem1.setIcon(new ImageIcon("ironat.png"));
							}
				if(a.getName().equals("Loki")) {
					rem1.setIcon(new ImageIcon("lokiat.png"));
							}
				if(a.getName().equals("Quicksilver")) {
					rem1.setIcon(new ImageIcon("quickat.png"));
							}
				if(a.getName().equals("Venom")) {
					rem1.setIcon(new ImageIcon("venomat.png"));
							}
				if(a.getName().equals("Yellow Jacket")) {
					rem1.setIcon(new ImageIcon("yelat.png"));
							}
					rem1.setText(a.getName());
			}
			if(b.getCurrentHP()>0) {
				if(b.equals(game.getFirstPlayer().getLeader())) {
					Border border=BorderFactory.createLineBorder(Color.red, 2);
					rem2.setBorder(border);
				}
				if(b.getName().equals("Thor")) {
					rem2.setIcon(new ImageIcon("thorat.png"));rem2.setText("Thor");
						}
				if(b.getName().equals("Spiderman")) {
					rem2.setIcon(new ImageIcon("spiderat.png"));rem2.setText("Spiderman");
							}
				if(b.getName().equals("Captain America")) {
					rem2.setIcon(new ImageIcon("capat.png"));rem2.setText("Captain America");
							}
				if(b.getName().equals("Deadpool")) {
					rem2.setIcon(new ImageIcon("deadat.png"));rem2.setText("Deadpool");
							}
				if(b.getName().equals("Dr Strange")) {
					rem2.setIcon(new ImageIcon("strangeat.png"));rem2.setText("Dr Strange");
							}
				if(b.getName().equals("Electro")) {
					rem2.setIcon(new ImageIcon("elcat.png"));rem2.setText("Electro");
							}
				if(b.getName().equals("Ghost Rider")) {
					rem2.setIcon(new ImageIcon("rideat.png"));rem2.setText("Ghost Rider");
							}
				if(b.getName().equals("Hela")) {
					rem2.setIcon(new ImageIcon("helat.png"));rem2.setText("Hela");
							}
				if(b.getName().equals("Hulk")) {
					rem2.setIcon(new ImageIcon("hulkat.png"));rem2.setText("Hulk");
							}
				if(b.getName().equals("Iceman")) {
					rem2.setIcon(new ImageIcon("iceat.png"));rem2.setText("Iceman");
							}
				if(b.getName().equals("Ironman")) {
					rem2.setIcon(new ImageIcon("ironat.png"));rem2.setText("Ironman");
							}
				if(b.getName().equals("Loki")) {
					rem2.setIcon(new ImageIcon("lokiat.png"));rem2.setText("Loki");
							}
				if(b.getName().equals("Quicksilver")) {
					rem2.setIcon(new ImageIcon("quickat.png"));rem2.setText("Quicksilver");
							}
				if(b.getName().equals("Venom")) {
					rem2.setIcon(new ImageIcon("venomat.png"));rem2.setText("Venom");
							}
				if(b.getName().equals("Yellow Jacket")) {
					rem2.setIcon(new ImageIcon("yelat.png"));rem2.setText("Yellow Jacket");
							}
				rem2.setText(b.getName());
		}
		
			if(c.getCurrentHP()>0) {
				if(c.equals(game.getFirstPlayer().getLeader())) {
					Border border=BorderFactory.createLineBorder(Color.red, 2);
					remx.setBorder(border);
				}
				if(c.getName().equals("Thor")) {
					remx.setIcon(new ImageIcon("thorat.png"));remx.setText("Thor");
						}
				if(c.getName().equals("Spiderman")) {
					remx.setIcon(new ImageIcon("spiderat.png"));remx.setText("Spiderman");
							}
				if(c.getName().equals("Captain America")) {
					remx.setIcon(new ImageIcon("capat.png"));remx.setText("Captain America");
							}
				if(c.getName().equals("Deadpool")) {
					remx.setIcon(new ImageIcon("deadat.png"));remx.setText("Deadpool");
							}
				if(c.getName().equals("Dr Strange")) {
					remx.setIcon(new ImageIcon("strangeat.png"));remx.setText("Dr Strange");
							}
				if(c.getName().equals("Electro")) {
					remx.setIcon(new ImageIcon("elcat.png"));remx.setText("Electro");
							}
				if(c.getName().equals("Ghost Rider")) {
					remx.setIcon(new ImageIcon("rideat.png"));remx.setText("Ghost Rider");
							}
				if(c.getName().equals("Hela")) {
					remx.setIcon(new ImageIcon("helat.png"));remx.setText("Hela");
							}
				if(c.getName().equals("Hulk")) {
					remx.setIcon(new ImageIcon("hulkat.png"));remx.setText("Hulk");
							}
				if(c.getName().equals("Iceman")) {
					remx.setIcon(new ImageIcon("iceat.png"));remx.setText("Iceman");
							}
				if(c.getName().equals("Ironman")) {
					remx.setIcon(new ImageIcon("ironat.png"));remx.setText("Ironman");
							}
				if(c.getName().equals("Loki")) {
					remx.setIcon(new ImageIcon("lokiat.png"));remx.setText("Loki");
							}
				if(c.getName().equals("Quicksilver")) {
					remx.setIcon(new ImageIcon("quickat.png"));remx.setText("Quicksilver");
							}
				if(c.getName().equals("Venom")) {
					remx.setIcon(new ImageIcon("venomat.png"));remx.setText("Venom");
							}
				if(c.getName().equals("Yellow Jacket")) {
					remx.setIcon(new ImageIcon("yelat.png"));remx.setText("Yellow Jacket");
							}
				remx.setText(c.getName());
		}
		
			 a=game.getSecondPlayer().getTeam().get(0);
			 b=game.getSecondPlayer().getTeam().get(1);
			 c=game.getSecondPlayer().getTeam().get(2);
			if(a.getCurrentHP()>0) {
				if(a.equals(game.getSecondPlayer().getLeader())) {
					Border border=BorderFactory.createLineBorder(Color.red, 2);
					rem3.setBorder(border);
				}
				if(a.getName().equals("Thor")) {
					rem3.setIcon(new ImageIcon("thorat.png"));rem3.setText("Thor");
						}
				if(a.getName().equals("Spiderman")) {
					rem3.setIcon(new ImageIcon("spiderat.png"));rem3.setText("Spiderman");
							}
				if(a.getName().equals("Captain America")) {
					rem3.setIcon(new ImageIcon("capat.png"));rem3.setText("Captain America");
							}
				if(a.getName().equals("Deadpool")) {
					rem3.setIcon(new ImageIcon("deadat.png"));rem3.setText("Deadpool");
							}
				if(a.getName().equals("Dr Strange")) {
					rem3.setIcon(new ImageIcon("strangeat.png"));rem3.setText("Dr Strange");
							}
				if(a.getName().equals("Electro")) {
					rem3.setIcon(new ImageIcon("elcat.png"));rem3.setText("Electro");
							}
				if(a.getName().equals("Ghost Rider")) {
					rem3.setIcon(new ImageIcon("rideat.png"));rem3.setText("Ghost Rider");
							}
				if(a.getName().equals("Hela")) {
					rem3.setIcon(new ImageIcon("helat.png"));rem3.setText("Hela");
							}
				if(a.getName().equals("Hulk")) {
					rem3.setIcon(new ImageIcon("hulkat.png"));rem3.setText("Hulk");
							}
				if(a.getName().equals("Iceman")) {
					rem3.setIcon(new ImageIcon("iceat.png"));rem3.setText("Iceman");
							}
				if(a.getName().equals("Ironman")) {
					rem3.setIcon(new ImageIcon("ironat.png"));rem3.setText("Ironman");
							}
				if(a.getName().equals("Loki")) {
					rem3.setIcon(new ImageIcon("lokiat.png"));rem3.setText("Loki");
							}
				if(a.getName().equals("Quicksilver")) {
					rem3.setIcon(new ImageIcon("quickat.png"));rem3.setText("Quicksilver");
							}
				if(a.getName().equals("Venom")) {
					rem3.setIcon(new ImageIcon("venomat.png"));rem3.setText("Venom");
							}
				if(a.getName().equals("Yellow Jacket")) {
					rem3.setIcon(new ImageIcon("yelat.png"));rem3.setText("Yellow Jacket");
							}
					rem3.setText(a.getName());
			}
			if(b.getCurrentHP()>0) {
				if(b.equals(game.getSecondPlayer().getLeader())) {
					Border border=BorderFactory.createLineBorder(Color.red, 2);
				}
				if(b.getName().equals("Thor")) {
					rem4.setIcon(new ImageIcon("thorat.png"));rem4.setText("Thor");
						}
				if(b.getName().equals("Spiderman")) {
					rem4.setIcon(new ImageIcon("spiderat.png"));rem4.setText("Spiderman");
							}
				if(b.getName().equals("Captain America")) {
					rem4.setIcon(new ImageIcon("capat.png"));rem4.setText("Captain America");
							}
				if(b.getName().equals("Deadpool")) {
					rem4.setIcon(new ImageIcon("deadat.png"));rem4.setText("Deadpool");
							}
				if(b.getName().equals("Dr Strange")) {
					rem4.setIcon(new ImageIcon("strangeat.png"));rem4.setText("Dr Strange");
							}
				if(b.getName().equals("Electro")) {
					rem4.setIcon(new ImageIcon("elcat.png"));rem4.setText("Electro");
							}
				if(b.getName().equals("Ghost Rider")) {
					rem4.setIcon(new ImageIcon("rideat.png"));rem4.setText("Ghost Rider");
							}
				if(b.getName().equals("Hela")) {
					rem4.setIcon(new ImageIcon("helat.png"));rem4.setText("Hela");
							}
				if(b.getName().equals("Hulk")) {
					rem4.setIcon(new ImageIcon("hulkat.png"));rem4.setText("Hulk");
							}
				if(b.getName().equals("Iceman")) {
					rem4.setIcon(new ImageIcon("iceat.png"));rem4.setText("Iceman");
							}
				if(b.getName().equals("Ironman")) {
					rem4.setIcon(new ImageIcon("ironat.png"));rem4.setText("Ironman");
							}
				if(b.getName().equals("Loki")) {
					rem4.setIcon(new ImageIcon("lokiat.png"));rem4.setText("Loki");
							}
				if(b.getName().equals("Quicksilver")) {
					rem4.setIcon(new ImageIcon("quickat.png"));rem4.setText("Quicksilver");
							}
				if(b.getName().equals("Venom")) {
					rem4.setIcon(new ImageIcon("venomat.png"));rem4.setText("Venom");
							}
				if(b.getName().equals("Yellow Jacket")) {
					rem4.setIcon(new ImageIcon("yelat.png"));rem4.setText("Yellow Jacket");
							}
				rem4.setText(b.getName());
		}
		
			if(c.getCurrentHP()>0) {
				if(c.equals(game.getSecondPlayer().getLeader())) {
					Border border=BorderFactory.createLineBorder(Color.red, 2);
					rem5.setBorder(border);
				}
				if(c.getName().equals("Thor")) {
					rem5.setIcon(new ImageIcon("thorat.png"));rem5.setText("Thor");
						}
				if(c.getName().equals("Spiderman")) {
					rem5.setIcon(new ImageIcon("spiderat.png"));rem5.setText("Spiderman");
							}
				if(c.getName().equals("Captain America")) {
					rem5.setIcon(new ImageIcon("capat.png"));rem5.setText("Captain America");
							}
				if(c.getName().equals("Deadpool")) {
					rem5.setIcon(new ImageIcon("deadat.png"));rem5.setText("Deadpool");
							}
				if(c.getName().equals("Dr Strange")) {
					rem5.setIcon(new ImageIcon("strangeat.png"));rem5.setText("Dr Strange");
							}
				if(c.getName().equals("Electro")) {
					rem5.setIcon(new ImageIcon("elcat.png"));rem5.setText("Electro");
							}
				if(c.getName().equals("Ghost Rider")) {
					rem5.setIcon(new ImageIcon("rideat.png"));rem5.setText("Ghost Rider");
							}
				if(c.getName().equals("Hela")) {
					rem5.setIcon(new ImageIcon("helat.png"));rem5.setText("Hela");
							}
				if(c.getName().equals("Hulk")) {
					rem5.setIcon(new ImageIcon("hulkat.png"));rem5.setText("Hulk");
							}
				if(c.getName().equals("Iceman")) {
					rem5.setIcon(new ImageIcon("iceat.png"));rem5.setText("Iceman");
							}
				if(c.getName().equals("Ironman")) {
					rem5.setIcon(new ImageIcon("ironat.png"));rem5.setText("Ironman");
							}
				if(c.getName().equals("Loki")) {
					rem5.setIcon(new ImageIcon("lokiat.png"));rem5.setText("Loki");
							}
				if(c.getName().equals("Quicksilver")) {
					rem5.setIcon(new ImageIcon("quickat.png"));rem5.setText("Quicksilver");
							}
				if(c.getName().equals("Venom")) {
					rem5.setIcon(new ImageIcon("venomat.png"));rem5.setText("Venom");
							}
				if(c.getName().equals("Yellow Jacket")) {
					rem5.setIcon(new ImageIcon("yelat.png"));rem5.setText("Yellow Jacket");
							}
				rem5.setText(c.getName());
		}
		
			names[0]=rem1.getText();
			names[1]=rem2.getText();
			names[2]=remx.getText();
			names[3]=rem3.getText();
			names[4]=rem4.getText();
			names[5]=rem5.getText();
			
			
			
			
		JLabel currently=new JLabel("Currently");
		currently.setFont(new Font("Ariel",Font.BOLD,20));
		currently.setForeground(Color.white);
		currently.setBounds(20,485,130,30);
		c1=new JPanel();
		Border border=BorderFactory.createLineBorder(Color.red, 3);
		c1.setBorder(border);
		c1.setBounds(490,515,450,100);
		c1.setBackground(Color.black);
		c1.setOpaque(true);
		c1.setLayout(null);
		
		int j=0;
		for(int i=0;i<450;i=i+90) {
				JLabel label=new JLabel();
				label.setBounds(492+i,55,85,85);
				label.setBackground(Color.white);
				label.setForeground(Color.black);
				label.setOpaque(true);
				frame.add(label);
				cells[4][j]=label;
				if(cells[4][j].getText().equals(game.getCurrentChampion().getName())) {
					Border border1=BorderFactory.createLineBorder(Color.red, 1);
					cells[4][j].setBorder(border1);
				}
				j++;
		}
		j=0;
		for(int i=0;i<450;i=i+90) {
			JLabel label=new JLabel();
			label.setBounds(492+i,145,85,85);
			label.setBackground(Color.white);
			label.setOpaque(true);
			frame.add(label);
			cells[3][j]=label;
			if(cells[3][j].getText().equals(game.getCurrentChampion().getName())) {
				Border border1=BorderFactory.createLineBorder(Color.red, 1);
				cells[3][j].setBorder(border1);
			}
			j++;
	}
		j=0;
		for(int i=0;i<450;i=i+90) {
			JLabel label=new JLabel();
			label.setBounds(492+i,235,85,85);
			label.setBackground(Color.white);
			label.setOpaque(true);
			frame.add(label);	
			cells[2][j]=label;
			if(cells[2][j].getText().equals(game.getCurrentChampion().getName())) {
				Border border1=BorderFactory.createLineBorder(Color.red, 1);
				cells[2][j].setBorder(border1);
			}
			j++;
	}
		j=0;
		for(int i=0;i<450;i=i+90) {
			JLabel label=new JLabel();
			label.setBounds(492+i,325,85,85);
			label.setBackground(Color.white);
			label.setOpaque(true);
			frame.add(label);
			cells[1][j]=label;
			if(cells[1][j].getText().equals(game.getCurrentChampion().getName())) {
				Border border1=BorderFactory.createLineBorder(Color.red, 1);
				cells[1][j].setBorder(border1);
			}
			j++;
	}
		j=0;
		for(int i=0;i<450;i=i+90) {
			JLabel label=new JLabel();
			label.setBounds(492+i,415,85,85);
			label.setBackground(Color.white);
			label.setOpaque(true);
			frame.add(label);
			cells[0][j]=label;
			
			j++;
	}
		for(int x=0;x<5;x++) {
			for(int y=0;y<5;y++) {
				if(game.getBoard()[x][y] instanceof Champion) {
					if(((Champion) game.getBoard()[x][y]).getName().equals("Thor")) {
						cells[x][y].setIcon(new ImageIcon("thorsmall.png"));
						
							}
					if(((Champion) game.getBoard()[x][y]).getName().equals("Spiderman")) {
						cells[x][y].setIcon(new ImageIcon("spidermansmall.png"));
					
								}
					if(((Champion) game.getBoard()[x][y]).getName().equals("Captain America")) {
						cells[x][y].setIcon(new ImageIcon("captain-americasmall.png"));
				
								}
					if(((Champion) game.getBoard()[x][y]).getName().equals("Deadpool")) {
						cells[x][y].setIcon(new ImageIcon("deadpoolsmall.png"));
								}
					if(((Champion) game.getBoard()[x][y]).getName().equals("Dr Strange")) {
						cells[x][y].setIcon(new ImageIcon("drstrangesmall.png"));
								}
					if(((Champion) game.getBoard()[x][y]).getName().equals("Electro")) {
						cells[x][y].setIcon(new ImageIcon("electrosmall.png"));
								}
					if(((Champion) game.getBoard()[x][y]).getName().equals("Ghost Rider")) {
						cells[x][y].setIcon(new ImageIcon("ghostridersmall.png"));
								}
					if(((Champion) game.getBoard()[x][y]).getName().equals("Hela")) {
						cells[x][y].setIcon(new ImageIcon("helasmall.png"));
								}
					if(((Champion) game.getBoard()[x][y]).getName().equals("Hulk")) {
						cells[x][y].setIcon(new ImageIcon("hulksmall.png"));
								}
					if(((Champion) game.getBoard()[x][y]).getName().equals("Iceman")) {
						cells[x][y].setIcon(new ImageIcon("icemansmall.png"));
								}
					if(((Champion) game.getBoard()[x][y]).getName().equals("Ironman")) {
						cells[x][y].setIcon(new ImageIcon("ironmansmall.png"));
								}
					if(((Champion) game.getBoard()[x][y]).getName().equals("Loki")) {
						cells[x][y].setIcon(new ImageIcon("lokismall.png"));
								}
					if(((Champion) game.getBoard()[x][y]).getName().equals("Quicksilver")) {
						cells[x][y].setIcon(new ImageIcon("quicksilversmall.png"));
								}
					if(((Champion) game.getBoard()[x][y]).getName().equals("Venom")) {
						cells[x][y].setIcon(new ImageIcon("venomsmall.png"));
								}
					if(((Champion) game.getBoard()[x][y]).getName().equals("Yellow Jacket")) {
						cells[x][y].setIcon(new ImageIcon("yellow jacketsmall.png"));
								}
					
				}
				if(game.getBoard()[x][y] instanceof Cover) {
					cells[x][y].setIcon(new ImageIcon("cover.png"));
					cells[x][y].setText(""+((Cover) game.getBoard()[x][y]).getCurrentHP());
					cells[x][y].setHorizontalTextPosition(JLabel.CENTER);
				}
			}
		}
		
		Border b1=BorderFactory.createLineBorder(Color.red, 2);
		cells[game.getCurrentChampion().getLocation().x][game.getCurrentChampion().getLocation().y].setBorder(b1);
		
	
		PriorityQueue q=new PriorityQueue(game.getTurnOrder().size());
		PriorityQueue tO=game.getTurnOrder();
		int g=0;
		while(!tO.isEmpty()) {
			JLabel label1=new JLabel();
				if(((Champion) tO.peekMin()).getName().equals("Thor")) {
					label1.setIcon(new ImageIcon("thorsmall.png"));
					//label1.setText("Thor");
					 
						}
				if(((Champion) tO.peekMin()).getName().equals("Spiderman")) {
					label1.setIcon(new ImageIcon("spidermansmall.png"));
					//label1.setText("Spiderman");
					 
							}
				if(((Champion) tO.peekMin()).getName().equals("Captain America")) {
					label1.setIcon(new ImageIcon("captain-americasmall.png"));
				//	label1.setText("Captain America");
					
							}
				if(((Champion) tO.peekMin()).getName().equals("Deadpool")) {
					label1.setIcon(new ImageIcon("deadpoolsmall.png"));
					//label1.setText("Deadpool");
							}
				if(((Champion) tO.peekMin()).getName().equals("Dr Strange")) {
					label1.setIcon(new ImageIcon("drstrangesmall.png"));
					//label1.setText("Dr Strange");
							}
				if(((Champion) tO.peekMin()).getName().equals("Electro")) {
					label1.setIcon(new ImageIcon("electrosmall.png"));
					//label1.setText("Electro");
							}
				if(((Champion) tO.peekMin()).getName().equals("Ghost Rider")) {
					label1.setIcon(new ImageIcon("ghostridersmall.png"));
					//label1.setText("Ghost Rider");
							}
				if(((Champion) tO.peekMin()).getName().equals("Hela")) {
					label1.setIcon(new ImageIcon("helasmall.png"));
					//label1.setText("Hela");
							}
				if(((Champion) tO.peekMin()).getName().equals("Hulk")) {
					label1.setIcon(new ImageIcon("hulksmall.png"));
					//label1.setText("Hulk");
							}
				if(((Champion) tO.peekMin()).getName().equals("Iceman")) {
					label1.setIcon(new ImageIcon("icemansmall.png"));
					//label1.setText("Iceman");
							}
				if(((Champion) tO.peekMin()).getName().equals("Ironman")) {
					label1.setIcon(new ImageIcon("ironmansmall.png"));
					//label1.setText("Ironman");
							}
				if(((Champion) tO.peekMin()).getName().equals("Loki")) {
					label1.setIcon(new ImageIcon("lokismall.png"));
					//label1.setText("Loki");
							}
				if(((Champion) tO.peekMin()).getName().equals("Quicksilver")) {
					label1.setIcon(new ImageIcon("quicksilversmall.png"));
					//label1.setText("Quicksilver");
							}
				if(((Champion) tO.peekMin()).getName().equals("Venom")) {
					label1.setIcon(new ImageIcon("venomsmall.png"));
					//label1.setText("Venom");
							}
				if(((Champion) tO.peekMin()).getName().equals("Yellow Jacket")) {
					label1.setIcon(new ImageIcon("yellow jacketsmall.png"));
					//label1.setText("Yellow Jacket");
							}
				label1.setBounds(465+g,700,80,80);
				g+=90;
				frame.add(label1);
				q.insert(tO.remove());
		
			}
		while(!q.isEmpty()) {
			tO.insert(q.remove());
		}
		
		
		
		for(int i=0;i<5;i++) {
			for(int o=0;o<5;o++) {
				if(game.getBoard()[i][o] instanceof Champion && game.getSecondPlayer().getTeam().contains(game.getBoard()[i][o])) {
					cells[i][o].setBackground(Color.lightGray);
				}
				if(game.getBoard()[i][o] instanceof Champion && game.getFirstPlayer().getTeam().contains(game.getBoard()[i][o])) {
					cells[i][o].setBackground(Color.blue);
				}
				if(game.getBoard()[i][o]==null) {
					cells[i][o].setBackground(Color.white);
				}
			}
		}
		
		
		
	
		
		JLabel turn=new JLabel("Turn Order");
		turn.setFont(new Font("Arial",Font.BOLD,15));
		turn.setBounds(675,675,100,30);
		turn.setForeground(Color.yellow);
		turn.setBackground(Color.black);
		turn.setOpaque(true);
		
		frame.add(turn);
		frame.add(move);
		frame.add(attack);
		//frame.add(cast);
		frame.add(cast1);
		frame.add(endTurn);
		frame.add(useLeaderAbility);
		frame.add(down);
		frame.add(right);
		frame.add(left);
		frame.add(up);
		//==frame.add(currently);
		frame.add(rem5);
		frame.add(remx);
		frame.add(used2);
		frame.add(ab2);
		frame.add(used);
		frame.add(ab1);
		frame.add(remain1);
		frame.add(rem1);
		frame.add(rem2);
		frame.add(remain2);
		frame.add(rem3);
		frame.add(rem4);
		frame.add(name2);
		frame.add(plr2);
		frame.add(plr1);
		frame.add(name1);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		frame.pack();
		frame.setSize(1435,830);
		frame.setResizable(false);
		frame.getContentPane().setBackground(Color.black);
		frame.setTitle("Marvel Ultimate War");
		frame.setVisible(true);
		
	
		}
	
	public static void main(String[] args) {
		new GameBoard(new Game(new Player("ahmed"),new Player("amgad")));
	//	String[] d= {"Left","Right","Down","Up"};
	//	int s=JOptionPane.showOptionDialog(null, "Choose direction of Cast","Cast Ability", 0, JOptionPane.PLAIN_MESSAGE, null, d, 0);
		
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		//lab.setLocation(null);
		//lab.setSize(null);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if(e.getSource()==rem1 && r1) {
			rem1.setIcon(null);
			if(ch1.getCurrentHP()>0) {
			Champion c=ch1;
			String q="<html>";
			for(int i=0;i<c.getAppliedEffects().size();i++) {
				Effect l=c.getAppliedEffects().get(i);
				q+="Effect: "+l.getName()+" Duration: "+l.getDuration()+"<br>";
			}
			String typess="";
			if(c instanceof Hero) {
				typess="Type: Hero"+" Current HP: "+c.getCurrentHP();
			}
			if(c instanceof AntiHero) {
				typess="Type: AntiHero"+" Current HP: "+c.getCurrentHP();
			}
			if(c instanceof Villain) {
				typess="Type: Villain"+" Current HP: "+c.getCurrentHP();
			}
			rem1.setText(q+typess);
			rem1.setFont(new Font("Arial",Font.PLAIN,8));
			rem1.setForeground(Color.red);
			}
			
		}
		if(e.getSource()==rem2 && r2) {
			rem2.setIcon(null);
			if(ch2.getCurrentHP()>0) {
				Champion c=ch2;
			
			String q="<html>";
			for(int i=0;i<c.getAppliedEffects().size();i++) {
				Effect l=c.getAppliedEffects().get(i);
				q+="Effect: "+l.getName()+" Duration: "+l.getDuration()+"<br>";
			}
			String typess="";
			if(c instanceof Hero) {
				typess="Type: Hero"+" Current HP: "+c.getCurrentHP();
			}
			if(c instanceof AntiHero) {
				typess="Type: AntiHero"+" Current HP: "+c.getCurrentHP();
			}
			if(c instanceof Villain) {
				typess="Type: Villain"+" Current HP: "+c.getCurrentHP();
			}
			rem2.setText(q+typess);
			rem2.setFont(new Font("Arial",Font.PLAIN,8));
			rem2.setForeground(Color.red);
			}
		}
		if(e.getSource()==remx && rx) {
			remx.setIcon(null);
			if(chx.getCurrentHP()>0) {
			Champion c=chx;
			String q="<html>";
			for(int i=0;i<c.getAppliedEffects().size();i++) {
				Effect l=c.getAppliedEffects().get(i);
				q+="Effect: "+l.getName()+" Duration: "+l.getDuration()+"<br>";
			}
			String typess="";
			if(c instanceof Hero) {
				typess="Type: Hero"+" Current HP: "+c.getCurrentHP();
			}
			if(c instanceof AntiHero) {
				typess="Type: AntiHero"+" Current HP: "+c.getCurrentHP();
			}
			if(c instanceof Villain) {
				typess="Type: Villain"+" Current HP: "+c.getCurrentHP();
			}
			remx.setText(q+typess);
			remx.setFont(new Font("Arial",Font.PLAIN,8));
			remx.setForeground(Color.red);
			}
			
		}
		if(e.getSource()==rem3 && r3) {
			rem3.setIcon(null);
			if(ch3.getCurrentHP()>0) {
			Champion c=ch3;
			String q="<html>";
			for(int i=0;i<c.getAppliedEffects().size();i++) {
				Effect l=c.getAppliedEffects().get(i);
				q+="Effect: "+l.getName()+" Duration: "+l.getDuration()+"<br>";
			}
			String typess="";
			if(c instanceof Hero) {
				typess="Type: Hero"+" Current HP: "+c.getCurrentHP();
			}
			if(c instanceof AntiHero) {
				typess="Type: AntiHero"+" Current HP: "+c.getCurrentHP();
			}
			if(c instanceof Villain) {
				typess="Type: Villain"+" Current HP: "+c.getCurrentHP();
			}
			rem3.setText(q+typess);
			rem3.setFont(new Font("Arial",Font.PLAIN,8));
			rem3.setForeground(Color.red);
			}
			
		}
		if(e.getSource()==rem4 && r4) {
			rem4.setIcon(null);
			if(ch4.getCurrentHP()>0) {
			Champion c=ch4;
			String q="<html>";
			for(int i=0;i<c.getAppliedEffects().size();i++) {
				Effect l=c.getAppliedEffects().get(i);
				q+="Effect: "+l.getName()+" Duration: "+l.getDuration()+"<br>";
			}
			String typess="";
			if(c instanceof Hero) {
				typess="Type: Hero"+" Current HP: "+c.getCurrentHP();
			}
			if(c instanceof AntiHero) {
				typess="Type: AntiHero"+" Current HP: "+c.getCurrentHP();
			}
			if(c instanceof Villain) {
				typess="Type: Villain"+" Current HP: "+c.getCurrentHP();
			}
			rem4.setText(q+typess);
			rem4.setFont(new Font("Arial",Font.PLAIN,8));
			rem4.setForeground(Color.red);
			}
			
		}
		if(e.getSource()==rem5 && r5) {
			rem5.setIcon(null);
			if(ch5.getCurrentHP()>0) {
			Champion c=ch5;
			String q="<html>";
			for(int i=0;i<c.getAppliedEffects().size();i++) {
				Effect l=c.getAppliedEffects().get(i);
				q+="Effect: "+l.getName()+" Duration: "+l.getDuration()+"<br>";
			}
			String typess="";
			if(c instanceof Hero) {
				typess="Type: Hero"+" Current HP: "+c.getCurrentHP();
			}
			if(c instanceof AntiHero) {
				typess="Type: AntiHero"+" Current HP: "+c.getCurrentHP();
			}
			if(c instanceof Villain) {
				typess="Type: Villain"+" Current HP: "+c.getCurrentHP();
			}
			rem5.setText(q+typess);
			rem5.setFont(new Font("Arial",Font.PLAIN,8));
			rem5.setForeground(Color.red);
			}
			
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==rem1 && r1) {
			if(ch1.getCurrentHP()>0) {
			Champion a=ch1;
			if(a.getCurrentHP()>0) {
				if(a.equals(game.getFirstPlayer().getLeader())) {
					Border border=BorderFactory.createLineBorder(Color.red, 2);
					rem1.setBorder(border);
				}
				if(a.getName().equals("Thor")) {
					rem1.setIcon(new ImageIcon("thorat.png"));
						}
				if(a.getName().equals("Spiderman")) {
					rem1.setIcon(new ImageIcon("spiderat.png"));
							}
				if(a.getName().equals("Captain America")) {
					rem1.setIcon(new ImageIcon("capat.png"));
							}
				if(a.getName().equals("Deadpool")) {
					rem1.setIcon(new ImageIcon("deadat.png"));
							}
				if(a.getName().equals("Dr Strange")) {
					rem1.setIcon(new ImageIcon("strangeat.png"));
							}
				if(a.getName().equals("Electro")) {
					rem1.setIcon(new ImageIcon("elcat.png"));
							}
				if(a.getName().equals("Ghost Rider")) {
					rem1.setIcon(new ImageIcon("rideat.png"));
							}
				if(a.getName().equals("Hela")) {
					rem1.setIcon(new ImageIcon("helat.png"));
							}
				if(a.getName().equals("Hulk")) {
					rem1.setIcon(new ImageIcon("hulkat.png"));
							}
				if(a.getName().equals("Iceman")) {
					rem1.setIcon(new ImageIcon("iceat.png"));
							}
				if(a.getName().equals("Ironman")) {
					rem1.setIcon(new ImageIcon("ironat.png"));
							}
				if(a.getName().equals("Loki")) {
					rem1.setIcon(new ImageIcon("lokiat.png"));
							}
				if(a.getName().equals("Quicksilver")) {
					rem1.setIcon(new ImageIcon("quickat.png"));
							}
				if(a.getName().equals("Venom")) {
					rem1.setIcon(new ImageIcon("venomat.png"));
							}
				if(a.getName().equals("Yellow Jacket")) {
					rem1.setIcon(new ImageIcon("yelat.png"));
							}
					rem1.setText(a.getName());
			}
			}
		}
		if(e.getSource()==rem2 && r2) {
			if(ch2.getCurrentHP()>0) {
			Champion b=ch2;
			if(b.getCurrentHP()>0) {
				if(b.equals(game.getFirstPlayer().getLeader())) {
					Border border=BorderFactory.createLineBorder(Color.red, 2);
					rem2.setBorder(border);
				}
				if(b.getName().equals("Thor")) {
					rem2.setIcon(new ImageIcon("thorat.png"));
						}
				if(b.getName().equals("Spiderman")) {
					rem2.setIcon(new ImageIcon("spiderat.png"));
							}
				if(b.getName().equals("Captain America")) {
					rem2.setIcon(new ImageIcon("capat.png"));
							}
				if(b.getName().equals("Deadpool")) {
					rem2.setIcon(new ImageIcon("deadat.png"));
							}
				if(b.getName().equals("Dr Strange")) {
					rem2.setIcon(new ImageIcon("strangeat.png"));
							}
				if(b.getName().equals("Electro")) {
					rem2.setIcon(new ImageIcon("elcat.png"));
							}
				if(b.getName().equals("Ghost Rider")) {
					rem2.setIcon(new ImageIcon("rideat.png"));
							}
				if(b.getName().equals("Hela")) {
					rem2.setIcon(new ImageIcon("helat.png"));
							}
				if(b.getName().equals("Hulk")) {
					rem2.setIcon(new ImageIcon("hulkat.png"));
							}
				if(b.getName().equals("Iceman")) {
					rem2.setIcon(new ImageIcon("iceat.png"));
							}
				if(b.getName().equals("Ironman")) {
					rem2.setIcon(new ImageIcon("ironat.png"));
							}
				if(b.getName().equals("Loki")) {
					rem2.setIcon(new ImageIcon("lokiat.png"));
							}
				if(b.getName().equals("Quicksilver")) {
					rem2.setIcon(new ImageIcon("quickat.png"));
							}
				if(b.getName().equals("Venom")) {
					rem2.setIcon(new ImageIcon("venomat.png"));
							}
				if(b.getName().equals("Yellow Jacket")) {
					rem2.setIcon(new ImageIcon("yelat.png"));
							}
				rem2.setText(b.getName());
					
			}
			}
		}
		if(e.getSource()==remx && rx) {
			if(chx.getCurrentHP()>0){
			Champion c=chx;
			if(c.getCurrentHP()>0) {
				if(c.equals(game.getFirstPlayer().getLeader())) {
					Border border=BorderFactory.createLineBorder(Color.red, 2);
					remx.setBorder(border);
				}
				if(c.getName().equals("Thor")) {
					remx.setIcon(new ImageIcon("thorat.png"));
						}
				if(c.getName().equals("Spiderman")) {
					remx.setIcon(new ImageIcon("spiderat.png"));
							}
				if(c.getName().equals("Captain America")) {
					remx.setIcon(new ImageIcon("capat.png"));
							}
				if(c.getName().equals("Deadpool")) {
					remx.setIcon(new ImageIcon("deadat.png"));
							}
				if(c.getName().equals("Dr Strange")) {
					remx.setIcon(new ImageIcon("strangeat.png"));
							}
				if(c.getName().equals("Electro")) {
					remx.setIcon(new ImageIcon("elcat.png"));
							}
				if(c.getName().equals("Ghost Rider")) {
					remx.setIcon(new ImageIcon("rideat.png"));
							}
				if(c.getName().equals("Hela")) {
					remx.setIcon(new ImageIcon("helat.png"));
							}
				if(c.getName().equals("Hulk")) {
					remx.setIcon(new ImageIcon("hulkat.png"));
							}
				if(c.getName().equals("Iceman")) {
					remx.setIcon(new ImageIcon("iceat.png"));
							}
				if(c.getName().equals("Ironman")) {
					remx.setIcon(new ImageIcon("ironat.png"));
							}
				if(c.getName().equals("Loki")) {
					remx.setIcon(new ImageIcon("lokiat.png"));
							}
				if(c.getName().equals("Quicksilver")) {
					remx.setIcon(new ImageIcon("quickat.png"));
							}
				if(c.getName().equals("Venom")) {
					remx.setIcon(new ImageIcon("venomat.png"));
							}
				if(c.getName().equals("Yellow Jacket")) {
					remx.setIcon(new ImageIcon("yelat.png"));
							}
				remx.setText(c.getName());
					
			}
			}
		}
		if(e.getSource()==rem3 && r3) {
			if(ch3.getCurrentHP()>0) {
			Champion a=ch3;
			if(a.getCurrentHP()>0) {
				if(a.equals(game.getSecondPlayer().getLeader())) {
					Border border=BorderFactory.createLineBorder(Color.red, 2);
					rem3.setBorder(border);
				}
				if(a.getName().equals("Thor")) {
					rem3.setIcon(new ImageIcon("thorat.png"));
						}
				if(a.getName().equals("Spiderman")) {
					rem3.setIcon(new ImageIcon("spiderat.png"));
							}
				if(a.getName().equals("Captain America")) {
					rem3.setIcon(new ImageIcon("capat.png"));
							}
				if(a.getName().equals("Deadpool")) {
					rem3.setIcon(new ImageIcon("deadat.png"));
							}
				if(a.getName().equals("Dr Strange")) {
					rem3.setIcon(new ImageIcon("strangeat.png"));
							}
				if(a.getName().equals("Electro")) {
					rem3.setIcon(new ImageIcon("elcat.png"));
							}
				if(a.getName().equals("Ghost Rider")) {
					rem3.setIcon(new ImageIcon("rideat.png"));
							}
				if(a.getName().equals("Hela")) {
					rem3.setIcon(new ImageIcon("helat.png"));
							}
				if(a.getName().equals("Hulk")) {
					rem3.setIcon(new ImageIcon("hulkat.png"));
							}
				if(a.getName().equals("Iceman")) {
					rem3.setIcon(new ImageIcon("iceat.png"));
							}
				if(a.getName().equals("Ironman")) {
					rem3.setIcon(new ImageIcon("ironat.png"));
							}
				if(a.getName().equals("Loki")) {
					rem3.setIcon(new ImageIcon("lokiat.png"));
							}
				if(a.getName().equals("Quicksilver")) {
					rem3.setIcon(new ImageIcon("quickat.png"));
							}
				if(a.getName().equals("Venom")) {
					rem3.setIcon(new ImageIcon("venomat.png"));
							}
				if(a.getName().equals("Yellow Jacket")) {
					rem3.setIcon(new ImageIcon("yelat.png"));
							}
				rem3.setText(a.getName());
					
			}
			}
		}
		if(e.getSource()==rem4 && r4) {
			if(ch4.getCurrentHP()>0) {
			Champion b=ch4;
			if(b.getCurrentHP()>0) {
				if(b.equals(game.getSecondPlayer().getLeader())) {
					Border border=BorderFactory.createLineBorder(Color.red, 2);
					rem4.setBorder(border);
				}
				if(b.getName().equals("Thor")) {
					rem4.setIcon(new ImageIcon("thorat.png"));
						}
				if(b.getName().equals("Spiderman")) {
					rem4.setIcon(new ImageIcon("spiderat.png"));
							}
				if(b.getName().equals("Captain America")) {
					rem4.setIcon(new ImageIcon("capat.png"));
							}
				if(b.getName().equals("Deadpool")) {
					rem4.setIcon(new ImageIcon("deadat.png"));
							}
				if(b.getName().equals("Dr Strange")) {
					rem4.setIcon(new ImageIcon("strangeat.png"));
							}
				if(b.getName().equals("Electro")) {
					rem4.setIcon(new ImageIcon("elcat.png"));
							}
				if(b.getName().equals("Ghost Rider")) {
					rem4.setIcon(new ImageIcon("rideat.png"));
							}
				if(b.getName().equals("Hela")) {
					rem4.setIcon(new ImageIcon("helat.png"));
							}
				if(b.getName().equals("Hulk")) {
					rem4.setIcon(new ImageIcon("hulkat.png"));
							}
				if(b.getName().equals("Iceman")) {
					rem4.setIcon(new ImageIcon("iceat.png"));
							}
				if(b.getName().equals("Ironman")) {
					rem4.setIcon(new ImageIcon("ironat.png"));
							}
				if(b.getName().equals("Loki")) {
					rem4.setIcon(new ImageIcon("lokiat.png"));
							}
				if(b.getName().equals("Quicksilver")) {
					rem4.setIcon(new ImageIcon("quickat.png"));
							}
				if(b.getName().equals("Venom")) {
					rem4.setIcon(new ImageIcon("venomat.png"));
							}
				if(b.getName().equals("Yellow Jacket")) {
					rem4.setIcon(new ImageIcon("yelat.png"));
							}
				rem4.setText(b.getName());
					
			}
			}
		}
		if(e.getSource()==rem5 && r5) {
			if(ch5.getCurrentHP()>0) {
			Champion c=ch5;
			if(c.getCurrentHP()>0) {
				if(c.equals(game.getSecondPlayer().getLeader())) {
					Border border=BorderFactory.createLineBorder(Color.red, 2);
					rem5.setBorder(border);
				}
				if(c.getName().equals("Thor")) {
					rem5.setIcon(new ImageIcon("thorat.png"));
						}
				if(c.getName().equals("Spiderman")) {
					rem5.setIcon(new ImageIcon("spiderat.png"));
							}
				if(c.getName().equals("Captain America")) {
					rem5.setIcon(new ImageIcon("capat.png"));
							}
				if(c.getName().equals("Deadpool")) {
					rem5.setIcon(new ImageIcon("deadat.png"));
							}
				if(c.getName().equals("Dr Strange")) {
					rem5.setIcon(new ImageIcon("strangeat.png"));
							}
				if(c.getName().equals("Electro")) {
					rem5.setIcon(new ImageIcon("elcat.png"));
							}
				if(c.getName().equals("Ghost Rider")) {
					rem5.setIcon(new ImageIcon("rideat.png"));
							}
				if(c.getName().equals("Hela")) {
					rem5.setIcon(new ImageIcon("helat.png"));
							}
				if(c.getName().equals("Hulk")) {
					rem5.setIcon(new ImageIcon("hulkat.png"));
							}
				if(c.getName().equals("Iceman")) {
					rem5.setIcon(new ImageIcon("iceat.png"));
							}
				if(c.getName().equals("Ironman")) {
					rem5.setIcon(new ImageIcon("ironat.png"));
							}
				if(c.getName().equals("Loki")) {
					rem5.setIcon(new ImageIcon("lokiat.png"));
							}
				if(c.getName().equals("Quicksilver")) {
					rem5.setIcon(new ImageIcon("quickat.png"));
							}
				if(c.getName().equals("Venom")) {
					rem5.setIcon(new ImageIcon("venomat.png"));
							}
				if(c.getName().equals("Yellow Jacket")) {
					rem5.setIcon(new ImageIcon("yelat.png"));
							}
					rem5.setText(c.getName());
			}
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//--------------start of method-----------------
		if(e.getSource()==move) {
			movebool=true;
			attackbool=false;
			castbool=false;
			endbool=false;
			leaderbool=false;
		}
		if(e.getSource()==attack) {
			movebool=false;
			attackbool=true;
			castbool=false;
			endbool=false;
			leaderbool=false;
		}
		
		if(e.getSource()==endTurn) {
			movebool=false;
			attackbool=false;
			castbool=false;
			endbool=true;
			leaderbool=false;
			int x=game.getCurrentChampion().getLocation().x;
			int y=game.getCurrentChampion().getLocation().y;
			Border border1=BorderFactory.createLineBorder(Color.red, 2);
			cells[x][y].setBorder(null);
			game.endTurn();
			if(game.getFirstPlayer().getTeam().size()<=0) {
				JOptionPane.showMessageDialog(null,"Game Over - Player 2 WINS!","Exception",JOptionPane.PLAIN_MESSAGE);
				frame.dispose();
			}
			if(game.getSecondPlayer().getTeam().size()<=0) {
				JOptionPane.showMessageDialog(null,"Game Over - Player 1 WINS!","Exception",JOptionPane.PLAIN_MESSAGE);
				frame.dispose();
			}

			 x=game.getCurrentChampion().getLocation().x;
			 y=game.getCurrentChampion().getLocation().y;
			 border1=BorderFactory.createLineBorder(Color.red, 2);
			cells[x][y].setBorder(border1);
			
			
			info.removeAll();
			info.setBackground(Color.black);
			panel.setOpaque(true);
			JLabel text=new JLabel("Name: "+game.getCurrentChampion().getName());
			text.setBounds(10,10,180,30);
			text.setFont(new Font("Arial",Font.BOLD,20));
			text.setForeground(Color.yellow);
			
			String types="";
			if(game.getCurrentChampion() instanceof Hero) {
				types="Hero";
			}
			if(game.getCurrentChampion() instanceof AntiHero) {
				types="Anti Hero";
			}
			if(game.getCurrentChampion() instanceof Villain) {
				types="Villain";
			}
			
			JLabel text2=new JLabel("--Type: "+types);
			text2.setBounds(220,10,180,30);
			text2.setFont(new Font("Arial",Font.BOLD,20));
			text2.setForeground(Color.yellow);
			
			JLabel text3=new JLabel("HP: "+game.getCurrentChampion().getCurrentHP()+"     Mana: "+game.getCurrentChampion().getMana()+"      Actions: "+game.getCurrentChampion().getCurrentActionPoints());
			text3.setBounds(10,80,300,30);
			text3.setFont(new Font("Arial",Font.BOLD,20));
			text3.setForeground(Color.yellow);
			
			JLabel text4=new JLabel("Damage: "+game.getCurrentChampion().getAttackDamage()+"      Range: "+game.getCurrentChampion().getAttackRange());
			text4.setBounds(10,120,300,30);
			text4.setFont(new Font("Arial",Font.BOLD,20));
			text4.setForeground(Color.yellow);
			info.add(text);
			info.add(text2);
			info.add(text3);
			info.add(text4);
			frame.add(info);
		
			panel.removeAll();
			panel.setBackground(Color.black);
			panel.setOpaque(true);
			String[] abs=new String[game.getCurrentChampion().getAbilities().size()];
			
			for(int i=0;i<game.getCurrentChampion().getAbilities().size();i++) {
				Ability ab=game.getCurrentChampion().getAbilities().get(i);
				if(ab instanceof HealingAbility ) {
					abs[i]="Name: "+ab.getName()+" Type: Heal"+" Area of Effect: "+ab.getCastArea()+" Range: "+ab.getCastRange()+" Cost: "+ab.getManaCost()+
							" Reequired points: "+ab.getRequiredActionPoints()+" Base Cooldown: "+ab.getBaseCooldown()+" Current Cooldown: "+ab.getCurrentCooldown()+
							" Heal Amount: "+((HealingAbility) ab).getHealAmount();
				}
				if(ab instanceof DamagingAbility ) {
					abs[i]="Name: "+ab.getName()+" Type: Damage"+" Area of Effect: "+ab.getCastArea()+" Range: "+ab.getCastRange()+" Cost: "+ab.getManaCost()+
							" Reequired points: "+ab.getRequiredActionPoints()+" Base Cooldown: "+ab.getBaseCooldown()+" Current Cooldown: "+ab.getCurrentCooldown()+
							" Damage Amount: "+((DamagingAbility) ab).getDamageAmount();
				}
				if(ab instanceof CrowdControlAbility ) {
					abs[i]="Name: "+ab.getName()+" Type: Crowd Control"+" Area of Effect: "+ab.getCastArea()+" Range: "+ab.getCastRange()+" Cost: "+ab.getManaCost()+
							" Reequired points: "+ab.getRequiredActionPoints()+" Base Cooldown: "+ab.getBaseCooldown()+" Current Cooldown: "+ab.getCurrentCooldown()+
							" Effect "+((CrowdControlAbility) ab).getEffect().getName()+" Duration: "+((CrowdControlAbility) ab).getEffect().getDuration();
				}
			}
			int plus=0;
			for(int i=0;i<abs.length;i++) {
				JLabel label=new JLabel(abs[i]);
				label.setFont(new Font("Arial",Font.PLAIN,5));
				label.setBounds(10,10+plus,200,40);
				label.setForeground(Color.yellow);
				plus+=50;
				panel.add(label);
			}
			String[] effects=new String[game.getCurrentChampion().getAppliedEffects().size()];
			for(int i=0;i<effects.length;i++) {
				Effect f=game.getCurrentChampion().getAppliedEffects().get(i);
				effects[i]="Effect: "+f.getName()+" Duration: "+f.getDuration();
			}
			JLabel applied=new JLabel("Applied Effects");
			applied.setBounds(10,80,200,30);
			applied.setForeground(Color.yellow);
			panel.add(applied);
			plus=0;
			for(int i=0;i<effects.length;i++) {
				JLabel label=new JLabel(effects[i]);
				label.setFont(new Font("Arial",Font.PLAIN,10));
				label.setBounds(10,120+plus,200,40);
				label.setForeground(Color.yellow);
				plus+=50;
				panel.add(label);
			}
			frame.add(panel);
			for(int i=0;i<5;i++) {
				for(int j=0;j<5;j++) {
					if(game.getBoard()[i][j]==null) {
						cells[i][j].setIcon(null);
						cells[i][j].setBackground(Color.white);
						cells[i][j].setText(null);
						cells[i][j].setBorder(null);
					}
				}
					
			}
			
			
		}
		if(e.getSource()==useLeaderAbility) {
			movebool=false;
			attackbool=false;
			castbool=false;
			endbool=false;
			leaderbool=true;
			Champion champ=game.getCurrentChampion();
			
			try {
				if(game.getFirstPlayer().getTeam().contains(game.getCurrentChampion()) && game.getCurrentChampion().equals(game.getFirstPlayer().getLeader())) {
					used.setIcon(new ImageIcon("cross.png"));
				}
				if(game.getSecondPlayer().getTeam().contains(game.getCurrentChampion()) && game.getCurrentChampion().equals(game.getSecondPlayer().getLeader())) {
					used2.setIcon(new ImageIcon("cross.png"));
				}
				game.useLeaderAbility();
				if(game.getFirstPlayer().getTeam().size()<=0) {
					JOptionPane.showMessageDialog(null,"Game Over - Player 2 WINS!","Exception",JOptionPane.PLAIN_MESSAGE);
					frame.dispose();
				}
				if(game.getSecondPlayer().getTeam().size()<=0) {
					JOptionPane.showMessageDialog(null,"Game Over - Player 1 WINS!","Exception",JOptionPane.PLAIN_MESSAGE);
					frame.dispose();
				}

				
			} catch (LeaderNotCurrentException e1) {
				JOptionPane.showMessageDialog(null,"Current champion is not the leader of your team","Exception",JOptionPane.PLAIN_MESSAGE);
			} catch (LeaderAbilityAlreadyUsedException e1) {
				JOptionPane.showMessageDialog(null,"You have already used your leader's ability","Exception",JOptionPane.PLAIN_MESSAGE);
			}
		}
		//---------------------move------------------------
		if(e.getSource()==up && movebool==true) {
			Champion champ=game.getCurrentChampion();
			int x=champ.getLocation().x;
			int y=champ.getLocation().y;
			cells[x][y].setBorder(null);
			try {
				game.move(Direction.UP);
				cells[x+1][y].setIcon(cells[x][y].getIcon());
				cells[x+1][y].setText(cells[x][y].getText());
				cells[x][y].setIcon(null);
				cells[x][y].setText(null);
				updateBorder(champ);
				info.removeAll();
				info.setBackground(Color.black);
				panel.setOpaque(true);
				JLabel text=new JLabel("Name: "+game.getCurrentChampion().getName());
				text.setBounds(10,10,180,30);
				text.setFont(new Font("Arial",Font.BOLD,20));
				text.setForeground(Color.yellow);
				
				String types="";
				if(game.getCurrentChampion() instanceof Hero) {
					types="Hero";
				}
				if(game.getCurrentChampion() instanceof AntiHero) {
					types="Anti Hero";
				}
				if(game.getCurrentChampion() instanceof Villain) {
					types="Villain";
				}
				
				JLabel text2=new JLabel("--Type: "+types);
				text2.setBounds(220,10,180,30);
				text2.setFont(new Font("Arial",Font.BOLD,20));
				text2.setForeground(Color.yellow);
				
				JLabel text3=new JLabel("HP: "+game.getCurrentChampion().getCurrentHP()+"     Mana: "+game.getCurrentChampion().getMana()+"      Actions: "+game.getCurrentChampion().getCurrentActionPoints());
				text3.setBounds(10,80,300,30);
				text3.setFont(new Font("Arial",Font.BOLD,20));
				text3.setForeground(Color.yellow);
				
				JLabel text4=new JLabel("Damage: "+game.getCurrentChampion().getAttackDamage()+"      Range: "+game.getCurrentChampion().getAttackRange());
				text4.setBounds(10,120,300,30);
				text4.setFont(new Font("Arial",Font.BOLD,20));
				text4.setForeground(Color.yellow);
				info.add(text);
				info.add(text2);
				info.add(text3);
				info.add(text4);
				frame.add(info);
				for(int i=0;i<5;i++) {
					for(int j=0;j<5;j++) {
						if(game.getBoard()[i][j] instanceof Champion && game.getSecondPlayer().getTeam().contains(game.getBoard()[i][j])) {
							cells[i][j].setBackground(Color.lightGray);
						}
						if(game.getBoard()[i][j] instanceof Champion && game.getFirstPlayer().getTeam().contains(game.getBoard()[i][j])) {
							cells[i][j].setBackground(Color.blue);
						}
						if(game.getBoard()[i][j]==null) {
							cells[i][j].setBackground(Color.white);
						}
					}
				}

			} catch (NotEnoughResourcesException e1) {
				JOptionPane.showMessageDialog(null,"Not enough resources","Exception",JOptionPane.PLAIN_MESSAGE);
				
			} catch(UnallowedMovementException e1) {
				JOptionPane.showMessageDialog(null,"Trying to move to an occupied cell or out of arena","Exception",JOptionPane.PLAIN_MESSAGE);
			}
			
			
		}
		if(e.getSource()==down && movebool==true) {
			Champion champ=game.getCurrentChampion();
			int x=champ.getLocation().x;
			int y=champ.getLocation().y;
			cells[x][y].setBorder(null);
			
			try {
				game.move(Direction.DOWN);
				cells[x-1][y].setIcon(cells[x][y].getIcon());
				cells[x-1][y].setText(cells[x][y].getText());
				cells[x][y].setIcon(null);
				cells[x][y].setText(null);
				updateBorder(champ);
				info.removeAll();
				info.setBackground(Color.black);
				panel.setOpaque(true);
				JLabel text=new JLabel("Name: "+game.getCurrentChampion().getName());
				text.setBounds(10,10,180,30);
				text.setFont(new Font("Arial",Font.BOLD,20));
				text.setForeground(Color.yellow);
				
				String types="";
				if(game.getCurrentChampion() instanceof Hero) {
					types="Hero";
				}
				if(game.getCurrentChampion() instanceof AntiHero) {
					types="Anti Hero";
				}
				if(game.getCurrentChampion() instanceof Villain) {
					types="Villain";
				}
				
				JLabel text2=new JLabel("--Type: "+types);
				text2.setBounds(220,10,180,30);
				text2.setFont(new Font("Arial",Font.BOLD,20));
				text2.setForeground(Color.yellow);
				
				JLabel text3=new JLabel("HP: "+game.getCurrentChampion().getCurrentHP()+"     Mana: "+game.getCurrentChampion().getMana()+"      Actions: "+game.getCurrentChampion().getCurrentActionPoints());
				text3.setBounds(10,80,300,30);
				text3.setFont(new Font("Arial",Font.BOLD,20));
				text3.setForeground(Color.yellow);
				
				JLabel text4=new JLabel("Damage: "+game.getCurrentChampion().getAttackDamage()+"      Range: "+game.getCurrentChampion().getAttackRange());
				text4.setBounds(10,120,300,30);
				text4.setFont(new Font("Arial",Font.BOLD,20));
				text4.setForeground(Color.yellow);
				info.add(text);
				info.add(text2);
				info.add(text3);
				info.add(text4);
				frame.add(info);
				for(int i=0;i<5;i++) {
					for(int j=0;j<5;j++) {
						if(game.getBoard()[i][j] instanceof Champion && game.getSecondPlayer().getTeam().contains(game.getBoard()[i][j])) {
							cells[i][j].setBackground(Color.lightGray);
						}
						if(game.getBoard()[i][j] instanceof Champion && game.getFirstPlayer().getTeam().contains(game.getBoard()[i][j])) {
							cells[i][j].setBackground(Color.blue);
						}
						if(game.getBoard()[i][j]==null) {
							cells[i][j].setBackground(Color.white);
						}
					}
				}
			} catch (NotEnoughResourcesException e1) {
				JOptionPane.showMessageDialog(null,"Not enough resources","Exception",JOptionPane.PLAIN_MESSAGE);
				
			} catch(UnallowedMovementException e1) {
				JOptionPane.showMessageDialog(null,"Trying to move to an occupied cell or out of arena","Exception",JOptionPane.PLAIN_MESSAGE);
			}
			
			
		}
		if(e.getSource()==right && movebool==true) {
			Champion champ=game.getCurrentChampion();
			int x=champ.getLocation().x;
			int y=champ.getLocation().y;
			cells[x][y].setBorder(null);
			
			try {
				game.move(Direction.RIGHT);
				cells[x][y+1].setIcon(cells[x][y].getIcon());
				cells[x][y+1].setText(cells[x][y].getText());
				cells[x][y].setIcon(null);
				cells[x][y].setText(null);
				updateBorder(champ);
				info.removeAll();
				info.setBackground(Color.black);
				panel.setOpaque(true);
				JLabel text=new JLabel("Name: "+game.getCurrentChampion().getName());
				text.setBounds(10,10,180,30);
				text.setFont(new Font("Arial",Font.BOLD,20));
				text.setForeground(Color.yellow);
				
				String types="";
				if(game.getCurrentChampion() instanceof Hero) {
					types="Hero";
				}
				if(game.getCurrentChampion() instanceof AntiHero) {
					types="Anti Hero";
				}
				if(game.getCurrentChampion() instanceof Villain) {
					types="Villain";
				}
				
				JLabel text2=new JLabel("--Type: "+types);
				text2.setBounds(220,10,180,30);
				text2.setFont(new Font("Arial",Font.BOLD,20));
				text2.setForeground(Color.yellow);
				
				JLabel text3=new JLabel("HP: "+game.getCurrentChampion().getCurrentHP()+"     Mana: "+game.getCurrentChampion().getMana()+"      Actions: "+game.getCurrentChampion().getCurrentActionPoints());
				text3.setBounds(10,80,300,30);
				text3.setFont(new Font("Arial",Font.BOLD,20));
				text3.setForeground(Color.yellow);
				
				JLabel text4=new JLabel("Damage: "+game.getCurrentChampion().getAttackDamage()+"      Range: "+game.getCurrentChampion().getAttackRange());
				text4.setBounds(10,120,300,30);
				text4.setFont(new Font("Arial",Font.BOLD,20));
				text4.setForeground(Color.yellow);
				info.add(text);
				info.add(text2);
				info.add(text3);
				info.add(text4);
				frame.add(info);
				for(int i=0;i<5;i++) {
					for(int j=0;j<5;j++) {
						if(game.getBoard()[i][j] instanceof Champion && game.getSecondPlayer().getTeam().contains(game.getBoard()[i][j])) {
							cells[i][j].setBackground(Color.lightGray);
						}
						if(game.getBoard()[i][j] instanceof Champion && game.getFirstPlayer().getTeam().contains(game.getBoard()[i][j])) {
							cells[i][j].setBackground(Color.blue);
						}
						if(game.getBoard()[i][j]==null) {
							cells[i][j].setBackground(Color.white);
						}
					}
				}
			} catch (NotEnoughResourcesException e1) {
				JOptionPane.showMessageDialog(null,"Not enough resources","Exception",JOptionPane.PLAIN_MESSAGE);
				
			} catch(UnallowedMovementException e1) {
				JOptionPane.showMessageDialog(null,"Trying to move to an occupied cell or out of arena","Exception",JOptionPane.PLAIN_MESSAGE);
			}
			
			
		}
		if(e.getSource()==left && movebool==true) {
			Champion champ=game.getCurrentChampion();
			int x=champ.getLocation().x;
			int y=champ.getLocation().y;
			cells[x][y].setBorder(null);
			
			try {
				game.move(Direction.LEFT);
				cells[x][y-1].setIcon(cells[x][y].getIcon());
				cells[x][y-1].setText(cells[x][y].getText());
				cells[x][y].setIcon(null);
				cells[x][y].setText(null);
				updateBorder(champ);
				info.removeAll();
				info.setBackground(Color.black);
				panel.setOpaque(true);
				JLabel text=new JLabel("Name: "+game.getCurrentChampion().getName());
				text.setBounds(10,10,180,30);
				text.setFont(new Font("Arial",Font.BOLD,20));
				text.setForeground(Color.yellow);
				
				String types="";
				if(game.getCurrentChampion() instanceof Hero) {
					types="Hero";
				}
				if(game.getCurrentChampion() instanceof AntiHero) {
					types="Anti Hero";
				}
				if(game.getCurrentChampion() instanceof Villain) {
					types="Villain";
				}
				
				JLabel text2=new JLabel("--Type: "+types);
				text2.setBounds(220,10,180,30);
				text2.setFont(new Font("Arial",Font.BOLD,20));
				text2.setForeground(Color.yellow);
				
				JLabel text3=new JLabel("HP: "+game.getCurrentChampion().getCurrentHP()+"     Mana: "+game.getCurrentChampion().getMana()+"      Actions: "+game.getCurrentChampion().getCurrentActionPoints());
				text3.setBounds(10,80,300,30);
				text3.setFont(new Font("Arial",Font.BOLD,20));
				text3.setForeground(Color.yellow);
				
				JLabel text4=new JLabel("Damage: "+game.getCurrentChampion().getAttackDamage()+"      Range: "+game.getCurrentChampion().getAttackRange());
				text4.setBounds(10,120,300,30);
				text4.setFont(new Font("Arial",Font.BOLD,20));
				text4.setForeground(Color.yellow);
				info.add(text);
				info.add(text2);
				info.add(text3);
				info.add(text4);
				frame.add(info);
				for(int i=0;i<5;i++) {
					for(int j=0;j<5;j++) {
						if(game.getBoard()[i][j] instanceof Champion && game.getSecondPlayer().getTeam().contains(game.getBoard()[i][j])) {
							cells[i][j].setBackground(Color.lightGray);
						}
						if(game.getBoard()[i][j] instanceof Champion && game.getFirstPlayer().getTeam().contains(game.getBoard()[i][j])) {
							cells[i][j].setBackground(Color.blue);
						}
						if(game.getBoard()[i][j]==null) {
							cells[i][j].setBackground(Color.white);
						}
					}
				}
			} catch (NotEnoughResourcesException e1) {
				JOptionPane.showMessageDialog(null,"Not enough resources","Exception",JOptionPane.PLAIN_MESSAGE);
				
			} catch(UnallowedMovementException e1) {
				JOptionPane.showMessageDialog(null,"Trying to move to an occupied cell or out of arena","Exception",JOptionPane.PLAIN_MESSAGE);
			}
			
			
		}
		//-----------------attack------------------------------
	
		if(e.getSource()==up && attackbool==true) {
			Champion champ=game.getCurrentChampion();
			try {
				game.attack(Direction.UP);
				if(game.getFirstPlayer().getTeam().size()<=0) {
					JOptionPane.showMessageDialog(null,"Game Over - Player 2 WINS!","Exception",JOptionPane.PLAIN_MESSAGE);
					frame.dispose();
				}
				if(game.getSecondPlayer().getTeam().size()<=0) {
					JOptionPane.showMessageDialog(null,"Game Over - Player 1 WINS!","Exception",JOptionPane.PLAIN_MESSAGE);
					frame.dispose();
				}
				
				info.removeAll();
				info.setBackground(Color.black);
				panel.setOpaque(true);
				JLabel text=new JLabel("Name: "+game.getCurrentChampion().getName());
				text.setBounds(10,10,180,30);
				text.setFont(new Font("Arial",Font.BOLD,20));
				text.setForeground(Color.yellow);
				
				String types="";
				if(game.getCurrentChampion() instanceof Hero) {
					types="Hero";
				}
				if(game.getCurrentChampion() instanceof AntiHero) {
					types="Anti Hero";
				}
				if(game.getCurrentChampion() instanceof Villain) {
					types="Villain";
				}
				
				JLabel text2=new JLabel("--Type: "+types);
				text2.setBounds(220,10,180,30);
				text2.setFont(new Font("Arial",Font.BOLD,20));
				text2.setForeground(Color.yellow);
				
				JLabel text3=new JLabel("HP: "+game.getCurrentChampion().getCurrentHP()+"     Mana: "+game.getCurrentChampion().getMana()+"      Actions: "+game.getCurrentChampion().getCurrentActionPoints());
				text3.setBounds(10,80,300,30);
				text3.setFont(new Font("Arial",Font.BOLD,20));
				text3.setForeground(Color.yellow);
				
				JLabel text4=new JLabel("Damage: "+game.getCurrentChampion().getAttackDamage()+"      Range: "+game.getCurrentChampion().getAttackRange());
				text4.setBounds(10,120,300,30);
				text4.setFont(new Font("Arial",Font.BOLD,20));
				text4.setForeground(Color.yellow);
				info.add(text);
				info.add(text2);
				info.add(text3);
				info.add(text4);
				frame.add(info);
				clean();
				clean2();
				
			} catch (NotEnoughResourcesException e1) {
				JOptionPane.showMessageDialog(null,"Not enough resources","Exception",JOptionPane.PLAIN_MESSAGE);
			} catch (ChampionDisarmedException e1) {
				JOptionPane.showMessageDialog(null,"Champion is disarmed","Exception",JOptionPane.PLAIN_MESSAGE);
			} catch (InvalidTargetException e1) {
				JOptionPane.showMessageDialog(null,"Invalid target retrieved","Exception",JOptionPane.PLAIN_MESSAGE);
			}
			
	
		}
		if(e.getSource()==down && attackbool==true) {
			Champion champ=game.getCurrentChampion();
			try {
				game.attack(Direction.DOWN);
				if(game.getFirstPlayer().getTeam().size()<=0) {
					JOptionPane.showMessageDialog(null,"Game Over - Player 2 WINS!","Exception",JOptionPane.PLAIN_MESSAGE);
					frame.dispose();
				}
				if(game.getSecondPlayer().getTeam().size()<=0) {
					JOptionPane.showMessageDialog(null,"Game Over - Player 1 WINS!","Exception",JOptionPane.PLAIN_MESSAGE);
					frame.dispose();
				}

				info.removeAll();
				info.setBackground(Color.black);
				panel.setOpaque(true);
				JLabel text=new JLabel("Name: "+game.getCurrentChampion().getName());
				text.setBounds(10,10,180,30);
				text.setFont(new Font("Arial",Font.BOLD,20));
				text.setForeground(Color.yellow);
				
				String types="";
				if(game.getCurrentChampion() instanceof Hero) {
					types="Hero";
				}
				if(game.getCurrentChampion() instanceof AntiHero) {
					types="Anti Hero";
				}
				if(game.getCurrentChampion() instanceof Villain) {
					types="Villain";
				}
				
				JLabel text2=new JLabel("--Type: "+types);
				text2.setBounds(220,10,180,30);
				text2.setFont(new Font("Arial",Font.BOLD,20));
				text2.setForeground(Color.yellow);
				
				JLabel text3=new JLabel("HP: "+game.getCurrentChampion().getCurrentHP()+"     Mana: "+game.getCurrentChampion().getMana()+"      Actions: "+game.getCurrentChampion().getCurrentActionPoints());
				text3.setBounds(10,80,300,30);
				text3.setFont(new Font("Arial",Font.BOLD,20));
				text3.setForeground(Color.yellow);
				
				JLabel text4=new JLabel("Damage: "+game.getCurrentChampion().getAttackDamage()+"      Range: "+game.getCurrentChampion().getAttackRange());
				text4.setBounds(10,120,300,30);
				text4.setFont(new Font("Arial",Font.BOLD,20));
				text4.setForeground(Color.yellow);
				info.add(text);
				info.add(text2);
				info.add(text3);
				info.add(text4);
				frame.add(info);
				clean();
				clean2();
			} catch (NotEnoughResourcesException e1) {
				JOptionPane.showMessageDialog(null,"Not enough resources","Exception",JOptionPane.PLAIN_MESSAGE);
			} catch (ChampionDisarmedException e1) {
				JOptionPane.showMessageDialog(null,"Champion is disarmed","Exception",JOptionPane.PLAIN_MESSAGE);
			} catch (InvalidTargetException e1) {
				JOptionPane.showMessageDialog(null,"Invalid target retrieved","Exception",JOptionPane.PLAIN_MESSAGE);
			}
			
			
			
		}
		if(e.getSource()==right && attackbool==true) {
			Champion champ=game.getCurrentChampion();
			try {
				game.attack(Direction.RIGHT);
				if(game.getFirstPlayer().getTeam().size()<=0) {
					JOptionPane.showMessageDialog(null,"Game Over - Player 2 WINS!","Exception",JOptionPane.PLAIN_MESSAGE);
					frame.dispose();
				}
				if(game.getSecondPlayer().getTeam().size()<=0) {
					JOptionPane.showMessageDialog(null,"Game Over - Player 1 WINS!","Exception",JOptionPane.PLAIN_MESSAGE);
					frame.dispose();
				}
				info.removeAll();
				info.setBackground(Color.black);
				panel.setOpaque(true);
				JLabel text=new JLabel("Name: "+game.getCurrentChampion().getName());
				text.setBounds(10,10,180,30);
				text.setFont(new Font("Arial",Font.BOLD,20));
				text.setForeground(Color.yellow);
				
				String types="";
				if(game.getCurrentChampion() instanceof Hero) {
					types="Hero";
				}
				if(game.getCurrentChampion() instanceof AntiHero) {
					types="Anti Hero";
				}
				if(game.getCurrentChampion() instanceof Villain) {
					types="Villain";
				}
				
				JLabel text2=new JLabel("--Type: "+types);
				text2.setBounds(220,10,180,30);
				text2.setFont(new Font("Arial",Font.BOLD,20));
				text2.setForeground(Color.yellow);
				
				JLabel text3=new JLabel("HP: "+game.getCurrentChampion().getCurrentHP()+"     Mana: "+game.getCurrentChampion().getMana()+"      Actions: "+game.getCurrentChampion().getCurrentActionPoints());
				text3.setBounds(10,80,300,30);
				text3.setFont(new Font("Arial",Font.BOLD,20));
				text3.setForeground(Color.yellow);
				
				JLabel text4=new JLabel("Damage: "+game.getCurrentChampion().getAttackDamage()+"      Range: "+game.getCurrentChampion().getAttackRange());
				text4.setBounds(10,120,300,30);
				text4.setFont(new Font("Arial",Font.BOLD,20));
				text4.setForeground(Color.yellow);
				info.add(text);
				info.add(text2);
				info.add(text3);
				info.add(text4);
				frame.add(info);
				clean();
				clean2();
			} catch (NotEnoughResourcesException e1) {
				JOptionPane.showMessageDialog(null,"Not enough resources","Exception",JOptionPane.PLAIN_MESSAGE);
			} catch (ChampionDisarmedException e1) {
				JOptionPane.showMessageDialog(null,"Champion is disarmed","Exception",JOptionPane.PLAIN_MESSAGE);
			} catch (InvalidTargetException e1) {
				JOptionPane.showMessageDialog(null,"Invalid target retrieved","Exception",JOptionPane.PLAIN_MESSAGE);
			}
			
			
		}
		if(e.getSource()==left && attackbool==true) {
			Champion champ=game.getCurrentChampion();
			try {
				game.attack(Direction.LEFT);
				if(game.getFirstPlayer().getTeam().size()<=0) {
					JOptionPane.showMessageDialog(null,"Game Over - Player 2 WINS!","Exception",JOptionPane.PLAIN_MESSAGE);
					frame.dispose();
				}
				if(game.getSecondPlayer().getTeam().size()<=0) {
					JOptionPane.showMessageDialog(null,"Game Over - Player 1 WINS!","Exception",JOptionPane.PLAIN_MESSAGE);
					frame.dispose();
				}

				info.removeAll();
				info.setBackground(Color.black);
				panel.setOpaque(true);
				JLabel text=new JLabel("Name: "+game.getCurrentChampion().getName());
				text.setBounds(10,10,180,30);
				text.setFont(new Font("Arial",Font.BOLD,20));
				text.setForeground(Color.yellow);
				
				String types="";
				if(game.getCurrentChampion() instanceof Hero) {
					types="Hero";
				}
				if(game.getCurrentChampion() instanceof AntiHero) {
					types="Anti Hero";
				}
				if(game.getCurrentChampion() instanceof Villain) {
					types="Villain";
				}
				
				JLabel text2=new JLabel("--Type: "+types);
				text2.setBounds(220,10,180,30);
				text2.setFont(new Font("Arial",Font.BOLD,20));
				text2.setForeground(Color.yellow);
				
				JLabel text3=new JLabel("HP: "+game.getCurrentChampion().getCurrentHP()+"     Mana: "+game.getCurrentChampion().getMana()+"      Actions: "+game.getCurrentChampion().getCurrentActionPoints());
				text3.setBounds(10,80,300,30);
				text3.setFont(new Font("Arial",Font.BOLD,20));
				text3.setForeground(Color.yellow);
				
				JLabel text4=new JLabel("Damage: "+game.getCurrentChampion().getAttackDamage()+"      Range: "+game.getCurrentChampion().getAttackRange());
				text4.setBounds(10,120,300,30);
				text4.setFont(new Font("Arial",Font.BOLD,20));
				text4.setForeground(Color.yellow);
				info.add(text);
				info.add(text2);
				info.add(text3);
				info.add(text4);
				frame.add(info);
				clean();
				clean2();
			} catch (NotEnoughResourcesException e1) {
				JOptionPane.showMessageDialog(null,"Not enough resources","Exception",JOptionPane.PLAIN_MESSAGE);
			} catch (ChampionDisarmedException e1) {
				JOptionPane.showMessageDialog(null,"Champion is disarmed","Exception",JOptionPane.PLAIN_MESSAGE);
			} catch (InvalidTargetException e1) {
				JOptionPane.showMessageDialog(null,"Invalid target retrieved","Exception",JOptionPane.PLAIN_MESSAGE);
			}
			
		}
		
		
		if(e.getSource()==cast1) {
					
			movebool=false;
			attackbool=false;
			castbool=true;
			endbool=false;
			leaderbool=false;
			
			
				try {
					Champion c=game.getCurrentChampion();
					for(int i=0;i<c.getAbilities().size();i++) {
						Ability a=c.getAbilities().get(i);
						if((a.getCastArea()==AreaOfEffect.SURROUND || a.getCastArea()==AreaOfEffect.TEAMTARGET || a.getCastArea()==AreaOfEffect.SELFTARGET) && a.getCurrentCooldown()==0) {
							game.castAbility(a);
							JOptionPane.showMessageDialog(null,"Ability Casted","INFO",JOptionPane.PLAIN_MESSAGE);
							if(game.getFirstPlayer().getTeam().size()<=0) {
								JOptionPane.showMessageDialog(null,"Game Over - Player 2 WINS!","Exception",JOptionPane.PLAIN_MESSAGE);
								frame.dispose();
							}
							if(game.getSecondPlayer().getTeam().size()<=0) {
								JOptionPane.showMessageDialog(null,"Game Over - Player 1 WINS!","Exception",JOptionPane.PLAIN_MESSAGE);
								frame.dispose();
							}

							break;
						}
						else if(a.getCastArea()==AreaOfEffect.DIRECTIONAL && a.getCurrentCooldown()==0) {
							String[] d= {"Left","Right","Down","Up"};
							int s=JOptionPane.showOptionDialog(null, "Choose direction of Cast","Cast Ability", 0, JOptionPane.PLAIN_MESSAGE, null, d, 0);
							if(s==3) {
								game.castAbility(a,Direction.UP);
							}
							if(s==2) {
								game.castAbility(a,Direction.DOWN);
							}
							if(s==1) {
								game.castAbility(a,Direction.RIGHT);
							}
							if(s==0) {
								game.castAbility(a,Direction.DOWN);
							}
							JOptionPane.showMessageDialog(null,"Ability Casted","INFO",JOptionPane.PLAIN_MESSAGE);
							if(game.getFirstPlayer().getTeam().size()<=0) {
								JOptionPane.showMessageDialog(null,"Game Over - Player 2 WINS!","Exception",JOptionPane.PLAIN_MESSAGE);
								frame.dispose();
							}
							if(game.getSecondPlayer().getTeam().size()<=0) {
								JOptionPane.showMessageDialog(null,"Game Over - Player 1 WINS!","Exception",JOptionPane.PLAIN_MESSAGE);
								frame.dispose();
							}

							break;
						}
						else if(a.getCastArea()==AreaOfEffect.SINGLETARGET && a.getCurrentCooldown()==0) {
							String x1=JOptionPane.showInputDialog("Enter an X coordinate [0,1,2,3,4]");
							String y1=JOptionPane.showInputDialog("Enter a Y coordinate [0,1,2,3,4]");
							while(x1==null || y1==null) {
								x1=JOptionPane.showInputDialog("Enter an X coordinate [0,1,2,3,4]");
								y1=JOptionPane.showInputDialog("Enter a Y coordinate [0,1,2,3,4]");
							}
							int x=Integer.parseInt(x1);
							int y=Integer.parseInt(y1);
								
							while(x<0 || x>4 || y<0 ||y>4) {
								x1=JOptionPane.showInputDialog("Enter an X coordinate [0,1,2,3,4]");
								y1=JOptionPane.showInputDialog("Enter a Y coordinate [0,1,2,3,4]");
								
							}
							try {
								game.castAbility(a, x, y);
								JOptionPane.showMessageDialog(null,"Ability Casted","INFO",JOptionPane.PLAIN_MESSAGE);
								if(game.getFirstPlayer().getTeam().size()<=0) {
									JOptionPane.showMessageDialog(null,"Game Over - Player 2 WINS!","Exception",JOptionPane.PLAIN_MESSAGE);
									frame.dispose();
								}
								if(game.getSecondPlayer().getTeam().size()<=0) {
									JOptionPane.showMessageDialog(null,"Game Over - Player 1 WINS!","Exception",JOptionPane.PLAIN_MESSAGE);
									frame.dispose();
								}

								break;
								
							} catch (InvalidTargetException e1) {
								JOptionPane.showMessageDialog(null,"Invalid target retrieved","Exception",JOptionPane.PLAIN_MESSAGE);
								// TODO Auto-generated catch block
								//e1.printStackTrace();
							}
							
						}
					} 
						clean();
					}catch (NotEnoughResourcesException e1) {
					JOptionPane.showMessageDialog(null,"Not enough resources","Exception",JOptionPane.PLAIN_MESSAGE);
					// TODO Auto-generated catch block
					//e1.printStackTrace();
				} catch (AbilityUseException e1) {
					JOptionPane.showMessageDialog(null,"trying to cast an ability on an out of range target or in a condition that prevents from casting","Exception",JOptionPane.PLAIN_MESSAGE);
					// TODO Auto-generated catch block
					//e1.printStackTrace();
				} catch (CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
			
			
				}
			

		
		//-------------end of method-----------------------
	
	}
	
	
	

	public void updateBorder(Champion c) {
			int x=c.getLocation().x;
			int y=c.getLocation().y;
			Border border=BorderFactory.createLineBorder(Color.red, 2);
			cells[x][y].setBorder(border);
		}
	public void clean() {
		for(int i=0;i<5;i++) {
			for(int j=0;j<5;j++) {
			
				if(game.getBoard()[i][j] instanceof Cover) {
					cells[i][j].setText(""+((Cover) game.getBoard()[i][j]).getCurrentHP());
				}
				if(game.getBoard()[i][j]==null) {
					cells[i][j].setIcon(null);
					cells[i][j].setText(null);
					cells[i][j].setBorder(null);
					
				}
				}
			}
		for(int i=0;i<5;i++) {
			for(int j=0;j<5;j++) {
				if(game.getBoard()[i][j]==null) {
					cells[i][j].setBackground(Color.white);
					cells[i][j].setIcon(null);
					cells[i][j].setBorder(null);
					cells[i][j].setText(null);
				}
			}
		}	

	}
	
	public void clean2() {
	Border border1=BorderFactory.createLineBorder(Color.black, 1);
		ArrayList<String> team1=new ArrayList<String>();
		ArrayList<String> team2=new ArrayList<String>();
		
		for(int i=0;i<game.getFirstPlayer().getTeam().size();i++) {
			Champion c=game.getFirstPlayer().getTeam().get(i);
			team1.add(c.getName());
		}
		for(int i=0;i<game.getSecondPlayer().getTeam().size();i++) {
			Champion c=game.getSecondPlayer().getTeam().get(i);
			team2.add(c.getName());
		}
		
		
			for(int i=0;i<game.getAvailableChampions().size();i++) {
				for(int j=0;j<names.length;j++) {
					if(names[j].equals(game.getAvailableChampions().get(i).getName())) {
						Champion champ=game.getAvailableChampions().get(i);
						if(!team1.contains(champ.getName()) && !team2.contains(champ.getName())) {
							if(champ.getName().equals(rem1.getText())) {
								rem1.setBorder(border1);
								rem1.setIcon(new ImageIcon("dead.png"));
								rem1.setText("");
								rem1.setForeground(Color.black);
								r1=false;
							}
							else if(champ.getName().equals(rem2.getText())) {
								rem2.setBorder(border1);
								rem2.setIcon(new ImageIcon("dead.png"));
								rem2.setText("");
								rem2.setForeground(Color.black);
								r2=false;
							}
							else if(champ.getName().equals(remx.getText())) {
								remx.setBorder(border1);
								remx.setIcon(new ImageIcon("dead.png"));
								remx.setForeground(Color.black);
								remx.setText("");
								rx=false;
							}
							else if(champ.getName().equals(rem3.getText())) {
								rem3.setBorder(border1);
								rem3.setIcon(new ImageIcon("dead.png"));
								rem3.setForeground(Color.black);
								rem3.setText("");
								r3=false;
							}
							else if(champ.getName().equals(rem4.getText())) {
								rem4.setBorder(border1);
								rem4.setIcon(new ImageIcon("dead.png"));
								rem4.setForeground(Color.black);
								rem4.setText("");
								r4=false;
							}
							else if(champ.getName().equals(rem5.getText())) {
								rem5.setBorder(border1);
								rem5.setIcon(new ImageIcon("dead.png"));
								rem5.setForeground(Color.black);
								rem5.setText("");
								r5=false;
							}
						}
					}
				}
			}
		
	}
	
}
