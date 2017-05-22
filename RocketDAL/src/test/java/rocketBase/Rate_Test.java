package rocketBase;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import rocketDomain.RateDomainModel;

public class Rate_Test {

	
	@Test
	public void rateTest(){
		assertTrue(RateDAL.getAllRates().size() > 0);
	}


}
