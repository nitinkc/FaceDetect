package com.nitin.gui;

import com.nitin.Properties;
import com.nitin.faceDetection.Algo;
import com.nitin.faceDetection.Detector;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Created by nichaurasia on Wednesday, June/10/2020 at 3:59 PM
 */

public class MainFrame extends JFrame {
    private static final long serialVersionUID = 1L;

    private ImagePanel imagePanel;
    private JFileChooser fileChooser;
    private Algo faceDetection;
    private File file;

    private Detector processor;
    private CameraPanel cameraPanel;

    //Constructor
    public MainFrame(){
        super(Properties.APPLICATION_NAME);

        //Create the menu bar
        setJMenuBar(createMenuBar());

        //Creating the File Chooser
        this.fileChooser = new JFileChooser();

        this.faceDetection = new Algo();

        //Image Panel to display the image
        this.imagePanel=new ImagePanel();

        add(imagePanel, BorderLayout.CENTER);

        setSize(Properties.FRAME_WIDTH, Properties.FRAME_HEIGHT);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(this);
    }

    public JMenuBar createMenuBar(){

        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu(Properties.JMENU);

        JMenuItem loadMenuItem = new JMenuItem(Properties.JMENU_ITEMS[0]);
        JMenuItem detectMenuItem = new JMenuItem(Properties.JMENU_ITEMS[1]);
        JMenuItem cameraMenuItem = new JMenuItem(Properties.JMENU_ITEMS[3]);
        JMenuItem exitMenuItem = new JMenuItem(Properties.JMENU_ITEMS[2]);

        fileMenu.add(loadMenuItem);
        fileMenu.add(detectMenuItem);
        fileMenu.add(cameraMenuItem);
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        /***************************************************************************************************************
         *****************************************ACTION LISTENER ******************************************************
         **************************************************************************************************************/

        //Upon selecting the file load option
        loadMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                if(fileChooser.showOpenDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION){
                    MainFrame.this.file = fileChooser.getSelectedFile();
                    MainFrame.this.imagePanel.loadImage(MainFrame.this.file);
                }
            }
        });

        //Upon selecting the run Face detect algo
        detectMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                MainFrame.this.faceDetection.detectFaces(MainFrame.this.file, MainFrame.this.imagePanel);
            }
        });

        cameraMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               new CameraFrame();
            }
        });

        exitMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {

                int action = JOptionPane.showConfirmDialog(MainFrame.this, Properties.EXIT_WARNING,"Warning",JOptionPane.YES_NO_OPTION);

                if( action == JOptionPane.OK_OPTION ){
                    System.gc();
                    System.exit(0);
                }
            }
        });

        return menuBar;
    }
}
