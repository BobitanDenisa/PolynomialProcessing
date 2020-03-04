package PT2019.assignment1.Assignment_1;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import model.Monomial;
import model.Polynomial;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {

	private Polynomial p1;
	private Polynomial p2;
	private Monomial m1;
	private Monomial m2;

	/**
	 * Create the test case
	 *
	 * @param testName name of the test case
	 */
	public AppTest(String testName) {
		super(testName);
		this.p1 = new Polynomial();
		this.p2 = new Polynomial();
		this.m1 = new Monomial();
		this.m2 = new Monomial();

	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(AppTest.class);
	}

	/**
	 * Rigourous Test :-)
	 */
	public void testMonAdd() {
		this.m1 = new Monomial(2, 3);
		this.m2 = new Monomial(3, 3);
		Monomial m3 = m1.addition(m2);
		assertEquals((double) 5, m3.getCoefficient());
		assertEquals(3, m3.getExponent());
	}

	public void testMonSub() {
		this.m1 = new Monomial(2, 3);
		this.m2 = new Monomial(3, 3);
		Monomial m3 = m1.subtraction(m2);
		assertEquals((double) -1, m3.getCoefficient());
		assertEquals(3, m3.getExponent());
	}

	public void testMonMul() {
		this.m1 = new Monomial(2, 3);
		this.m2 = new Monomial(3, 3);
		Monomial m3 = m1.multiplication(m2);
		assertEquals((double) 6, m3.getCoefficient());
		assertEquals(6, m3.getExponent());
	}

	public void testMonDiv() {
		this.m1 = new Monomial(6, 3);
		this.m2 = new Monomial(3, 2);
		Monomial m3 = m1.division(m2);
		assertEquals((double) 2, m3.getCoefficient());
		assertEquals(1, m3.getExponent());
	}

	public void testMonDeriv() {
		this.m1 = new Monomial(2, 3);
		Monomial m3 = m1.derivation();
		assertEquals((double) 6, m3.getCoefficient());
		assertEquals(2, m3.getExponent());
	}

	public void testMonIntegr() {
		this.m1 = new Monomial(-3, 2);
		Monomial m3 = m1.integration();
		assertEquals((double) -1, m3.getCoefficient());
		assertEquals(3, m3.getExponent());
	}

	public void testPolyAdd() {
		this.p1.generateMonomials("2x^3+3x");
		this.p2.generateMonomials("3x^3+x+5");
		assertEquals("5x^3+4x+5", p1.addition(p2).generateOutputString());
	}

	public void testPolySub() {
		this.p1.generateMonomials("2x^3+3x");
		this.p2.generateMonomials("3x^3+x+5");
		assertEquals("-x^3+2x-5", p1.subtraction(p2).generateOutputString());
	}

	public void testPolyMul() {
		this.p1.generateMonomials("2x^3+3x");
		this.p2.generateMonomials("3x^3+x+5");
		assertEquals("6x^6+11x^4+10x^3+3x^2+15x", p1.multiplication(p2).generateOutputString());
	}

	public void testPolyDiv() {
		this.p1.generateMonomials("2x^3+3x");
		this.p2.generateMonomials("x^2+1");
		Polynomial[] p3 = p1.division(p2);
		assertEquals("2x", p3[0].generateOutputString());
		assertEquals("x", p3[1].generateOutputString());
	}

	public void testPolyDeriv() {
		this.p1.generateMonomials("-x^2+1");
		assertEquals("-2x", p1.derivation().generateOutputString());
	}

	public void testPolyIntegr() {
		this.p1.generateMonomials("-3x^2+1");
		assertEquals("-x^3+x", p1.integration().generateOutputString()); // "+C" is added only to display
	}
}
