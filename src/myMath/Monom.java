package myMath;

/**
 * This class represents a simple "Monom" of shape a*x^b, where a is a real number and a is an integer (summed a none negative), 
 * see: https://en.wikipedia.org/wiki/Monomial 
 * The c lass implements function and support simple operations as: construction, value at x, derivative, add and multiply. 
 *@author Bar Genish
 *@author elyashiv deri
 */
public class Monom implements function{
	public Monom(double a, int b) {//defult constractor
		this.set_coefficient(a);
		try{this.set_power(b);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public Monom(Monom ot) {//copy constractor
		this.set_coefficient(ot._coefficient);
		try{this.set_power(ot._power);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public Monom(String str) throws Exception {//string constractor
		str = str.replaceAll("X", "x");
		if(str.matches("(?=.+)([+-]?[0-9]*[.]?[0-9]*(?:\\*?x?(?:\\^[0-9]+)?)?)*"))
		{
			str = str.replaceAll("\\*", "");
			this.set_coefficient(getCoef(str));
			try {this.set_power(getPow(str));}
			catch(Exception e) {System.out.println(e.getMessage());}
		}
		else throw new Exception("Monom must be from shape a*x^b while b must be positive");
	}
	private double getCoef(String str) {//help functation that get string and calculate the coefficient
		if(str.startsWith("x")) return 1;
		else if(str.startsWith("-x")) return -1;
		else if(!str.contains("x")) return Double.parseDouble(str);
		else return Double.parseDouble(str.split("x")[0]);
	}
	private int getPow(String str) throws Exception {//help functation that get string and calculate the power
		if(!str.contains("x")) return 0;
		else if(!str.contains("^")) return 1;
		else return Integer.parseInt(str.split("\\^")[1]);
		//int b=Integer.parseInt(str.split("x")[0]);
		//if(b>=0 )return b;
		//throw new Exception("the power must be positive");
	}
	public double get_coefficient() {//coefficient getter
		return this._coefficient;
	}
	public int get_power() {//power getter
		return this._power;
	}
	// ***************** add your code below **********************


	//****************** Private Methods and Data *****************

	private void set_coefficient(double a){//coefficient setter
		this._coefficient = a;
	}
	private void set_power(int p) throws Exception {//power setter
		if(p>=0) 
			this._power=p;
		else {
			throw new Exception("the power must be larger than 0");

		}
	}

	private double _coefficient;  
	private int _power;
	/**
	 *this function of type y=f(x), where both y and x are real numbers.
	 *@param x this is the value of x
	 *@return the value of this function on axis x
	 */
	@Override
	public double f(double x) {
		return( Math.pow(x, this._power)*this._coefficient);
	} 
	/**
	 *this function of type y=f(x), y real number and x natural number.
	 *@param x this is the value of x
	 *@return the value of this function on axis x
	 */
	public double f(int x) {
		return( Math.pow(x, this._power)*this._coefficient);
	}
	/**
	 * Test if this is the Zero monom
	 * @return true/false it its equals to zero
	 */
	public boolean isZero() {
		return(this._coefficient==0);
	}
	/**
	 * Test if this monom is logically equals to a.
	 * @param a monom I compre with my monom
	 * @return true iff this monom represents the same function ans a
	 */
	public boolean equals(Monom a) {
		return (a._coefficient==this._coefficient&&a._power==this._power);
	}
	/**
	 * write the monom as string
	 * @return string of all the monom
	 */
	public String toString() {
		if(this._power<0) return "the power must be larger then 0";
		if(this._coefficient==0)
			return("0");
		if(this._coefficient==1&&this._power==1)
			return ("x");
		if(this._coefficient==-1&&this._power==1)
			return ("-x");
		if(this._coefficient==1&&this._power>1)
			return ("x^"+this._power);
		if(this._coefficient==-1&&this._power>1)
			return ("-x^"+this._power);
		else if(this._power==1)
			return(this._coefficient+"x");
		else if(this._power==0)
			return(this._coefficient+"");
		else {
			return(this._coefficient+"x^"+this._power);
		}
	}
	/**
	 * Compute a new monom which is the derivative of this monom
	 * @return new monom I derivative
	 */
	public Monom derivative()

	{

		if (this._power == 0)

			return new Monom(0,0);

		return new Monom(this._coefficient*this._power,this._power-1);

	}
	/**
	 * Multiply ot monom with my monom
	 * @param ot the monom I multiply with my monom
	 * @return Multiply(Monom ot) 
	 */

	public Monom Multiply(Monom ot) {
		if(ot.isZero()||this.isZero()) return new Monom(0,0 );
		else {
			return new Monom(this._coefficient*ot._coefficient , this._power+ot._power);
		}
	}

	/**
	 * Add m1 to my monom
	 * @param a real number that represent coefficient
	 * @param b int number represent power
	 * @throws RuntimeException if the power of my monom isn't the same as the power b
	 * @throws java.lang.Exception if the power of my monom isn't the same as the power b
	 */
	public void add(double a, int b) throws Exception {
		if(b==this._power) {
			this._coefficient+=a;
		}
		else if(a==0) return;
		else {
			throw new Exception("the power must be the same as the original monom");
		}
	}
	/**
	 * Add m1 to my monom
	 * @param m1 Monom
	 * @throws RuntimeException if the power of my monom isn't the same as m1 power
	 * @throws java.lang.Exception if the power of my monom isn't the same as the power b
	 */
	public void add(Monom m1) throws Exception {
		if(m1.get_power()==this._power) {
			this._coefficient+=m1.get_coefficient();
		}
		else if(m1.get_coefficient()==0) return;
		else {
			throw new Exception("the power must be the same as the original monom");
		}
	}
	/**
	 * Subtract m from my monom
	 * @param m the monom I substract from my monom
	 * @throws RuntimeException if the power of my monom isn't the same as m power
	 * @return new Monom m substract from my monom 
	 */
	public Monom substract(Monom m){
		if(m.get_power()==this.get_power()) {
			this._coefficient -=m.get_coefficient();
			return new Monom(this.get_coefficient(),this.get_power() );
		}
		else if(m.get_coefficient()==0) return new Monom(this.get_coefficient(),this.get_power() );
		else if(this.isZero()) return new Monom(m._coefficient,m.get_power() );
		else
			throw new RuntimeException( "the power must be equals or the monom must be zero");
	}
}
