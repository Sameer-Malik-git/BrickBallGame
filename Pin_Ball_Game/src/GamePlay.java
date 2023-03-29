
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePlay extends JPanel implements ActionListener, KeyListener {
	
	private boolean play=false;
	private int totalBrick=21;
	private int score=0;
	private Timer timer;
	private int delay=10;
	private int ballPosX=350; 
	private int ballPosY=450; 
	private int ballXDir=-1; 
	private int ballYDir=-3;
	private int playerX=300;
	private Bricks map;
	
	public GamePlay() {
		
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(true);
		
		timer=new Timer(delay,this);
		timer.start();
		
		map=new Bricks(3,7);
	}
	
	public void paint(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(1,1,692,592); //change
		
		g.setColor(Color.yellow);
		g.fillRect(0, 0, 693, 3);
		g.fillRect(0, 3, 3, 592);
		g.fillRect(691, 3, 3, 592);
		
		map.draw((Graphics2D)g);

		g.setColor(Color.red);
		g.fillRect(playerX, 550,100,8);
		
		g.setColor(Color.blue);
		g.fillOval(ballPosX, ballPosY, 20, 20);
		
		g.setColor(Color.yellow);
		g.setFont(new Font("score", Font.BOLD, 17));
		g.drawString("Score : "+score,580,30);
		
		if(ballPosY>=600) {
			
			play=false;
			ballXDir=0;
			ballYDir=0;
			
			g.setColor(Color.red);
			g.setFont(new Font("score", Font.BOLD, 50));
			g.drawString("GAME OVER!!",180,300);
			
			}

			g.setColor(Color.green);
			g.setFont(new Font("score", Font.BOLD, 20));
			g.drawString(" Score : "+score,290,330);
			
			g.setFont(new Font("score", Font.BOLD, 20));
			g.drawString("Press Enter To Restart",240,360);
		
		if(totalBrick==0) {
			play=false;
			g.setColor(Color.green);
			g.setFont(new Font("score", Font.BOLD, 60));
			g.drawString("YOU WON!!",190,330);
			g.setFont(new Font("score", Font.BOLD, 20));
			g.drawString(" Score : "+score,310,360);
			g.setFont(new Font("score", Font.BOLD, 20));
			g.drawString("Press Enter To Restart",260,390);
			
		}
		
		
	}
	
	private void moveLeft() {
		play=true;
		playerX-=30;
	}
	
	private void moveRight() {
		play=true;
		playerX+=30;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_LEFT) {
			if(playerX<=5) {
				playerX=5;
			}
			else
			moveLeft();
			
		}
		
		if(e.getKeyCode()==KeyEvent.VK_RIGHT) {
			if(playerX>=565) {
				playerX=565;
			}
			moveRight();
			
		}
		
		if(e.getKeyCode()==KeyEvent.VK_ENTER) {
			if(!play) {
				totalBrick=21;
				score=0;
				ballPosX=350; 
				ballPosY=450; 
				ballXDir=-1; 
				ballYDir=-3;
				playerX=300;
				
				map=new Bricks(3,7);
			}
		}
		
		repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(play) {
			
			if(ballPosX<=5) {
				ballXDir=-ballXDir;
			}
			
			if(ballPosX>=670) {
				ballXDir=-ballXDir;
			}
			
			if(ballPosY<=5) {
				ballYDir=-ballYDir;
			}
			
			Rectangle ballRect=new Rectangle(ballPosX, ballPosY, 20, 20);
			Rectangle paddleRect=new Rectangle(playerX, 550,100,8);
			
			
			if(ballRect.intersects(paddleRect)) {
				ballYDir=-ballYDir;
			}
			
			
			
			A:for(int i=0;i<map.map.length;i++) {	//here one map is difined in private calss and one map  is for 2d array of brick
				for(int j=0;j<map.map[0].length;j++) {
					
					if(map.map[i][j]>0) {
						
						int w=map.brickWidth;
						int h=map.brickHieght;
						int bXPos=75+j*w;
						int bYPos=50+i*h;
						
						Rectangle brickRect=new Rectangle(bXPos, bYPos, w, h);
						
						if(ballRect.intersects(brickRect)) {
							map.setBrick(0,i,j);
							totalBrick--;
							score+=10;
							
							if(ballPosX+19<=bXPos || ballPosX+1>=bXPos+w) {
								ballXDir=-ballXDir;
							}
							else {
								ballYDir=-ballYDir;
							}
							
							break A;
						}
					}
				}
			}
				
			
			ballPosX+=ballXDir;
			ballPosY+=ballYDir;
		}
		repaint();
	}
	
}