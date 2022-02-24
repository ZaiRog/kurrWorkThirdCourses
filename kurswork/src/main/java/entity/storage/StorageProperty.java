package entity.storage;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.Objects;

public class StorageProperty {


    private transient SimpleStringProperty name = new SimpleStringProperty("");
    private transient SimpleDoubleProperty number = new SimpleDoubleProperty(0);
    private transient SimpleStringProperty unit = new SimpleStringProperty("");
    private transient SimpleDoubleProperty cost = new SimpleDoubleProperty(0);
    private transient SimpleStringProperty code = new SimpleStringProperty("");

    public StorageProperty( String name, double number, String unit, double cost, String code) {
        this.name = new SimpleStringProperty(name);
        this.number = new SimpleDoubleProperty(number);
        this.unit = new SimpleStringProperty(unit);
        this.cost = new SimpleDoubleProperty(cost);
        this.code = new SimpleStringProperty(code);
    }

    public StorageProperty(){

    }



    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public Double getNumber() {
        return number.get();
    }

    public SimpleDoubleProperty numberProperty() {
        return number;
    }

    public void setNumber(double number) {
        this.number.set(number);
    }

    public String getUnit() {
        return unit.get();
    }

    public SimpleStringProperty unitProperty() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit.set(unit);
    }

    public Double getCost() {
        return cost.get();
    }

    public SimpleDoubleProperty costProperty() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost.set(cost);
    }

    public String getCode() {
        return code.get();
    }

    public SimpleStringProperty codeProperty() {
        return code;
    }

    public void setCode(String code) {
        this.code.set(code);
    }



    public SimpleStringProperty getPropertyName() {
        return this.name;
    }

    public SimpleDoubleProperty getPropertyNumber() {
        return this.number;
    }

    public SimpleStringProperty getPropertyUnit() {
        return this.unit;
    }

    public SimpleDoubleProperty getPropertyCost() {
        return this.cost;
    }

    public SimpleStringProperty getPropertyCode() {
        return this.code;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StorageProperty that = (StorageProperty) o;
        return  Objects.equals(name, that.name) && Objects.equals(number, that.number) && Objects.equals(unit, that.unit) && Objects.equals(cost, that.cost) && Objects.equals(code, that.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash( name, number, unit, cost, code);
    }

    @Override
    public String toString() {
        return "StorageProperty{" +
                " name=" + name +
                ", number=" + number +
                ", unit=" + unit +
                ", cost=" + cost +
                ", code=" + code +
                '}';
    }

}
