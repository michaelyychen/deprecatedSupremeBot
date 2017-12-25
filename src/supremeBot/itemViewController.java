/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supremeBot;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author MC
 */
public class itemViewController implements Initializable {

    homeViewController homeController;

    @FXML
    private ComboBox<String> categoryBox;

    @FXML
    private ComboBox<String> expMonthBox;

    @FXML
    private ComboBox<String> expYearBox;

    @FXML
    private ComboBox<String> sizeBox;

    @FXML
    private TextField nameValue;

    @FXML
    private TextField emailValue;

    @FXML
    private TextField phoneValue;

    @FXML
    private TextField addrValue;

    @FXML
    private TextField cityValue;

    @FXML
    private TextField zipValue;

    @FXML
    private TextField stateValue;

    @FXML
    private TextField cardValue;

    @FXML
    private TextField cvvValue;

    @FXML
    private TextField itemValue;

    @FXML
    private TextField colorValue;
    @FXML
    public Button closeButton;
    @FXML
    public Button editButton;
    
    @FXML 
    private SplitPane sp;
    
    Stage currentStage;
    int indexToUpdate;
    
    @FXML
    private void handleFinishedAction(ActionEvent event) {

        Account newTarget = new Account();
        newTarget.setName(nameValue.getText().trim());
        newTarget.setPhone(phoneValue.getText().trim());
        newTarget.setEmail(emailValue.getText().trim());
        newTarget.setAddress1(addrValue.getText().trim());
        newTarget.setCity(cityValue.getText().trim());
        newTarget.setZip(zipValue.getText().trim());
        newTarget.setState(stateValue.getText().trim());
        newTarget.setCredit(cardValue.getText().trim());
        newTarget.setCreditmonth(expMonthBox.getSelectionModel().getSelectedItem());
        newTarget.setCredityear(expYearBox.getSelectionModel().getSelectedItem());
        newTarget.setCvv(cvvValue.getText().trim());
        newTarget.setCategory(categoryBox.getSelectionModel().getSelectedItem());
        newTarget.setItem(itemValue.getText().trim());
        newTarget.setColor(colorValue.getText().trim());
        newTarget.setSize(sizeBox.getSelectionModel().getSelectedItem());
        newTarget.setType("Visa");
        newTarget.setStatus("Ready");

        homeViewController h = Main.myControllerHandle;
        h.data.add(newTarget);
        
        currentStage = (Stage)sp.getScene().getWindow();
        currentStage.close();

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        categoryBox.getItems().addAll("jackets", "shirts", "tops_sweaters", "t-shirts", "sweatshirts", "hats", "bags", "accessories", "shoes");
        expMonthBox.getItems().addAll("01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12");
        expYearBox.getItems().addAll("2017", "2018", "2019", "2020", "2021", "2022");
        sizeBox.getItems().addAll("Small", "Medium", "Large", "XLarge", "8", "8.5", "9", "9.5", "10", "10.5", "11", "11.5", "Free");

        categoryBox.getSelectionModel().selectFirst();
        expMonthBox.getSelectionModel().selectFirst();
        expYearBox.getSelectionModel().selectFirst();
        sizeBox.getSelectionModel().selectFirst();

        editButton.setDisable(true);
        editButton.setVisible(false);

    }

    public void editItem(int index,Account a) {
        editButton.setDisable(false);
        editButton.setVisible(true);
        closeButton.setDisable(true);
        closeButton.setVisible(false);
        
        
        nameValue.setText(a.getName());
        phoneValue.setText(a.getPhone());
        emailValue.setText(a.getEmail());
        addrValue.setText(a.getAddress1());
        cityValue.setText(a.getCity());
        zipValue.setText(a.getZip());
        stateValue.setText(a.getState());
        cardValue.setText(a.getCredit());
        expMonthBox.getSelectionModel().select(a.getCreditmonth());
        expYearBox.getSelectionModel().select(a.getCredityear());
        cvvValue.setText(a.getCvv());
        categoryBox.getSelectionModel().select(a.getCategory());
        itemValue.setText(a.getItem());
        colorValue.setText(a.getColor());
        sizeBox.getSelectionModel().select(a.getSize());
        a.setType("Visa");
        a.setStatus("Ready");    
        
        indexToUpdate = index;
        
    }
    
     @FXML
    private void handleEditRequest(ActionEvent event) {

        System.out.println("Finished Edit");
        
        
        Account newTarget = new Account();
        newTarget.setName(nameValue.getText().trim());
        newTarget.setPhone(phoneValue.getText().trim());
        newTarget.setEmail(emailValue.getText().trim());
        newTarget.setAddress1(addrValue.getText().trim());
        newTarget.setCity(cityValue.getText().trim());
        newTarget.setZip(zipValue.getText().trim());
        newTarget.setState(stateValue.getText().trim());
        newTarget.setCredit(cardValue.getText().trim());
        newTarget.setCreditmonth(expMonthBox.getSelectionModel().getSelectedItem());
        newTarget.setCredityear(expYearBox.getSelectionModel().getSelectedItem());
        newTarget.setCvv(cvvValue.getText().trim());
        newTarget.setCategory(categoryBox.getSelectionModel().getSelectedItem());
        newTarget.setItem(itemValue.getText().trim());
        newTarget.setColor(colorValue.getText().trim());
        newTarget.setSize(sizeBox.getSelectionModel().getSelectedItem());
        newTarget.setType("Visa");
        newTarget.setStatus("Ready");
        
        
        Main.myControllerHandle.data.set(indexToUpdate,newTarget);
        currentStage = (Stage)sp.getScene().getWindow();
        currentStage.close();
        
    }
    
}
