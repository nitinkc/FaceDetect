package com.nitin.gui;

import com.nitin.faceDetection.Detector;
import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;

import javax.swing.*;


/**
 * Created by nichaurasia on Wednesday, June/10/2020 at 11:11 PM
 */

public class CameraFrame extends JFrame{

    private static final long serialVersionUID = 1L;
    private Detector processor;
    private CameraPanel cameraPanel;

    public CameraFrame() {
        super("Face Detection");

        //System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        nu.pattern.OpenCV.loadLocally(); // Use in case loadShared() doesn't work

        processor = new Detector();
        cameraPanel = new CameraPanel();

        setContentPane(cameraPanel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 500);
        setVisible(true);

        displayScreen();
    }

    public void displayScreen() {

        Mat webcamImage = new Mat();
        VideoCapture videoCapture = new VideoCapture(0);

        if (videoCapture.isOpened()) {

            while (true) {

                videoCapture.read(webcamImage);

                if (!webcamImage.empty()) {
                    setSize(webcamImage.width() + 50, webcamImage.height() + 70);
                    webcamImage = processor.detect(webcamImage);
                    cameraPanel.convertMatToImage(webcamImage);
                    cameraPanel.repaint();
                } else {
                    System.out.println("Problem");
                    break;
                }
            }
        }
    }
}