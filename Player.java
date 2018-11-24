package ufoshooting;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Player {
	
	private Image img;
	private int xpos;
	private int ypos;
	private int missedUfos;
	private int points;
	
	
	public Player(){
		
		ImageIcon im = new ImageIcon("images/player.jpg");
		Image image = im.getImage();
		
		this.img = image;
		this.xpos = 330;
		this.ypos = 400;
		this.points = 0;
		
	}


	public Image getImg() {
		return img;
	}


	public void setImg(Image img) {
		this.img = img;
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


	public int getPoints() {
		return points;
	}


	public void setPoints(int points) {
		this.points = points;
	}
	
	
	


public int getMissedUfos() {
		return missedUfos;
	}


	public void setMissedUfos(int missedUfos) {
		this.missedUfos = missedUfos;
	}


public void draw(Graphics g){
	
	g.drawImage(this.getImg(), this.getXpos(), this.getYpos(), null);
	
}

public void moveLeft() { // Move spaceman to the left
	
	if(this.getXpos() > 20)
	
	this.setXpos(this.getXpos() - 20);
	
}

public void moveRight() { // Move spaceman to the right
	
	if(this.getXpos() < 640)
	
	this.setXpos(this.getXpos() + 20);
	
}





}
