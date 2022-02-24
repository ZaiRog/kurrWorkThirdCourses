package entity.orders;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.Objects;

public class OrdersProperty {


    private transient SimpleStringProperty nameOfOrderProduct = new SimpleStringProperty("");
    private transient SimpleDoubleProperty numberOfOrderProduct = new SimpleDoubleProperty(0);
    private transient SimpleStringProperty unitOfOrderProduct = new SimpleStringProperty("");
    private transient SimpleDoubleProperty cost = new SimpleDoubleProperty(0);
    private transient SimpleStringProperty nameCustomer = new SimpleStringProperty("");
    private transient SimpleStringProperty comments = new SimpleStringProperty("");
    private transient SimpleStringProperty code = new SimpleStringProperty("");
    private transient SimpleIntegerProperty userId = new SimpleIntegerProperty(0);
    private transient SimpleIntegerProperty storageId = new SimpleIntegerProperty(0);





    public OrdersProperty(String nameOfOrderProduct, double numberOfOrderProduct, String unitOfOrderProduct,double cost, String nameCustomer, String comments, String code, int userId, int storageId) {
        this.nameOfOrderProduct = new SimpleStringProperty(nameOfOrderProduct);
        this.numberOfOrderProduct = new SimpleDoubleProperty(numberOfOrderProduct);
        this.unitOfOrderProduct = new SimpleStringProperty(unitOfOrderProduct);
        this.cost = new SimpleDoubleProperty(cost);
        this.nameCustomer = new SimpleStringProperty(nameCustomer);
        this.comments = new SimpleStringProperty(comments);
        this.code = new SimpleStringProperty(code);
        this.userId = new SimpleIntegerProperty(userId);
        this.storageId = new SimpleIntegerProperty(storageId);
    }

    public OrdersProperty(){}


    public String getNameOfOrderProduct() {
        return nameOfOrderProduct.get();
    }

    public SimpleStringProperty nameOfOrderProductProperty() {
        return nameOfOrderProduct;
    }

    public void setNameOfOrderProduct(String nameOfOrderProduct) {
        this.nameOfOrderProduct.set(nameOfOrderProduct);
    }

    public double getNumberOfOrderProduct() {
        return numberOfOrderProduct.get();
    }

    public SimpleDoubleProperty numberOfOrderProductProperty() {
        return numberOfOrderProduct;
    }

    public void setNumberOfOrderProduct(double numberOfOrderProduct) {
        this.numberOfOrderProduct.set(numberOfOrderProduct);
    }

    public String getUnitOfOrderProduct() {
        return unitOfOrderProduct.get();
    }

    public SimpleStringProperty unitOfOrderProductProperty() {
        return unitOfOrderProduct;
    }

    public void setUnitOfOrderProduct(String unitOfOrderProduct) {
        this.unitOfOrderProduct.set(unitOfOrderProduct);
    }

    public String getNameCustomer() {
        return nameCustomer.get();
    }

    public SimpleStringProperty nameCustomerProperty() {
        return nameCustomer;
    }

    public void setNameCustomer(String nameCustomer) {
        this.nameCustomer.set(nameCustomer);
    }

    public String getComments() {
        return comments.get();
    }

    public SimpleStringProperty commentsProperty() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments.set(comments);
    }

    public double getCost() {
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

    public int getUserId() {
        return userId.get();
    }

    public SimpleIntegerProperty userIdProperty() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId.set(userId);
    }

    public int getStorageId() {
        return storageId.get();
    }

    public SimpleIntegerProperty storageIdProperty() {
        return storageId;
    }

    public void setStorageId(int storageId) {
        this.storageId.set(storageId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrdersProperty that = (OrdersProperty) o;
        return Objects.equals(nameOfOrderProduct, that.nameOfOrderProduct) && Objects.equals(numberOfOrderProduct, that.numberOfOrderProduct) && Objects.equals(unitOfOrderProduct, that.unitOfOrderProduct) && Objects.equals(cost, that.cost) && Objects.equals(nameCustomer, that.nameCustomer) && Objects.equals(comments, that.comments) && Objects.equals(code, that.code) && Objects.equals(userId, that.userId) && Objects.equals(storageId, that.storageId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nameOfOrderProduct, numberOfOrderProduct, unitOfOrderProduct, cost, nameCustomer, comments, code, userId, storageId);
    }

    @Override
    public String toString() {
        return "OrdersProperty{" +
                "nameOfOrderProduct=" + nameOfOrderProduct +
                ", numberOfOrderProduct=" + numberOfOrderProduct +
                ", unitOfOrderProduct=" + unitOfOrderProduct +
                ", cost=" + cost +
                ", nameCustomer=" + nameCustomer +
                ", comments=" + comments +
                ", code=" + code +
                ", userId=" + userId +
                ", storageId=" + storageId +
                '}';
    }
}
