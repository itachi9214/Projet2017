package ca.ulaval.glo4002.billing.infrastructure.bill;

import java.io.IOException;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import ca.ulaval.glo4002.billing.api.dto.product.ProductDto;
import ca.ulaval.glo4002.billing.http.Http;
import ca.ulaval.glo4002.billing.http.Product;
import ca.ulaval.glo4002.billing.http.ProductNotFoundException;

public class CrmHttpProduct implements Product {

  private static final String LOCALHOST = "http://localhost:8080";
  private static final String PRODUCTS = "/products/";
  private ObjectMapper mapper;
  private Http http;

  public CrmHttpProduct(Http http) {

    this.http = http;
    mapper = new ObjectMapper().registerModule(new JavaTimeModule());
    mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
  }

  public CrmHttpProduct() {

  }

  @Override
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
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