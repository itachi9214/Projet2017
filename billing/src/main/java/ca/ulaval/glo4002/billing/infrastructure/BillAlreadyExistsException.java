package ca.ulaval.glo4002.billing.infrastructure;

import ca.ulaval.glo4002.billing.domain.Submission.BillingException;

public class BillAlreadyExistsException extends BillingException {
	
	private static final long serialVersionUID = 1L;
	
	public BillAlreadyExistsException() {
		super();
	}

	public BillAlreadyExistsException(String message) {
		super(message);
	}

}
