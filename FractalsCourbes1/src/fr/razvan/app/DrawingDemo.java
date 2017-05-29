package fr.razvan.app;
/*
 * This work by W. Patrick Hooper is free of known copyright restrictions.
 * The work is in the public domain.
 * 
 * Author's website: <a href="http://wphooper.com">http://wphooper.com</a>. 
 */

import java.awt.*;
import java.awt.geom.*;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Demonstrates a very simple drawing in Java.
 *
 * @author W. Patrick Hooper <wphooper@gmail.com>
 */
public class DrawingDemo extends JPanel {

    /**
	 * 
	 */
	private static final long serialVersionUID = -6704667376481592071L;

	/** Default constructor. */
    public DrawingDemo() {
        this.setBackground(Color.WHITE); // Make the background color white
    }

    /** This is called when we need to draw something. */
    @Override
    public void paintComponent(Graphics gfx) {
        // This stuff is standard:
        super.paintComponent(gfx); // Paint the component (such as the background).
        Graphics2D g = (Graphics2D) gfx; // Better to draw with a Graphics2D object.
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON); // Anti-aliasing looks pretty!

        // We are drawing to a rectangle representing the visible portion of the screen.
        // The upper left corner of the rectangle has coordinates (0,0).
        // The lower right corner is (getWidth(), getHeight()). 
        
        // Construct a rectangle with one vertex (0,0) with width getWidth()/2 and 
        // height getHeight()/2.
        Rectangle2D rect = new Rectangle2D.Double(0, 0, getWidth() / 2.0, getHeight() / 2.0);
        // Fill in the rectangle in red 
        g.setColor(Color.RED);
        g.fill(rect);

        // Consruct an ellipse:
        Ellipse2D e = new Ellipse2D.Double(getWidth() / 2.0, getHeight() / 2.0, getWidth() / 2.0, getHeight() / 2.0);
        // Draw it in light blue with a line of width 3.5 pixels.
        g.setColor(new Color(145, 167, 249)); // red green and blue components of color
        g.setStroke(new BasicStroke(3.5f));
        g.draw(e);
        
        // Fill in and an orange triangle, and outline it in gray.
        GeneralPath tri=new GeneralPath();
        tri.moveTo(getWidth()/2.0, 6*getHeight()/10.0);
        tri.lineTo(9*getWidth()/10.0, getHeight()/2.0);
        tri.lineTo(7*getWidth()/10.0, getHeight()/10.0);
        tri.closePath();
        g.setColor(Color.ORANGE);
        g.fill(tri);
        g.setColor(Color.GRAY);
        g.draw(tri);
        
        // Draw a large translucent line segment joining (0,0) to (W,H). 
        Line2D line=new Line2D.Double(0,0, getWidth(), getHeight());
        g.setColor(new Color(0,0,0,64)); // Draw in black, but translucent! 
        g.setStroke(new BasicStroke(20));
        g.draw(line);
    }

    /** Create a window and put our panel on display inside it. */
    public static void main(String[] args) {
        JFrame frame = new JFrame("Drawing Demo");
        // Dimensions of the window in pixels:
        frame.setSize(640, 480);
        // Quit the program when the window is closed:
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // The window will contain only our panel:
        DrawingDemo dd=new DrawingDemo();
        frame.add(dd);
        // Make the window visible:
        frame.setVisible(true);
    }
}
