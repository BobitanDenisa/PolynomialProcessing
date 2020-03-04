package model;

import java.text.DecimalFormat;
import java.util.*;

import javax.swing.JOptionPane;

public class Polynomial {

	private ArrayList<Monomial> monomials;
	private int degree;

	public Polynomial() {
		this.monomials = new ArrayList<Monomial>();
		this.degree = -1;
	}

	/**
	 * The method generates a polynomial with all coefficients 0 and the monomials
	 * in the right order of their exponents
	 * 
	 * @param deg is the degree of the returned polynomial
	 * @return Polynomial type object
	 */
	public Polynomial initZeroPolynomial(int deg) {
		Polynomial rez = new Polynomial();
		rez.degree = deg;
		for (int i = 0; i <= deg; i++) {
			rez.monomials.add(new Monomial(0, deg - i));
		}
		return rez;
	}

	/**
	 * The degree of a polynomial (ordered in descending order of its exponents) is
	 * the greatest exponent of the array of monomials
	 */
	public void setDegree() {
		if (this.monomials.size() > 0) {
			this.degree = this.monomials.get(0).getExponent();
		} else {
			this.degree = -1;
		}
	}

	/**
	 * The method generates the array list of monomials for a polynomial from a
	 * given string Split method was used to separate string in tokens and obtain
	 * the coefficients and exponents
	 * 
	 * @param s
	 * @return an integer - 0 if everything went as planned or 1, if an exeception
	 *         has been thrown
	 */
	public int generateMonomials(String s) {
		ArrayList<Monomial> m = new ArrayList<Monomial>();
		try {
			s = s.replaceAll("-", "+-");
			String[] parts = s.split("\\+"); // first split by "+"
			for (String aux : parts) {
				if (aux.length() > 0) {
					String[] rez = aux.split("x\\^|x"); // then split the results by "x^" or "x"
					int len = rez.length;
					if (len == 0) { // len=0 means that the monomial is "x"
						if (!aux.equals("x")) {
							throw new WrongInputException("Wrong input string!");
						}
						m.add(new Monomial(1, 1));
					} else {
						if (len == 1) { // len=1 means that the monomial has the form "Cx" or "C", C being the
										// coefficient
							if (!rez[0].matches("^-[0-9]*$|^[0-9]*$|-")) {
								throw new WrongInputException("Wrong input string!");
							}
							if (aux.contains("x")) { // "Cx"
								if (rez[0].equals("-")) {
									m.add(new Monomial(Double.valueOf("-1"), 1));
								} else {
									m.add(new Monomial(Double.valueOf(rez[0]), 1));
								}
							} else { // "C"
								m.add(new Monomial(Double.valueOf(rez[0]), 0));
							}
						} else { // len=2 means that the monomial has the form "Cx^E" or "x^E", C being the
									// coefficient and E, the exponent
							if (!rez[0].matches("^-[0-9]*$|^[0-9]*$|-") || !rez[1].matches("^-[0-9]*$|^[0-9]*$")) {
								throw new WrongInputException("Wrong input string!");
							}
							if (rez[0].length() > 0) { // "Cx^E"
								if (rez[0].equals("-")) {
									m.add(new Monomial(Double.valueOf("-1"), Integer.valueOf(rez[1])));
								} else {
									m.add(new Monomial(Double.valueOf(rez[0]), Integer.valueOf(rez[1])));
								}
							} else { // "x^E"
								m.add(new Monomial(1, Integer.valueOf(rez[1])));
							}
						}
					}
				}
			}
			this.monomials = m;
			this.orderByExponent();
			this.reducePolynomial();
			return 0;
		} catch (WrongInputException e) {
			JOptionPane.showMessageDialog(null, "Wrong input string!", "Wrong input", JOptionPane.ERROR_MESSAGE);
			return 1;
		}
	}

	/**
	 * Method generates string for monomial, according to the values of the
	 * coefficient and exponent
	 * 
	 * @param coeff is integer and represents the coefficient
	 * @param exp   is integer and represents the exponent
	 * @param s     is the string that is being processed
	 * @return a String that represents the monomial represented by the couple
	 *         (coeff, exp)
	 */
	private String generateOutputUtil(int coeff, int exp, String s) {
		if (s.length() != 0 && coeff > 0) {
			// write + only if it is not the first monomial and the coefficient is positive
			s = s.concat("+");
		} else if (s.length() == 0 && coeff == -1 && exp != 0) {
			s = s.concat("-");
		}
		if (coeff < -1 || (coeff == 1 && exp == 0) || coeff > 1) {
			s = s.concat(String.valueOf(coeff));
		}
		if (exp >= 1) { // write x only if the coefficient >= 1
			s = s.concat("x");
		}
		if (exp > 1) { // write ^exp only if the exponent is > 1
			s = s.concat("^" + exp);
		}
		return s;
	}

	/**
	 * The same method as above: overloaded to display non integer numbers with 2
	 * decimals
	 * 
	 * @param coeff is a double
	 * @param exp
	 * @param s
	 * @return a String that represents the monomial represented by the couple
	 *         (coeff, exp)
	 */
	private String generateOutputUtil(double coeff, int exp, String s) {
		DecimalFormat df = new DecimalFormat("#.##");
		if (s.length() != 0 && coeff > 0) {
			// write + only if it is not the first monomial and the coefficient is positive
			s = s.concat("+");
		}
		if (coeff > 1 || coeff < 1) {
			s = s.concat(String.valueOf(df.format(coeff)));
		}
		if (exp >= 1) { // write x only if the coefficient >= 1
			s = s.concat("x");
		}
		if (exp > 1) { // write ^exp only if the exponent is > 1
			s = s.concat("^" + exp);
		}
		return s;
	}

	/*
	 * Method decides which of the "generateOutputUtil" methods must be called and
	 * generates and returns the string representing the polynomial for which the
	 * method is called
	 */
	public String generateOutputString() {
		String s = new String();
		if (this.monomials.size() != 0) {
			for (Monomial m : this.monomials) {
				int exp = m.getExponent();
				double coeff = m.getCoefficient();
				if (coeff == Math.floor(coeff)) {
					s = generateOutputUtil((int) coeff, exp, s);
				} else {
					s = generateOutputUtil(coeff, exp, s);
				}
			}
			return s;
		}
		return "0";
	}

	/**
	 * The method orders monomials is a polynomial descendingly, by their degree
	 */
	public void orderByExponent() { // and also remember the degree of the polynomial
		ExponentComparator comp = new ExponentComparator();
		this.monomials.sort(comp);
		this.setDegree();
	}

	/*
	 * The method erases from the polynomial the monomials with coefficient equals
	 * to 0
	 */
	public void deleteZeroCoefficients() {
		int i = 0;
		ArrayList<Monomial> x = this.monomials;
		while (i < x.size()) {
			Monomial m = x.get(i);
			if (m.getCoefficient() == 0) {
				x.remove(m);
			} else {
				i++;
			}
		}
		this.monomials = x;
	}

	/*
	 * Method used to reduce the polynomial, if it in can be simplified, deletes
	 * zero coefficients, orders polynomial by exponents and sets the degree
	 */
	public void reducePolynomial() {
		ArrayList<Monomial> rez = new ArrayList<Monomial>();
		for (int i = 0; i <= this.degree; i++) {
			rez.add(i, new Monomial());
		}
		for (int i = this.degree; i >= 0; i--) {
			for (Monomial m : this.monomials) { // for each monomial in the list
				if (m.getExponent() == i) { // add it to the position equal to its coefficient
					Monomial aux = new Monomial(); // use an auxiliary monomial to add coefficents of the same exponent
													// monomials
					aux.setExponent(i);
					aux.setCoefficient(rez.get(i).getCoefficient() + m.getCoefficient());
					if (aux.getCoefficient() != 0)
						rez.set(i, aux);
				}
			}
		}
		this.monomials = rez;
		this.deleteZeroCoefficients();
		this.orderByExponent();
		this.setDegree();
	}

	public int maxDegreePolynomial(Polynomial p) {
		return Math.max(this.degree, p.degree);
	}

	private Polynomial additionUtil(Polynomial p, int deg) { // deg is the degree of 'this' polynomial
		Polynomial rez = this;
		for (Monomial m : p.monomials) {
			int index = m.getExponent();
			rez.monomials.set(deg - index, rez.monomials.get(deg - index).addition(m));
		}
		return rez;
	}

	/**
	 * 
	 * @param p is the second polynomial that is added to the first one
	 * @return Polynomial type object: the result of the addition
	 */
	public Polynomial addition(Polynomial p) {
		int deg = this.maxDegreePolynomial(p);
		Polynomial rez = this.initZeroPolynomial(deg);
		rez = rez.additionUtil(this, deg);
		rez = rez.additionUtil(p, deg);
		rez.deleteZeroCoefficients();
		rez.setDegree();
		return rez;
	}

	private Polynomial subtractionUtil(Polynomial p, int deg) {
		Polynomial rez = this;
		for (Monomial m : p.monomials) {
			int index = m.getExponent();
			rez.monomials.set(deg - index, rez.monomials.get(deg - index).subtraction(m));
		}
		return rez;
	}

	/**
	 * 
	 * @param p is the second polynomial that is subtracted from the first one
	 * @return Polynomial type object: the result of the subtraction
	 */
	public Polynomial subtraction(Polynomial p) {
		int deg = this.maxDegreePolynomial(p);
		Polynomial rez = this.initZeroPolynomial(deg);
		rez = rez.additionUtil(this, deg);
		rez = rez.subtractionUtil(p, deg);
		rez.deleteZeroCoefficients();
		rez.setDegree();
		return rez;
	}

	/**
	 * 
	 * @param p is the second polynomial that is multiplied to the first one
	 * @return Polynomial type object: the result of the multiplication
	 */
	public Polynomial multiplication(Polynomial p) {
		int deg = this.degree + p.degree;
		Polynomial rez = this.initZeroPolynomial(deg);
		for (Monomial m : this.monomials) {
			for (Monomial n : p.monomials) {
				int index = m.getExponent() + n.getExponent();
				rez.monomials.set(deg - index, rez.monomials.get(deg - index).addition(m.multiplication(n)));
			}
		}
		rez.deleteZeroCoefficients();
		rez.setDegree();
		return rez;
	}

	/*
	 * Method returns the result of multiplication of a polynomial with a monomial
	 * It is used in the division method
	 */
	public Polynomial multiplyWithMonome(Monomial m) {
		Polynomial rez = new Polynomial();
		rez.degree = this.degree + m.getExponent();
		for (Monomial mon : this.monomials) {
			rez.monomials.add(mon.multiplication(m));
		}
		return rez;
	}

	/**
	 * 
	 * @param p is the second polynomial that the first polynomial has to be divided
	 *          to
	 * @return Polynomial array type object: the result of the division as quotient
	 *         and remainder
	 */
	public Polynomial[] division(Polynomial p) {
		Polynomial rez = new Polynomial(); // result
		Polynomial rem = new Polynomial(); // remainder
		Polynomial aux = this;
		while (aux.degree >= p.degree && aux.monomials.size() > 0) {
			rez.monomials.add(aux.monomials.get(0).division(p.monomials.get(0)));
			rez.setDegree();
			aux = aux.subtraction(p.multiplyWithMonome(rez.monomials.get(rez.monomials.size() - 1)));
			aux.deleteZeroCoefficients();
			aux.setDegree();
		}
		rem = aux;
		Polynomial[] pol = new Polynomial[2];
		pol[0] = rez;
		pol[1] = rem;
		return pol;
	}

	/**
	 * 
	 * @return polynomial: the result of derivation of a polynomial
	 */
	public Polynomial derivation() {
		Polynomial rez = this;
		int i = 0;
		for (Monomial m : rez.monomials) {
			m.setCoefficient(m.getCoefficient() * m.getExponent());
			m.setExponent(m.getExponent() - 1);
			rez.monomials.set(i, m);
			i++;
		}
		rez.setDegree();
		rez.deleteZeroCoefficients();
		return rez;
	}

	/**
	 * 
	 * @return polynomial: the result of integration of a polynomial
	 */
	public Polynomial integration() {
		Polynomial rez = this;
		int i = 0;
		for (Monomial m : rez.monomials) {
			m.setCoefficient(m.getCoefficient() / (m.getExponent() + 1));
			m.setExponent(m.getExponent() + 1);
			rez.monomials.set(i, m);
			i++;
		}
		rez.setDegree();
		return rez;
	}
}
