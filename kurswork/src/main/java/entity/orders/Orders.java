package entity.orders;

import java.io.Serializable;
import java.util.Objects;

public class Orders implements Serializable {

    static final long serialVersionUID=1L;

    private String nameOfOrderProduct;
    private double numberOfOrderProduct;
    private double cost;
    private String unitOfOrderProduct;
    private String nameCustomer;
    private String comments;
    private String code;
    private int userId;
    private int storageId;




    public Orders(){

    }

    public String getNameOfOrderProduct() {
        return nameOfOrderProduct;
    }

    public void setNameOfOrderProduct(String nameOfOrderProduct) {
        this.nameOfOrderProduct = nameOfOrderProduct;
    }

    public double getNumberOfOrderProduct() {
        return numberOfOrderProduct;
    }

    public void setNumberOfOrderProduct(double numberOfOrderProduct) {
        this.numberOfOrderProduct = numberOfOrderProduct;
    }

    public String getUnitOfOrderProduct() {
        return unitOfOrderProduct;
    }

    public void setUnitOfOrderProduct(String unitOfOrderProduct) {
        this.unitOfOrderProduct = unitOfOrderProduct;
    }

    public String getNameCustomer() {
        return nameCustomer;
    }

    public void setNameCustomer(String nameCustomer) {
        this.nameCustomer = nameCustomer;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getStorageId() {
        return storageId;
    }

    public void setStorageId(int storageId) {
        this.storageId = storageId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Orders orders = (Orders) o;
        return Double.compare(orders.numberOfOrderProduct, numberOfOrderProduct) == 0 && Double.compare(orders.cost, cost) == 0 && userId == orders.userId && storageId == orders.storageId && Objects.equals(nameOfOrderProduct, orders.nameOfOrderProduct) && Objects.equals(unitOfOrderProduct, orders.unitOfOrderProduct) && Objects.equals(nameCustomer, orders.nameCustomer) && Objects.equals(comments, orders.comments) && Objects.equals(code, orders.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nameOfOrderProduct, numberOfOrderProduct, cost, unitOfOrderProduct, nameCustomer, comments, code, userId, storageId);
    }

    @Override
    public String toString() {
        return "Orders{" +
                "nameOfOrderProduct='" + nameOfOrderProduct + '\'' +
                ", numberOfOrderProduct=" + numberOfOrderProduct +
                ", cost=" + cost +
                ", unitOfOrderProduct='" + unitOfOrderProduct + '\'' +
                ", nameCustomer='" + nameCustomer + '\'' +
                ", comments='" + comments + '\'' +
                ", code='" + code + '\'' +
                ", userId=" + userId +
                ", storageId=" + storageId +
                '}';
    }
}
