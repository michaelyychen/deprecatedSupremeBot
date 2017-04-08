/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supremeBot;

/**
 *
 * @author MC
 */
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * @author MC
 */
public class dialogController implements Initializable {
    
    @FXML
    private TextField oldItemName;
    @FXML
    private TextField newItemName;
    
    @FXML
    private Button okButton;
     @FXML
    private GridPane gp;
     
    @FXML
    private void handleUpdateRequest(ActionEvent event) {
       
    String old = oldItemName.getText();
    String target = newItemName.getText();
    
    List<Account> t = PhantomFXML.myControllerHandle.data;
    
    for(Account a : t){
    
        if(a.getItem().equals(old))
            a.setItem(target);
       
    }
        
        
    Stage currentStage = (Stage)gp.getScene().getWindow();
    currentStage.close();
        
    }
    
    @FXML
    private void handleCancelRequest(ActionEvent event) {
    Stage currentStage = (Stage)gp.getScene().getWindow();
    currentStage.close();
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void populateOldName(String s){
        oldItemName.setText(s);
    }
    
}
