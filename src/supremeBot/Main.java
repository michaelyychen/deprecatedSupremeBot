/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supremeBot;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author MC
 */
public class Main extends Application {

    static homeViewController myControllerHandle;
    static FileWriter  fw ;
    static BufferedWriter bw;
    static Semaphore sem;
    static Semaphore semF;
    Stage primaryStage;
   
    
    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("homeView.fxml"));

        Parent root = loader.load();

        myControllerHandle = (homeViewController) loader.getController();

        Scene scene = new Scene(root);

        stage.setOnCloseRequest((WindowEvent we) -> {
            try {
                myControllerHandle.handleExitRequest();
                bw.close();
                fw.close();
                Platform.exit();
                System.exit(0);
            } catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        stage.setTitle("MC BOT v 3.3N");
        stage.setScene(scene);
        stage.show();
              
    }


    public static void main(String[] args) throws IOException {      
        fw = new FileWriter("speedResult.txt");
        bw = new BufferedWriter(fw);
        sem = new Semaphore(1);
        semF = new Semaphore(1);
        
        //Depends on which os the program is running on.
        if (System.getProperty("os.name").startsWith("W")) {
            System.setProperty("phantomjs.binary.path", "phantomjs.exe");
            System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        } else {
            System.setProperty("phantomjs.binary.path", "/Desktop/phantomjs");
            System.setProperty("webdriver.chrome.driver", "/Desktop/chromedriver");
        }
        
        launch(args);
    }

}
