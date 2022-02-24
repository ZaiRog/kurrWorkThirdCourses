package entity.storage;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public class Storage implements Serializable {

    static final long serialVersionUID=1L;

    private String name;
    private double number;
    private String unit;
    private double cost;
    private String code;


    public Storage(){

    }

    public Storage( String name, double number, String unit, double cost, String code ) {
        this.name = name;
        this.number = number;
        this.unit = unit;
        this.cost = cost;
        this.code = code;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getNumber() {
        return number;
    }

    public void setNumber(double number) {
        this.number = number;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Storage storage = (Storage) o;
        return  number == storage.number && cost == storage.cost && Objects.equals(name, storage.name) && Objects.equals(unit, storage.unit) && Objects.equals(code, storage.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash( name, number, unit, cost, code);
    }

    @Override
    public String toString() {
        return "Storage{" +
                ", name='" + name + '\'' +
                ", number=" + number +
                ", unit='" + unit + '\'' +
                ", cost=" + cost +
                ", code='" + code + '\'' +
                '}';
    }
}
