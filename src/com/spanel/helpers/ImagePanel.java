package com.spanel.helpers;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Joel on 06/11/2016.
 */
public class ImagePanel extends JPanel {
    private BufferedImage image;
    private JPanel pan;
    private JButton button;

    public ImagePanel(String monFichierImage, JPanel pan) {
        this.pan = pan;
        this.button = button;
        image = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
        try {
            image = ImageIO.read(new File(monFichierImage));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, getParent().getWidth(), getParent().getHeight(), button);


    }

    public BufferedImage getImage() {
        return image;
    }

    public ImageIcon toImageIcon(){
        return new ImageIcon(image);
    }

    public JLabel toJLabel(){
        return new JLabel(toImageIcon());
    }

    public  JLabel get(String path){

        return new ImagePanel(path,pan).toJLabel();
    }
}
