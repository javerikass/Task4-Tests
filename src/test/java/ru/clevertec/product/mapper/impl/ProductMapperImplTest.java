package ru.clevertec.product.mapper.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.Test;
import ru.clevertec.product.data.InfoProductDto;
import ru.clevertec.product.data.ProductDto;
import ru.clevertec.product.entity.Product;
import ru.clevertec.product.mapper.ProductMapper;
import ru.clevertec.product.mapper.ProductMapperImpl;
import ru.clevertec.product.util.ProductTestData;

class ProductMapperImplTest {

  private final ProductMapper mapper = new ProductMapperImpl();

  @Test
  void toProductShouldReturnProduct() {
    // given
    ProductDto expected = ProductTestData.builder().build().buildProductDto();

    // when
    Product actual = mapper.toProduct(expected);

    // then
    assertThat(actual)
        .hasFieldOrPropertyWithValue(Product.Fields.description, expected.description())
        .hasFieldOrPropertyWithValue(Product.Fields.name, expected.name())
        .hasFieldOrPropertyWithValue(Product.Fields.price, expected.price())
        .hasFieldOrPropertyWithValue(Product.Fields.uuid, null)
        .hasFieldOrPropertyWithValue(Product.Fields.created, null);
  }

  @Test
  void toInfoProductDtoTestShouldReturnInfoProductDto() {
    // given
    Product expected = ProductTestData.builder().build().buildProduct();

    // when
    InfoProductDto actual = mapper.toInfoProductDto(expected);

    // then
    assertThat(actual)
        .hasFieldOrPropertyWithValue(InfoProductDto.Fields.description, expected.getDescription())
        .hasFieldOrPropertyWithValue(InfoProductDto.Fields.name, expected.getName())
        .hasFieldOrPropertyWithValue(InfoProductDto.Fields.price, expected.getPrice())
        .hasFieldOrPropertyWithValue(InfoProductDto.Fields.uuid, expected.getUuid());
  }

  @Test
  void mergeTestShouldReturnProduct() {
    // given
    Product expectedProduct = ProductTestData.builder().build().buildProduct();
    ProductDto expectedProductDto = ProductTestData.builder().build().buildProductDto();

    // when
    Product actual = mapper.merge(expectedProduct, expectedProductDto);

    // then
    assertThat(actual)
        .hasFieldOrPropertyWithValue(ProductDto.Fields.description,
            expectedProductDto.description())
        .hasFieldOrPropertyWithValue(ProductDto.Fields.name, expectedProductDto.name())
        .hasFieldOrPropertyWithValue(ProductDto.Fields.price, expectedProductDto.price())
        .hasFieldOrPropertyWithValue(Product.Fields.uuid, expectedProduct.getUuid())
        .hasFieldOrPropertyWithValue(Product.Fields.created, expectedProduct.getCreated());
  }

  @Test
  void toListInfoProductDtoShouldReturnInfoProductDtoList() {
    // given
    List<Product> productList = ProductTestData.getProductList();
    List<InfoProductDto> expected = ProductTestData.getProductDtoList();

    // when
    List<InfoProductDto> actual = mapper.toListInfoProductDto(productList);

    // then
    assertEquals(expected, actual);
  }
}