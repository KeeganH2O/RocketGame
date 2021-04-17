package application;

public class Graphics extends Shapes{
	
	
	double maxHightNow;
	double maxHightPast;
	double maxHightTotal = 640;
	double hangTime = 0.0;
	double hangStart;
	double rocketTime = 0.0;
	double rocketStart;
	double time;
	double gravity = -2;
	double velocityG; //returns negative
	double velocityT;// returns negative
	
	/**
	 * hangTime (used for gravity and velocity down)
	 */
	public void resetHangTime() {
		hangTime = 0.0;
	}
	public void setHangTime() {
		hangTime =  time - hangStart;
		//System.out.println(hangTime);
	}
	public double getHangTime() {
		return hangTime;
	}
	public void setHangStart() {
		hangStart = time;
	}
	
	/**
	 * rocketTime (used for rocket velocity) 
	 */
	public double getRocketTime() {
		return rocketTime;
	}
	public void setRocketTime() {
		rocketTime = time - rocketStart;
	}
	public double getRocketStart() {
		return rocketStart;
	}
	public void setRocketStart() {
		rocketStart = time;
	}

	
	/**
	 * time (game time)	
	 * @param CNT
	 * @param SNT
	 */
	public void setTime(double CNT, double SNT) {
		time = (CNT - SNT)/1000000000.0;
	}
	public double getTime() {
		return time;
	}
	

	/**
	 * Gravity (used to calculate downward motion)
	 */
	public double getCurrentGravity() {
		return gravity;
	}
	public void updateGravity() {
		velocityG = gravity*hangTime;
	}
	
	/**
	 * used to calculate overall trajectory
	 * @return
	 */
	public double getVelocity() {
		return velocityT - velocityG;
	}
	
	/**
	 * used to calculate if moving up
	 */
	public void updateVelocityT() {
		velocityT = hangTime-rocketTime;
	}
	
	/**
	 * calculates maxHight (to be used later to see if it was a high score)
	 * @return
	 */
	public double getMaxHightNow() {
		return maxHightNow;
	}
	public void setMaxHightNow(double maxHightNow) {
		this.maxHightNow = maxHightNow;
	}
	public double getMaxHightPast() {
		return maxHightPast;
	}
	public void setMaxHightPast(double maxHightPast) {
		this.maxHightPast = maxHightPast;
	}
	public double getMaxHightTotal() {
		return maxHightTotal;
	}
	public void setMaxHightTotal() {
		this.maxHightTotal = maxHightNow;
	}
	public void setMaxhightTotal(double hight) {
		this.maxHightTotal = hight;
	}
	
	
	
	

	
	
	
}
