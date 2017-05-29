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

import java.util.*;         // for working with Java's Lists and Collections

import fr.razvan.geometry.LineSegment;
import fr.razvan.geometry.PlanarMap;
import fr.razvan.number.Complex;


/**
 * A VertexPath is a polygonal path which is constructed from a list of
 * vertices.
 *
 * @author W. Patrick Hooper
 */
public class VertexPath implements PolygonalPath {
    
    // The list of vertices
    private final List<Complex> vert;
    
    /** Construct from a collection of coordinates for vertices. */
    public VertexPath(Collection<Complex> vertex_list) {
        vert = new LinkedList<Complex>();
        vert.addAll(vertex_list);
    }
    
    /** Construct from a list of complex numbers. */
    public VertexPath(Complex... vertices) {
        // Vertices is an array of complex numbers.
        // The following command converts it to a list.
        vert=Arrays.asList(vertices);
    }

    /**
     * Convert p into a VertexPath.
     */
    public VertexPath(PolygonalPath p) {
        vert = new LinkedList<Complex>();
        vert.add(p.startingPoint());
        EdgeIterator it=p.iterator();
        while (it.hasNext()) {
            vert.add(it.next().endingPoint());
        }
    }

    /**
     * Construct the image of the path p under the map f.
     */
    public VertexPath(PolygonalPath p, PlanarMap f) {
        vert = new LinkedList<Complex>();
        vert.add(f.map(p.startingPoint()));
        EdgeIterator it=p.iterator();
        while (it.hasNext()) {
            vert.add(f.map(it.next().endingPoint()));
        }
    }

    private class MyEdgeIterator implements EdgeIterator {

        private Iterator<Complex> vertex_iterator;
        private Complex current_vertex;

        /** Default constructor. Begins at the first edge. */
        public MyEdgeIterator() {
            // Create an iterator which moves over the vertices.
            vertex_iterator = vert.iterator();
            // Set the current vertex to be the first vertex in the list.
            current_vertex = vertex_iterator.next();
            // The next vertex returned by "vertex_iterator.next()" will be the 
            // second vertex of the list.
        }

        /**
         * Return true if there is another segment in the path.
         */
        @Override
        public boolean hasNext() {
            // Check to see if there is another vertex.
            return vertex_iterator.hasNext();
        }

        /**
         * Return the next segment in the path.
         */
        @Override
        public LineSegment next() {
            Complex a = current_vertex;
            current_vertex = vertex_iterator.next();
            return new LineSegment(a, current_vertex);
        }
    }

    /** Return an iterator over the segments making up the path. */
    @Override
    public EdgeIterator iterator() {
        return new MyEdgeIterator();
    }

    /** Return the ending point of the path. */
    @Override
    public Complex endingPoint() {
        return vert.get(vert.size() - 1);
    }

    /** Return the starting point of the path. */
    @Override
    public Complex startingPoint() {
        return vert.get(0);
    }
    
    /** Return a string representing the polygonal path */
    @Override
    public String toString() {
        return PathUtil.toString(this);
    }
}
