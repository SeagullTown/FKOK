package application.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

public class FKOKViewController implements Initializable  {
	private Main main;
	
	private final ObservableList<String> fagListe = FXCollections.observableArrayList(
			"Elektrisk", "Vann");
	
	@FXML
	private Label fagLabel;
	@FXML
	private Label merkeLabel;
	@FXML
	private Label merkeEksempel;
	@FXML
	private ChoiceBox<String> fagBox;
	@FXML
	private Button updateLabelButton;
	@FXML
	private AnchorPane elPane;
	@FXML
	private StackPane fagPane;
	
	@FXML
	private ElViewController elController;
	@FXML
	private FKOKViewController fkokController;
	
	
	
	
	public FKOKViewController() {
		
		
	}
	
	
	public void initialize(URL location, ResourceBundle resources) {
		
		
		fagBox.getItems().removeAll(fagBox.getItems());
		fagBox.getItems().addAll("Elektrisk","Vvs");
		//fagBox.getSelectionModel().select("Option B");
		
		fagBox.setOnAction((event) -> {
			if(fagBox.getValue().equals("Elektrisk")) {
				showElView();
			}
		});
		
		
	}
	
	
	
	public void setMain(Main main) {
		this.main = main;
	}
	
	public void setBindings() {
		merkeEksempel.textProperty().bind(elController.getMerking());
	}
	
	public void updateLabel() {
		elController.createString();
	}
	@FXML
	public void showElView() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/ElView.fxml"));
			
			elPane = (AnchorPane) loader.load();
			
			elController = loader.getController();
			//setBindings();
			
			
			
			
			//main.fkokView.getChildren().add(fagPane);
			fagPane.getChildren().add(elPane);
			//controller.setBindings();
			
			
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}
}
