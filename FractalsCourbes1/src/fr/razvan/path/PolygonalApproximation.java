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

import java.util.NoSuchElementException;
import java.util.Stack;

import fr.razvan.geometry.LineSegment;
import fr.razvan.number.Complex;

/**
 * Construct a new polygonal path which iterates through sub-paths at a certain depth.
 * Depth zero is equivalent to the original path.
 *
 * @author W. Patrick Hooper
 */
public class PolygonalApproximation implements PolygonalPath {

    final Path p; // original path
    final int d;  // depth 

    /**
     * Construct a version of the path whose iterator moves over sub-paths of
     * the given depth
     */
    public PolygonalApproximation(Path path, int subdivision_depth) {
        p = path;
        d = subdivision_depth;
    }

    /**
     * This is a private class which implements an iterator over the sub-paths
     * of depth d.
     */
    private class ApproximationIterator implements EdgeIterator {

        Stack<PathIterator> iterator_stack; // A sequence of iterators at increasing depths

        /**
         * Construct an initial iterator starting at the first sub-path of depth
         * d.
         */
        public ApproximationIterator() {
            iterator_stack = new Stack<PathIterator>();
            iterator_stack.push(p.iterator());
        }

        /** This function removes all iterators who have no more sub-paths from the stack. */
        private void removeEnds() {
            while ((!iterator_stack.empty()) && (!iterator_stack.peek().hasNext())) {
                iterator_stack.pop();
            }
        }

        @Override
        public boolean hasNext() {
            removeEnds();
            return !iterator_stack.empty();
        }

        @Override
        public LineSegment next() {
            removeEnds();

            if (iterator_stack.empty()) {
                // Throw an error. This would have been avoided if the user had called hasNext() first.
                throw new NoSuchElementException("The DepthApproximation iterator has no more elements.");
            }

            // This will store the deepest path in the stack:
            Path deepest_path = iterator_stack.peek().next();
            // Its depth is given by the stack size minus 1.

            while (iterator_stack.size() - 1 < d) {

                // temp.iterator() returns an iterator over the next level of subpath
                PathIterator next_depth_iterator = deepest_path.iterator();

                if (!next_depth_iterator.hasNext()) {
                    // it should have at least one edge!
                    throw new NoSuchElementException("DepthApproximation was given a path with no edges.");
                }

                // this path is one deeper:
                deepest_path = next_depth_iterator.next();

                iterator_stack.add(next_depth_iterator);
            }
            
            // now the path is guaranteed to be at depth d, so we return it.
            return new LineSegment(deepest_path.startingPoint(), deepest_path.endingPoint());
        }
    }
    
    /** Iterate over the sub-paths making up the path. */
    @Override
    public EdgeIterator iterator() {
        return new ApproximationIterator();
    }

    /** Return the ending point of the path. */
    @Override
    public Complex endingPoint() {
        return p.endingPoint();
    }

    /** Return the starting point of the path. */
    @Override
    public Complex startingPoint() {
        return p.startingPoint();
    }

    /** Convert the path to a string. */
    @Override
    public String toString() {
        return PathUtil.toString(this);
    }
}
