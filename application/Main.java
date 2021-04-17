package application;
	
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.Group;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


public class Main extends Application{
	@Override

	public void start(Stage primaryStage) throws FileNotFoundException {
		DecimalFormat df = new DecimalFormat("#.##");
		final long startNanoTime=System.nanoTime();
		Graphics G = new Graphics();
		Document D = new Document();
		D.readFile();
		
		//Document D = new Document();
		
		
		
		/**
		 * Rocket Instantiation
		 * Rocket Cords
		 */
		Image rocket = new Image(new FileInputStream("RocketImage.png"));
		ImageView rocketImage = new ImageView(rocket);
		rocketImage.setX(375);
		rocketImage.setY(640);
		rocketImage.setFitHeight(80);
		rocketImage.setFitWidth(50);

		
			//sets title of 'game'
			/**
			 * sets the place where game is shown
			 */
			primaryStage.setTitle("Rocket Game: Earth Launch");
			Group group = new Group();
			Scene scene = new Scene(group,800,750,Color.LIGHTBLUE);
			Shapes Shape = new Shapes();
			Canvas canvas = new Canvas(500,500);
			Rectangle ground = Shape.ground();
			
			
			/**
			 * The key event of space being pressed
			 */
			scene.addEventFilter(KeyEvent.KEY_PRESSED,keyEvent -> {
				G.updateVelocityT();
				//G.setVelocityT(-2);
				G.resetHangTime();
				G.setHangStart();
				
			});
			
			

			
			/**
			 * sets the font and text type
			 */
			GraphicsContext gc = canvas.getGraphicsContext2D();
			Font TNR = Font.font("Times New Roman",FontWeight.NORMAL,24);
			gc.setFont(TNR);
			gc.setStroke(Color.BLACK);
			gc.setLineWidth(1);
			
			
			/**
			 * all the animation is located here
			 */
			new AnimationTimer(){
				public void handle(long currentNanoTime) {
					boolean landing = true;
			
					
					G.setTime(currentNanoTime, startNanoTime);
					G.setHangTime();
					G.setRocketTime();

					

					/*
					 * starts time for Rocket (being held)
					 */
					if(G.getHangTime() > 0.5) {
						G.setRocketStart();
					}

					/**
					 * sees if rocket is above minimum threshold to start going down
					 */
					if(rocketImage.getY() < 641) {
						G.updateGravity();
					}
					
					
					//resets the background
					gc.setFill(Color.LIGHTBLUE);
					gc.fillRect(0, 0, 500,500 );
					
					gc.setFill(Color.GREEN);
					gc.fillRect(0, 640, 500, 500);
					
					//Shows where they are at (ui)
					gc.setFill(Color.BLACK);
					String Box = "Current Hight: "+ df.format(-1*(rocketImage.getY()-640)) + "\nSpeed: " + df.format(-1*G.getVelocity()) + "\nHigh Score: " + df.format(-1*(D.getHighScore()-640) );
					gc.fillText(Box, 20, 20);
					
					
					G.setMaxHightPast(rocketImage.getY());;
					//System.out.println("P" +G.getMaxHightPast());
					
					//checks again for minimum threshold and if true then sees if rocket should go up or down
					if(rocketImage.getY() < 641) {
						
					/*if(rocketImage.getY() < 0) {
						rocketImage.setY(0);
					}*/
						
					rocketImage.setY(rocketImage.getY() + G.getVelocity());                                 
					}
					
					//resets rocket to 640
					if(rocketImage.getY() > 641) {
						rocketImage.setY(640);
					}
					
					//Checks if landing was a success
					if(rocketImage.getY() == 640 && G.getTime() > 5.00 ) {
						if( G.getVelocity() > 3) {
							System.out.println("FAILURE");
							landing = false;
							stop();
						}
						else {
							System.out.println("TRUE");
							landing = true;
							if(-1*(G.getMaxHightTotal()-640) > -1*(D.getHighScore()-640)) {
								//D.setHSB(true);
									try {
										D.writeHighScore(G.getMaxHightTotal());
									} catch (IOException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
							}
							stop();
						}
						
						//displays win or loss (sucessful landing or not)
						gc.setFill(Color.BLACK);
						String WinLoss = "Landing: " + landing + "\nYour Score: " + df.format(G.getMaxHightTotal());
						gc.fillText(WinLoss, 20, 120);
					}
					
					G.setMaxHightNow(rocketImage.getY());	
					//System.out.println("N"+ G.getMaxHightNow());
				
					if    (G.getMaxHightPast() > G.getMaxHightNow() && G.getMaxHightNow() < G.getMaxHightTotal()) {
						G.setMaxHightTotal();
					}
					
					
					//System.out.println("T"+ G.getMaxHightTotal());
					//System.out.println("A:" + D.getHighScore());
				}
			}.start();
			
			
			
			
			

			group.getChildren().add(canvas);
			group.getChildren().add(ground);
			group.getChildren().add(rocketImage);
			
			primaryStage.setScene(scene);
			primaryStage.show();
			

			

	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
