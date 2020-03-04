package model;

import java.util.Comparator;

class ExponentComparator implements Comparator<Monomial> {

	public int compare(Monomial m1, Monomial m2) {
		return m2.getExponent() - m1.getExponent();
	}

}