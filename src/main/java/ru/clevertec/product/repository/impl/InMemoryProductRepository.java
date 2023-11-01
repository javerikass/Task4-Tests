package ru.clevertec.product.repository.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import ru.clevertec.product.entity.Product;
import ru.clevertec.product.repository.ProductRepository;

public class InMemoryProductRepository implements ProductRepository {

  Map<UUID, Product> storage;

  public InMemoryProductRepository(Map<UUID, Product> storage) {
    this.storage = storage;
  }

  @Override
  public Optional<Product> findById(UUID uuid) {
    return Optional.ofNullable(storage.get(uuid));
  }

  @Override
  public List<Product> findAll() {
    return new ArrayList<>(storage.values());
  }

  @Override
  public Product save(Product product) {
    storage.put(product.getUuid(), product);
    return product;
  }

  @Override
  public void delete(UUID uuid) {
    storage.remove(uuid);
  }
}

