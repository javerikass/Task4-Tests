package ru.clevertec.product.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.clevertec.product.data.InfoProductDto;
import ru.clevertec.product.data.ProductDto;
import ru.clevertec.product.entity.Product;
import ru.clevertec.product.exception.ProductNotFoundException;
import ru.clevertec.product.mapper.ProductMapper;
import ru.clevertec.product.repository.ProductRepository;
import ru.clevertec.product.util.ProductTestData;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

  @Mock
  private ProductMapper mapper;
  @Mock
  private ProductRepository productRepository;

  @Captor
  private ArgumentCaptor<Product> productCaptor;

  @InjectMocks
  private ProductServiceImpl productService;

  @Test
  void getShouldReturnInfoProductDto() {
    // given
    UUID uuid = ProductTestData.UUID;
    InfoProductDto expected = ProductTestData.builder().build().buildInfoProductDto();
    Product product = ProductTestData.builder().build().buildProduct();
    when(productRepository.findById(uuid))
        .thenReturn(Optional.ofNullable(product));
    when(mapper.toInfoProductDto(product))
        .thenReturn(expected);

    // when
    InfoProductDto actual = productService.get(uuid);

    // then
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  void getShouldThrowExceptionWhenInfoProductDtoNotFound() {
    // given
    UUID uuid = ProductTestData.UUID;
    when(productRepository.findById(uuid))
        .thenReturn(Optional.empty());

    // when
    Executable actual = () -> productService.get(uuid);

    // then
    assertThrows(ProductNotFoundException.class, actual);
  }

  @Test
  void getAllShouldReturnListOfInfoProductDto() {
    // given
    List<InfoProductDto> expected = ProductTestData.getProductDtoList();
    List<Product> productList = ProductTestData.getProductList();

    when(productRepository.findAll()).thenReturn(productList);
    when(mapper.toListInfoProductDto(productList)).thenReturn(expected);

    // when
    List<InfoProductDto> actual = productService.getAll();

    // then
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  void createShouldReturnUUID() {
    // given
    ProductDto productDto = ProductTestData.builder().build().buildProductDto();
    Product product = ProductTestData.builder().build().buildProduct();
    UUID expected = ProductTestData.UUID;

    when(mapper.toProduct(productDto))
        .thenReturn(product);
    when(productRepository.save(product))
        .thenReturn(product);

    // when
    UUID actual = productService.create(productDto);

    // then
    assertEquals(expected, actual);
  }

  @ParameterizedTest
  @MethodSource("ru.clevertec.product.util.ProductTestData#provideProductData")
  void createShouldThrowExceptionWhenPassedInvalidData(String name, String description,
      BigDecimal price) {
    // given
    ProductDto productDto = ProductTestData.builder().build().buildProductDto();

    // when/then
    assertThrows(IllegalArgumentException.class, () -> productService.create(productDto));
    verifyNoInteractions(productRepository);
    verifyNoInteractions(mapper);

  }

  @Test
  void createShouldThrowExceptionWhenProductDtoIsNull() {
    // given
    ProductDto productDto = null;

    // when
    Executable actual = () -> productService.create(productDto);

    // then
    assertThrows(IllegalArgumentException.class, actual);
    verifyNoInteractions(productRepository);
    verifyNoInteractions(mapper);
  }

  @Test
  void createShouldSaveProductDtoToRepositoryWithoutUUIDAndWithCreatedDate() {
    // given
    ProductDto productDto = ProductTestData.builder().withUuid(null).build().buildProductDto();
    Product productToSave = ProductTestData.builder().withUuid(null).withCreated(null).build()
        .buildProduct();
    Product product = ProductTestData.builder().build().buildProduct();
    UUID expectedUUID = product.getUuid();

    Mockito.when(mapper.toProduct(productDto)).thenReturn(productToSave);
    Mockito.when(productRepository.save(productToSave)).thenReturn(product);

    // when
    UUID actualUUID = productService.create(productDto);

    // then
    assertEquals(expectedUUID, actualUUID);
    verify(productRepository).save(productCaptor.capture());
    assertNull(productCaptor.getValue().getUuid());
    assertNotNull(productCaptor.getValue().getCreated());

  }

  @Test
  void updateShouldCallProductRepositorySave() {
    // given
    UUID expected = ProductTestData.UUID;
    ProductDto productDto = ProductTestData.builder().build().buildProductDto();
    Product productWithoutUUID = ProductTestData.builder().withUuid(null).build().buildProduct();
    Product productWithUUID = ProductTestData.builder().build().buildProduct();

    when(mapper.toProduct(productDto)).thenReturn(productWithoutUUID);
    when(productRepository.save(productWithUUID)).thenReturn(productWithUUID);

    // when
    productService.update(expected, productDto);

    // then
    verify(productRepository).save(productCaptor.capture());
    assertEquals(expected, productCaptor.getValue().getUuid());

  }

  @Test
  void updateShouldThrowExceptionWhenGivenNull() {
    // given
    UUID expected = null;
    ProductDto productDto = null;

    // when
    Executable actual = () -> productService.update(expected, productDto);

    // then
    assertThrows(IllegalArgumentException.class, actual);
    verifyNoInteractions(productRepository);
    verifyNoInteractions(mapper);

  }

  @Test
  void deleteShouldCallProductRepositoryDelete() {
    // given
    UUID uuid = ProductTestData.UUID;

    // when
    productService.delete(uuid);

    // then
    verify(productRepository).delete(uuid);
  }

  @Test
  void deleteShouldThrowExceptionWhenGivenNull() {
    // given
    UUID uuid = null;

    // when
    Executable actual = () -> productService.delete(uuid);

    // then
    assertThrows(IllegalArgumentException.class, actual);
    verifyNoInteractions(productRepository);
  }

}