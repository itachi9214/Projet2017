package ca.ulaval.glo4002.billing.http;

import java.io.IOException;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import ca.ulaval.glo4002.billing.api.dto.ClientDto;
import ca.ulaval.glo4002.billing.api.dto.ProductDto;

public class CrmHttpClient extends HttpClient {

  private static final String LOCALHOST = "http://localhost:8080";
  private static final String CLIENTS = "/clients/";
  private static final String PRODUCTS = "/products/";
  private static final int HTTP_STATUS_NOT_FOUND = 404;
  private static final String MESSAGE_CLIENT_NOT_FOUND = "client not found";
  private static final String MESSAGE_PRODUCT_NOT_FOUND = "product not found";

  @Override
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public ClientDto getClientDto(Long clientNumber) {
    ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());
    mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

    String url = LOCALHOST + CLIENTS + clientNumber;
    Response response = callUrlWithGetMethod(url);

    if (response.getStatus() == HTTP_STATUS_NOT_FOUND) {
      throw new NotFoundClientException(MESSAGE_CLIENT_NOT_FOUND);
    }

    ClientDto clientDto = null;
    try {
      clientDto = mapper.readValue(response.readEntity(JsonParser.class), ClientDto.class);
    } catch (IOException exception) {
      exception.printStackTrace();
    }

    response.close();
    return clientDto;
  }

  @Override
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public ProductDto getProductDto(Integer productId) {
    ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());
    mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

    String url = LOCALHOST + PRODUCTS + productId;
    Response response = callUrlWithGetMethod(url);

    if (response.getStatus() == HTTP_STATUS_NOT_FOUND) {
      throw new NotFoundProductException(MESSAGE_PRODUCT_NOT_FOUND);
    }

    ProductDto productDto = null;
    try {
      productDto = mapper.readValue(response.readEntity(JsonParser.class), ProductDto.class);
    } catch (IOException e) {
      e.printStackTrace();
    }

    response.close();
    return productDto;
  }

}
