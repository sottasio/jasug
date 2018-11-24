package ufoshooting;

import java.awt.Graphics;
import java.awt.Image;
import java.util.Random;

import javax.swing.ImageIcon;

public class Ufo {
	
	private Image img;
	private long stayTime;
	private long stayEndTime;
	private int xpos;
	private int ypos;
	
	
	
	
	public Ufo(int stayTime){
		
		ImageIcon im = new ImageIcon("images/ufo.jpg");
		Image image = im.getImage();
		
		this.img = image;
		this.stayTime = stayTime;
		
		Random rand = new Random();
                int  x = rand.nextInt(580) + 60; // Random x position
                int  y = rand.nextInt(240) + 60; // Random y position
        
                this.xpos = x;
                this.ypos = y;
	}

	public Image getImg() {
		return img;
	}

	public void setImg(Image img) {
		this.img = img;
	}

	public long getStayTime() {
		return stayTime;
	}

	public void setStayTime(long time) {
		this.stayTime = time;
	}
	
	
	
  public int getXpos() {
		return xpos;
	}

	public void setXpos(int xpos) {
		this.xpos = xpos;
	}

	public int getYpos() {
		return ypos;
	}

	public void setYpos(int ypos) {
		this.ypos = ypos;
	}
		

	public long getStayEndTime() {
		return stayEndTime;
	}

	public void setStayEndTime(long stayStartTime) {
		this.stayEndTime = stayStartTime;
	}

	public void draw(Graphics g){
		
		g.drawImage(this.getImg(), this.getXpos(), this.getYpos(), null);
		
	}
	
	public boolean isHit(Bullet b) {
		
		if(b.getXpos() > this.getXpos() & b.getXpos() < this.getXpos() + 60 & 
		   b.getYpos() < this.getYpos() + 40)
			
		return true;
		
		else
			
		return false;
		
	}
	
	public void changePosition(){
		
		Random rand = new Random();
                
                int  x = rand.nextInt(580) + 60;
                int  y = rand.nextInt(240) + 60;
        
                this.xpos = x;
                this.ypos = y;
		
	}

}
