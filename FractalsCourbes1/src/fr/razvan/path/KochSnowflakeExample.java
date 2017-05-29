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

import fr.razvan.number.*;

/**
 * This implements the famous 
 * <a href="http://en.wikipedia.org/wiki/Koch_snowflake">Koch Snowflake</a>. 
 * 
 * @author W. Patrick Hooper
 */
public class KochSnowflakeExample {

    /** Return the Koch Snowflake as a SubstitutionFractal. */
    public static SubstitutionFractal snowflake() {
        //
        VertexPath p = new VertexPath(
                new Complex(0.0),
                new Complex(1.0),
                new Complex(0.5, -Math.sqrt(3) / 2),
                new Complex(0.0));

        // This is the path we will be substituting in.
        VertexPath gamma = new VertexPath(
                new Complex(0.0),
                new Complex(1.0 / 3.0),
                new Complex(0.5, Math.sqrt(3) / 6),
                new Complex(2.0 / 3.0),
                new Complex(1.0));

        return new SubstitutionFractal(p, gamma);
    }

    /**
     * Print the first few polygonal approximations of the Koch Snowflake.
     */
    public static void main(String[] args) {
        SubstitutionFractal snowflake = snowflake();

        System.out.println("First approximation of Koch Snowflake:");
        PolygonalApproximation pa = 
                new PolygonalApproximation(snowflake, 0);
        System.out.println(pa);

        System.out.println("Second approximation of Koch Snowflake:");
        pa = new PolygonalApproximation(snowflake, 1);
        System.out.println(pa);

        System.out.println("Third approximation of Koch Snowflake:");
        pa = new PolygonalApproximation(snowflake, 2);
        System.out.println(pa);
    }
}
