package myMath;



import static org.junit.jupiter.api.Assertions.*;



import org.junit.jupiter.api.Test;



class MonomTest {



	@Test

	void testMonomString() throws Exception {

		Monom DERI =new Monom("3x^3");

		Monom BAR =new Monom(3,3);

		if(!DERI.equals(BAR))

			fail("EROR FREND :both Monom have to be equals");

	}


	@Test

	void testMultiply() {

		double coef_DERI = Math.random()*10;

		double coef_DERI2 = Math.random()*10;

		int power_BAR = (int)(Math.random()*10);

		int power_BAR2 = (int)(Math.random()*10);

		Monom DERI = new Monom(coef_DERI,power_BAR);

		Monom BAR = new Monom(coef_DERI2,power_BAR2);

		DERI=DERI.Multiply(BAR);

		if(DERI.get_coefficient()!=coef_DERI*coef_DERI2||DERI.get_power()!=power_BAR+power_BAR2)

			fail("EROR FREND :both Monom have to be equals");

	}



	@Test

	void testderivative() {

		double coef_DERI = Math.random()*100;

		int power_BAR = (int)(Math.random()*100);

		Monom m0 = new Monom(coef_DERI , power_BAR);

		m0=m0.derivative();

		if(m0.get_coefficient() != power_BAR*coef_DERI || m0.get_power() != power_BAR-1)

			fail("EROR FREND: coefficient and power are not equal !!!!");

	}



	@Test

	void testadd() throws Exception {

		double DERI = Math.random()*100;

		double BAR = Math.random()*100;

		int power = (int)(Math.random()*100);

		Monom m1 = new Monom(DERI , power);

		Monom m2 = new Monom(BAR , power);

		m1.add(m2);

		if((m1.get_coefficient() != DERI+BAR))

			fail("EROR FREND: coefficient and power are not equal !,to that after adding the two monoms");

	}



	@Test

	void testsubstract() {

		double DERI = Math.random()*100;

		double BAR = Math.random()*100;

		int power = (int)(Math.random()*100);

		Monom m1 = new Monom(DERI , power);

		Monom m2 = new Monom(BAR , power);

		m1.substract(m2);

		if((m1.get_coefficient() != DERI-BAR))

			fail("EROR FREND: coefficient and power are not equal ! to that after substracting the two monoms");

	}



	@Test

	void testf() {

		double coef = Math.random()*100;

		int power = (int)(Math.random()*100);

		Monom m = new Monom(coef , power);

		double x = Math.random()*100;

		if(coef*Math.pow(x, power) != m.f(x))

			fail("EROR:wrong answer");

	}

	@Test

	void testToString() {

		double coef = Math.random()*10;

		int power = (int)(Math.random()*10);

		Monom m = new Monom(coef,power);

		

		if(coef == 0) {

			if(!m.toString().equals("0"))

				fail("EROR");



		}

		else if(power == 0) {

			if(!m.toString().equals("" + coef))

				fail("ERR:the power or the coef are not equals");



		}

		else if(power == 1) {

			if(!m.toString().equals("" + coef + "x"))

				fail("EROR");

		}

		else {

			if(!m.toString().equals("" + coef + "x^" + power))

				fail("EROR:the power or the coef are not equals");

		}

	}



}