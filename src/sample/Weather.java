package sample;

import java.sql.SQLException;

public class Weather {
    private String name;
    private String country;
    private double temp;
    private long humidity;
    private double lon;
    private double let;


    public Weather(String name, String country, double temp, long humidity, double lon, double let) {
        this.name = name;
        this.country = country;
        this.temp = temp;
        this.humidity = humidity;
        this.lon = lon;
        this.let = let;
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

    public long getHumidity() {
        return humidity;
    }

    public double getLon() {
        return lon;
    }

    public double getLet() {
        return let;
    }


}
