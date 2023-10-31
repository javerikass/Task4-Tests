package ru.clevertec.product.mapper;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.clevertec.product.data.InfoProductDto;
import ru.clevertec.product.data.ProductDto;
import ru.clevertec.product.entity.Product;

@Mapper()
public interface ProductMapper {

  ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

  /**
   * Маппит DTO в продукт без UUID
   *
   * @param productDto - DTO для маппинга
   * @return новый продукт
   */
  @Mapping(target = "uuid", ignore = true)
  @Mapping(target = "created", ignore = true)
  Product toProduct(ProductDto productDto);

  /**
   * Маппит текущий продукт в DTO без даты
   *
   * @param product - существующий продукт
   * @return DTO с идентификатором
   */
  InfoProductDto toInfoProductDto(Product product);

  /**
   * Сливает существующий продукт с информацией из DTO не меняет дату создания и идентификатор
   *
   * @param product    существующий продукт
   * @param productDto информация для обновления
   * @return обновлённый продукт
   */
  @Mapping(target = "name", source = "productDto.name")
  @Mapping(target = "description", source = "productDto.description")
  @Mapping(target = "price", source = "productDto.price")
  Product merge(Product product, ProductDto productDto);

  /**
   * Маппит текущий список продуктов в список DTO без даты
   *
   * @param productList - список продуктов
   * @return список DTO с идентификатором
   */
  List<InfoProductDto> toListInfoProductDto(List<Product> productList);

}
