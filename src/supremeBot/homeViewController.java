/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supremeBot;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import static supremeBot.PhantomFXML.myControllerHandle;

/**
 *
 * @author MC
 */
public class homeViewController implements Initializable {

    Stage itemStage;

    homeViewController controller;

    Account a;
    ObservableList<Account> data = FXCollections.observableArrayList();
    ArrayList<Phantom> list = new ArrayList<Phantom>();

    @FXML
    public TableView<Account> mainTable;

    @FXML
    private TableColumn nameCol;
    @FXML
    private TableColumn emailCol;
    @FXML
    private TableColumn addrCol;
    @FXML
    private TableColumn cardCol;
    @FXML
    private TableColumn ItemCol;
    @FXML
    private TableColumn categoryCol;
    @FXML
    private TableColumn colorCol;
    @FXML
    private TableColumn sizeCol;
    @FXML
    private TableColumn statusCol;

    @FXML
    private Button startBtn;
    
    Account clipboard;
    
    @FXML
    private void handleAddAction(ActionEvent event) throws IOException {

        Parent itemView = FXMLLoader.load(getClass().getResource("itemView.fxml"));

        Scene scene = new Scene(itemView);
        itemStage = new Stage();
        itemStage.setTitle("Add Item");
        itemStage.setScene(scene);
        itemStage.show();
        
//        itemStage.setOnCloseRequest((WindowEvent we) -> {
//            itemStage.close();
//        });
        

    }

    @FXML
    private void handleClickAction(MouseEvent event) throws IOException {
        TableRow<Account> row = new TableRow<>();
        
        Account temp = mainTable.getSelectionModel().getSelectedItem();
        
        if (event.getClickCount() == 2 && (temp!=null)) {
            
            System.out.println("Click Action");
           
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("itemView.fxml"));

            Parent root = loader.load();
            itemViewController itemController = (itemViewController) loader.getController();

            Scene scene = new Scene(root);
            itemStage = new Stage();
            itemStage.setTitle("Edit Item");
            itemStage.setScene(scene);
            itemStage.show();
            
            itemController.editItem(mainTable.getSelectionModel().getFocusedIndex(),temp);
            
//            itemStage.setOnCloseRequest((WindowEvent we) -> {
//                itemStage.close();
//            });
            }
        }
        @FXML
        private void handleContextDeleteAction(ActionEvent event) {
        Account temp = mainTable.getSelectionModel().getSelectedItem();
        if(temp!=null)
            data.remove(temp);
        
        }
        
        @FXML
        private void handleKeyAction(KeyEvent event) {
        
        if (event.getCode().equals(KeyCode.DELETE)) {
            //Delete or whatever you like:       
            data.remove(mainTable.getSelectionModel().getSelectedItem());
        }                
        if (event.getCode() == KeyCode.C && event.isControlDown()) {
             clipboard = mainTable.getSelectionModel().getSelectedItem();
                
        }
        
        if (event.getCode() == KeyCode.V && event.isControlDown()) {
            if(clipboard!=null)
                data.add(clipboard);
                
        }
        
        }
        
        
        @FXML
        private void handleContextReplaceAction(ActionEvent event) throws IOException {
            
            Account temp = mainTable.getSelectionModel().getSelectedItem();
            

            FXMLLoader loader = new FXMLLoader(getClass().getResource("dialog.fxml"));

            Parent root = loader.load();

            dialogController c = (dialogController) loader.getController();
            
            
            Scene scene = new Scene(root);
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Replace All");
            dialogStage.setScene(scene);
            dialogStage.show();
            
            if(temp!=null)
                c.populateOldName(temp.getItem());
                
        }
        
        
        
        @FXML
        private void handleStartRequest(ActionEvent event) throws InterruptedException {
        startBtn.setDisable(true);
        startBtn.setMinWidth(100);
        startBtn.setText("Running...");

//           for (Account user : data) {
//
//                Thread one;
//                one = new Thread() {
//
//                    public void run() {
//
//                        try {
//                            Phantom p = new Phantom();
//                            list.add(p);
//                            String target = "//div[h1='" + user.getItem() + "' and p='" + user.getColor() + "']/a";
//                            p.runPhantom("http://www.supremenewyork.com/shop/all/" + user.getCategory(), By.xpath(target), user.getSize(), user, user.getItem(), user.getColor());
//                        } catch (InterruptedException ex) {
//                            Logger.getLogger(homeViewController.class.getName()).log(Level.SEVERE, null, ex);
//                        } catch (IOException ex) {
//                            Logger.getLogger(homeViewController.class.getName()).log(Level.SEVERE, null, ex);
//                        } catch (ParseException ex) {
//                            Logger.getLogger(homeViewController.class.getName()).log(Level.SEVERE, null, ex);
//                        }
//
//                    }
//
//                };
//
//                one.start();
//
//                Thread.sleep(100);
//            }

ExecutorService executorService = Executors.newFixedThreadPool(data.size());
for(Account user : data){
executorService.execute(new Runnable() {
    public void run() {
        try {
            Phantom p = new Phantom();
            list.add(p);
            String target = "//div[h1='" + user.getItem() + "' and p='" + user.getColor() + "']/a";
            p.runPhantom("http://www.supremenewyork.com/shop/all/" + user.getCategory(), By.xpath(target), user.getSize(), user, user.getItem(), user.getColor());
        } catch (InterruptedException ex) {
            Logger.getLogger(homeViewController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(homeViewController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(homeViewController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
        );
}
executorService.shutdown();


        }

        @Override
        public void initialize (URL url, ResourceBundle rb) {
        mainTable.setEditable(true);
            mainTable.setItems(data);

            nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
            emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
            addrCol.setCellValueFactory(new PropertyValueFactory<>("address1"));
            cardCol.setCellValueFactory(new PropertyValueFactory<>("credit"));
            ItemCol.setCellValueFactory(new PropertyValueFactory<>("item"));
            categoryCol.setCellValueFactory(new PropertyValueFactory<>("category"));
            colorCol.setCellValueFactory(new PropertyValueFactory<>("color"));
            sizeCol.setCellValueFactory(new PropertyValueFactory<>("size"));
            statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));

            loadUser();
        }

    

    public void handleExitRequest() {
        System.out.println("exiting");
        saveUser(data);

        for (Phantom p : list) {
            p.quit();
        }

    }

    private void saveUser(ObservableList<Account> data) {
        JSONArray ja = new JSONArray();

        for (Account user : data) {

            JSONObject obj = new JSONObject();

            obj.put("Name", user.getName());
            obj.put("Email", user.getEmail());
            obj.put("Phone", user.getPhone());
            obj.put("Address", user.getAddress1());
            obj.put("City", user.getCity());
            obj.put("Zip", user.getZip());
            obj.put("State", user.getState());
            obj.put("Type", user.getType());
            obj.put("Credit", user.getCredit());
            obj.put("Creditmonth", user.getCreditmonth());
            obj.put("Credityear", user.getCredityear());
            obj.put("Cvv", user.getCvv());
            obj.put("Item", user.getItem());
            obj.put("Color", user.getColor());
            obj.put("Size", user.getSize());
            obj.put("Category", user.getCategory());

            ja.add(obj);

        }
        JSONObject obj2 = new JSONObject();

        obj2.put("Users", ja);

        try (FileWriter file = new FileWriter("user.txt")) {
            file.write(obj2.toJSONString());

        } catch (IOException ex) {

        }

    }

    private void loadUser() {
        JSONParser parser = new JSONParser();

        try {

            File f = new File("user.txt");

            if (f.exists()) {
                Object obj = parser.parse(new FileReader("user.txt"));

                // JSONArray ja = (JSONArray) obj;
                JSONObject jsonO = (JSONObject) obj;
                JSONArray slideContent = (JSONArray) jsonO.get("Users");
                Iterator i = slideContent.iterator();

                while (i.hasNext()) {

                    JSONObject jsonObject = (JSONObject) i.next();
                    Account temp = new Account();
                    temp.setName((String) jsonObject.get("Name"));
                    temp.setEmail((String) jsonObject.get("Email"));
                    temp.setPhone((String) jsonObject.get("Phone"));
                    temp.setAddress1((String) jsonObject.get("Address"));
                    temp.setCity((String) jsonObject.get("City"));
                    temp.setZip((String) jsonObject.get("Zip"));
                    temp.setState((String) jsonObject.get("State"));
                    temp.setType((String) jsonObject.get("Type"));
                    temp.setCredit((String) jsonObject.get("Credit"));
                    temp.setCreditmonth((String) jsonObject.get("Creditmonth"));
                    temp.setCredityear((String) jsonObject.get("Credityear"));
                    temp.setCvv((String) jsonObject.get("Cvv"));
                    temp.setItem((String) jsonObject.get("Item"));
                    temp.setColor((String) jsonObject.get("Color"));
                    temp.setSize((String) jsonObject.get("Size"));
                    temp.setCategory((String) jsonObject.get("Category"));
                    temp.setStatus("Ready");
                    data.add(temp);

                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
