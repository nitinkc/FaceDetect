package com.nitin.faceDetection;

import org.opencv.core.*;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

/**
 * Created by nichaurasia on Wednesday, June/10/2020 at 10:01 PM
 */

public class Detector {
    private CascadeClassifier cascadeClassifier;
    private MatOfRect detectedFaces;
    private Mat coloredImage;
    private Mat greyImage;

    public Detector() {
        this.detectedFaces = new MatOfRect();
        this.coloredImage = new Mat();
        this.greyImage = new Mat();
        this.cascadeClassifier = new CascadeClassifier("./src/main/resources/haarcascade_frontalface_alt.xml");
    }

    public Mat detect(Mat inputframe) {

        inputframe.copyTo(coloredImage);
        inputframe.copyTo(greyImage);

        Imgproc.cvtColor(coloredImage, greyImage, Imgproc.COLOR_BGR2GRAY);
        Imgproc.equalizeHist(greyImage, greyImage);

        cascadeClassifier.detectMultiScale(greyImage, detectedFaces);

        showFacesOnScreen();

        return coloredImage;
    }

    private void showFacesOnScreen(){
        for (Rect rect : detectedFaces.toArray()) {
            /*Imgproc.rectangle(coloredImage, new Point(rect.x, rect.y), new Point(
                    rect.x + rect.width, rect.y + rect.height), new Scalar(100,
                    100, 255), 3);*/

            Point center = new Point(rect.x + rect.width / 2, rect.y + rect.height / 2);
            Imgproc.ellipse(coloredImage, center, new Size(rect.width / 2, rect.height / 2), 0, 0, 360,
                    new Scalar(100, 100, 255),5);
            Mat faceROI = greyImage.submat(rect);
        }
    }
}
