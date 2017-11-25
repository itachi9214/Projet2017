package ca.ulaval.glo4002.billing.infrastructure.submitting;

import ca.ulaval.glo4002.billing.ServiceLocator;
import ca.ulaval.glo4002.billing.api.dto.product.ProductDto;
import ca.ulaval.glo4002.billing.domain.submitting.ProductRepository;
import ca.ulaval.glo4002.billing.http.CrmHttpProduct;

public class ProductHttpRepository implements ProductRepository {

  private CrmHttpProduct httpProduct;

  public ProductHttpRepository() {
    this.httpProduct = ServiceLocator.getService(CrmHttpProduct.class);
  }

  public ProductHttpRepository(CrmHttpProduct httpProduct) {
    this.httpProduct = httpProduct;
  }

  @Override
  public ProductDto getProductDto(Integer productId) {
    return httpProduct.getProductDto(productId);
  }

}
