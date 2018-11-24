package ufoshooting;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Scanner;

public class Game {
	

	private int ufoNumber;
	private int maxPoints;
	private boolean finished;
	
	
	public Game(int ufoNumber)  {
		
		this.ufoNumber = ufoNumber;
	}



	public int getUfoNumber() {
		return ufoNumber;
	}


	public void setUfoNumber(int ufoNumber) {
		this.ufoNumber = ufoNumber;
	}



	public int getMaxPoints() {
		return maxPoints;
	}



	public void setMaxPoints(int maxPoints) {
		this.maxPoints = maxPoints;
	}
	
	
	
	public boolean isFinished() {
		return finished;
	}



	public void setFinished(boolean finished) {
		this.finished = finished;
	}



	public void savePointsToFile(int points){
		
		
		try {
			Writer wr = new FileWriter("ufoscore.txt");
			wr.write("" + points);
			wr.close();
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}
	
	public int loadPointsFromFile(){
		
		int points = 0;
		Scanner scanner;
		
		File f = new File("ufoscore.txt");
		
		if(f.exists()) {
		
		try {
			scanner = new Scanner(new File("ufoscore.txt"));
			
			while(scanner.hasNextInt())
			   points = scanner.nextInt();
			
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		
	}
		
		else  {   // if file doesn't exist, create it and set 0 as maxPoints
			
			this.savePointsToFile(0);
			
		}
		
		return points;
	}
	
	

}
