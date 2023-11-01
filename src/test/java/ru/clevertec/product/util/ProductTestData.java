package ru.clevertec.product.util;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Stream;
import lombok.Builder;
import lombok.Data;
import org.junit.jupiter.params.provider.Arguments;
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
  public static final java.util.UUID UUID4 = java.util.UUID.fromString(
      "850393c0-7736-11ee-b962-0242ac120002");

  public static final String NAME = "Машина";
  public static final String NAME2 = "Яблоко";
  public static final String NAME3 = "Мышка";
  public static final String DESCRIPTION = "Описание товара машина";
  public static final String DESCRIPTION2 = "Описание товара яблоко";
  public static final String DESCRIPTION3 = "Описание товара мышка";
  public static final BigDecimal PRICE = BigDecimal.valueOf(10.99);
  public static final BigDecimal PRICE2 = BigDecimal.valueOf(19.99);
  public static final BigDecimal PRICE3 = BigDecimal.valueOf(29.99);
  public static final LocalDateTime CREATED_TIME = LocalDateTime.of(2023, Month.OCTOBER, 20, 10, 0);
  public static final LocalDateTime CREATED_TIME2 = LocalDateTime.of(2023, Month.OCTOBER, 21, 10,
      0);
  public static final LocalDateTime CREATED_TIME3 = LocalDateTime.of(2023, Month.OCTOBER, 22, 10,
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
    InfoProductDto infoProductDto1 = ProductTestData.builder().build().buildInfoProductDto();

    InfoProductDto infoProductDto2 = ProductTestData.builder()
        .withUuid(ProductTestData.UUID2)
        .withName(ProductTestData.NAME2)
        .withDescription(ProductTestData.DESCRIPTION2)
        .withPrice(ProductTestData.PRICE2)
        .build().buildInfoProductDto();

    InfoProductDto infoProductDto3 = ProductTestData.builder()
        .withUuid(ProductTestData.UUID3)
        .withName(ProductTestData.NAME3)
        .withDescription(ProductTestData.DESCRIPTION3)
        .withPrice(ProductTestData.PRICE3)
        .build().buildInfoProductDto();

    return Arrays.asList(infoProductDto1, infoProductDto2, infoProductDto3);
  }

  public static List<Product> getProductList() {
    Product product = ProductTestData.builder().build().buildProduct();

    Product product2 = ProductTestData.builder()
        .withUuid(ProductTestData.UUID2)
        .withName(ProductTestData.NAME2)
        .withDescription(ProductTestData.DESCRIPTION2)
        .withPrice(ProductTestData.PRICE2)
        .withCreated(ProductTestData.CREATED_TIME2)
        .build().buildProduct();

    Product product3 = ProductTestData.builder()
        .withUuid(ProductTestData.UUID3)
        .withName(ProductTestData.NAME3)
        .withDescription(ProductTestData.DESCRIPTION3)
        .withPrice(ProductTestData.PRICE3)
        .withCreated(ProductTestData.CREATED_TIME3)
        .build().buildProduct();

    return Arrays.asList(product, product2, product3);
  }

  public static Stream<Arguments> provideProductData() {
    return Stream.of(
        Arguments.of(null, "Описание товара", new BigDecimal("10.00")),
        Arguments.of("", "Описание товара", new BigDecimal("20.00")),
        Arguments.of("прод", "Описание товара", new BigDecimal("30.00")),
        Arguments.of("продуктовый", "Описание товара", new BigDecimal("30.00")),
        Arguments.of("product", "Описание товара", new BigDecimal("30.00")),
        Arguments.of("Продукт", "Описание товара", null),
        Arguments.of("Продукт", "Описание товара", new BigDecimal("-30.00")),
        Arguments.of("Продукт", "Описание", new BigDecimal("30.00")),
        Arguments.of("Продукт", "Description", new BigDecimal("30.00"))
    );
  }

  public static Map<UUID, Product> getTestStorageMap() {
    Map<UUID, Product> map = new HashMap<>();
    Product product = ProductTestData.builder().build().buildProduct();

    Product product2 = ProductTestData.builder()
        .withUuid(ProductTestData.UUID2)
        .withName(ProductTestData.NAME2)
        .withDescription(ProductTestData.DESCRIPTION2)
        .withPrice(ProductTestData.PRICE2)
        .withCreated(ProductTestData.CREATED_TIME2)
        .build().buildProduct();

    Product product3 = ProductTestData.builder()
        .withUuid(ProductTestData.UUID3)
        .withName(ProductTestData.NAME3)
        .withDescription(ProductTestData.DESCRIPTION3)
        .withPrice(ProductTestData.PRICE3)
        .withCreated(ProductTestData.CREATED_TIME3)
        .build().buildProduct();

    map.put(UUID, product);
    map.put(UUID2, product2);
    map.put(UUID3, product3);
    return map;
  }
}
