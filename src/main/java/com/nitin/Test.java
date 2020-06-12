package com.nitin;

/**
 * Created by nichaurasia on Wednesday, June/10/2020 at 11:27 PM
 */

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;

import java.io.FileNotFoundException;
import java.io.IOException;


import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.videoio.VideoCapture;

import javax.swing.*;

public class Test {

    public static void main(String[] args) {
        Mat frame = new Mat();
        //0; default video device id
        VideoCapture camera = new VideoCapture(0);
        JFrame jframe = new JFrame("Title");
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel vidpanel = new JLabel();
        jframe.setContentPane(vidpanel);
        jframe.setVisible(true);

        while (true) {
            if (camera.read(frame)) {

                ImageIcon image = new ImageIcon(Mat2bufferedImage(frame));
                vidpanel.setIcon(image);
                vidpanel.repaint();

            }
        }
    }

    private static byte[] Mat2bufferedImage(Mat frame) {
        return new byte[]{};
    }

    /*private static byte[] Mat2bufferedImage(Mat frame) {
        Mat mat = new Mat();
        BufferedImage img;

        public void getSpace(Mat mat) {
            int type = 0;
            if (mat.channels() == 1) {
                type = BufferedImage.TYPE_BYTE_GRAY;
            } else if (mat.channels() == 3) {
                type = BufferedImage.TYPE_3BYTE_BGR;
            }
            this.mat = mat;
            int w = mat.cols();
            int h = mat.rows();
            if (img == null || img.getWidth() != w || img.getHeight() != h || img.getType() != type)
                img = new BufferedImage(w, h, type);
        }

        BufferedImage getImage(Mat mat){
            getSpace(mat);
            WritableRaster raster = img.getRaster();
            DataBufferByte dataBuffer = (DataBufferByte) raster.getDataBuffer();
            byte[] data = dataBuffer.getData();
            mat.get(0, 0, data);
            return img;
        }
    }*/
}