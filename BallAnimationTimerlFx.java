
import java.util.ArrayList;
import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
/**
 * 
 * @author Marek Krafcik
 *
 */
public class BallAnimationTimerlFx extends Application {
	static long frameCnt, lasttimeNano;

	@Override
	public void start(Stage primaryStage) {
		BallPane1 balls = new BallPane1();
		AnimationTimer at = new AnimationTimer() {
			@Override
			public void handle(long now) { // cas v nanosekundach, 10^9, mili,
											// micro, nanoseconds
				if (now > lasttimeNano + 100000000) { // ak uplynie sekunda,
														// tak vypis fps
					System.out.println(frameCnt + " fps");
					frameCnt = 0;
					lasttimeNano = now;
					balls.pridaj_gulku();
					//balls.spomal(0.3);
				}
				balls.update();
				balls.paintBallPane();
				frameCnt++;
			}
		};
		at.start();

		
		Scene scene = new Scene(balls, 450, 450);// vytvor scenu

		// nastavena vazieb
		scene.widthProperty().addListener(
				ov -> balls.setW((int) scene.getWidth()));
		scene.heightProperty().addListener(
				ov -> balls.setH((int) scene.getHeight()));

		primaryStage.setTitle("Balls in the Box"); // pomenuj okno aplikacie,
													// javisko
		primaryStage.setScene(scene); // vloz scenu do hlavneho okna, na javisko
		primaryStage.show(); // zobraz javisko
	}

	public static void main(String[] args) {
		launch(args);
	}
}

class BallPane1 extends Pane {
	private int w = 450, h = 450;
	//private IdealBall1 red;
	//private IdealBall1 blue;
	private ArrayList<IdealBall1> gulky = new ArrayList<IdealBall1>();
	Color[] farby = {Color.RED, Color.BLUE, Color.GREEN, Color.CYAN, Color.YELLOW};
	
	public BallPane1() {
		Random rr = new Random();
		for (int i = 0; i < 2; i++){
			pridaj_gulku();
		}
	}
	public void spomal(double n){
		for (IdealBall1 gulka : gulky){
			if(gulka.dx - n < 0){
				gulka.dx -= 0;
			}
			else{
				gulka.dx -= n;
			}
			if(gulka.dy  - n < 0){
				gulka.dy -= 0;
			}
			else{
				gulka.dy -= n;
			}
		}
	}
	public void pridaj_gulku(){
		Random rr = new Random();
		
		IdealBall1 gulka = new IdealBall1(rr.nextInt(w), rr.nextInt(h), // x,y
				rr.nextInt(27), rr.nextInt(27),  // dx, dy
				rr.nextInt(20) + 1, // size
				farby[rr.nextInt(farby.length)]); // color f
		gulky.add(gulka);
	}
	public void update() {
		for (IdealBall1 gulka : gulky){
			gulka.update(w, h);
		}
	}

	public void setH(int h) {
		this.h = h;
	}

	public void setW(int w) {
		this.w = w;
	}

	protected void paintBallPane() {
		getChildren().clear();
		for (IdealBall1 i : gulky){
			Circle gulka = new Circle(i.x, i.y, i.size);
			gulka.setFill(i.f);
			gulka.setStroke(i.f);
			gulka.setStrokeWidth(0.3);
			getChildren().add(gulka);
		}
	}
}

class IdealBall1 {
	int x, y;
	int dx, dy;
	int size;
	Color f;

	public IdealBall1(int x, int y, int dx, int dy, int size, Color f) {
		this.x = x;
		this.y = y;
		this.dx = dx;
		this.dy = dy;
		this.size = size;
		this.f = f;
	}

	public void update(int w, int h) {
		x += dx;
		y += dy;
		if (x < size)
			dx = -dx;
		if (y < size)
			dy = -dy;
		if (x > w - size)
			dx = -dx;
		if (y > h - size)
			dy = -dy;
	}
}