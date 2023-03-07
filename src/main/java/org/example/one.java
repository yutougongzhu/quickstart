package org.example;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.*;
import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.io.*;
import java.net.URL;

public class one extends Application {
    static Boolean issmall = false;
    AnchorPane anchorPane;
    static Stage stage1;

    @Override
    public void start(final Stage stage) throws Exception {
        final Parent root = FXMLLoader.load(getClass().getResource("/fxml/one.fxml"));
        final Parent qqjiemian = FXMLLoader.load(getClass().getResource("/fxml/qq.fxml"));
        Scene scene = new Scene(root);
        final Scene qwe = new Scene(qqjiemian);
        stage.setTitle("快捷程序");
        stage.setResizable(false);
        stage.setScene(scene);
        anchorPane = (AnchorPane)root.lookup("#behand");
        System.out.println(anchorPane);
        stage.getIcons().add(new Image("/img/ico.png"));
        Platform.setImplicitExit(false);
        final Button butqq = (Button) root.lookup("#butqq") ;
        final Button firechose = (Button) qqjiemian.lookup("#fire") ;
        final Button login = (Button) qqjiemian.lookup("#login") ;
        final Button exitqq = (Button) qqjiemian.lookup("#exitqq") ;
        final Button peizhi = (Button) qqjiemian.lookup("#peizhi") ;
        final Button say = (Button) root.lookup("#say") ;
        final javafx.scene.control.TextField textField = (javafx.scene.control.TextField) qqjiemian.lookup("#tf");
        final javafx.scene.control.TextField passfield = (javafx.scene.control.TextField) qqjiemian.lookup("#pass");


        //程序关闭
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                System.exit(1);
            }
        });

        //加载界面
        if (SplashScreen.getSplashScreen() != null) {
            Thread.sleep(1000);
            SplashScreen.getSplashScreen().close();
        }
        say.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent actionEvent) {
                JOptionPane.showMessageDialog(null,"1.esc可以进入托盘模式\n2.f7可以进入cmd控制台模式\n3.右上角关闭退出软件\n" +
                        "4.QQ密码只能为大小写字母加数字\n5.自动输入QQ密码前确认电脑大写关闭");
            }
        });

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                esc(keyEvent,stage);
            }
        });
        qwe.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                esc(keyEvent,stage);
            }
        });

        exitqq.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent actionEvent) {
                String ccc = "taskkill /f /im qq.exe";
                try {
                    Runtime.getRuntime().exec(ccc);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        login.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent actionEvent) {
                String qqw = textField.getText();
                String pa = passfield.getText();
                Runtime run = Runtime.getRuntime();
                try {
                    run.exec(qqw);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                StringSelection text  = new StringSelection(pa);
                clipboard.setContents(text,null);
                System.out.println(pa);
                onqq();

            }
        });

        butqq.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent actionEvent) {
                stage.setScene(qwe);
                anchorPane = (AnchorPane)qqjiemian.lookup("#behand");
                try {
                    String sss = read("qwe.txt");
                    String ddd = read("pasd.txt");
                    passfield.setText(ddd);
                    textField.setText(sss);
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        });
        peizhi.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent actionEvent) {
                String pasd = passfield.getText();
                try {
                    FileWriter fileWriter= new FileWriter("pasd.txt");
                    fileWriter.write(pasd);
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        });
        firechose.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent actionEvent) {
                FileChooser chooser = new FileChooser();
                chooser.setInitialDirectory(new File("C:\\"));
                chooser.setTitle("选择qq路径");
                chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("exe","QQScLauncher.exe"));
                String pa = chooser.showOpenDialog(stage).getAbsolutePath();
                textField.setText(pa);
                File fil = new File("qwe.txt");
                try {
                    FileWriter fileWriter = new FileWriter(fil);
                    fileWriter.write(pa);
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

        //设置窗口位置
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((screenBounds.getWidth() - 600) / 2);
        stage.setY((screenBounds.getHeight() - 400) / 2);
        stage.show();
        stage1=stage;
        lesson ll  = new lesson();

    }

    private void esc(KeyEvent keyEvent ,Stage stage ) {

        if (keyEvent.getCode()== KeyCode.ESCAPE){
            stage.hide();
            if (issmall){

            }else {

                issmall = true;
                small(stage);
            }
        }
    }

    public void small(final Stage stage){
        MenuItem exit = new MenuItem("关闭");
        MenuItem walk = new MenuItem("离开");
        MenuItem xianshi = new MenuItem("显示");
        PopupMenu popupMenu = new PopupMenu();
        popupMenu.add(xianshi);
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
        xianshi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
//                        显示窗口
                        showmainwind();
                    }
                });

            }
        });

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
    }

    public static String read(String path) throws IOException {
        FileReader fileReader = new FileReader(path);
        char[] data = new char[100];
        int length = 0;
        String str = null;
        while ((length = fileReader.read(data))!=-1){
             str = new String(data,0,length);
        }
        return str;
    }

    public static  void onqq(){//公测方法
        Robot ro = null;
        try {
            ro = new Robot();
        Thread.sleep(5000);
        } catch (AWTException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ro.mouseMove(900,590);
        ro.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        ro.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        /**
         * qq登录窗口没法复制
         */
        ro.keyPress(java.awt.event.KeyEvent.VK_ENTER);
        ro.keyRelease(java.awt.event.KeyEvent.VK_ENTER);
    }


    public static void showmainwind() {
        if (one.issmall) {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    stage1.show();
                }
            });
        }

    }

}
