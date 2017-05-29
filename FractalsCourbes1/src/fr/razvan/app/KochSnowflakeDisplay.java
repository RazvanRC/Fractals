package fr.razvan.app;
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

import javax.swing.JFrame;

import fr.razvan.graphics.PolygonalPathPanel;
import fr.razvan.path.KochSnowflakeExample;
import fr.razvan.path.PolygonalApproximation;
import fr.razvan.path.SubstitutionFractal;

/**
 * This program creates a window which displays the Koch Snowflake.
 *
 * Note that with small modifications, the same program could be used to display
 * any path in a window.
 *
 * @author Pat Hooper
 */
public class KochSnowflakeDisplay {

    public static void main(String[] args) {
        // Build the KochSnowflake.
        SubstitutionFractal path = KochSnowflakeExample.snowflake();
        // Construct a polygonal approximation.
        PolygonalApproximation approx = new PolygonalApproximation(path, 3);

        // Construct a panel to display the approximation
        PolygonalPathPanel path_panel = new PolygonalPathPanel(approx);

        // Construct a new window:
        JFrame frame = new JFrame("The Koch Snowflake");
        // Dimensions of the window in pixels:
        frame.setSize(1200, 1200);
        // Quit the program when the window is closed:
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // The window will contain only our panel:
        frame.add(path_panel);
        // Make the window visible:
        frame.setVisible(true);
    }
}
