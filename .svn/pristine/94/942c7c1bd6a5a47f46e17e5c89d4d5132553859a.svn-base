package com.spanel.views.schedule.imge;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by koria on 31/12/2016.
 */

public class SaveIconFile extends JPanel{
    private JPanel pan;
    public SaveIconFile(JPanel pan){
        this.pan = pan;

    }
    public BufferedImage getImagePanneau()
    {
        // récupérer une image du panneau
        int width  = pan.getWidth();
        int height = pan.getHeight();
        BufferedImage image = new BufferedImage(width, height,  BufferedImage.TYPE_INT_RGB);
        Graphics2D g = image.createGraphics();
        pan.paintAll(g);
        g.dispose();
        return image;
    }
  public void enregistrerImage(File fichierImage)
    {
        String format ="JPG";
        BufferedImage image = getImagePanneau();
        try {
            ImageIO.write(image, format, fichierImage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }






}
