package cst8284.calculator;

public class Complex {
    private double real = 0;
    private double imag = 0;

    // default constructor with initial values of [0, 0]
    public Complex() {
    	this(0, 0); 
    }

    // Complex constructor that takes in a single string, e.g. "2-4i"
    public Complex(String cStr) {
    	// we need to remove extra spaces with "replace(old, new)" function from
    	// initial string value before use another String method - "split()"
        this(cStr.replace(" ","").split("(?=\\+)|(?=\\-)"));  // splits cStr at + or - into an
        // array of strings having two elements.  The first element of the
        // resultant array will be the real portion, while the second is the
        // imaginary portion.  This array is passed to the next constructor.
    }


    // Complex constructor that takes in an array of two strings from the above
    // constructor e.g. cStr[0]="2", cStr[1]="-4i"
    public Complex(String[] cStr) {
    	// send array value 1 and 2 separately to the next constructor
        this(cStr[0], cStr[1]);
    }


    // Complex constructor that takes two separate strings as parameters, e.g. "2" and "-4i"
    public Complex(String realPart, String imaginary) {
    	// here we need to convert [String] into [int] to call next chain constructor.
    	// to do that we have to use "Integer.parseInt()". 
    	// We also have to remove the last 'i' from imaginary part because we cannot
    	// convert from [String] to [int].
    	// Also we need to use [substring] to delete last symbol
        this(Integer.parseInt(realPart), Integer.parseInt(imaginary.substring(0, imaginary.length() - 1)));
    }


    // Complex constructor that takes in two ints as parameters, e.g. 2 and -4
    public Complex(int realPart, int imaginary) {
    	
    	// [real] and [imaginary] here are of type [int], but we need to convert them to type [double]
        this((double) realPart, (double) imaginary);
    }


    // Complex constructor that takes in two ints and stores them as floats, e.g. as 2.0 and -4.0
    public Complex(double realPart, double imaginary) {
    	
    	// use setters to set up instance variables
        setReal(realPart);
        setImag(imaginary);
    }

    public double getReal() {
        return this.real;
    }

    public double getImag() {
        return this.imag;
    }

    public void setReal(double real) {
        this.real = real;
    }

    public void setImag(double imag) {
        this.imag = imag;
    }

    public Complex getComplex() {
        return this;
    }

    public String toString() {
        double realPart = this.getReal();
        double imaginary  = this.getImag();
        if (imaginary >= 0) {
        	// since [img] here is positive or nul value, so we need to use
        	// special sign of [+] to conjunct numbers
            return String.valueOf(realPart) + " + " + String.valueOf(imaginary) + "i";
        } else {
        	// since [img] is negative here and we use separate sign of [-]
        	// it means we must use [img] without sign - thus use Math.abs(img)
            return String.valueOf(realPart) + " - " + String.valueOf(Math.abs(imaginary)) + "i";
        }
    }

    public boolean isZero(){
    	
    	// if both part (real and imaginary) are equal 0 then we must return TRUE
        return this.getReal() == 0 && this.getImag() == 0;
    }

    public Complex conjugate() {
        return new Complex(this.getReal(), -this.getImag());
    } 
    
    public boolean equals(Complex c) {
    	// compare "this" Complex with "c" Complex
    	return this.getReal() == c.getReal() && this.getImag() == c.getImag();
    }
    
    public boolean equals(int real, int imag) {
    	//using parameterized constructor create new object "cn"
    	Complex cn = new Complex(real, imag);
    	// compare "this" Complex with "cn" Complex
    	return this.getReal() == cn.getReal() && this.getImag() == cn.getImag();
    }
    
    public boolean equals(String c) {
    	//using parameterized constructor create new object "cn"
    	Complex cn = new Complex(c);
    	// compare "this" Complex with "cn" Complex
    	return this.getReal() == cn.getReal() && this.getImag() == cn.getImag();
    }
    
    public boolean equals(double real, double imag, double delta) {
    	//using parameterized constructor create new object "cn"
    	Complex cn = new Complex(real, imag);
    	// compare "this" Complex with "cn" Complex
    	return this.getReal() == cn.getReal() && this.getImag() == cn.getImag();
    }
}
