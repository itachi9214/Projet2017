package ca.ulaval.glo4002.billing.service.bill;

import ca.ulaval.glo4002.billing.ServiceLocator;
import ca.ulaval.glo4002.billing.api.dto.bill.BillDto;
import ca.ulaval.glo4002.billing.api.dto.payment.RequestPaymentDto;
import ca.ulaval.glo4002.billing.domain.bill.Bill;
import ca.ulaval.glo4002.billing.domain.bill.BillRepository;
import ca.ulaval.glo4002.billing.domain.bill.PaymentRepository;
import ca.ulaval.glo4002.billing.domain.identity.Identity;
import ca.ulaval.glo4002.billing.domain.identity.IdentityFactory;
import ca.ulaval.glo4002.billing.domain.payment.PaymentSource;
import ca.ulaval.glo4002.billing.domain.submision.Submission;
import ca.ulaval.glo4002.billing.domain.submision.SubmissionRepository;

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
    return billAssembler.assembleBill(bill);
  }

  public void cancelBill(long billNumber) {
    Identity identity = identityFactory.createIdFromNumber(billNumber);
    System.out.println(identity);
    System.out.println("2");
    Bill billToCancel = billRepository.findById(identity);
    System.out.println(billToCancel.getClientId());
    System.out.println("entre les deux");
    System.out.println(billToCancel.getPaidAmount().floatValue());
    System.out.println("youpi j ai passé les gardes");
    paymentRepository.savePayment(new RequestPaymentDto(billToCancel.getClientId(),
        billToCancel.getPaidAmount().floatValue(), ACCOUNT, SOURCE));
    System.out.println("affiche moi ");
  }

}
