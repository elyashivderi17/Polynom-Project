package myMath;

public class test {
	public static void main(String[] args) throws Exception  {
		System.out.println("the test begins:");
		tests();
	}
  
	public static void tests() throws Exception {
		Polynom p=new Polynom("5x^3-2x+1");
  
		System.out.println("equals test:");

		System.out.println("test success ? :" + p.equals(new Polynom ("5x^3-2x+1")));

		System.out.println("f(x) test:");
		double fx=p.f(1);
		boolean ans=fx==4;
		System.out.println("test success ? :" + ans);

		System.out.println("derivative test: ");
		Polynom_able p2=p.derivative();
		Polynom p3=new Polynom("15x^2-2");
		System.out.println("test success ? :" + p2.equals(p3));
		System.out.println("multyply test: ");
		Polynom mult = new Polynom("3x^2+2");
		p=new Polynom("5x^3-2x+1");
		mult.multiply(p);
		ans=mult.equals(new Polynom("15x^5+4x^3+3x^2-4x+2"));
		System.out.println("test success ? :" + ans);
		System.out.println("add monom test: ");
		Monom m3 = new Monom(5,2);
		Polynom p4 = new Polynom("1x^4+6x^3+1x");
		p4.add(m3);
		Polynom p5=new Polynom("1x^4+6x^3+5x^2+1x");
		ans=p4.equals(p5);
		System.out.println("test success ? :" + ans);		

		System.out.println("add polynom test: ");
		p5.add(new Polynom("3x^5+2x+1"));
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
		Polynom_able copy =p.copy();
		ans=copy.equals(p);
		System.out.println("test success ? :" + ans);

		Polynom p6 = new Polynom("x-2");
		System.out.println("area test:");
		double are=p6.area(0,4,0.01);
		ans= (are>=2 && are<=2.01);
		System.out.println("test success ? :" + ans);

		System.out.println("root test:");
		Polynom p7 = new Polynom("x-1");
		ans=(p7.root(0, 4, 0.01)>=1&&p7.root(0, 4, 0.01)<=1.01);
		System.out.println("test success ? :" + ans);
	}
}

