package com.nitin.gui;

import com.nitin.Properties;

import javax.swing.*;
import java.awt.*;
import java.io.File;

/**
 * Created by nichaurasia on Wednesday, June/10/2020 at 5:20 PM
 */

public class ImagePanel extends JPanel {

    private static final long serialVersionUID = 353462476807463L;
    private JLabel imageLabel;
    private ImageIcon transformedImageIcon;

    public ImagePanel() {

        this.imageLabel = new JLabel();

        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(
                Properties.IMAGE_LABEL_BORDER,
                Properties.IMAGE_LABEL_BORDER,
                Properties.IMAGE_LABEL_BORDER,
                Properties.IMAGE_LABEL_BORDER));

        add(imageLabel, BorderLayout.CENTER);

    }

    public void updateImage(final Image image){
        SwingUtilities.invokeLater(() -> imageLabel.setIcon(new ImageIcon(scaleImage(image))));
    }

    private Image scaleImage(Image image){
        return image.getScaledInstance(Properties.SCALE_WIDTH, Properties.SCALE_HEIGHT,Image.SCALE_SMOOTH);
    }

    public void loadImage(File file) {

        this.transformedImageIcon = new ImageIcon(file.getAbsolutePath());
        Image image = transformedImageIcon.getImage();

        updateImage(image);
    }
}

