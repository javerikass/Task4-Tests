package ru.clevertec.product.validator;

import java.math.BigDecimal;
import java.util.Objects;
import ru.clevertec.product.data.ProductDto;

public class ProductValidator {

  public static boolean validateProductDto(ProductDto product) throws IllegalArgumentException {
    if (Objects.isNull(product)) {
      throw new IllegalArgumentException("ProductDto must not be null");
    }
    return Objects.nonNull(product.name())
        && !product.name().isEmpty()
        && product.name().matches("^[а-яА-Я\\s]{5,10}$")
        && Objects.nonNull(product.price())
        && product.price().compareTo(BigDecimal.ZERO) >= 0
        && product.description().matches("^[а-яА-Я\\s]{10,30}$");
  }

}

