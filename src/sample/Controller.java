package sample;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;

public class Controller {
    public ComboBox comboBox1;
    public Label cityLabel;
    public ComboBox comboBox2;
    public Button okBtn;
    public Label weatherLabel;
    public Label populationLabel;
    public Label popValueLbl;
    public Label cityLbl;
    public Label countryLbl;
    public Label tempLbl;
    public Label humidityLbl;
    public Label visiblLbl;
    public Label riseLbl;
    public Label downLbl;
    public CheckBox chkBox1;
    public CheckBox chkBox2;
    public Label coordLbl;
    public Button coordBtn;
    //public Hyperlink link;
    private List<City> cities; // create a list Cities

    List<String> countries;  // create a list of Countries

    //VBox vbox = new VBox();
    //Scene scene = new Scene(vbox);
    final WebView browser = new WebView();
    final WebEngine webEngine = browser.getEngine();


    public Controller() throws SQLException, ClassNotFoundException {
        Database database = new Database();
        countries = database.getCountries();

        //comboBox1.setItems(FXCollections.observableArrayList(countries));

/*
        if (countries != null) {

            assert false;
            comboBox1.setItems(FXCollections.observableArrayList(countries));
            //comboBox1.getItems().addAll((ObservableList) countries);
        }

 */
    }

    /* old functions
        public void getCountry(){
            String country = null;
            country = (String) comboBox1.getValue();
            List<City> cities;
            if (country != null){
                Database db = new Database();
                cities = db.getCountries();
                comboBox2.getItems().clear();
                for (City s : cities){
                    System.out.println(s.getName());
                    comboBox2.getItems().add(s.getName());
                }
                comboBox2.setDisable(false);
            }
        }
    */
    public String getComboBox1Value() {
        return (String) comboBox1.getValue();
    }

    public String getComboBox2Value() {
        return (String) comboBox2.getValue();
    }

    public void selectCountries(Event event) throws SQLException, ClassNotFoundException {
        //Database data = new Database();
        comboBox1.getItems().setAll(countries);
    }

    public void selectCities(Event event) throws SQLException, ClassNotFoundException {
        String country = null;
        country = getComboBox1Value();  // get the value from the first comboBox
        if (country != null) {  //if the value from the first comboBox not null
            Database db = new Database(); // create a new object db with Database type
            cities = db.getCities(country); // write all info about the selected country into the list of Cities
            comboBox2.getItems().clear(); // clear the previous items from second comboBox
            for (City s : cities) { // go though all cities
                //System.out.println(s.getName());
                comboBox2.getItems().add(s.getName()); // fill the second comboBox with the names of the cities in the selected country
            }
            comboBox2.setDisable(false); // enable the second comboBox
            chkBox1.setDisable(false);
            chkBox2.setDisable(false);
        }
        /*
        Database database = new Database();
        comboBox2.getItems().setAll(database.getCities(getComboBox1Value()));
         */
    }


    public void showNextSelect(ActionEvent actionEvent) {
        if (getComboBox1Value() != null) {
            //cityLabel.setVisible(true);
            //comboBox2.setVisible(true);
            comboBox2.setDisable(false);
        }
    }

    public void showAllLabels(ActionEvent actionEvent) {
        //weatherLabel.setVisible(true);
        //populationLabel.setVisible(true);
        //popValueLbl.setVisible(true);
        tempLbl.setVisible(false);
        humidityLbl.setVisible(false);
        okBtn.setDisable(false);
    }

    /*
        public String getData() {
            String cityName = getComboBox2Value();
            City city = null;  // create an object with type City
            for (City c : cities) { //run through cities list
                if (c.getName().equals(cityName)) { //if found city.Name in list cities which is equal to the cityName that we selected in first comboBox
                    city = c; //set to null object city all the data (city.Name, country.Name, population and etc.) from the found city in the list. Now we're able to work with it.
                    break; // if OK - stop further iterations
                }
            }
            if (city == null)
                return null;
            String date = city.getName();
            //date.add(city.getName());
            return date;
        }

        public String getCode() {
            String cityName = getComboBox2Value();
            City city = null;  // create an object with type City
            for (City c : cities) { //run through cities list
                if (c.getName().equals(cityName)) { //if found city.Name in list cities which is equal to the cityName that we selected in first comboBox
                    city = c; //set to null object city all the data (city.Name, country.Name, population and etc.) from the found city in the list. Now we're able to work with it.
                    break; // if OK - stop further iterations
                }
            }
            if (city == null)
                return null;
            String date = city.getCode2();
            //date.add(city.getCode2());
            return date;
        }

    */
    public void getAllInfo(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String cityName = getComboBox2Value();
        City city = null;  // create an object with type City
        for (City c : cities) { //run through cities list
            if (c.getName().equals(cityName)) { //if found city.Name in list cities which is equal to the cityName that we selected in first comboBox
                city = c; //set to null object city all the data (city.Name, country.Name, population and etc.) from the found city in the list. Now we're able to work with it.
                break; // if OK - stop further iterations
            }
        }

        if (city == null)  // condition to avoid a NullPointerException
            return;

        cityLbl.setText("City:         " + city.getName());
        countryLbl.setText("Country:      " + city.getCountry() + " (" + city.getCode2() + ")");
        populationLabel.setText("Population:    " + formatPopulation(city.getPopulation()));
        Weather weather = new APIWeather().getWeather(city.getName(), city.getCode3());
        if (weather != null) {
            String temp = String.valueOf(weather.getTemp());
            //String temp = Double.toString(weather.getTemp());
            String hum = String.valueOf(weather.getHumidity());
            String vis = String.valueOf(weather.getLon());
            tempLbl.setText("The temperature: " + temp + "°C");
            humidityLbl.setText("Humidity: " + hum + "%");
            visiblLbl.setText("Visibility: " + vis);
            riseLbl.setText("Sunrise: " + weather.getSunRise());
            downLbl.setText("Sunset: " + weather.getSunSet());
            coordLbl.setText("http://www.google.com/maps/place/" + weather.getLat() +","+weather.getLon());

        } else {
            tempLbl.setText("---");
            humidityLbl.setText("---");
            visiblLbl.setText("---");
            riseLbl.setText("---");
            downLbl.setText("---");
        }

        //Hyperlink link = new Hyperlink();
        //link.setText("https://maps.google.com/?q=<"+weather.getLon()+">,<"+weather.getLat()+">");
        //coordLbl.setText("http://www.google.com/maps/place/" + weather.getLat() +","+weather.getLon()+);
        System.out.println(coordLbl);;
        /*coordLbl.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                System.out.println("This link is clicked");
            }
        });
        */
        //weather.getTemp();


        //Weather we = new Weather();
        //we.showReq();

    }
    /*
    public static void MyGETRequest() throws IOException {  //some API Connector
        URL urlForGetRequest = new URL("https://jsonplaceholder.typicode.com/posts/1");
        String readLine = null;
        HttpURLConnection conection = (HttpURLConnection) urlForGetRequest.openConnection();
        conection.setRequestMethod("GET");
        conection.setRequestProperty("userId", "a1bcdef"); // set userId its a sample here
        int responseCode = conection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(conection.getInputStream()));
            StringBuffer response = new StringBuffer();
            while ((readLine = in .readLine()) != null) {
                response.append(readLine);
            } in .close();
            // print result
            System.out.println("JSON String Result " + response.toString());
            //GetAndPost.POSTRequest(response.toString());
        } else {
            System.out.println("GET NOT WORKED");
        }
    }
    */

    private String formatPopulation(int population) { // format the population result
        DecimalFormat df = new DecimalFormat("#,###,###");
        DecimalFormatSymbols symbols = df.getDecimalFormatSymbols();
        symbols.setGroupingSeparator(' ');
        df.setDecimalFormatSymbols(symbols);
        return df.format(population);
    }

    public void switchToFirstCity(KeyEvent keyEvent) { // the method to switch old cities into the new one if the user will choose another country
        /*
        Database data = new Database();
        //comboBox2.set
        comboBox2.getSelectionModel().selectFirst(data.getCities(getComboBox1Value()).);
        //comboBox2.getItems().setAll(data.getCities(getComboBox1Value()));

         */
    }

    public void setLbl(ActionEvent actionEvent) {
        if (chkBox1.isSelected()) {
            weatherLabel.setVisible(true);
            tempLbl.setVisible(true);
            humidityLbl.setVisible(true);
            visiblLbl.setVisible(true);
            riseLbl.setVisible(true);
            downLbl.setVisible(true);
        } else {
            weatherLabel.setVisible(false);
            tempLbl.setVisible(false);
            humidityLbl.setVisible(false);
            visiblLbl.setVisible(false);
            riseLbl.setVisible(false);
            downLbl.setVisible(false);
        }
        if (chkBox2.isSelected()) {
            populationLabel.setVisible(true);
            popValueLbl.setVisible(true);
            cityLbl.setVisible(true);
            countryLbl.setVisible(true);
        } else {
            populationLabel.setVisible(false);
            popValueLbl.setVisible(false);
            cityLbl.setVisible(false);
            countryLbl.setVisible(false);
        }
    }

    public void goToCoord() {
        System.out.println("The label inside the method: " +coordLbl);
        Hyperlink link = new Hyperlink(coordLbl.getText());
        //link.setText("http://www.google.com/maps/place/" + weather.getLat() +","+weather.getLon());
        System.out.println("The link inside the method: " +link);
        link.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                webEngine.load("http://www.google.com/");
                //getChildren().add(browser);
            }
        });
        /*
        Hyperlink link = new Hyperlink();
        link.setText(coordLbl.getText());
        link.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                System.out.println("This link is clicked");
            }
        });
    */
    }
}
