package fr.razvan.courbes;

import java.util.Scanner;

import fr.razvan.outils.StdDraw;

/******************************************************************************
 *  Compilation:  javac Hadamard.java
 *  Execution:    java Hadamard n
 *  Dependencies: StdDraw.java
 *
 *  Plots an order n Hadamard pattern. This is an N-by-N grid of black
 *  and white cells, where N = 2^n. An order 0 Hadamard pattern is
 *  a black square. An order n Hadamarad pattern is 4 order n-1
 *  Hadamard patterns arranged in a 2-by-2 grid, but the bottom
 *  right is an order n-1 pattern with the roles of black and white
 *  reversed.
 *
 ******************************************************************************/

public class Hadamard {

    // order n Hadamard pattern with lower left endpoint (x0, y0) and
    // upper right endpoint (x1, y1).
    public static void recur(double x0, double y0, double x1, double y1, int n, boolean color) {
        double xmid = (x0 + x1) / 2.0;
        double ymid = (y0 + y1) / 2.0;

        if (n == 0) {
            if (color) StdDraw.setPenColor(StdDraw.BLACK);
            else       StdDraw.setPenColor(StdDraw.WHITE);
            StdDraw.filledSquare(xmid, ymid, 0.45);
        }
        else {
            recur(x0,   y0,   xmid, ymid, n-1,  color);   // lower left
            recur(xmid, ymid, x1,   y1,   n-1,  color);   // upper right
            recur(x0,   ymid, xmid, y1,   n-1,  color);   // upper left
            recur(xmid, y0,   x1,   ymid, n-1, !color);   // lower right
        }

    }

    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
        int n;
        System.out.println("Introduire n: ");
        n = sc.nextInt();
        sc.close();
        double MAX = Math.pow(2, n);
        StdDraw.setXscale(0, MAX);
        StdDraw.setYscale(0, MAX);
        StdDraw.clear(StdDraw.GRAY);
        recur(0, 0, MAX, MAX, n, true);
    }

}
