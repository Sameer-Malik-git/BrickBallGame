

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class Bricks {
	public int map[][];
	public int brickWidth;
	public int brickHieght;
	
	public Bricks(int row,int col) {
		map=new int[row][col];
		
		for(int i=0;i<row;i++)
			for(int j=0;j<col;j++) {
				map[i][j]=1;
			}
		
		brickWidth=540/col;
		brickHieght=150/row;
		
	}
	
	public void setBrick(int val,int r,int c) {
		map[r][c]=val;
	}
	
	public void draw(Graphics2D g) {
		
		for(int i=0;i<map.length;i++) {
			for(int j=0;j<map[0].length;j++) {
				if(map[i][j]>0) {
					
					g.setColor(Color.white);
					g.fillRect(j*brickWidth+75, i*brickHieght+50, brickWidth, brickHieght);
					
					g.setColor(Color.black);
					g.setStroke(new BasicStroke(3));
					g.drawRect(j*brickWidth+75, i*brickHieght+50, brickWidth, brickHieght);
				}
			}
		}
	}

}