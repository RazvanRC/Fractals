/*
 * This work by W. Patrick Hooper <wphooper@gmail.com> is free of known copyright restrictions.
 * The work is in the public domain.
 * 
 * Author's website: <a href="http://wphooper.com">http://wphooper.com</a>. 
 */
package fr.razvan.geometry;

import fr.razvan.number.Complex;

/**
 * This class represents the choice of a line segment in the complex plane.
 *
 * This class is immutable. That is, once a LineSegment is constructed, it will
 * not change.
 *
 * @author W. Patrick Hooper
 */
public final class LineSegment {

    // The start and end point of the segment.
    private final Complex start, end;

    /**
     * Construct a new LineSegment from a start and an end.
     *
     * @param starting_point the starting point of the segment.
     * @param ending_point the ending point of the segment. segment.
     */
    public LineSegment(Complex starting_point, Complex ending_point) {
        start = starting_point;
        end = ending_point;
    }

    /**
     * Construct the image of a line segment under a similarity
     */
    public LineSegment(LineSegment seg, Similarity sim) {
        start = sim.map(seg.startingPoint());
        end = sim.map(seg.endingPoint());
    }

    /**
     * Return the starting point.
     */
    public Complex startingPoint() {
        return start;
    }

    /**
     * Return the endpoint.
     */
    public Complex endingPoint() {
        return end;
    }

    /**
     * Return the distance between the starting and ending points.
     */
    public double length() {
        return endingPoint().minus(startingPoint()).abs();
    }

    /**
     * Return the point midway between the starting and ending points.
     */
    public Complex midpoint() {
        return endingPoint().add(startingPoint()).div(2);
    }
}
