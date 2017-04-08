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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author MC
 */
public class PhantomFXML extends Application {

    static homeViewController myControllerHandle;
    Stage primaryStage;

    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("homeView.fxml"));

        Parent root = loader.load();

        myControllerHandle = (homeViewController) loader.getController();

        Scene scene = new Scene(root);

        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                myControllerHandle.handleExitRequest();
                Platform.exit();
                System.exit(0);
            }
        });

        stage.setTitle("MC BOT v 3.0");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        if (System.getProperty("os.name").startsWith("W")) {
            System.setProperty("phantomjs.binary.path", "phantomjs.exe");
            System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        } else {
            System.setProperty("phantomjs.binary.path", "/Users/MC/Desktop/phantomjs");
            System.setProperty("webdriver.chrome.driver", "/Users/MC/Desktop/chromedriver");
        }

        launch(args);
    }

}
