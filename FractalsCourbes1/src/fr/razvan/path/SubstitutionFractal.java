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

import fr.razvan.geometry.Similarity;
import fr.razvan.number.Complex;

/**
 * A substitution fractal is the limit of a sequence of polygonal approximations.
 * 
 * It is obtained by the following construction. Let p be an arbitrary polygonal 
 * path. This will be the first polygonal approximation of our path. 
 * 
 * Let gamma be a polygonal path which starts at zero and ends at one. We call 
 * this the substitution path. The 
 * second polygonal approximation is obtained by replacing each edge of
 * p by the image of gamma under a similarity sending the endpoints of gamma
 * to the endpoints of the edge. The third approximation is obtained by 
 * applying the same process to the second approximation, and so on.
 * 
 * @author W. Patrick Hooper
 */
public class SubstitutionFractal implements Path {

    final PolygonalPath p; // Starting path
    final PolygonalPath gamma; // "substituted" for every line segment in p.
    
    /** Construct a Substitution path with a given first polygonal approximation 
     * p and a substitution path gamma.
     */
    public SubstitutionFractal(PolygonalPath p, PolygonalPath gamma) {
        this.p=p;
        this.gamma=gamma;
    }

    /** Construct a Substitution path with gamma serving as both the first
     * polygonal approximation and the substitution path. 
     */
    public SubstitutionFractal(PolygonalPath gamma) {
        this.p=gamma;
        this.gamma=gamma;
    }

    // Inner class which allows us to build an iterator:
    private class SubstitutionIterator implements PathIterator {
        private EdgeIterator it;
        
        public SubstitutionIterator() {
            it=p.iterator();
        }

        @Override
        public boolean hasNext() {
            return it.hasNext();
        }

        @Override
        public SubstitutionFractal next() {
            Similarity sim=new Similarity(it.next()); 
            // sim is the similarity sending 0 and 1 to the endpoints of the segment it.next().
            
            // "new VertexPath(gamma,sim)" gives the image of gamma under the similarity.
            return new SubstitutionFractal(new VertexPath(gamma, sim),gamma);
        }
    }

    /** Return an iterator over the sub-paths */
    @Override
    public PathIterator iterator() {
        return new SubstitutionIterator();
    }

    /** Return the ending point of the path */
    @Override
    public Complex endingPoint() {
        return p.endingPoint();
    }

    /** Return the starting point of the path */
    @Override
    public Complex startingPoint() {
        return p.startingPoint();
    }    
}
