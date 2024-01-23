// Takes an image as input and outputs grid on top of image
// credit : https://stackoverflow.com/questions/22728431/laying-grids-above-images
package com.mainpackage;


import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class ImageGrid extends JLabel {

    private static final int CELLS = 15;

    BufferedImage img;

    public ImageGrid() {
        try {
            img = ImageIO.read(getClass().getResource("/woodenboard.png"));
            setIcon(new ImageIcon(img));
        } catch (IOException ex) {
            Logger.getLogger(ImageGrid.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (img != null) {
            int cellHeight = (int) ((getHeight()-10) / CELLS);
            int cellWidth = (int) ((getWidth()-10) / CELLS);
            final int x = 18;
            final int y = 18;
            for(int i=0;i<CELLS;i++) {
                g.drawLine(x, y+cellWidth*i, x+cellWidth*(CELLS-1), y+cellWidth*i);
            }
            for(int j=0;j<CELLS;j++) {
                g.drawLine(x+cellHeight*j, y, x+cellHeight*j, y+cellHeight*(CELLS-1));
            }
        }
    }


}
