/*
 * Copyright (C) 2012 W. Patrick Hooper <wphooper@gmail.com>
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package fr.razvan.graphics;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import javax.swing.JPanel;

import fr.razvan.geometry.LineSegment;
import fr.razvan.path.EdgeIterator;
import fr.razvan.path.PathUtil;
import fr.razvan.path.PolygonalPath;

/**
 * A PolygonalPathPanel just displays a PolgonalPath in a JPanel.
 *
 * @author W. Patrick Hooper <wphooper@gmail.com>
 */
public class PolygonalPathPanel extends JPanel {    
    /**
	 * 
	 */
	private static final long serialVersionUID = 8052958712360069573L;
	private PolygonalPath p; // This is the polygonal path we will display.
    private Rectangle2D display_box; // represents the region we want to be sure is displayed.  

    // This affine transformation converts from math coordinates to screen coordinates,
    // guaranteeing that we display everything in the display_box. 
    private AffineTransform current_transform; 
    
    /** Construct a PolygonalPathPanel which displays the provided path */
    public PolygonalPathPanel(PolygonalPath path) {
        p = path;
        display_box=PathUtil.boundingBox(p); // Store the bounding box for p
        this.setBackground(Color.WHITE); // Make the background color white
    }

    /**
     * This function returns an affine transform which sends display_box
     * into the rectangle representing screen coordinates for this panel. 
     * 
     * The affine transformation we return has the following properties. It
     * sends the bounding_box into the panel rectangle, so that the center
     * of the bounding box is sent the center of the panel. The scaling 
     * preserves the aspect ratio.
     */
    private AffineTransform getTransform() {
        /*
         * The component has pixels whose x-coordinates are numbered 0 to
         * getWidth()-1 as we move rightward. The y-coordinates of these pixels
         * increases from 0 to getHeight()-1 as we move downward.
         * 
         * We will return the transformation which takes the bounding box for
         * our curve into the rectangle representing coordinates for our
         * component on the screen. To move this box into the screen we use the
         * following steps: 
         * 1. We translate our box, moving the center of our box to the origin. 
         * 2. We scale the box by a constant so that the image of our box has 
         * width and height less than or equal to the width and height of this 
         * component. 
         * 3. We negate the y-coordinate, because in mathematics the 
         * y-coordinate increases as we move upward. 
         * 4. We translate the origin so that it is moved to the center of the
         * component.
         *
         * These steps are carried out below.
         */

        // Construct a transformation which translates the plane moving the center of 
        // our box to the origin.
        AffineTransform transform =
                AffineTransform.getTranslateInstance(-display_box.getCenterX(),
                -display_box.getCenterY());
                
        // The number scale is the minimal ratio of screen dimensions to bounding box dimensions.
        double scale = Math.min(getWidth()/display_box.getWidth(), 
                getHeight()/display_box.getHeight() );

        // The following line post-composes by scaling the plane by the number "scale".
        // Because we use the same constant in each coordinate, we preserve the aspect ratio.
        transform.preConcatenate(AffineTransform.getScaleInstance(scale, scale));

        // This has the effect of negating the y-coordinate:
        transform.preConcatenate(AffineTransform.getScaleInstance(1, -1));

        // Now translate the origin until it is centered in the component.
        transform.preConcatenate(
                AffineTransform.getTranslateInstance(getWidth()/2, getHeight()/2));

        return transform;
    }
    
    /**
     * This is called when we need to draw the polygonal path.
     */
    @Override
    public void paintComponent(Graphics gfx) {
        // This stuff is standard, and should be in any paintComponent method. 
        super.paintComponent(gfx);
        Graphics2D g = (Graphics2D) gfx;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        // Reset the transformation which converts from math coordinates to screen coordinates.
        current_transform = getTransform();

        // Draw in black, with lines of width equal to one pixel. 
        g.setColor(Color.BLACK);
        g.setStroke(new BasicStroke(1));

        // We now iterate over the segments in the path. 
        EdgeIterator it = p.iterator();
        while (it.hasNext()) {
            LineSegment s = it.next();
            // Constuct a Line2D with the same start and end points.
            // The advantage of this object is that it can be drawn.
            Line2D line_segment = new Line2D.Double(s.startingPoint().re(),
                    s.startingPoint().im(),
                    s.endingPoint().re(),
                    s.endingPoint().im());
            // Convert the line segment into screen coordinates, then draw it.
            g.draw(current_transform.createTransformedShape(line_segment));
        }
    }
}
