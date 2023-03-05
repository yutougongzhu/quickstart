package org.example;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.awt.*;
import java.io.IOException;

public class setcmd  {
    static Stage stage;
    public setcmd() {

        Platform.runLater(new Runnable() {
            @Override
            public void run() {


                final Stage stage = new Stage();
                Parent pa = null;
                try {
                    pa = FXMLLoader.load(getClass().getResource("/fxml/cmd.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Scene zxc  = new Scene(pa);
                Point p = MouseInfo.getPointerInfo().getLocation();
                stage.setScene(zxc);
                stage.initStyle(StageStyle.UNDECORATED);
                stage.setX(p.getX());
                stage.setY(p.getY());
                stage.setResizable(false);
                stage.setAlwaysOnTop(true);
                stage.setOpacity(0.4);
                final TextField tf = (TextField) pa.lookup("#str");
                tf.setOnKeyPressed(new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent keyEvent) {
                        if (keyEvent.getCode()== KeyCode.ESCAPE){
                            stage.close();
                        }else if (keyEvent.getCode()== KeyCode.ENTER){
                            String qwe = tf.getText();
                            try {
                                Runtime.getRuntime().exec(qwe);
                                tf.setText("");
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
                stage.show();
                setcmd.stage = stage;
            }
        });


    }
}
