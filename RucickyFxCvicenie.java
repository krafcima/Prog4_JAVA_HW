
import java.util.Calendar;
import java.util.GregorianCalendar;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * 
 * @author Marek Krafcik
 *
 */

public class RucickyFxCvicenie extends Application {
	@Override 
	public void start(Stage primaryStage) {
		ClockPaneCvicenie clock = new ClockPaneCvicenie();
		EventHandler<ActionEvent> eventHandler = e -> {
			clock.setCurrentTime(); // Set a new clock time
		};
		Timeline animation = new Timeline(new KeyFrame(Duration.millis(1000), e -> {clock.setCurrentTime();}));
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.play(); // Start animation
		
		Scene scene = new Scene(clock);	// vytvor scenu
		scene.widthProperty().addListener(ov -> clock.setW(scene.getWidth()));
		scene.heightProperty().addListener(ov -> clock.setH(scene.getHeight()));
		
		primaryStage.setTitle("Hodinky"); 	
		primaryStage.setScene(scene); 			
		primaryStage.show(); 					
	}
	public static void main(String[] args) {
		launch(args);
	}
}

class ClockPaneCvicenie extends Pane {
	private int hour;
	private int minute;
	private int second;

	
	private double w = 850, h = 850;

	public ClockPaneCvicenie() {
		setPrefSize(w, h);
		setCurrentTime();
	}
	public void setH(double h) {
		this.h = h;
		paintClock();
	}
	public void setW(double w) {
		this.w = w;
		paintClock();
	}
	public void setCurrentTime() {
		Calendar calendar = new GregorianCalendar();
		this.hour = calendar.get(Calendar.HOUR);
		this.minute = calendar.get(Calendar.MINUTE);
		this.second = calendar.get(Calendar.SECOND);
		
		paintClock(); 
	}
		/**
		 * kresli Analogove hodniy
		 */
	protected void paintClock() {
		// toto by sa malo automaticky menit podla 24 h formatu casu, ak chcete otestovat, tak staci zamenit 0 za 1 a ak chcete aby vzkreslil obidva, nastavte den = 2
		int den = 2;
		//test den
		// den = 1
		//test noc 
		//den = 0
		// test oboje 
		//den = 2
		
		double clockRadius = Math.min(w, h) * 0.7/2;
		double centerX = w / 2;
		double centerY = h / 2;
		getChildren().clear();

		// kruhova plocha
		Circle kruh = new Circle(centerX, centerY, clockRadius);
		kruh.setFill(Color.BLACK);
		kruh.setStroke(Color.DARKGOLDENROD);
		kruh.setStrokeWidth(5);


		// rucicky
		double sekundovkaDlzka = clockRadius * 0.7;
		double sekundovkaX = centerX + sekundovkaDlzka * Math.sin(second * (2 * Math.PI / 60));
		double sekundovkaY = centerY - sekundovkaDlzka * Math.cos(second * (2 * Math.PI / 60));
		Line sekundovka = new Line(centerX, centerY, sekundovkaX, sekundovkaY);
		sekundovka.setStroke(Color.DARKGOLDENROD);
		sekundovka.setStrokeWidth(2);
		

		
		double minutovkaDlzka = clockRadius * 0.60;
		double minutovkaX = centerX + minutovkaDlzka * Math.sin(minute * (2 * Math.PI / 60));
		double minutovkaY = centerY - minutovkaDlzka * Math.cos(minute * (2 * Math.PI / 60));
		Line minutovka = new Line(centerX, centerY, minutovkaX, minutovkaY);
		minutovka.setStroke(Color.DARKGOLDENROD);
		minutovka.setStrokeWidth(4);

		
		double hodinovkaDlzka = clockRadius * 0.5;
		double hodinovkaX = centerX + hodinovkaDlzka * Math.sin((hour % 12 + minute / 60.0) * (2 * Math.PI / 12));
		double hodinovkaY = centerY - hodinovkaDlzka * Math.cos((hour % 12 + minute / 60.0) * (2 * Math.PI / 12));
		Line hodinovka = new Line(centerX, centerY, hodinovkaX, hodinovkaY);
		hodinovka.setStroke(Color.DARKGOLDENROD);
		hodinovka.setStrokeWidth(6);

		getChildren().addAll(kruh, sekundovka, minutovka, hodinovka);
		
		//kresli cifernik + meni den a noc, 
		if (this.hour == 12 && den == 0){
			den += 1;
		}
		if (this.hour == 12 && den == 1) {
			den -= 1;
		}
		if (den == 1){
			paintCiselnik(clockRadius, centerX, centerY); // od 1 - 12
			
		}
		if (den == 0 ){
			paintCiselnik3(clockRadius, centerX, centerY); // od 12 - 23 	
		}
		
		// kresli ciferniky pre den aj noc 
		if (den == 2){
			paintCiselnik(clockRadius, centerX, centerY); // od 1 - 12
			paintCiselnik2(clockRadius - 30, centerX, centerY); // od 13 - 23 
		}
	}
	/**
	 * kresli magicky ciselnik pre poobednajsi cas s 12 suplujucov 00:00 
	 * @param clockRadius
	 * @param centerX
	 * @param centerY
	 */
	protected void paintCiselnik3(double clockRadius, double centerX, double centerY) {
		for(int h = 13; h < 25; h++) {
			double sl = clockRadius * 0.9;
			double sX = centerX + sl * Math.sin(h * 360/12 * (2 * Math.PI)/360 );
			double sY = centerY - sl * Math.cos(h * 360/12 * (2 * Math.PI)/360);
			if (h == 24){
				Text ciselnik = new Text(sX - 3, sY, "12");
				ciselnik.setStroke(Color.DARKGOLDENROD);
				ciselnik.setStrokeWidth(2);
				getChildren().add(ciselnik);
			}
			else{
				Text ciselnik = new Text(sX - 3, sY, ""+(h==0?12:h));
				ciselnik.setStroke(Color.DARKGOLDENROD);
				ciselnik.setStrokeWidth(2);
				getChildren().add(ciselnik);
			}

		}
	}
	/**
	 *  kresli iba poobednajsi cifernik bez 12 
	 * @param clockRadius
	 * @param centerX
	 * @param centerY
	 */
	protected void paintCiselnik2(double clockRadius, double centerX, double centerY) {
		for(int h = 13; h < 24; h++) {
			double sl = clockRadius * 0.9;
			double sX = centerX + sl * Math.sin(h * 360/12 * (2 * Math.PI)/360 );
			double sY = centerY - sl * Math.cos(h * 360/12 * (2 * Math.PI)/360);
			Text ciselnik = new Text(sX - 3, sY, ""+(h==0?12:h));
			ciselnik.setStroke(Color.DARKGOLDENROD);
			ciselnik.setStrokeWidth(2);
			getChildren().add(ciselnik);
		}
	}
	/**
	 * kresli doobedny cifernik 
	 * @param clockRadius
	 * @param centerX
	 * @param centerY
	 */
	protected void paintCiselnik(double clockRadius, double centerX, double centerY) {
		for(int h = 0; h < 12; h++) {
			double sl = clockRadius * 0.9;
			double sX = centerX + sl * Math.sin(h * 360/12 * (2 * Math.PI)/360 );
			double sY = centerY - sl * Math.cos(h * 360/12 * (2 * Math.PI)/360);
			Text ciselnik = new Text(sX - 3, sY, ""+(h==0?12:h));
			ciselnik.setStroke(Color.DARKGOLDENROD);
			ciselnik.setStrokeWidth(2);
			getChildren().add(ciselnik);
		}
	}
}

