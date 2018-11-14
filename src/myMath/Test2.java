package myMath;

public class Test2 {
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("We Are Testing now!!!");
		System.out.println("First test for all Monom fuctions ");
		System.out.println("Second test for all Polynom fuctions ");
		Polynomtest();
	}
	public static void Polynomtest() throws Exception {
		Polynom p1=new Polynom("3x^2-4x+7");

		System.out.println("isEquals test: ");

		System.out.println("test success ? :" + p1.equals(new Polynom ("3x^2-4x+7")));

		System.out.println("fx test: ");
		double fxpoly=p1.f(1);
		boolean ans=fxpoly==6;
		System.out.println("test success ? :" + ans);

		System.out.println("derivative test: ");
		Polynom_able deri=p1.derivative();
		Polynom dx=new Polynom("6x-4");
		System.out.println("test success ? :" + deri.equals(dx));

		System.out.println("multyply test: ");
		Polynom mult = new Polynom("3x^2+2");
		p1=new Polynom("3x^2-4x+7");
		mult.multiply(p1);
		ans=mult.equals(new Polynom("9x^4-12x^3+27x^2-8x+14"));
		System.out.println("test success ? :" + ans);

		System.out.println("add monom test: ");
		Monom m3 = new Monom(5,2);
		Polynom p3 = new Polynom("x^4+6x^3+x");
		p3.add(m3);
		Polynom p4=new Polynom("x^4+6x^3+5x^2+x");
		ans=p3.equals(p4);
		System.out.println("test success ? :" + ans);		

		System.out.println("add polynom test: ");
		p4.add(new Polynom("3x^5+2x+1"));
		System.out.println("test success ? :" + ans);

		System.out.println("substract test: ");
		Polynom sub = new Polynom("3x^2+2");
		sub.substract(new Polynom("3x^2-1"));
		ans=sub.equals(new Polynom("3"));
		System.out.println("test success ? :" + ans);

		System.out.println("isZero test: ");
		Polynom zero =new Polynom("0x^5");
		ans=zero.isZero();
		System.out.println("test success ? :" + ans);

		System.out.println("copy test: ");
		Polynom_able copy =p1.copy();
		ans=copy.equals(p1);
		System.out.println("test success ? :" + ans);

		Polynom p10 = new Polynom("x-2");
		System.out.println("area test:");
		double are=p10.area(0,4,0.01);
		ans= (are>=2 && are<=2.01);
		System.out.println("test success ? :" + ans);

		System.out.println("root test:");
		Polynom p11 = new Polynom("x-1");
		ans=(p11.root(0, 4, 0.01)>=1&&p11.root(0, 4, 0.01)<=1.01);
		System.out.println("test success ? :" + ans);
	}
}
