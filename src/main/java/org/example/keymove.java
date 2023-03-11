package org.example;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 *ASCII码
 * a    97
 * A    65
 * 0    48
 * 1    49
 */
public class keymove {

    public keymove(String str) {
        char[]  arr = str.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            int a = arr[i];
            System.out.println(a);
        }
        move(arr);
    }
    public void move(char[] arr){
        try {
            Robot ro = new Robot();
            for (int i = 0; i < arr.length; i++) {
                int a= arr[i];
                if (65<=a&&a<=90){
                    ro.keyPress(KeyEvent.VK_SHIFT);
                    ro.keyPress(a);
                    ro.keyRelease(a);
                    ro.keyRelease(KeyEvent.VK_SHIFT);
                }else if (97<=a&&a<=122){
                    ro.keyPress(a-32);
                    ro.keyRelease(a-32);
                }
                else if (48<=a&&a<=57){
                    ro.keyPress(a);
                    ro.keyRelease(a);
                }
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }
}