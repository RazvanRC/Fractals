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

import fr.razvan.geometry.LineSegment;
import fr.razvan.number.Complex;

/**
 * This demonstrates of the Path interface by constructing a circle. 
 * 
 * An instance of the UnitCircleExample class either represents the full
 * unit circle or an arc of the unit circle.
 * 
 * @author W. Patrick Hooper
 */
public class UnitCircleExample implements Path {

    private VertexPath path;
    
    /** Construct a path representing the unit circle in the plane. */
    public UnitCircleExample() {
        // Begin with a polygonal approximation using the fourth roots of unity.
        path = new VertexPath(
                new Complex(1, 0),
                new Complex(0, 1),
                new Complex(-1, 0),
                new Complex(0, -1),
                new Complex(1, 0));
    }

    /**
     * Takes a segment with endpoints on the unit circle, and returns a path
     * constructed from three points on the circle.
     */
    private UnitCircleExample(LineSegment s) {
        Complex m = s.midpoint();
        path = new VertexPath(
                s.startingPoint(),
                m.div(m.abs()), // midpoint projected onto circle
                s.endingPoint());
    }

    
    /** Return the ending point of this arc or circle. */
    @Override
    public Complex endingPoint() {
        return path.endingPoint();
    }

    /** Return the starting point of this arc or circle. */
    @Override
    public Complex startingPoint() {
        return path.startingPoint();
    }

    // Inner class which allows us to build an iterator:
    private class CircleIterator implements PathIterator {

        EdgeIterator it;

        public CircleIterator() {
            it = path.iterator();
        }

        @Override
        public boolean hasNext() {
            return it.hasNext();
        }

        @Override
        public UnitCircleExample next() {
            // it.next() returns the next segment in the path. 
            // We convert it to a UnitCircleExample constructed from this segment.
            return new UnitCircleExample(it.next());
        }
    }

    /** Return an iterator over the sub-arcs.*/
    @Override
    public PathIterator iterator() {
        return new CircleIterator();
    }

    /** Print out the first several approximations of the unit circle by polygons. */
    public static void main(String args[]) {
        UnitCircleExample ce = new UnitCircleExample();

        System.out.println("First approximation of the unit circle.");
        PolygonalApproximation approx = new PolygonalApproximation(ce, 0);
        System.out.println(approx);

        System.out.println("Second approximation of the unit circle.");
        approx = new PolygonalApproximation(ce, 1);
        System.out.println(approx);

        System.out.println("Third approximation of the unit circle.");
        approx = new PolygonalApproximation(ce, 2);
        System.out.println(approx);
    }
}
