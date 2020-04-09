package com.psm.model;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class DynamicTime {

	
	private  static Time time  = null ; 
	private static Timeline line ; 
	
	public static void startTime(Text showTime , Text grettings){
		
		EventHandler<ActionEvent> handle = e ->{
			
			time = new Time();
			if(showTime != null)
			showTime.setText(time.toString());
			if(grettings != null)
				grettings.setText("Good " + time.getMorning_afternoon());
			System.out.println(time.toString());
			
		};
		
		line = new Timeline(new KeyFrame(Duration.seconds(1) , handle ));
		line.setCycleCount(Timeline.INDEFINITE); 
		line.play();
	}
	
	
	public static void stopTime(){
		
		line.stop();
		
	}
	

}
