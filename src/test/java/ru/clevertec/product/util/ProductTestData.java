package ru.clevertec.product.util;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import lombok.Builder;
import lombok.Data;
import ru.clevertec.product.data.InfoProductDto;
import ru.clevertec.product.data.ProductDto;
import ru.clevertec.product.entity.Product;

@Data
@Builder(setterPrefix = "with")
public class ProductTestData {

  public static final java.util.UUID UUID = java.util.UUID.fromString(
      "f26a8f36-7702-11ee-b962-0242ac120002");
  public static final java.util.UUID UUID2 = java.util.UUID.fromString(
      "809ca3b0-7736-11ee-b962-0242ac120002");
  public static final java.util.UUID UUID3 = java.util.UUID.fromString(
      "890388c0-7736-11ee-b962-0242ac120002");
  public static final String NAME = "Phone";
  public static final String DESCRIPTION = "Black";
  public static final BigDecimal PRICE = BigDecimal.valueOf(999.99);
  public static final LocalDateTime CREATED_TIME = LocalDateTime.of(2023, Month.OCTOBER, 20, 10, 0,
      0);

  @Builder.Default
  private UUID uuid = UUID;

  @Builder.Default
  private String name = NAME;

  @Builder.Default
  private String description = DESCRIPTION;

  @Builder.Default
  private BigDecimal price = PRICE;

  @Builder.Default
  private LocalDateTime created = CREATED_TIME;

  public Product buildProduct() {
    return new Product(uuid, name, description, price, created);
  }

  public ProductDto buildProductDto() {
    return new ProductDto(name, description, price);
  }

  public InfoProductDto buildInfoProductDto() {
    return new InfoProductDto(uuid, name, description, price);
  }

  public static List<InfoProductDto> getProductDtoList() {
    InfoProductDto infoProductDto1 = InfoProductDto.builder().build();
    InfoProductDto infoProductDto2 = InfoProductDto.builder()
        .uuid(ProductTestData.UUID2).build();
    InfoProductDto infoProductDto3 = InfoProductDto.builder()
        .uuid(ProductTestData.UUID3).build();

    return Arrays.asList(infoProductDto1, infoProductDto2, infoProductDto3);
  }

  public static List<Product> getProductList() {
    Product product = Product.builder().build();
    Product product2 = Product.builder().uuid(UUID2).build();
    Product product3 = Product.builder().uuid(UUID3).build();

    return Arrays.asList(product, product2, product3);
  }
}

