package supremeBot;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Account {

    private final SimpleStringProperty name;
    private final SimpleStringProperty email;
    private final SimpleStringProperty phone;
    private final SimpleStringProperty address1;
    private final SimpleStringProperty city;
    private final SimpleStringProperty zip;
    private final SimpleStringProperty state;
    private final SimpleStringProperty type;
    private final SimpleStringProperty credit;
    private final SimpleStringProperty creditmonth;
    private final SimpleStringProperty credityear;
    private final SimpleStringProperty cvv;
    private final SimpleStringProperty item;
    private final SimpleStringProperty color;
    private final SimpleStringProperty size;
    private final SimpleStringProperty category;
    private final SimpleStringProperty status;

    public Account() {
        this.name = new SimpleStringProperty();
        this.email = new SimpleStringProperty();
        this.phone = new SimpleStringProperty();
        this.address1 = new SimpleStringProperty();
        this.city = new SimpleStringProperty();
        this.zip = new SimpleStringProperty();
        this.state = new SimpleStringProperty();
        this.type = new SimpleStringProperty();
        this.credit = new SimpleStringProperty();
        this.creditmonth = new SimpleStringProperty();
        this.credityear = new SimpleStringProperty();
        this.cvv = new SimpleStringProperty();
        this.item = new SimpleStringProperty();
        this.color = new SimpleStringProperty();
        this.size = new SimpleStringProperty();
        this.category = new SimpleStringProperty();
        this.status = new SimpleStringProperty();
    }

    public String getName() {
        return name.get();
    }

    public void setName(String newValue) {
        name.set(newValue);
    }

    public StringProperty nameProperty() {
        return name;
    }

    public String getEmail() {
        return email.get();
    }

    public void setEmail(String newValue) {
        email.set(newValue);
    }

    public StringProperty emailProperty() {
        return email;
    }

    public String getPhone() {
        return phone.get();
    }

    public void setPhone(String newValue) {
        phone.set(newValue);
    }

    public StringProperty phoneProperty() {
        return phone;
    }

    public String getAddress1() {
        return address1.get();
    }

    public void setAddress1(String newValue) {
        address1.set(newValue);
    }

    public StringProperty address1Property() {
        return address1;
    }

    public String getCity() {
        return city.get();
    }

    public void setCity(String newValue) {
        city.set(newValue);
    }

    public StringProperty cityProperty() {
        return city;
    }

    public String getZip() {
        return zip.get();
    }

    public void setZip(String newValue) {
        zip.set(newValue);
    }

    public StringProperty zipProperty() {
        return zip;
    }

    public String getState() {
        return state.get();
    }

    public void setState(String newValue) {
        state.set(newValue);
    }

    public StringProperty stateProperty() {
        return state;
    }

    public String getType() {
        return type.get();
    }

    public void setType(String newValue) {
        type.set(newValue);
    }

    public StringProperty typeProperty() {
        return type;
    }

    public String getCredit() {
        return credit.get();
    }

    public void setCredit(String newValue) {
        credit.set(newValue);
    }

    public StringProperty creditProperty() {
        return credit;
    }

    public String getCreditmonth() {
        return creditmonth.get();
    }

    public void setCreditmonth(String newValue) {
        creditmonth.set(newValue);
    }

    public StringProperty creditmonthProperty() {
        return creditmonth;
    }

    public String getCredityear() {
        return credityear.get();
    }

    public void setCredityear(String newValue) {
        credityear.set(newValue);
    }

    public StringProperty credityeareProperty() {
        return credityear;
    }

    public String getCvv() {
        return cvv.get();
    }

    public void setCvv(String newValue) {
        cvv.set(newValue);
    }

    public StringProperty cvvProperty() {
        return cvv;
    }

    public String getItem() {
        return item.get();
    }

    public void setItem(String newValue) {
        item.set(newValue);
    }

    public StringProperty itemProperty() {
        return item;
    }

    public String getSize() {
        return size.get();
    }

    public void setSize(String newValue) {
        size.set(newValue);
    }

    public StringProperty sizeProperty() {
        return size;
    }

    public String getStatus() {
        return status.get();
    }

    public void setStatus(String newValue) {
        status.set(newValue);
    }

    public StringProperty statusProperty() {
        return status;
    }

    public String getCategory() {
        return category.get();
    }

    public void setCategory(String newValue) {
        category.set(newValue);
    }

    public StringProperty categoryProperty() {
        return category;
    }

    public String getColor() {
        return color.get();
    }

    public void setColor(String newValue) {
        color.set(newValue);
    }

    public StringProperty colorProperty() {
        return color;
    }
}
