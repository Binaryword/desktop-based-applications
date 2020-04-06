package com.psm.model;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javafx.scene.text.Text;

public class Time {
	
	private int hour ; 
	private int minute ; 
	private int second ; 
	private int am_pm; 
	private int myDay ; 
	private Text showDay  = new  Text(); 
	Calendar now = new GregorianCalendar();
	
	public Time(){
		
		hour = now.get(Calendar.HOUR); 
		minute = now.get(Calendar.MINUTE); 
		second = now.get(Calendar.SECOND);
		am_pm = now.get(Calendar.AM_PM) ; 
		myDay =  now.get(Calendar.DAY_OF_WEEK) ; 
		getDay(myDay);
		 
		
	}
	
	
	public int getHour(){
		return hour ; 
	}
	
	public int getMinute(){
		return minute ; 
	}
	
	public int getSecond(){
		return second ; 
	}
	
	public int getAm_pm(){
		return am_pm ; 
	}
	
	public void getDay(int myDate){
		
		   switch(myDate){
		   case 1 : 
			   showDay.setText("SUN");
			   break ; 
		   case 2 : 
			   showDay.setText("MON");
			   break ;
		   case 3 : 
			   showDay.setText("TUE");
			   break ;
		   case 4 : 
			   showDay.setText("WED");
			   break ;
		   case 5 : 
			   showDay.setText("THUR");
			   break ;
		   case 6 : 
			   showDay.setText("FRI");
			   break ;
		   case 7 : 
			   showDay.setText("SAT");
			   break ; 
		   }
			   
	}
	
	
	public String toString(){
		return String.format("%02d : %02d : %2d %s   %s",  getHour() , getMinute() , getSecond() , (now.get(Calendar.AM_PM) == 0 ? "AM":"PM")  , showDay.getText()); 
		
	}
	

}
