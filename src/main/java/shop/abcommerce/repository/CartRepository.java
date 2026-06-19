package shop.abcommerce.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import shop.abcommerce.domain.Cart;

/**
 * Spring Data MongoDB repository for the Cart entity.
 */
@Repository
public interface CartRepository extends MongoRepository<Cart, String> {}
