/* This work by W. Patrick Hooper <wphooper@gmail.com> is free of known copyright restrictions.
 * The work is in the public domain.
 * 
 * Author's website: <a href="http://wphooper.com">http://wphooper.com</a>. 
 */
package fr.razvan.geometry;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import fr.razvan.number.Complex;


/**
 * This class is meant to demonstrate the PlanarMap interface.
 * 
 * @author W. Patrick Hooper
 */
public class ComplexPolynomial implements PlanarMap {
    private List<Complex> coefficent_list;
    
    /** Creates a polynomial from a list of coefficients in order of increasing
     * degree. Note that the ellipsis (...) indicates that the parameters
     * are a list of complex numbers. For instance, this can be called
     * with "new ComplexPolynomial(a0,a1,a2)" to construct the polynomial
     * "a0+a1*z+a2*z^2."
     */
    public ComplexPolynomial(Complex ... coefficients){
        // In this constructor, coefficients can be accessed as an array.
        coefficent_list=Arrays.asList(coefficients);        
    }

    /** Evaluate the polynomial at z. */
    @Override
    public Complex map(Complex z) {
        Complex power=new Complex(1);
        Complex sum=new Complex(0);
        
        // Below is a for each loop. 
        // It iterates over all the coefficients in order.
        for ( Complex coef : coefficent_list ) {
            sum=sum.add(coef.mult(power));
            power=power.mult(z);
        }
        return sum;
    }
    
    /** Return a string describing the polynomial as a map of the Complex plane. */
    public String toString() {
        StringBuilder sb=new StringBuilder();
        sb.append("z -> ");
        
        Iterator<Complex> it=coefficent_list.iterator();
        if (! it.hasNext()) {
            sb.append(0);
            return sb.toString();
        }
        sb.append(it.next());
        
        if (! it.hasNext()) {
            return sb.toString();
        }
        sb.append(" + "+it.next()+"*z");
        
        int deg=2;
        while (it.hasNext()) {
            sb.append(" + "+it.next()+"*z^"+deg);
            deg=deg+1;
        }
        return sb.toString();
    }
}
