/*
 * This work by W. Patrick Hooper <wphooper@gmail.com> is free of known copyright restrictions.
 * The work is in the public domain.
 * 
 * Author's website: <a href="http://wphooper.com">http://wphooper.com</a>. 
 */
package fr.razvan.geometry;

import fr.razvan.number.Complex;

/**
 * This class is just meant to demonstrate the utility of interfaces.
 *
 * @author W. Patrick Hooper
 */
public class PlanarMapDemo {

    /**
     * Apply the map f one hundred times to the point z and return the result.
     */
    static Complex applyOneHundredTimes(PlanarMap f, Complex z) {
        for (int i = 0; i < 100; i++) {
            z = f.map(z);
        }
        return z;
    }
    
    public static void main(String[] args) {
        Complex z=new Complex(0.5, 0.8);
        System.out.println("The complex number z="+z);
        
        Similarity f=new Similarity(new Complex(0,0.75),new Complex(1));
        System.out.println("The map f is given by "+f+".");
        System.out.println("We have f^100(z)="+applyOneHundredTimes(f,z)+".");
        System.out.println("That should be pretty close to the fixed point of f.");
        ComplexPolynomial g=new ComplexPolynomial(new Complex(0), 
                new Complex(0), 
                new Complex(1));
        System.out.println("The map g is given by "+g+".");
        System.out.println("We have g^100(z)="+applyOneHundredTimes(g,z)+".");
        System.out.println("That should be close to zero, since |z|<1.");
        
        // The point of this is that we could write the method applyOneHundredTimes
        // once, rather than writing a version for each type of function.
    }
}
