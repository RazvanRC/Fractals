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

/**
 * A PathIterator iterates over the sub-paths of a Path.
 *
 * Note that PathIterator extends EdgeIterator. Thus, an implementation of
 * PathIterator must include a hasNext() function.
 *
 * When you call the next() function on an EdgeIterator, you will get a Segment.
 * That is, something with a beginning and an end. When you call it on a
 * PathIterator you get a Path, which is a special type of Segment. Thus, it is
 * natural for the PathIterator to extend EdgeIterator, because it can be
 * treated as one. That is, the output of next() in PathIterator can be either
 * treated as a Segment or a Path.
 *
 * @author W. Patrick Hooper <wphooper@gmail.com>
 */
public interface PathIterator {

    /**
     * Return true when the iterator will return another sub-path with a call to
     * next(). Returns false otherwise.
     */
    boolean hasNext();

    /**
     * Returns the next sub-path.
     */
    Path next();
}
