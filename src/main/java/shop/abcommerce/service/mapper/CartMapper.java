package shop.abcommerce.service.mapper;

import org.mapstruct.*;
import shop.abcommerce.domain.AccountU;
import shop.abcommerce.domain.Cart;
import shop.abcommerce.service.dto.AccountUDTO;
import shop.abcommerce.service.dto.CartDTO;

/**
 * Mapper for the entity {@link Cart} and its DTO {@link CartDTO}.
 */
@Mapper(componentModel = "spring")
public interface CartMapper extends EntityMapper<CartDTO, Cart> {
    @Mapping(target = "account", source = "account", qualifiedByName = "accountUId")
    CartDTO toDto(Cart s);

    @Named("accountUId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    AccountUDTO toDtoAccountUId(AccountU accountU);
}
