package application.view;


import java.awt.Color;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ElViewController implements Initializable {
	
	@FXML
	private String byggNr;
	@FXML
	private String systemNr;
	@FXML
	private String systemLNr;
	@FXML
	private String kompNr;
	@FXML
	private String kompLNr;
	
	@FXML
	private Label byggLabel;
	
	@FXML
	private TextField byggNrField;
	@FXML
	private TextField systemNrField;
	@FXML
	private TextField systemLNrField;
	@FXML
	private TextField kompNrField;
	@FXML
	private TextField kompLNrField;
	
	@FXML
	private StringProperty byggDefault;
	private StringProperty systemDefault;
	private StringProperty komponentDefault;
	private String initialValue = "";
	
	@FXML
	private StringProperty byggString;
	
	private StringProperty systemString;
	private StringProperty komponentString;
	
	private StringProperty merking;
	
	
	
	
	public ElViewController() {
		
	}
	
	
	public void initialize(URL location, ResourceBundle resources) {
		
		byggNrField = new TextField();
		systemNrField = new TextField();
		systemLNrField = new TextField();
		kompNrField = new TextField();
		kompLNrField = new TextField();
		
		
		byggDefault = new SimpleStringProperty("+");
		systemDefault = new SimpleStringProperty("=");
		komponentDefault = new SimpleStringProperty("-");
		
		byggString = new SimpleStringProperty(this, "byggString", initialValue);
		systemString = new SimpleStringProperty(this,"systemString", initialValue);
		komponentString = new SimpleStringProperty(this,"komponentString",initialValue);
		merking = new SimpleStringProperty();
		
		byggLabel = new Label();
		byggLabel.setLayoutY(70.0);
		byggLabel.setLayoutX(14.0);
		
		
		
		
	
		
		//System.out.println(byggString.get());
		bygglabel.textProperty().bind(byggNrField.textProperty().concat(" concat"));
		System.out.println("testing label" + bygglabel.getText());
		
		bygglabel.setText("test");
		
			
		
		
		//putting together the byggString.
		//byggString.bind((ObservableValue<? extends String>) Bindings.concat(byggDefault, byggNrField.textProperty()));
		
				
				//putting together the systemString.
		//systemString.bindBidirectional((Property<String>) Bindings.concat(systemString, systemNrField.textProperty(), systemLNrField.textProperty()));
		//systemString.bind(systemNrField.textProperty());
				
				//putting together the componentString.
		//komponentString.bindBidirectional((Property<String>) Bindings.concat(komponentString,kompNrField.textProperty(),kompLNrField.textProperty()));
		//komponentString.bind(kompNrField.textProperty());
				
		//merking.bind(Bindings.concat(byggDefault,byggString,systemDefault,systemString,komponentDefault,komponentString));
		
	}
	
	public void createString() {
		
		merking.set(byggDefault + byggString.getValue() + systemDefault + systemString.getValue() + komponentDefault + komponentString.getValue());
		System.out.println(merking.get());
		
	}
	
	public StringProperty getMerking() {
		return merking;
	}
	
	public void setBindings() {
		bygglabel.textProperty().bind(byggNrField.textProperty());
	}

}


















