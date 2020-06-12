package com.nitin.faceDetection;

import com.nitin.Properties;
import com.nitin.gui.ImagePanel;
import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by nichaurasia on Wednesday, June/10/2020 at 6:03 PM
 */

public class Algo {
    private CascadeClassifier cascadeClassifier;
    static Logger logger = Logger.getLogger(Algo.class.getName());


    public Algo() {
        nu.pattern.OpenCV.loadLocally(); // Use in case loadShared() doesn't work
        
        String filenameFaceCascade =  "./src/main/resources/haarcascade_frontalface_alt.xml";

        this.cascadeClassifier = new CascadeClassifier();
        
        if (!cascadeClassifier.load(filenameFaceCascade)) {
            System.err.println("--(!)Error loading face cascade: " + filenameFaceCascade);
            System.exit(0);
        }

        logger.log(Level.FINER, "Testing");
    }

    public void detectFaces(File file, ImagePanel imagePanel) {

        Mat image = Imgcodecs.imread(file.getAbsolutePath(),Imgcodecs.CV_LOAD_IMAGE_COLOR);

        MatOfRect faceDetections = new MatOfRect();

        cascadeClassifier.detectMultiScale(
                image,
                faceDetections,
                1.2,
                3,
                10,
                new Size(20, 20), new Size(500, 500));

        System.out.println("Num of faces detected: "+faceDetections.toArray().length);

        //Make Rectangle around the detected image
        for (Rect rect : faceDetections.toArray()) {
            Imgproc.rectangle(image, new Point(rect.x, rect.y), new Point(
                    rect.x + rect.width, rect.y + rect.height), new Scalar(100,
                    100, 250), 10);
        }

        BufferedImage bufImage =  convertMatToImage(image);
        imagePanel.updateImage(bufImage);
    }

    public BufferedImage convertMatToImage(Mat mat){

        int type = BufferedImage.TYPE_BYTE_GRAY;
        if ( mat.channels() > 1 ) {
            type = BufferedImage.TYPE_3BYTE_BGR;
        }

        int bufferSize = mat.channels()*mat.cols()*mat.rows();
        byte [] bytes = new byte[bufferSize];
        mat.get(0,0,bytes); // get all the pixels
        BufferedImage image = new BufferedImage(mat.cols(),mat.rows(), type);
        final byte[] targetPixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
        System.arraycopy(bytes, 0, targetPixels, 0, bytes.length);
        return image;
    }
}
