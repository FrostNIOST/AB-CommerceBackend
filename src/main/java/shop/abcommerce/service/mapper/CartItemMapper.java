package shop.abcommerce.service.mapper;

import org.mapstruct.*;
import shop.abcommerce.domain.Cart;
import shop.abcommerce.domain.CartItem;
import shop.abcommerce.domain.Product;
import shop.abcommerce.service.dto.CartDTO;
import shop.abcommerce.service.dto.CartItemDTO;
import shop.abcommerce.service.dto.ProductDTO;

/**
 * Mapper for the entity {@link CartItem} and its DTO {@link CartItemDTO}.
 */
@Mapper(componentModel = "spring")
public interface CartItemMapper extends EntityMapper<CartItemDTO, CartItem> {
    @Mapping(target = "product", source = "product", qualifiedByName = "productProductName")
    @Mapping(target = "cart", source = "cart", qualifiedByName = "cartId")
    CartItemDTO toDto(CartItem s);

    @Named("productProductName")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "productName", source = "productName")
    ProductDTO toDtoProductProductName(Product product);

    @Named("cartId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    CartDTO toDtoCartId(Cart cart);
}
