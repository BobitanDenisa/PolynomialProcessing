package model;

import javax.swing.JOptionPane;

public class Monomial {

	private double coefficient;
	private int exponent;

	public Monomial() {
		this.coefficient = 0;
		this.exponent = 0;
	}

	/**
	 * The constructor checks whether the input coefficient is an integer and the exponent positive
	 * @param coeff: double
	 * @param exp: int
	 */
	public Monomial(double coeff, int exp) {
		try {
			if (exp < 0) {
				throw new WrongExponentOrCoefficientException("Exponent must be positive");
			}
			if (Math.floor(coeff) != coeff) {
				System.out.println(coeff);
				throw new WrongExponentOrCoefficientException("Coefficient must be an integer number");
			}
			this.coefficient = coeff;
			this.exponent = exp;
		} catch (WrongExponentOrCoefficientException e) {
			JOptionPane.showMessageDialog(null, "Coefficients must be integers and exponents must be positive!",
					"Wrong input", JOptionPane.ERROR_MESSAGE);
		}
	}

	public double getCoefficient() {
		return coefficient;
	}

	public void setCoefficient(double coefficient) {
		this.coefficient = coefficient;
	}

	public int getExponent() {
		return exponent;
	}

	public void setExponent(int exponent) {
		this.exponent = exponent;
	}

	public Monomial addition(Monomial m) {
		Monomial rez = new Monomial();
		if (this.exponent == m.exponent) {
			rez.coefficient = this.coefficient + m.coefficient;
			rez.exponent = this.exponent;
			return rez;
		}
		return null;
	}

	public Monomial subtraction(Monomial m) {
		Monomial rez = new Monomial();
		if (this.exponent == m.exponent) {
			rez.coefficient = this.coefficient - m.coefficient;
			rez.exponent = this.exponent;
			return rez;
		}
		return null;
	}

	public Monomial multiplication(Monomial m) {
		Monomial rez = new Monomial();
		rez.coefficient = this.coefficient * m.coefficient;
		rez.exponent = this.exponent + m.exponent;
		return rez;
	}

	public Monomial division(Monomial m) {
		Monomial rez = new Monomial();
		if (this.exponent >= m.exponent) {
			rez.exponent = this.exponent - m.exponent;
			rez.coefficient = this.coefficient / m.coefficient;
		}
		return rez;
	}

	public Monomial derivation() {
		Monomial rez = new Monomial();
		if (this.exponent >= 1) {
			rez.coefficient = this.coefficient * this.exponent;
			rez.exponent = this.exponent - 1;
		}
		return rez;
	}

	public Monomial integration() {
		Monomial rez = new Monomial();
		rez.coefficient = this.coefficient / (this.exponent + 1);
		rez.exponent = this.exponent + 1;
		return rez;
	}

}