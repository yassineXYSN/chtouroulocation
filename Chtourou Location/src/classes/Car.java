package classes;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Car{

    public Car(int id, Model Model, int Age, Float Price, String Color,boolean availability) {
        this.id = id;
        this.Model = Model;
        this.Age = Age;
        this.Price = Price;
        this.Color = Color;
        this.availability = availability;
    }
    int id;
    public Model Model;

    public int Age;

    public Float Price;

    public boolean availability;

    public String Color;

    public String toString() {
        return "Car{Make='" + this.Model.MakeName + "', Model=" + this.Model.ModelName +  "', Age=" + this.Age +  "', Price=" + this.Price +  "', Color=" + this.Color + "', Availability=" + this.availability +"}";
    }

}