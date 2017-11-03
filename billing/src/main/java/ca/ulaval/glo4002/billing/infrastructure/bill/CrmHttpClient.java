package ca.ulaval.glo4002.billing.http;

import java.io.IOException;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import ca.ulaval.glo4002.billing.api.dto.client.ClientDto;
import ca.ulaval.glo4002.billing.api.dto.product.ProductDto;

public class CrmHttpClient extends Http {

  private static final String LOCALHOST = "http://localhost:8080";
  private static final String CLIENTS = "/clients/";
  private static final String PRODUCTS = "/products/";
  private ObjectMapper mapper;

  public CrmHttpClient() {
    mapper = new ObjectMapper().registerModule(new JavaTimeModule());
    mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
  }

  @Override
  public ClientDto getClientDto(Long clientNumber) throws ClientNotFoundException {
    String url = LOCALHOST + CLIENTS + clientNumber;
    Response response = callUrlWithGetMethod(url);

    if (Status.fromStatusCode(response.getStatus()).equals(Status.NOT_FOUND)) {
      throw new ClientNotFoundException(clientNumber);
    }
    ClientDto clientDto = null;
    try {
      clientDto = mapper.readValue(response.readEntity(String.class), ClientDto.class);
    } catch (IOException exception) {
      exception.printStackTrace();
    } finally {
      response.close();
    }
    return clientDto;
  }

  @Override
  public ProductDto getProductDto(Integer productId) throws ProductNotFoundException {
    String url = LOCALHOST + PRODUCTS + productId;
    Response response = callUrlWithGetMethod(url);

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
