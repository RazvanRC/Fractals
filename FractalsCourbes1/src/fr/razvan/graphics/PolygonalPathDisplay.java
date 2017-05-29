package fr.razvan.graphics;

/*
 * Copyright (C) 2012 W. Patrick Hooper <wphooper@gmail.com>
 *
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later
 * version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program; if not, write to the Free Software Foundation, Inc., 59 Temple
 * Place - Suite 330, Boston, MA 02111-1307, USA.
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import org.freehep.util.export.ExportDialog;

import fr.razvan.path.KochSnowflakeExample;
import fr.razvan.path.PolygonalApproximation;
import fr.razvan.path.PolygonalPath;
import fr.razvan.path.SubstitutionFractal;



/**
 * This program creates a window which displays the Koch Snowflake.
 *
 * Note that with small modifications, the same program could be used to display
 * any path in a window.
 *
 * @author Pat Hooper
 */
public class PolygonalPathDisplay extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1866960940591335822L;
	PolygonalPath p;
    PolygonalPathPanel path_panel;

    public PolygonalPathDisplay(PolygonalPath path) {
        p = path;

        // Create the panel for displaying the path. 
        path_panel = new PolygonalPathPanel(p);
        add(path_panel); // We'll display only this. 

        setJMenuBar(buildMenuBar());
    }

    /**
     * This function creates and returns the menu bar.
     */
    private JMenuBar buildMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu file = new JMenu("File");
        menuBar.add(file);

        try {
            // The following throws an error if the class in quotes does not exist.
            Class.forName("org.freehep.util.export.ExportDialog", false, this.getClass().getClassLoader());
            // If an error was not thrown, then we add the menu item.
            JMenuItem exportItem = new JMenuItem("Export...");
            exportItem.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    ExportDialog export = new ExportDialog();
                    export.showExportDialog(path_panel, "Export view as ...", path_panel, "export");
                }
            });
            file.add(exportItem);
        } catch (ClassNotFoundException e) {
            System.err.println("Failed to find the class org.freehep.util.export.ExportDialog.");
            System.err.println("Check your classpath.");
            System.err.println("You will not be able to export images.");
        }

        JMenuItem quitItem = new JMenuItem("Close");
        quitItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                PolygonalPathDisplay.this.dispose();
            }
        });
        file.add(quitItem);

        return menuBar;
    }

    public static void main(String[] args) {
        // Build the KochSnowflake.
        SubstitutionFractal path = KochSnowflakeExample.snowflake();
        // Construct a polygonal approximation.
        PolygonalApproximation approx = new PolygonalApproximation(path, 6);

        PolygonalPathDisplay display = new PolygonalPathDisplay(approx);

        // Configure the window:
        display.setTitle("The Koch Snowflake"); // Set the window's title.
        display.setSize(640, 480); // Set the dimensions of the window
        display.setDefaultCloseOperation(EXIT_ON_CLOSE);

        display.setVisible(true);
    }
}
