package ca.ulaval.glo4002.billing.http;

import ca.ulaval.glo4002.billing.api.dto.product.ProductDto;

public interface ProductRepository {

  public ProductDto getProductDto(Integer productId);

}