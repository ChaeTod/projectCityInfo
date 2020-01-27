package sample;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {

    private Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://itsovy.sk:3306/world_x?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "student", "kosice2019");
    }

    public List getCountries() {
        try {
            getConnection();
            PreparedStatement ps = getConnection().prepareStatement("SELECT country.Code, country.Name FROM country");
            ResultSet rs = ps.executeQuery();
            List<String> list = new ArrayList<>();
            while (rs.next()) {
                String countryName = rs.getString("Name");
                //System.out.println(countryName);
                list.add(rs.getString("Name"));
                //countryList.add(rs.getString("Name"));
            }
            ps.close();
            rs.close();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List getCities(String countryName) {
        try {
            System.out.println(countryName);
            PreparedStatement ps = getConnection().prepareStatement("SELECT * FROM city JOIN country ON country.Code = city.CountryCode WHERE country.Name LIKE ?");
            ps.setString(1, countryName);
            List<String> list = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(rs.getString("Name"));
            }
            ps.close();
            rs.close();
            return list;
        } catch (
                Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getPopulation(String cityName){
        try {
            PreparedStatement ps = getConnection().prepareStatement("SELECT json_extract(Info, '$.Population') AS Population FROM city WHERE city.Name LIKE ?");
            ps.setString(1, cityName);
            ps.executeQuery();
            String population = null;
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                population = rs.getString("Population");
            }
            ps.close();
            return population;
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

/*
    //@FXML
    private void getStation() {
        .setItems(countryList);
    }
*/

}
