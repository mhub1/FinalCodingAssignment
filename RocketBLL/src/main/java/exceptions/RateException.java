package exceptions;

import rocketDomain.RateDomainModel;

public class RateException extends Exception {

	private RateDomainModel RateModel;

	public RateException(RateDomainModel rateModel) {
		super();
		RateModel = rateModel;
	}

	public RateDomainModel getRateModel() {
		return RateModel;
	}
}
