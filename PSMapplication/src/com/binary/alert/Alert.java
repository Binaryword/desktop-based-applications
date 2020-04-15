package com.binary.alert;

import java.util.List;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.events.JFXDialogEvent;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.effect.BoxBlur;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

public class Alert {


	private static JFXDialog log ;

	public static void showAlert(StackPane stackpane , Pane pane , JFXButton button  , String information, String title){

		BoxBlur blur = new BoxBlur(3,3,3);
		JFXDialogLayout layout = new JFXDialogLayout();
		JFXDialog dialog = new JFXDialog(stackpane, layout, JFXDialog.DialogTransition.TOP);
		HBox box = new HBox();
		addControll(button, box);
		layout.setHeading(new Label(title));
		layout.setBody(new Text(information));
		layout.setActions(box);

		log = dialog ;
		layout.getStylesheets().add("com/binary/alert/alert.css");
		button.getStyleClass().add("alert_button");

		dialog.setOnDialogClosed((EventHandler<JFXDialogEvent>) e->{

				pane.setEffect(null);
		});

//		button.setOnAction((EventHandler<ActionEvent>) e->{
//
//			dialog.close();
//
//		});

		dialog.show();
		pane.setEffect(blur);

	}


	public static void showConfirmation(StackPane stackpane , Pane pane , List<JFXButton> buttons  , String information, String title){

		BoxBlur blur = new BoxBlur(3,3,3);
		JFXDialogLayout layout = new JFXDialogLayout();
		JFXDialog dialog = new JFXDialog(stackpane, layout, JFXDialog.DialogTransition.TOP);
		HBox box = new HBox();
		addControll(buttons, box);
		layout.setHeading(new Label(title));
		layout.setBody(new Text(information));
		layout.setActions(box);

		log = dialog ;
		layout.getStylesheets().add("com/binary/alert/alert.css");
		//button.getStyleClass().add("alert_button");

		dialog.setOnDialogClosed((EventHandler<JFXDialogEvent>) e->{

				pane.setEffect(null);
		});


		dialog.show();
		pane.setEffect(blur);

	}


	private static void addControll(JFXButton button , HBox box){

		// controll layout.
		button.setPrefWidth(100);
		button.setPrefHeight(30);
		box.setSpacing(10);
		box.setPadding(new Insets(10));
		box.getChildren().add(button);

	}// end of methods....



	private static void addControll(List<JFXButton> buttons , HBox box){

		// controll layout.
		box.setSpacing(10);
		box.setPadding(new Insets(10));

		for(JFXButton button : buttons){

			button.getStyleClass().add("alert_button");
			button.setPrefWidth(100);
			button.setPrefHeight(30);
			box.getChildren().add(button);

		}

	}// end of methods....

	public static JFXDialog  getDialog(){
		return log ;
	}

}
