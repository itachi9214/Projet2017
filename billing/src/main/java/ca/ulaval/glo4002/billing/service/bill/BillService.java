package ca.ulaval.glo4002.billing.service.bill;

import ca.ulaval.glo4002.billing.ServiceLocator;
import ca.ulaval.glo4002.billing.api.dto.bill.BillDto;
import ca.ulaval.glo4002.billing.api.dto.bill.BillForPaymentDto;
import ca.ulaval.glo4002.billing.api.dto.payment.RequestPaymentDto;
import ca.ulaval.glo4002.billing.domain.bill.Bill;
import ca.ulaval.glo4002.billing.domain.bill.BillRepository;
import ca.ulaval.glo4002.billing.domain.bill.PaymentRepository;
import ca.ulaval.glo4002.billing.domain.identity.Identity;
import ca.ulaval.glo4002.billing.domain.identity.IdentityFactory;
import ca.ulaval.glo4002.billing.domain.payment.PaymentSource;
import ca.ulaval.glo4002.billing.domain.submitting.Submission;
import ca.ulaval.glo4002.billing.domain.submitting.SubmissionRepository;

public class BillService {

  private static final String ACCOUNT = "1111-111-111";
  private static final PaymentSource SOURCE = PaymentSource.EFT;

  private BillRepository billRepository;
  private BillAssembler billAssembler;
  private SubmissionRepository submissionRepository;
  private IdentityFactory identityFactory;
  private PaymentRepository paymentRepository;

  public BillService() {
    this.billRepository = ServiceLocator.getService(BillRepository.class);
    this.billAssembler = ServiceLocator.getService(BillAssembler.class);
    this.submissionRepository = ServiceLocator.getService(SubmissionRepository.class);
    this.identityFactory = ServiceLocator.getService(IdentityFactory.class);
    this.paymentRepository = ServiceLocator.getService(PaymentRepository.class);
  }

  public BillService(IdentityFactory identityFactory, BillAssembler billAssembler,
      SubmissionRepository submissionRepository, BillRepository billRepository) {
    this.billRepository = billRepository;
    this.billAssembler = billAssembler;
    this.submissionRepository = submissionRepository;
    this.identityFactory = identityFactory;
  }

  public BillDto createBill(long billNumber) {
    Identity identity = identityFactory.createIdFromNumber(billNumber);
    Submission submission = submissionRepository.findSubmissionById(identity);

    Bill bill = billAssembler.createBillFromSubmission(submission);
    billRepository.createBill(bill);

    BillDto billDto = billAssembler.assembleBill(bill);
    return billDto;
  }

  public BillForPaymentDto getOldestUnpaidBillForClient(Long clientId) {
    Bill bill = billRepository.findOldestUnpaidBillByClientId(clientId);

    BillForPaymentDto billForPaymentDto = billAssembler.assembleBillForPayment(bill);
    return billForPaymentDto;
  }

  public void updateBillAfterPayment(BillForPaymentDto billForPaymentDto) {
    Identity identity = identityFactory.createIdFromNumber(billForPaymentDto.getBillNumber());
    Bill bill = billRepository.findById(identity);

    bill.updateAfterPayment(billForPaymentDto.getRemainingAmount());
    billRepository.updateBill(bill);
  }

  public void cancelBill(long billNumber) {
    Identity identity = identityFactory.createIdFromNumber(billNumber);
    Bill billToCancel = billRepository.findById(identity);

    billRepository.cancelBill(identity);
    paymentRepository.savePayment(new RequestPaymentDto(billToCancel.getClientId(),
        billToCancel.getPaidAmount().floatValue(), ACCOUNT, SOURCE));
  }

}
