package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import javax.xml.crypto.Data;
import java.sql.SQLException;
import java.util.List;

public class Controller {
    public ComboBox comboBox1;
    public Label cityLabel;
    public ComboBox comboBox2;
    public Button okBtn;

    //private ObservableList<String> stationsList = FXCollections.observableArrayList();

    List<String> countries;
    public Controller() throws SQLException, ClassNotFoundException {
        //Database database = new Database();
        //countries = database.getCountries();

        //comboBox1.setItems(FXCollections.observableArrayList(countries));

/*
        if (countries != null) {

            assert false;
            comboBox1.setItems(FXCollections.observableArrayList(countries));
            //comboBox1.getItems().addAll((ObservableList) countries);
        }

 */
    }

    public String getComboBox1Value(){
        return (String) comboBox1.getValue();
    }

    public void selectCountries(Event event) throws SQLException, ClassNotFoundException {
        Database data = new Database();
        comboBox1.getItems().setAll(data.getCountries());
        //getComboBox1Value();
        if (getComboBox1Value() != null)
            System.out.println(getComboBox1Value());
    }

    public void selectCities(Event event) throws SQLException, ClassNotFoundException {
       Database database = new Database();
       comboBox2.getItems().setAll(database.getCities());
        /*
        Database data = new Database();
        //data.showComboList();
        comboBox2.getItems().setAll(data.getCities());
        System.out.println(getComboBox1Value());

        */
    }

    public void showNextSelect(ActionEvent actionEvent) {
        if (getComboBox1Value() != null) {
            cityLabel.setVisible(true);
            comboBox2.setVisible(true);
        }
    }



}
