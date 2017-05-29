/*
 * This work by W. Patrick Hooper <wphooper@gmail.com> is free of known copyright restrictions.
 * The work is in the public domain.
 * 
 * Author's website: <a href="http://wphooper.com">http://wphooper.com</a>. 
 */

package fr.razvan.geometry;

import fr.razvan.number.Complex;

/**
 * This interface represents a map from the complex plane to itself.
 * 
 * @author W. Patrick Hooper
 */
public interface PlanarMap {
    /** Apply the map to the point z. */
    Complex map(Complex z);
}
