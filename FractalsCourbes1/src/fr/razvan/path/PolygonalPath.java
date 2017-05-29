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

import fr.razvan.number.Complex;

/**
 * A PolygonalPath is a sequence of edges. We implicitly assume that the end
 * point of an edge coincides with the starting point of the next edge.
 *
 * A PolygonalPath extends Segment, because a path should have a beginning and
 * an end. It includes an iterator() function which allows the user to iterate
 * over the edges of the path.
 *
 * Here, we take a general view of what an edge is, as in the Segment class. So,
 * an edge can be anything with a beginning and an end.
 *
 * The rationale for this is explained as <a
 * href="http://wphooper.com/java/tutorial/curve_math.php#PolygonalPath">part of
 * my tutorial</a>.
 *
 * @author W. Patrick Hooper
 */
public interface PolygonalPath {

    /**
     * Return the starting point.
     */
    Complex startingPoint();

    /**
     * Return the endpoint.
     */
    public Complex endingPoint();

    /**
     * Return an iterator over the segments making up the polygonal path.
     */
    EdgeIterator iterator();
}
