package cst8284.calculator;

public class ComplexCalculator {

    private java.util.Scanner op = new java.util.Scanner(System.in);
    private Complex c;  // stores result of current calculation

    public ComplexCalculator(Complex c1, Complex c2) {

        System.out.println("Which math operation do you wish to perform?  Enter +, -, *, /");

        // choose which operations we need to execute
        switch (op.nextLine().charAt(0)) {
            case '+':
                setComplexResult(add(c1, c2));
                break;
            case '-':
                setComplexResult(subtract(c1, c2));
                break;
            case '*':
                setComplexResult(multiply(c1, c2));
                break;
            case '/':
                setComplexResult(divide(c1, c2));
                break;
            default:
                System.out.println("Unknown operation requested");
        }
    }

    public ComplexCalculator() {
    }  // Needed for Lab 4; do not change

    public Complex add(Complex c1, Complex c2) {
        double a = c1.getReal();
        double x = c2.getReal();
        double b = c1.getImag();
        double y = c2.getImag();

        double real = a + x;  // As per the Lab Appendix
        double imag = b + y;
        return (new Complex(real, imag));
    }

    public Complex subtract(Complex c1, Complex c2) {
        double a = c1.getReal();
        double x = c2.getReal();
        double b = c1.getImag();
        double y = c2.getImag();

        double real = a - x;
        double imag = b - y;
        return (new Complex(real, imag));
    }

    public Complex multiply(Complex c1, Complex c2) {
        double a = c1.getReal();
        double x = c2.getReal();
        double b = c1.getImag();
        double y = c2.getImag();
        double real = (a * x) - (b * y);
        double imag = (a * y) + (b * x);
        return (new Complex(real, imag));
    }

//    public Complex divide(Complex c1, Complex c2) {
//        double a    = c1.getReal();
//        double x    = c2.getReal();
//        double b    = c1.getImag();
//        double y    = c2.getImag();
//
//        if (c2.isZero()) {
//            System.out.println("Divide by ZERO error detected!");
//            return new Complex(0,0);
//        }
//
//        double real = (a * x + b * y) / (x * x + y * y);
//        double imag = (x * b - a * y) / (x * x + y * y);
//        return (new Complex(real, imag));
//    }

    // If attempting Bonus C, comment out the above divide() method, which must use
    // the calculation given in the Lab 03 document--this must be included for marks--and
    // add a new divide() method here that employs the complex conjugate in the Complex
    // class, as described in the BONUS MARKS section of the Lab 3 document.
    public Complex divide(Complex c1, Complex c2) {
        if (c2.isZero()) {
            System.out.println("Divide by ZERO error detected!");
            return new Complex(0, 0);
        }
        double denom = multiply(c2, c2.conjugate()).getReal();
        double real = multiply(c1, c2.conjugate()).getReal() /denom;
        double imag = multiply(c1, c2.conjugate()).getImag() / denom;
        return (new Complex(real, imag));
    }

    public void setComplexResult(Complex c) {
        this.c = c;
    }

    public Complex getComplexResult() {
        return this.c;
    }

    public String toString() {
        return c.toString();
    }

}
