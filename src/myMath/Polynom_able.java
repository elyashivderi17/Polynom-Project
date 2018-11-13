package myMath;

import java.util.Iterator;

/**
 *@author Bar Genish
 *@author elyashiv deri
 *
 */
public interface Polynom_able extends cont_function{
	/**
	 * Add p1 to this Polynom
	 * @param p1 polynom I added
	 */
	public void add(Polynom_able p1);
	/**
	 * Add m1 to this Polynom
	 * @param m1 Monom
	 */
	public void add(Monom m1);
	/**
	 * Subtract p1 from this Polynom
	 * @param p1 substract p1 from my poly
	 */
	public void substract(Polynom_able p1);
	/**
	 * Multiply this Polynom by p1
	 * @param p1 the polynom I multiply with my polynom
	 * @throws Exception 
	 */
	public void multiply(Polynom_able p1) throws Exception;
	/**
	 * Test if this Polynom is logically equals to p1.
	 * @param p1 Polynom I compre with my Polynom
	 * @return true iff this pulynom represents the same function ans p1
	 */
	public boolean equals (Polynom_able p1);
	/**
	 * Test if this is the Zero Polynom
	 * @return true/false it its equals to zero
	 */
	public boolean isZero();

	public double root(double x0, double x1, double eps);
	/**
	 * create a deep copy of this Polynum
	 * @return same Polynom I sent
	 */
	public Polynom_able copy();
	/**
	 * Compute a new Polynom which is the derivative of this Polynom
	 * @return new polynom I derivative
	 */
	public Polynom_able derivative();
	/**
	 * Compute Riemann's Integral over this Polynom starting from x0, till x1 using eps size steps,
	 * see: https://en.wikipedia.org/wiki/Riemann_integral
	 * @return the approximated area above the x-axis below this Polynom and between the [x0,x1] range.
	 */
	public double area(double x0,double x1, double eps);
	/**
	 * @return an Iterator (of Monoms) over this Polynom
	 */
	public Iterator<Monom> iteretor();
}
