package ufoshooting;

import java.awt.*;       // Using AWT's Graphics and Color
import java.awt.event.*; // Using AWT's event classes and listener interfaces
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.*;    // Using Swing's components and container



public class UfoShooting extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	
	// Define named-constants
	   private static final int CANVAS_WIDTH = 700;
	   private static final int CANVAS_HEIGHT = 500;
	   private static final int UPDATE_PERIOD = 10; // refreshing in milliseconds
	 
	   private DrawCanvas canvas;  // the drawing canvas (an inner class extends JPanel)
	   
	   private Game g1;
	   private Player p1;
	   private Ufo u1;
	   private Bullet b1;
	   
	   private int counter, missedUfos;
	   
	
	
	public UfoShooting() throws IOException {
		
	      canvas = new DrawCanvas();
	      canvas.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
	      this.setContentPane(canvas);
	      this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	      this.pack();
	      this.setTitle("UfoShooting");
	      this.setVisible(true);
	      
	       g1 = new Game(5); // UfoNumber = 5 missed and game is over
	   
	       g1.setMaxPoints(g1.loadPointsFromFile());
	      
	       
	       p1 = new Player();
	       u1 = new Ufo(3000); // 3000 mills = 3 seconds
	       u1.setStayEndTime(System.currentTimeMillis() + u1.getStayTime());
	       b1 = new Bullet();
	       counter = 1;
	       missedUfos = 0;
	      
		

	      // Define an ActionListener to perform update at regular interval
	      ActionListener updateTask = new ActionListener() {
	         @Override
	         public void actionPerformed(ActionEvent evt) {
	           
					
			update();
			
				
				
	                repaint();  // Refresh the JFrame, callback paintComponent()
	         }
	      };
	      // Allocate a Timer to run updateTask's actionPerformed() after every delay msec
	      new Timer(UPDATE_PERIOD, updateTask).start();
	      
	      addKeyListener(new KeyAdapter() {
	           @Override
	           public void keyPressed(KeyEvent evt) {
	        	   
	              switch(evt.getKeyCode()) {
	              
	                 case KeyEvent.VK_LEFT:  p1.moveLeft();  break;
	                 
	                 case KeyEvent.VK_RIGHT: p1.moveRight(); break;
	                 
	                 case KeyEvent.VK_SPACE:  { 
	                	                     b1.setXpos(p1.getXpos() + 30);
	       	                                     b1.setYpos(p1.getYpos());
	       	                                     b1.setFired(true); }
                         
                         
                         case KeyEvent.VK_N : {
                          
                         if (g1.isFinished()) {
                          try {
                              dispose();
                              new UfoShooting();
                          } catch (IOException ex) {
                              Logger.getLogger(UfoShooting.class.getName()).log(Level.SEVERE, null, ex);
                          }
                         }
                         }
	              break;
	       	                                 
	       	          
	                 
	              }
	           }
	        });
	
	     
	      
	   }

	   // Define inner class DrawCanvas, which is a JPanel used for custom drawing
	   private class DrawCanvas extends JPanel {
	      /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
	      public void paintComponent(Graphics g) {
	         super.paintComponent(g);  // paint parent's background
	         setBackground(Color.BLACK);
	        
	         p1.draw(g);
	         
	         u1.draw(g);
	         
	         
	         if(b1.isFired()) {
	        	
	        	 b1.draw(g);
	        	 
	         }
	         
	         
                 
                 g.setColor(Color.GREEN);
	         
	         g.drawString("Points " + p1.getPoints(), 550, 20);
	         
	         g.drawString("StayTime " + u1.getStayTime() + " ms", 550, 40);
	         
	         g.drawString("Missed Ufos " + p1.getMissedUfos(), 550, 60);
	         
	         g.drawString("Hi_Score " + g1.getMaxPoints(), 550, 80);
	         
	         if(g1.isFinished()) {
	        	 
	        	 g.drawString("GAME OVER", 300, 200);
                 
                         g.drawString("Press N for New Game", 50, 450);
                         
                 }
	      
	      }
	   }
		
			
	   // Update the (x, y) position of the moving objects
	   public void update()  {
		   
		   if(missedUfos >= g1.getUfoNumber()){
			   g1.setFinished(true);
			  
			 
		   }
		   
		   if(!g1.isFinished()) { // While the game is not finished...
		   
		   
		   
		         if(b1.isFired())
		            b1.isMoving();
		   
		         if(b1.isFired() & u1.isHit(b1)) {
			   
			        b1.setFired(false);
		   
		                p1.setPoints(p1.getPoints() + 10);
		                p1.setMissedUfos(0);
		   
		                u1.changePosition();
		                u1.setStayEndTime(System.currentTimeMillis() + u1.getStayTime());
		   
		           }
		   
		   
		         if(u1.getStayEndTime() - System.currentTimeMillis() < 0 ) {
		        	   missedUfos = p1.getMissedUfos() + 1;
			           p1.setMissedUfos(missedUfos);
	  		           u1.changePosition();
	                           u1.setStayEndTime(System.currentTimeMillis() + u1.getStayTime());
	               }
		   
		  
		         if(p1.getPoints() == counter * 50 ) {
			   
			         u1.setStayTime(Double.valueOf(u1.getStayTime() * 0.8).longValue()); // Reducing stayTime by 20%
		   
			          counter ++ ;
			   
		   }
		
	   }
		   
                   else    // if game is finished
			   
		   {
			   p1.setXpos(300);
			   p1.setYpos(400);
			   u1.setXpos(0);
			   u1.setYpos(0);
			   
			   if(p1.getPoints() > g1.getMaxPoints()) {
                              
			      g1.savePointsToFile(p1.getPoints());
                           }
		   }
     
		   
	   }	
			
		

	

	   // The entry main method
	   public static void main(String[] args) {
	      // Run GUI codes in Event-Dispatching thread for thread safety
	      SwingUtilities.invokeLater(new Runnable() {
	         @Override
	         public void run() {
	            try {
					new UfoShooting();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} // Let the constructor do the job
	         }
	      });
	   }

	

	
	

}

