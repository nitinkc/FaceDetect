package com.nitin.gui;

import org.opencv.core.Mat;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

/**
 * Created by nichaurasia on Wednesday, June/10/2020 at 8:39 PM
 */

public class CameraPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private BufferedImage image;

    public CameraPanel() {

    }

    public boolean convertMatToImage(Mat matBGR) {

        int width = matBGR.width(), height = matBGR.height(), channels = matBGR.channels();
        byte[] sourcePixels = new byte[width * height * channels];
        matBGR.get(0, 0, sourcePixels);

        image = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
        final byte[] targetPixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
        System.arraycopy(sourcePixels, 0, targetPixels, 0, sourcePixels.length);

        return true;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (this.image == null){
            return;
        }

        g.drawImage(this.image, 5, 10, this.image.getWidth(),this.image.getHeight(), null);
    }
}
