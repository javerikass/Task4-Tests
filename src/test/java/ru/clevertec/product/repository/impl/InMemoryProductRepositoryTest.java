package ru.clevertec.product.repository.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.clevertec.product.entity.Product;
import ru.clevertec.product.repository.ProductRepository;
import ru.clevertec.product.util.ProductTestData;

class InMemoryProductRepositoryTest {

  private Map<UUID, Product> testStorage;
  private ProductRepository repository;

  @BeforeEach
  void setUp() {
    testStorage = ProductTestData.getTestStorageMap();
    repository = new InMemoryProductRepository(testStorage);
  }

  @Test
  void findByIdShouldReturnProduct() {
    // given
    UUID uuid = ProductTestData.UUID;
    Product expected = ProductTestData.builder().build().buildProduct();

    // when
    Product actual = repository.findById(uuid).orElseThrow();

    // then
    assertEquals(expected, actual);
  }

  @Test
  void findByIdShouldReturnEmptyOptionalWhenProductNotFound() {
    // given
    UUID uuid = ProductTestData.UUID4;

    // when
    Optional<Product> actual = repository.findById(uuid);

    // then
    assertFalse(actual.isPresent());
  }

  @Test
  void findAllShouldReturnProductList() {
    // given
    List<Product> expected = ProductTestData.getProductList();

    // when
    List<Product> actual = repository.findAll();

    // then
    assertEquals(expected, actual);
  }

  @Test
  void saveShouldSaveProduct() {
    // given
    Product product = ProductTestData.builder().withUuid(ProductTestData.UUID4).build()
        .buildProduct();
    int testStorageSizeExpected = 4;

    // when
    repository.save(product);

    // then
    assertEquals(testStorageSizeExpected, testStorage.size());
  }

  @Test
  void deleteShouldRemoveProduct() {
    // given
    Product product = ProductTestData.builder().build().buildProduct();

    // when
    repository.delete(product.getUuid());
    boolean actual = repository.findById(product.getUuid()).isEmpty();

    //then
    assertTrue(actual);
  }
}