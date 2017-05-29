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
 * A Path is a PolygonalPath so that the curves (Segments) between the vertices
 * are again paths, which we call sub-paths.
 *
 * This interface is able to extend PolygonalPath because PathIterator extends
 * EdgeIterator. That is, a PathIterator is also an EdgeIterator. So, an
 * implementation of Path also implements PolygonalPath.
 *
 * @author W. Patrick Hooper
 */
public interface Path {

    /**
     * Return the starting point.
     */
    Complex startingPoint();

    /**
     * Return the endpoint.
     */
    Complex endingPoint();

    /**
     * Return an iterator over the sub-paths.
     */
    PathIterator iterator();
}
