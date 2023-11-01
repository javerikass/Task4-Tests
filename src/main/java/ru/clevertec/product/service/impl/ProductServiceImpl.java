package ru.clevertec.product.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import ru.clevertec.product.data.InfoProductDto;
import ru.clevertec.product.data.ProductDto;
import ru.clevertec.product.entity.Product;
import ru.clevertec.product.exception.ProductNotFoundException;
import ru.clevertec.product.mapper.ProductMapper;
import ru.clevertec.product.repository.ProductRepository;
import ru.clevertec.product.service.ProductService;
import ru.clevertec.product.validator.ProductValidator;

@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

  private final ProductMapper mapper;
  private final ProductRepository productRepository;

  @Override
  public InfoProductDto get(UUID uuid) {
    Product product = productRepository.findById(uuid)
        .orElseThrow(() -> new ProductNotFoundException(uuid));
    return mapper.toInfoProductDto(product);
  }

  @Override
  public List<InfoProductDto> getAll() {
    return mapper.toListInfoProductDto(productRepository.findAll());
  }

  @Override
  public UUID create(ProductDto productDto) {
    if (ProductValidator.validateProductDto(productDto)) {
      Product product = mapper.toProduct(productDto);
      product.setCreated(LocalDateTime.now());
      return productRepository.save(product).getUuid();
    } else {
      throw new IllegalArgumentException("Invalid product data");
    }
  }

  @Override
  public void update(UUID uuid, ProductDto productDto) {
    if (ProductValidator.validateProductDto(productDto)) {
      Product product = mapper.toProduct(productDto);
      product.setUuid(uuid);
      productRepository.save(product);
    } else {
      throw new IllegalStateException("Invalid product data");
    }
  }

  @Override
  public void delete(UUID uuid) {
    if (Objects.isNull(uuid)) {
      throw new IllegalArgumentException("UUID cannot be null");
    }
    productRepository.delete(uuid);
  }
}

