package application;
	
import java.io.IOException;

import application.view.FKOKViewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/*
 * FKOK
 */
public class Main extends Application {
	
	private Stage primaryStage;
	private BorderPane mainWindow;
	public AnchorPane fkokView;
	
	
	public Main() {
		
	}
	
	@Override
	public void start(Stage primaryStage) {
		
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("FKOK-merkegenerator");
		
		initMainWindow();
		showFKOKView();
	}
	
	
	/*
	 * 
	 * This is the main window of the application. everything shown in the program is a 
	 * scene inside this scene that is initiated here to the primaryStage.
	 */
	public void initMainWindow() {
		
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/MainWindow.fxml"));
		
			mainWindow = (BorderPane) loader.load();
			
			Scene scene = new Scene(mainWindow);
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	public void showFKOKView() {
		
		try {
			//FKOKViewController FKOKController = new FKOKViewController();
			
			//loading the fkok input fxml.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/FKOKView.fxml"));
			
			fkokView = (AnchorPane) loader.load();
			
			//adding the fkokview to the mainwindow
			mainWindow.setCenter(fkokView);
			
			
			//give the controller access to the Main class.
			FKOKViewController controller = loader.getController();
			controller.setMain(this);
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}
	
	
	
	/*
	 * Main method.
	 */
	public static void main(String[] args) {
		launch(args);
	}
}