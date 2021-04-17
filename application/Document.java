package application;

import java.io.*;
import java.util.Scanner;

public class Document{
	double highScore;
	


	/**
	 * reads file "Score.dat" to load highScore into the program
	 */
	public void readFile() {
		try { 
			File Dict = new File("Score.dat");
			Scanner Reader = new Scanner(Dict);
			while(Reader.hasNextDouble()) {
				highScore = Reader.nextDouble();
				System.out.println(highScore);
			}
			Reader.close();
			}
		
		
		 catch(FileNotFoundException e) {
			System.out.println("error");
			e.printStackTrace();
		}//ends catch
	}
	
	/**
	 * writes the highScore within the document "Score.dat"
	 * @param data
	 * @throws IOException
	 */
	public void writeHighScore(double data) throws IOException {
	try {
		File Score = new File("Score.dat");
		Writer wr = new FileWriter(Score);
		
		
		wr.write(data + "\n");
		
		
		wr.close();
	}//ends try
	catch(FileNotFoundException e) {
		System.out.println("error");
		e.printStackTrace();
	}//ends catch 	
	}
	
	/**
	 * getters and setters for highScore
	 * @return
	 */
	public double getHighScore() {
		return highScore;
	}

	public void setHighScore(int highScore) {
		this.highScore = highScore;
	}	
	
	
}
