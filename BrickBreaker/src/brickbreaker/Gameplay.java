package brickbreaker;
import javax.swing.*;


import java.awt.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.*;
public class Gameplay extends JPanel implements KeyListener,ActionListener
{
	//Game shouldn't start by itself;
	private boolean play=false;
	private int score=0;
	//Initial configuration is of 7*3 bricks.However we can change it
	private int totalbricks=21;
	private Timer timer;
	//This is the delay time or speed of the ball
	private int delay=8;
	
	private int playerx=310;
	
	//Ball position
	private int ballposx=80;
	private int ballposy=350;
	private int ballxdir=-1;
	private int ballydir=-2;
	
	
	private Mapgenerator map;
	
	public Gameplay()
	{  
		map=new Mapgenerator(3,7);
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer =new Timer(delay,this);
		timer.start();
		
	}
	
	
	public void paint(Graphics g){
		//background
		g.setColor(Color.black);
		g.fillRect(1, 1, 692, 592);	
		
		
		//draw map
		map.draw((Graphics2D)g);
		
		//borders
		g.setColor(Color.yellow);
		g.fillRect(0,0,3,592);
		g.fillRect(0,0,692,3);
		g.fillRect(691,0,3,592);
		
		
		//scores
		g.setColor(Color.white);
		g.setFont(new Font("Comic Sans MS",Font.BOLD,25));
		g.drawString("Score"+score,570,30);
		
		//paddle
		g.setColor(Color.green);
		g.fillRect(playerx, 550, 100, 8);
		
		
		//the ball
		g.setColor(Color.yellow);
		//g.fillRect(ballposx, ballposy,20, 20);
		g.fillOval(ballposx, ballposy, 20, 20);
		
		if(totalbricks<=0)
		{
			play=false;
			ballxdir=0;
			ballydir=0;
			g.setColor(Color.red);
			g.setFont(new Font("Comic Sans MS",Font.BOLD,30));
			g.drawString("You Won!",260, 300);
			
			g.setFont(new Font("Comic Sans MS",Font.BOLD,30));
			g.drawString("Press Enter to Restart",173, 350);
			
		}
		
		
		
		if(ballposy>570)
		{
			play=false;
			ballxdir=0;
			ballydir=0;
			g.setColor(Color.red);
			g.setFont(new Font("Comic Sans MS",Font.BOLD,30));
			g.drawString("Game Over,Scores:"+score,190, 300);
			
			g.setFont(new Font("Comic Sans MS",Font.BOLD,30));
			g.drawString("Press Enter to Restart",173, 350);
		}
		
		g.dispose();
		
		
		
	}
	
	@Override
	public void keyTyped(KeyEvent e) {}
	@Override
	public void keyReleased(KeyEvent e) {}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		timer.start();
		if(play)
		{
			if(new Rectangle(ballposx,ballposy,20,20).intersects(new Rectangle(playerx,550,100,8)))
			{
				ballydir=-ballydir;  
			}
			
			A: for(int i=0;i<map.map.length;i++)
			{
				for(int j=0;j<map.map[0].length;j++)
				{
					if(map.map[i][j]>0)
					{
						int brickx=j*map.brickwidth+80;
						int bricky=i*map.brickheight+50;
						int brickwidth=map.brickwidth;
						int brickheight=map.brickheight;
						
						Rectangle rect =new Rectangle(brickx,bricky,brickwidth,brickheight);
						Rectangle ballrect=new Rectangle(ballposx,ballposy,20,20);
						Rectangle brickrect=rect;
						
						if(ballrect.intersects(brickrect))
						{
							map.setBrickValue(0, i, j);
							totalbricks--;
							score+=5;
							
							
							if(ballposx+19<=brickrect.x || ballposx+1>=brickrect.x + brickrect.width )
							{
								ballxdir=-ballxdir;
							}
							else
								ballydir=-ballydir;
							break A;
						}
							
					}
				}
			}
			
			
			ballposx+=ballxdir;
			ballposy+=ballydir;
			if(ballposx<0)
			{
				ballxdir=-ballxdir;
			}
			if(ballposy<0)
			{
				ballydir=-ballydir;
			}
			if(ballposx>670)
			{
				ballxdir=-ballxdir;
			}
			
		}
		repaint();
	}
	@Override
	public void keyPressed(KeyEvent e) 
	{
		 if(e.getKeyCode()==KeyEvent.VK_RIGHT)
		 {
			 if(playerx>=600)
				 playerx=600;
			 else
				 moveRight();
		 }
		 if(e.getKeyCode()==KeyEvent.VK_LEFT)
		 {
			 if(playerx <10)
				 playerx=10;
			 else
				 moveLeft(); 
		 }
		 if(e.getKeyCode()==KeyEvent.VK_ENTER)
		 {
			 if(!play)
			 {
				 play=true;
				 ballposx=120;
				 ballposy=350;
				 ballxdir=-1;
				 ballydir=-2;
				 playerx=310;
				 score=0;
				 totalbricks=21;
				 map=new Mapgenerator(3,7);
				 
				 
				 repaint();
			 }
		 }
		
	}
	public void moveRight(){
		play=true;
		playerx+=20;
	}
	public void moveLeft(){
		play=true;
		playerx-=20;
	}
	

	
	

}
