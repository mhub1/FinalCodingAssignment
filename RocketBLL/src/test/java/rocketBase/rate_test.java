package rocketBase;

import static org.junit.Assert.*;

import org.junit.Test;

import exceptions.RateException;

public class rate_test {

	//TODO - RocketBLL rate_test
	@Test
	public void rate_1_Test() throws RateException {
		assertTrue(6.0 == RateBLL.getRate(700));
	}
	
	@Test (expected = RateException.class)
	public void rate_2_Test() throws RateException {
		RateBLL.getRate(30);
	}
	
	@Test
	public void GetPaymentTest(){
		double pmt = RateBLL.getPayment(0.04, 20, 10000, 36000, false);
		double result = 107.56;
		assertTrue((pmt - result) < 0.01);
	}

}
