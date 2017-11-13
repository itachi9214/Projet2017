package ca.ulaval.glo4002.billing.http;

import java.io.IOException;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import ca.ulaval.glo4002.billing.ServiceLocator;
import ca.ulaval.glo4002.billing.api.dto.product.ProductDto;

public class CrmHttpProduct {

  private static final String LOCALHOST = "http://localhost:8080";
  private static final String PRODUCTS = "/products/";

  private ObjectMapper mapper;
  private UtilHttp http;

  public CrmHttpProduct(UtilHttp http) {
    this.http = http;
    mapper = new ObjectMapper().registerModule(new JavaTimeModule());
    mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
  }

  public CrmHttpProduct() {
    this(ServiceLocator.getService(UtilHttp.class));
  }

  public ProductDto getProductDto(Integer productId) {
    String url = LOCALHOST + PRODUCTS + productId;
    Response response = http.callUrlWithGetMethod(url);

    if (Status.fromStatusCode(response.getStatus()).equals(Status.NOT_FOUND)) {
      throw new ProductNotFoundException(productId);
    }
    ProductDto productDto = null;
    try {
      productDto = mapper.readValue(response.readEntity(String.class), ProductDto.class);
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      response.close();
    }
    return productDto;
  }

}
