package com.dursun;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main extends Application {

    private static ApplicationContext applicationContext;

//    @Autowired
//    private IMainBoardService service;

    @Override
    public void start(Stage primaryStage) throws Exception {

        applicationContext = new ClassPathXmlApplicationContext("hibernate-config.xml");
        Parent root = FXMLLoader.load(getClass().getResource("/personel.fxml"));
        primaryStage.setTitle("stok");
        primaryStage.setScene(new Scene(root, 800   , 600));
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
