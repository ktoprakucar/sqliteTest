package dto;

import java.sql.Date;

/**
 * Created by toprak on 24-Mar-17.
 */
public class Input {
  public Date date;

  public Input(Date date, Long cardNumber, String discountCode) {
    this.date = date;
    this.cardNumber = cardNumber;
    this.discountCode = discountCode;
  }

  public Long cardNumber;
  public String discountCode;
}
