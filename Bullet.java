package ufoshooting;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Bullet {
	
	private Image img;
	private int xpos;
	private int ypos;
	private boolean fired; 
	
	
	public Bullet() {
		
		ImageIcon im = new ImageIcon("images/bullet.jpg");
		Image image = im.getImage();
		this.img = image;
		
	}


	public Image getImg() {
		return this.img;
	}


	public void setImg(Image img) {
		this.img = img;
	}


	public int getXpos() {
		return this.xpos;
	}


	public void setXpos(int xpos) {
		this.xpos = xpos;
	}


	public int getYpos() {
		return this.ypos;
	}


	public void setYpos(int ypos) {
		this.ypos = ypos;
	}
	
	
	
	

	public boolean isFired() {
		return this.fired;
	}


	public void setFired(boolean fired) {
		this.fired = fired;
	}


	public void draw(Graphics g){
		
		g.drawImage(this.getImg(), this.getXpos(), this.getYpos(), null);
		
	}
	
	public void isMoving() { 
		
		this.setYpos(this.getYpos() - 10);
	}
	
}
