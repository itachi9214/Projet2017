package ca.ulaval.glo4002.payment.api.dto;

import java.util.List;

public class BadRequestErrorListDto {

  private List<BadRequestDto> errors;

  public BadRequestErrorListDto() {
  }

  public BadRequestErrorListDto(List<BadRequestDto> errors) {
    this.errors = errors;
  }

  public List<BadRequestDto> getErrors() {
    return errors;
  }

  public void setErrors(List<BadRequestDto> errors) {
    this.errors = errors;
  }

}
