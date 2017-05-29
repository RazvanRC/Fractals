/*
 * This work by W. Patrick Hooper <wphooper@gmail.com> is free of known copyright restrictions.
 * The work is in the public domain.
 * 
 * Author's website: <a href="http://wphooper.com">http://wphooper.com</a>. 
 */
package fr.razvan.geometry;

import fr.razvan.number.Complex;

/**
 * This class represents an orientation preserving similarity of the plane.
 * These maps are of the form z maps to w*z+a, for complex numbers w and a.
 *
 * @author W. Patrick Hooper
 */
public final class Similarity implements PlanarMap {

    private final Complex w, a;

    /**
     * Construct the similarity z maps to w*z+a.
     */
    public Similarity(Complex w, Complex a) {
        this.w = w;
        this.a = a;
    }

    /**
     * Construct the similarity sending zero to the starting point of the
     * segment and one to the ending point.
     */
    public Similarity(LineSegment s) {
        this.w = s.endingPoint().minus(s.startingPoint());
        this.a = s.startingPoint();
    }

    /**
     * Apply the similarity to z.
     */
    public Complex map(Complex z) {
        return w.mult(z).add(a);
    }
    
    /**
     * Return a representation of the similarity as a string. 
     */
    public String toString() {
        return "z -> "+w+"*z + "+a;
    }
}
