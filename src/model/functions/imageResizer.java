package model.functions;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class imageResizer {
    private int preferredWidth;
    private int preferredLength;
    private Image image;
    private float scalar;


    public imageResizer(String fileName, int width, int length) throws IOException {
        preferredLength = length;
        preferredWidth = width;
        image = newImage(fileName);
    }

    public Image getImage() {
        return this.image;
    }

    public Image newImage(String fileName) throws IOException {
        BufferedImage image = ImageIO.read(new File(fileName));

        int newHeight = 0;
        int newWidth = 0;

        int height = image.getHeight();
        int width = image.getWidth();

        if ((height <= preferredLength) && (width <= preferredWidth )) {
            if (height > width) {
                scalar = (float) preferredLength / (float) height;
            } else if (width > height) {
                scalar = (float) preferredWidth / (float) width;
            } else if (width == height) {
                scalar = (float) preferredLength / (float) height;
            }
            newHeight = (int) ((float) height * scalar);
            newWidth = (int) ((float) width * scalar);
        } else if ((height > preferredLength) || (width > preferredWidth)) {
            if (height > width) {
                scalar = (float) height / (float) preferredLength;
            } else if (width > height) {
                scalar = (float) width / (float) preferredWidth;
            } else if (width == height) {
                scalar = (float) height / (float) preferredLength;
            }
            newHeight = (int) ((float) height / scalar);
            newWidth = (int) ((float) width / scalar);
        }

        if (newHeight > preferredLength) {
            scalar = (float) height / (float) preferredLength;
            newHeight = (int) ((float) height / scalar);
            newWidth = (int) ((float) width / scalar);
        } else if (newWidth > preferredWidth) {
            scalar = (float) width / (float) preferredWidth;
            newHeight = (int) ((float) height / scalar);
            newWidth = (int) ((float) width / scalar);
        }

        return new ImageIcon(fileName).getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_DEFAULT);
    }

}
