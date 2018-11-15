

package myMath;



import static org.junit.jupiter.api.Assertions.*;



import org.junit.jupiter.api.Test;



public class PolynomTest {





	@Test

	public void testf() throws Exception {

		Polynom_able p= new Polynom("0.2x^4-1.5x^3+3x^2-1x^1-5");

		double actual= p.f(1);

		double expected= -4.3;

		assertEquals(expected, actual);

	}

	@Test

	public void testsubstract() throws Exception{

		Polynom_able p1= new Polynom("2x^5+3x^3-2");

		Polynom_able p2= new Polynom("2x^4-1x^3+6x^2+5");

		p1.substract(p2);

		assertEquals(p1.toString(),"2.0x^5-2.0x^4+4.0x^3-6.0x^2-7.0");

		assertNotEquals(p1.toString(), "3x^4-1x^3+2x^2-11x+7");		

	}

		@Test

		public void testadd() throws Exception{

			Polynom_able p1= new Polynom("5x^5+3x^3-2");

			Monom m= new Monom(4,4);

			p1.add(m);

			assertEquals(p1.toString(),"5.0x^5+4.0x^4+3.0x^3-2.0");
	

	}

		@Test

		public void testmultiply() throws Exception{

			Polynom_able p1= new Polynom("5x^5+3x^3-2");

			Polynom_able p2= new Polynom("8x^4-1x^3+2x^2-11x+7");

			p1.multiply(p2);;

			assertNotEquals(p1.toString(), "5.0x^5-8.0x^4+4.0x^3-2.0x^2+11.0x-9.0");			

	}
		@Test

		public void testarea() throws Exception {

			Polynom_able p1= new Polynom("0.2x^4-1.5x^3+3x^2-1x^1-5");

			double actual= p1.area(-0.941, 4.831, 0.01);

			double expected= 25.183451113677425;

			assertEquals(expected, actual);

		}

		@Test

		public void testIsZero() throws Exception {

			Polynom_able p1= new Polynom("3x^4+6x^3-2");

			Polynom_able p2= new Polynom();

			boolean Test_1= p1.isZero();

			boolean Test_2= p2.isZero();

			assertFalse(Test_1);

			assertTrue(Test_2);

		}

		@Test

		public void testroot() throws Exception {

			Polynom_able p1= new Polynom("4x^3+2x^2+11x+23");

			double temp= p1.root(-2, 0, 0.01);

			double ans_root= -1.4189453125;

			assertEquals(ans_root, temp);

		}

		@Test

		public void testderivative() throws Exception{

			Polynom_able p= new Polynom("3x^4-2x^3+2x^2");

			p=p.derivative();
			assertEquals(p.toString(),"12.0x^3-6.0x^2+4.0x");

			assertNotEquals(p.toString(), "3x^4-2x^3+2x^2");		

		}

		

}