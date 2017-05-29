package fr.razvan.courbes;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.function.Function;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import fr.razvan.outils.ComplexNumber;

public class FractalGenerator {

	private BufferedImage img;
    private int imageWidth = 500, imageHeight = imageWidth;
    private double xMin = -2, xMax = 2, yMin = -2, yMax = 2;
    private final ComplexNumber[] zeros;
    private Function<ComplexNumber, ComplexNumber> function, preDerivative, derivative;

    public FractalGenerator() {
        double h = 1E-8;
        function = z -> z.pow(3).subtract(1, 0);
        preDerivative = function.compose((ComplexNumber c) -> c.add(h, 0));
        derivative = c -> preDerivative.apply(c).subtract(function.apply(c)).divide(h);
        zeros = new ComplexNumber[] {new ComplexNumber(1, 0),
                                     new ComplexNumber(-.5, Math.sqrt(3)/2),
                                     new ComplexNumber(-.5, -Math.sqrt(3)/2)};
        createImage();
        createAndShowGUI();
    }

    private void createAndShowGUI() {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame();
            JPanel panel = new JPanel() {
                /**
				 * 
				 */
				private static final long serialVersionUID = 6370140874899238279L;

				@Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    g.drawImage(img, 0, 0, this);
                }

                @Override
                public Dimension getPreferredSize() {
                    return new Dimension(imageWidth, imageHeight);
                }
            };

            frame.add(panel);

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);
        });
    }

    private void createImage() {
        double reductionFactor = .96;
        double graphWidth = xMax - xMin, graphHeight = yMax - yMin;
        img = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);
        Graphics g = img.getGraphics();
        for (int y = 0; y < imageHeight; y++) {
            double graphY = yMax - y * graphHeight /(double) imageHeight;
            for (int x = 0; x < imageWidth; x++) {
                double graphX = xMin + x * graphWidth / (double) imageWidth;
                int[] arr = applyNewtonMethod(graphX, graphY);
                if (arr[0] == -1)
                    g.setColor(Color.black);
                else {
                    Color color = getColor(arr[0]);
                    int red = color.getRed(), green = color.getGreen(), blue = color.getBlue();
                    for (int i = 0; i < arr[1]; i++) {
                        red = (int) (red * reductionFactor);
                        green = (int) (green * reductionFactor);
                        blue = (int) (blue * reductionFactor);
                    }
                    g.setColor(new Color(red, green, blue));
                }
                g.drawLine(x, y, x, y);
            }
        }
    }

    private int[] applyNewtonMethod(double x, double y) {
        ComplexNumber c = new ComplexNumber(x, y);
        double tolerance = 1E-6;
        int iterations = 1, max = 512;
        while (iterations < max) {
            c = c.subtract(function.apply(c).divide(derivative.apply(c)));
            for (int k = 0; k < zeros.length; k++) {
                ComplexNumber z = zeros[k], difference = c.subtract(z);
                if (Math.abs(difference.getReal()) < tolerance && Math.abs(difference.getImaginary()) < tolerance)
                    return new int[] {k, iterations};
            }
            iterations++;
        }
        return new int[] {-1};
    }

    private Color getColor(int num) {
        switch (num) {
            case 0: return Color.red;
            case 1: return Color.green;
            case 2: return Color.blue;
            case 3: return Color.yellow;
            case 4: return Color.magenta;
            case 5: return Color.cyan;
            case 6: return Color.white;
            case 7: return new Color(139, 69, 19);
            case 8: return new Color(255, 165, 0);
            case 9: return new Color(255, 192, 203);
            default: return Color.black;
        }
    }

    public static void main(String[] args) {
        new FractalGenerator();
    }
	
}
