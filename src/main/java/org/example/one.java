package org.example;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;

public class one extends Application {
    Stage stage1 ;
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/one.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("快捷程序");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.getIcons().add(new Image("/img/ico.png"));
        Platform.setImplicitExit(false);

        if (SplashScreen.getSplashScreen() != null) {
            Thread.sleep(1000);
            SplashScreen.getSplashScreen().close();
        }
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode()== KeyCode.ESCAPE){
                    stage1.close();
                    small();
                }
            }
        });
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                small();
            }
        });


        stage.show();
        stage1 = stage;
        small();///////////////////////////
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((screenBounds.getWidth() - 600) / 2);
        stage.setY((screenBounds.getHeight() - 400) / 2);

    }

    private void small(){
        MenuItem exit = new MenuItem("关闭");
        MenuItem walk = new MenuItem("离开");
        PopupMenu popupMenu = new PopupMenu();
        popupMenu.add(walk);
        popupMenu.add(exit);
        URL url = this.getClass().getResource("/img/ico16.png");
        java.awt.Image im = Toolkit.getDefaultToolkit().getImage(url);
        TrayIcon trayIcon  = new TrayIcon(im);
        SystemTray tray = SystemTray.getSystemTray();
        trayIcon.setPopupMenu(popupMenu);
        trayIcon.setToolTip("看你麻痹");
        try {
            tray.add(trayIcon);
        } catch (AWTException e) {
            e.printStackTrace();
        }
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(1);
            }
        });
        walk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    live();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                } catch (AWTException awtException) {
                    awtException.printStackTrace();
                }
            }
        });
    }
    private void live() throws IOException, AWTException {
        String ccc = "taskkill /f /im qq.exe";
        String eee = "taskkill /f /im Wechat.exe";
        String lock = "Rundll32.exe user32.dll,LockWorkStation";
        Runtime.getRuntime().exec(ccc);
        Runtime.getRuntime().exec(eee);
        Runtime.getRuntime().exec(lock);
        //System.out.println("第一次测试更新");

    }
}
