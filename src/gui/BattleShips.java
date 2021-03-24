package gui;

import com.sun.tools.javac.Main;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;

public class BattleShips extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("stylefiles/begin.fxml"));
        stage.setScene(new Scene(root));

        try {
            URL iconURL = Main.class.getResource("./images/icon.png");
            java.awt.Image image = new ImageIcon(iconURL).getImage();
            com.apple.eawt.Application.getApplication().setDockIconImage(image);
        } catch (Exception e) {
            e.getMessage();
        }

        stage.setTitle("Battleships");
        stage.setResizable(false);
        stage.getIcons().add(new Image("./images/icon.png"));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
