package sample;

import java.sql.SQLException;

public class Weather {
    private String name;
    private String country;
    private double temp;
    private int humidity;
    private double lon;
    private double let;
    private String sunRise;
    private String sunSet;


    public Weather(String name, String country, double temp, int humidity, double lon, double let, String sunRise, String sunSet) {
        this.name = name;
        this.country = country;
        this.temp = temp;
        this.humidity = humidity;
        this.lon = lon;
        this.let = let;
        this.sunRise = sunRise;
        this.sunSet = sunSet;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public double getTemp() {
        return temp;
    }

    public String getSunSet(){
        return sunSet;
    }

    public String getSunRise(){
        return sunRise;
    }

    public int getHumidity() {
        return humidity;
    }

    public double getLon() {
        return lon;
    }

    public double getLet() {
        return let;
    }


}
