package fr.razvan.outils;

public class ComplexNumber {

	double real, imaginary;

    public ComplexNumber(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    public ComplexNumber add(double real, double imaginary) {
        return new ComplexNumber(this.real + real, this.imaginary + imaginary);
    }

    public ComplexNumber add(ComplexNumber c) {
        return new ComplexNumber(this.real + c.real, this.imaginary + c.imaginary);
    }

    public ComplexNumber subtract(double real, double imaginary) {
        return new ComplexNumber(this.real - real, this.imaginary - imaginary);
    }

    public ComplexNumber subtract(ComplexNumber c) {
        return new ComplexNumber(this.real - c.real, this.imaginary - c.imaginary);
    }

    public ComplexNumber multiply(double scalar) {
        return new ComplexNumber(real * scalar, imaginary * scalar);
    }

    public ComplexNumber multiply(ComplexNumber c) {
        return new ComplexNumber(real * c.real - imaginary * c.imaginary, real * c.imaginary + imaginary * c.real);
    }

    public ComplexNumber divide(double scalar) {
        return multiply(1.0 / scalar);
    }

    public ComplexNumber divide(ComplexNumber c) {
        return multiply(c.getConjugate()).multiply(1.0 / (c.real * c.real + c.imaginary * c.imaginary));
    }

    public ComplexNumber getConjugate() {
        return new ComplexNumber(real, imaginary * -1);
    }

    public ComplexNumber pow(int exp) {
        ComplexNumber c = new ComplexNumber(real, imaginary);
        for (int k = 1; k < exp; k++) {
            c = multiply(c);
        }
        return c;
    }

    public ComplexNumber exp() {
        return new ComplexNumber(Math.exp(real) * Math.cos(imaginary), Math.exp(real) * Math.sin(imaginary));
    }

    public static ComplexNumber exp(ComplexNumber c) {
        return c.exp();
    }

    public ComplexNumber cos() {
        return exp(multiply(new ComplexNumber(0, 1))).add(exp(multiply(new ComplexNumber(0, -1)))).divide(2);
    }

    public static ComplexNumber cos(ComplexNumber c) {
        return c.cos();
    }

    public ComplexNumber sin() {
        return exp(multiply(new ComplexNumber(0, 1))).subtract(exp(multiply(new ComplexNumber(0, -1)))).divide(new ComplexNumber(0, 2));
    }

    public static ComplexNumber sin(ComplexNumber c) {
        return c.sin();
    }

    public ComplexNumber tan() {
        return sin().divide(cos());
    }

    public static ComplexNumber tan(ComplexNumber c) {
        return c.sin().divide(c.cos());
    }

    public double getReal() {
        return real;
    }

    public double getImaginary() {
        return imaginary;
    }

    @Override
    public String toString() {
        return "" + real + (imaginary >= 0 ? "+" : "") + imaginary + "i";
    }
	
}
