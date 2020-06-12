package com.nitin;

import com.nitin.gui.CameraFrame;
import com.nitin.gui.MainFrame;

import javax.swing.*;

/**
 * Created by nichaurasia on Wednesday, June/10/2020 at 3:49 PM
 */

public class Driver {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException
                | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

       SwingUtilities.invokeLater(() -> new MainFrame());
       /* SwingUtilities.invokeLater(() -> {
            CameraFrame cameraFrame = new CameraFrame();
            cameraFrame.displayScreen();
        });*/
       CameraFrame cameraFrame = new CameraFrame();
        cameraFrame.displayScreen();
    }
}
