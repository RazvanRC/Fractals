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

package fr.razvan.path;

import java.awt.geom.Rectangle2D;

import fr.razvan.geometry.LineSegment;

/**
 * Some useful functions for working with Paths and PolygonalPaths. 
 * 
 * @author W. Patrick Hooper
 */
public class PathUtil {
    
    /** Return the smallest rectangle containing the polygonal path. 
     * Here, the polygonal path is considered to be a sequence of line segments,
     * rather than paths. 
     */
    public static Rectangle2D boundingBox(PolygonalPath p) {
        double xmin = p.startingPoint().re();
        double xmax = p.startingPoint().re();
        double ymin = p.startingPoint().im();
        double ymax = p.startingPoint().im();

        EdgeIterator it = p.iterator();

        while (it.hasNext()) {
            LineSegment s = it.next();
            if (s.endingPoint().re() < xmin) {
                xmin = s.endingPoint().re();
            }
            if (s.endingPoint().re() > xmax) {
                xmax = s.endingPoint().re();
            }
            if (s.endingPoint().im() < ymin) {
                ymin = s.endingPoint().im();
            }
            if (s.endingPoint().im() > ymax) {
                ymax = s.endingPoint().im();
            }
        }
        
        return new Rectangle2D.Double(xmin, ymin, xmax-xmin, ymax-ymin);
    }
    
    /** Convert a polygonal path to a string representation. */
    public static String toString(PolygonalPath p) {
        EdgeIterator it=p.iterator();
        if (! it.hasNext()) {
            return "<>";
        }
        // Now we know we have at least one segment.
        LineSegment seg=it.next();

        // A StringBuilder is a useful way to assemble a long string.
        StringBuilder builder=new StringBuilder();
        builder.append("< ");
        builder.append(seg.startingPoint());
        builder.append(", ");
        builder.append(seg.endingPoint());
        while (it.hasNext()) {
            seg=it.next();
            builder.append(", ");
            builder.append(seg.endingPoint());
        }
        builder.append(">");
        
        return builder.toString();
    }
    
    public static double distanceFromStartToEnd(Path p) {
        return p.endingPoint().minus(p.startingPoint()).abs();
    }
}
