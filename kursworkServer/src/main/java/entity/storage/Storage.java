package entity.storage;

import java.io.Serializable;
import java.util.Objects;

public class Storage implements Serializable {

    static final long serialVersionUID=1L;

    private String name;
    private double number;
    private String unit;
    private double cost;
    private String code;

    public Storage() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getNumber() {
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

    public double getCost() {
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
        return Double.compare(storage.number, number) == 0 &&
                Double.compare(storage.cost, cost) == 0 &&
                Objects.equals(name, storage.name) &&
                Objects.equals(unit, storage.unit) &&
                Objects.equals(code, storage.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash( name, number, unit, cost, code);
    }

    @Override
    public String toString() {
        return "Storage{" +
                " name='" + name + '\'' +
                ", number=" + number +
                ", unit='" + unit + '\'' +
                ", cost=" + cost +
                ", code='" + code + '\'' +
                '}';
    }
}
