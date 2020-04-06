package com.psm.model;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class DynamicTime {

	
	private Time time  = null ; 
	
	public void startTime(Text text){
		
		EventHandler<ActionEvent> handle = e ->{
			
			time = new Time();
			text.setText(time.toString());
			
		};
		
		Timeline line = new Timeline(new KeyFrame(Duration.seconds(1) , handle ));
		line.setCycleCount(Timeline.INDEFINITE); 
		line.play();
	}
	
	

}
