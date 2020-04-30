package it.polito.tdp.poweroutages;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class FXMLController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<?> ComboNERC;

    @FXML
    private TextField txtYears;

    @FXML
    private TextField txtHours;

    @FXML
    private Button btnDO;

    @FXML
    void doAlgorthm(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert ComboNERC != null : "fx:id=\"ComboNERC\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtYears != null : "fx:id=\"txtYears\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtHours != null : "fx:id=\"txtHours\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnDO != null : "fx:id=\"btnDO\" was not injected: check your FXML file 'Scene.fxml'.";

    }
}
