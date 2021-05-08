import Media.Media;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 * Responsible of painting all the elements on screen that need to be repainted.
 */
public class Painter {
    
    private static Toolkit t= Toolkit.getDefaultToolkit();
    private ArrayList<Sprite> sprites = new ArrayList<>();
    
    private JFrame gameframe;
    
    /**
     * Initializes the Painter object by creating and setting the game window.
     */
    public Painter() {
        Dimension screenSize = t.getScreenSize();
        int size = Math.min(screenSize.height, screenSize.width);
        
        gameframe = new JFrame("SwingPacman");
        gameframe.setSize(size, size);
        gameframe.setResizable(true);
        gameframe.getContentPane().setBackground(Color.black);
        gameframe.setUndecorated(true);
        
        gameframe.setLayout(null);
        gameframe.setVisible(true);
    
        gameframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    /**
     * Initializes a Sprite on the game screen by creating
     *  a corrisponding Swing image and adding it to the HashMap.
     * @param sprite the spite to initialize
     */
    public void registerSprite(Sprite sprite) {
        if ( !sprites.contains(sprite)) {
            sprites.add(sprite);
            gameframe.add(sprite);
            sprite.repaint();
        }
    }
    
    
    ///////////////////
    // Getters and Setters below
    
    
}
