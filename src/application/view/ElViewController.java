package application.view;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import java.util.stream.Stream;

import com.sun.javafx.scene.control.SelectedCellsMap;

import javafx.beans.InvalidationListener;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class ElViewController implements Initializable {

	// these labels are constatnly updated with the text from their respective
	// fields.
	private Label byggLabel;
	private Label systemLabel;
	private Label komponentLabel;

	// the panes from the accordionpane.
	@FXML
	private AnchorPane byggPane;
	@FXML
	private AnchorPane systemPane;
	@FXML
	private AnchorPane kompPane;

	// the textfields for input added to the accordion without fxml because of
	// incopatibility.
	private TextField byggNrField;
	private TextField systemNrField;
	private TextField systemLNrField;
	private TextField kompNrField;
	private TextField kompLNrField;

	// these are the default prefixes for bygg,system and komponent.
	private StringProperty byggDefault;
	private StringProperty systemDefault;
	private StringProperty komponentDefault;
	private String initialValue = "";

	// Strings for storing the different parts of tag.
	private StringProperty byggString;
	private StringProperty systemString;
	private StringProperty komponentString;
	// stores the entire tag comprised of the StringProperties above.
	private StringProperty merking;

	private ObservableList<String> bygningsdelnummer;
	private ObservableList<String> etasjekoder;
	private ObservableList<String> lokasjonskoder;

	private String filePathBygningsdelnummer = "/resources/bygningsdelsnummer.txt";
	private String filePathEtasjekoder = "/model/etasjekoder.txt";
	private String filePathLokasjonskoder = "/model/lokasjonskoder.txt";
	
	private ComboBox<String> systemkoder;

	public ElViewController() {

	}

	public void initialize(URL location, ResourceBundle resources) {

		byggNrField = new TextField();
		systemNrField = new TextField();
		systemLNrField = new TextField();
		kompNrField = new TextField();
		kompLNrField = new TextField();

		byggDefault = new SimpleStringProperty("+");
		systemDefault = new SimpleStringProperty(" =");
		komponentDefault = new SimpleStringProperty(" -");

		byggString = new SimpleStringProperty(this, "byggString", initialValue);
		systemString = new SimpleStringProperty(this, "systemString", initialValue);
		komponentString = new SimpleStringProperty(this, "komponentString", initialValue);
		merking = new SimpleStringProperty();
		
		bygningsdelnummer = FXCollections.observableArrayList();
		readFiles();
		systemkoder = new ComboBox<>(bygningsdelnummer);
		systemkoder.setEditable(true);
		
		
		
		fillPanes();
		setBindings();

	}
	
	/*
	 * currently deprecated.
	 */
	public void createString() {

		merking.set(byggDefault + byggString.getValue() + systemDefault + systemString.getValue() + komponentDefault
				+ komponentString.getValue());
		System.out.println(merking.get());

	}

	public ObservableValue<? extends String> getMerking() {
		return merking;
	}

	public void setBindings() {
		/*
		 * binding the labels together.
		 */
		byggLabel.textProperty().bind(Bindings.concat(byggDefault, byggNrField.textProperty()));

		systemLabel.textProperty()
				.bind(Bindings.concat(systemDefault, systemkoder.valueProperty(), ".", systemLNrField.textProperty()));

		komponentLabel.textProperty()
				.bind(Bindings.concat(komponentDefault, kompNrField.textProperty(), ".", kompLNrField.textProperty()));

		// the final variable that puts all the tags together.
		merking.bind(
				Bindings.concat(byggLabel.textProperty(), systemLabel.textProperty(), komponentLabel.textProperty()));

	}

	public void fillPanes() {
		/*
		 * adding manually to the accordion because fxml does not support
		 * advanced binding yet. Or at least no documentasjon.
		 */

		// Location nodes
		byggNrField.setLayoutX(184.0);
		byggNrField.setLayoutY(16.0);
		byggNrField.setPrefWidth(100.0);
		byggNrField.setPromptText("Bygg");
		byggPane.getChildren().add(byggNrField);

		// Location Label
		byggLabel = new Label();
		byggLabel.setLayoutY(70.0);
		byggLabel.setLayoutX(16.0);
		byggLabel.setPrefHeight(17.0);
		byggPane.getChildren().add(byggLabel);

		// System nodes
		
		systemkoder.setLayoutX(184.0);
		systemkoder.setLayoutY(16.0);
		systemkoder.setPrefWidth(100.0);
		systemkoder.setPromptText("System");
		systemPane.getChildren().add(systemkoder);
		
		
		/*
		 * 
		systemNrField.setLayoutX(184.0);
		systemNrField.setLayoutY(16.0);
		systemNrField.setPrefWidth(100.0);
		systemNrField.setPromptText("System");
		systemPane.getChildren().add(systemNrField);
		*/

		systemLNrField.setLayoutX(184.0);
		systemLNrField.setLayoutY(46);
		systemLNrField.setPrefWidth(100.0);
		systemLNrField.setPromptText("System Løpenummer");
		systemPane.getChildren().add(systemLNrField);

		// System Label
		systemLabel = new Label();
		systemLabel.setLayoutX(16.0);
		systemLabel.setLayoutY(83.0);
		systemPane.getChildren().add(systemLabel);

		// Komponent nodes
		kompNrField.setLayoutX(184.0);
		kompNrField.setLayoutY(16.0);
		kompNrField.setPrefWidth(100.0);
		kompNrField.setPromptText("Komponent nummer");
		kompPane.getChildren().add(kompNrField);

		kompLNrField.setLayoutX(184.0);
		kompLNrField.setLayoutY(46.0);
		kompLNrField.setPrefWidth(100.0);
		kompLNrField.setPromptText("Komponent Løpenumer");
		kompPane.getChildren().add(kompLNrField);

		// Komponent Label
		komponentLabel = new Label();
		komponentLabel.setLayoutX(184.0);
		komponentLabel.setLayoutY(76);
		kompPane.getChildren().add(komponentLabel);

	}
	
	public void readFiles() {
		
		try  {
			
			
			Stream<String> stream = Files.lines(Paths.get("resources/bygningsdelsnummer.txt"));
		
			//System.out.println(stream.count());
			stream.forEach(bygningsdelnummer::add);
			
			stream.close();
			System.out.println(bygningsdelnummer.get(0));
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	

}
