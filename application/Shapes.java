package application;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class Shapes {
	int gx = 0;
	int gy = 700;

	/**
	 * creates the ground (green) at bottom of screen
	 * @return
	 */
	public Rectangle ground() {
		
		Rectangle land = new Rectangle();
		land.setX(gx);
		land.setY(gy);
		land.setWidth(800);
		land.setHeight(50);
		land.setFill(Color.GREEN);
		return land;	
	}

	/**
	 * getters and setters for gx and gy
	 * @return
	 */
	public int getGy() {
		return gy;
	}
	public void setGy(int gy) {
		this.gy = gy;
	}
	
	
	

}
