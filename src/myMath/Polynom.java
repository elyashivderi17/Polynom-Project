package myMath;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import myMath.Monom;
/**
 * This class represents a Polynom with add, multiply functionality, it also should support the following:
 * 1. Riemann's Integral: https://en.wikipedia.org/wiki/Riemann_integral
 * 2. Finding a numerical value between two values (currently support root only f(x)=0).
 * 3. Derivative
 *@author Bar Genish
 *@author elyashiv deri
 */
public class Polynom implements Polynom_able{
	private ArrayList<Monom>Polly;

	public Polynom() {//defult constractor
		this.Polly=new ArrayList<Monom>(0);
	}
	public Polynom(Polynom p) {//copy constractor
		Polly = new ArrayList<Monom>();
		Iterator<Monom> it = p.iteretor();
		while(it.hasNext()) {
			Monom a = new Monom(it.next());
			add(a);
		}
	}
	public Polynom(String str) throws Exception {//string constractor
		str = str.replaceAll("X", "x");
		if(str.matches("(?=.+)([+-]?[0-9]*[.]?[0-9]*(?:\\*?x?(?:\\^[0-9]+)?)?)*"))
		{
			this.Polly=new ArrayList<Monom>(0);
			str = str.replaceAll("\\-", "+-");
			str = str.replaceAll("\\*", "");
			if(str.charAt(0)=='+') {
				str=str.substring(1);
			}
			for(String m :str.split("\\+")) Polly.add(new Monom(m));
		}
		else System.err.println("insert unvaild polynom");
		Polly.sort(new Monom_Comperator());	
		//removeZeros();
	}
	/**
	 *this function of type y=f(x), where both y and x are real numbers.
	 *@param x this is the value of x
	 *@return the value of this function on axis x
	 */
	@Override
	public double f(double x) {
		double ans=0;
		Iterator<Monom> runner=this.iteretor();
		while(runner.hasNext()) {
			Monom m=runner.next();
			ans+=m.f(x);
		}
		return ans;
	}
	/**
	 *this function of type y=f(x), where both y real numbers and x natural number
	 *@param x this is the value of x
	 *@return the value of this function on axis x
	 */
	public double f(int x) {
		double ans=0;
		Iterator<Monom> runner=this.iteretor();
		while(runner.hasNext()) {
			Monom m=runner.next();
			ans+=m.f(x);
		}
		return ans;
	}
	/**
	 * Add p1 to this Polynom
	 * @param p1 polynom I added
	 */
	@Override
	public void add(Polynom_able p1) {
		Iterator<Monom> runner =p1.iteretor();
		while (runner.hasNext()) {

			this.add(runner.next());
		}
		Polly.sort(new Monom_Comperator());
		//removeZeros();
	}
	/**
	 * Add m1 to this Polynom
	 * @param m1 Monom
	 */
	@Override
	public void add(Monom m1) {
		if(m1.get_coefficient()==0)
			return;
		Iterator<Monom> runner=this.iteretor();
		while (runner.hasNext())
		{
			Monom m=runner.next();
			if (m.get_power()==m1.get_power()) {
				try	{m.add(m1);
				}catch(Exception e) {System.out.println(e.getMessage());}

				if(m.isZero()) {
					Polly.remove(m);
				}
				return;
			}
		}
		Polly.add(m1);
		Polly.sort(new Monom_Comperator());
	}

	/**
	 * Subtract p1 from this Polynom
	 * @param p1 the Polynom I substract with my polynom
	 */
	@Override
	public void substract(Polynom_able p1) {
		try {
			Polynom_able p2 = p1.copy();
			p2.multiply(new Polynom("-1"));
			add(p2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

		Polly.sort(new Monom_Comperator());
		//removeZeros();
	}
	/**
	 * Subtract m from this Polynom
	 * @param m the monom I substract from my polynom
	 */
	public void substract(Monom m) {
		Polynom p = new Polynom();
		p.add(m);
		this.substract(p);
		Polly.sort(new Monom_Comperator());
	}
	/**
	 * Multiply this Polynom by p1
	 * @param p1 the polynom I multiply with my polynom
	 */
	@Override
	public void multiply(Polynom_able p1) {
		Iterator<Monom> runner=this.iteretor();
		Polynom tmp=new Polynom();
		while(runner.hasNext()) {
			Monom run= (runner.next());
			Iterator<Monom> runner2 = p1.iteretor();
			while (runner2.hasNext())
			{
				tmp.add(runner2.next().Multiply(run));
			}
		}
		Polly=tmp.Polly;
		Polly.sort(new Monom_Comperator());
		//removeZeros();
	}

	/**
	 * Test if this Polynom is logically equals to p1.
	 * @param p1 Polynom I compre with my Polynom
	 * @return true iff this pulynom represents the same function ans p1
	 */
	@Override
	public boolean equals(Polynom_able p1)
	{	
		Iterator<Monom>runner=this.iteretor();
		Iterator<Monom> runner2 = p1.iteretor();
		while(runner.hasNext()) {
			if(!runner2.hasNext())
				return false;
			if(!runner.next().equals(runner2.next()))
				return false;
		}
		return true;
	}
	/**
	 * Test if this is the Zero Polynom
	 * @return true/false it its equals to zero
	 */
	@Override
	public boolean isZero() 
	{
		Iterator<Monom> runner = this.iteretor();
		while (runner.hasNext())
		{
			if(runner.next().get_coefficient()!=0)
			{
				return false;
			}
		}		
		return true;
	}

	/**
	 * The root function finds a root value of a polynom while given 2 values that 1 is positive and the other negative
	 * and eps which will make sure that we are eps close to the 0.
	 * @param x0 should be the lower value of the x's provided
	 * @param x1 should be the higher value of the x's provided
	 * @param eps is the threshold value to check if the x value is close to the correct answer
	 * @return real number that represent the distance of our x-Axis to the X axis of an apsilon deviation
	 */
	@Override
	public double root(double x0, double x1, double eps) {
		if (f(x0) * f(x1) <= 0) {
			double mid_x = (x1 + x0) / 2;
			if (Math.abs(f(mid_x)) < eps) {
				return mid_x;
			}
			if (f(x0) == 0) {
				return x0;
			}
			if (f(x1) == 0) {
				return x1;
			}
			if (f(mid_x) < 0) {
				x0 = mid_x;
			} else if (f(mid_x) > 0) {
				x1 = mid_x;
			}
		}
		else {
			throw new IllegalArgumentException("The array is invalid ");
		}
		return root(x0, x1, eps);
	}
	/**
	 * create a deep copy of this Polynum
	 * @return same Polynom I sent
	 */
	@Override
	public Polynom_able copy() {
		Iterator<Monom>runner=this.iteretor();
		Polynom p1=new Polynom();
		while(runner.hasNext()) {
			p1.add(runner.next());
		}
		return p1;
	}
	/**
	 * Compute a new Polynom which is the derivative of this Polynom
	 * @return new polynom I derivative
	 */
	@Override
	public Polynom_able derivative() {
		Iterator<Monom>runner=this.iteretor();
		Polynom p1=new Polynom();
		while(runner.hasNext()) {
			Monom m=runner.next().derivative();
			if(m.get_coefficient()!=0&&m.get_power()>=0)
				p1.add(m);
		}
		return p1;


	}
	/**
	 * Compute Riemann's Integral over this Polynom starting from x0, till x1 using eps size steps,
	 * see: https://en.wikipedia.org/wiki/Riemann_integral
	 * @return the approximated area above the x-axis below this Polynom and between the [x0,x1] range.
	 */
	@Override
	public double area(double x0, double x1, double eps) {
		double x = 0.0;
		try{if(x0>x1)
			throw new Exception("x0 nust be smaller than x1");
		}catch (Exception e) {System.out.println(e.getMessage()); return Double.NaN;}
		for(double i=x0;i<x1;i=i+eps)
		{
			if(f(i)<0)
			{

			}
			else
			{
				x+=f(i)*eps;
			}
		}
		return x;
	}
	/**
	 * @return an Iterator (of Monoms) over this Polynom
	 */
	@Override
	public Iterator<Monom> iteretor() {
		return Polly.iterator();
	}
	/**
	 * write the polynom as string
	 * @return string of all the polynom
	 */
	public String toString() {
		String s ="";
		Iterator <Monom> runner=this.iteretor();
		//if(runner.hasNext()) {
		//s=runner.next().toString();
		//	}
		while(runner.hasNext()) {
			Monom m=runner.next();
			if(!m.isZero()) {
				s+="+"+m.toString();
			}
		}
		while(runner.hasNext()) {
			Monom m=runner.next();
			if(!m.isZero()) {
				s+="+"+m.toString();
			}
		}
		if(s=="") s="0";
		if(s.charAt(0)=='+')
			s=s.substring(1);
		s=s.replace("+-", "-");

		return s;
	}
	public void GUI(double x0, double x1, double eps) {

		System.out.println("The total area on the X axis is: " );
		System.out.println(area(x0, x1, eps));
		System.out.println();
		Graph frame = new Graph(this, x0, x1, eps);

		frame.setVisible(true);

	}


	public LinkedList<Double> extremaPoints(double x0, double x1, double eps) {

		LinkedList<Double> answer = new LinkedList<>();

		if (x0 > x1)

			return answer;

		Polynom der = (Polynom)this.derivative();

		double pointer = x0;

		while (pointer <= x1) {

			double changeDer = der.f(pointer)*der.f(pointer-eps); 

			if (changeDer < 0 )

				answer.add(pointer);

			else if (changeDer == 0 && der.f(pointer)==0) //pointer is extreme point

				answer.add(pointer);

			pointer += eps;

		}

		return answer;

	}


	// ********** add your code below ***********
	//	private void removeZeros() {
	//	Iterator <Monom> runner=this.iteretor();
	//	for(Monom m : Polly) {
	//		if(m.get_coefficient()==0) Polly.remove(m);
	//		else if(m.equals("0")||m.equals("+0")||m.equals("-0")) Polly.remove(m);
	//	}
	//		if(Polly.isEmpty())
	//		Polly.add(new Monom(0,0));
	//}
}

