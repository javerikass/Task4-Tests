package ru.clevertec.product.mapper.impl;

import java.util.List;
import ru.clevertec.product.data.InfoProductDto;
import ru.clevertec.product.data.ProductDto;
import ru.clevertec.product.entity.Product;
import ru.clevertec.product.mapper.ProductMapper;

public class ProductMapperImpl implements ProductMapper {

    @Override
    public Product toProduct(ProductDto productDto) {
        return null;
    }

    @Override
    public InfoProductDto toInfoProductDto(Product product) {
        return null;
    }

    @Override
    public Product merge(Product product, ProductDto productDto) {
        return null;
    }

    @Override
    public List<InfoProductDto> toListInfoProductDto(List<Product> productList) {
        return null;
    }
}
