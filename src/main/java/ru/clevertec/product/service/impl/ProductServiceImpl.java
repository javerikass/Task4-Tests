package ru.clevertec.product.service.impl;

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
    if (Objects.isNull(productDto)) {
      throw new IllegalArgumentException("ProductDto must not be null");
    }
    return productRepository.save(mapper.toProduct(productDto)).getUuid();
  }

  @Override
  public void update(UUID uuid, ProductDto productDto) {
    Product product = mapper.toProduct(productDto);
    product.setUuid(uuid);
    productRepository.save(product);
  }

  @Override
  public void delete(UUID uuid) {
    productRepository.delete(uuid);
  }
}
