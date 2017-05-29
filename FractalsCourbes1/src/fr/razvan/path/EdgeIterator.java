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

/**
 * An EdgeIterator is an object which iterates over edges in a PolygonalPath.
 * We have based the interface on java.util.Iterator. 
 * 
 * We have chosen to make our own class rather than use Java's built in 
 * Iterator<T> class for simplicity and because the PathIterator class will 
 * extend this one.
 * 
 * <em>Technical discussion:</em> We can't do exactly this with Java's built
 * in Iterator class, because Iterator<Path> does not extend Iterator<Segment>.
 * It is true that Iterator<? extends Path> extends Iterator<? extends Segment>,
 * so this is another possible approach. But, this approach seems to be overly
 * technical for an introduction to java.
 * 
 * @see java.util.Iterator Java's Iterator<T> interface
 * @see path.PathIterator The PathIterator interface, which extends EdgeIterator
 * @author W. Patrick Hooper <wphooper@gmail.com>
 */
public interface EdgeIterator {

    /**
     * Return true when the iterator will return another edge with a call to
     * next(). Returns false otherwise.
     */
    public boolean hasNext();

    /**
     * Return the next segment.
     */
    public LineSegment next();
}
