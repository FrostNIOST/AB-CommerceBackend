package shop.abcommerce.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import shop.abcommerce.domain.enumeration.State;

/**
 * A Cart.
 */
@Document(collection = "cart")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Cart implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("state")
    private State state;

    @DBRef
    @Field("account")
    private AccountU account;

    @DBRef
    @Field("items")
    @JsonIgnoreProperties(value = { "product", "cart" }, allowSetters = true)
    private Set<CartItem> itemses = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public Cart id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public State getState() {
        return this.state;
    }

    public Cart state(State state) {
        this.setState(state);
        return this;
    }

    public void setState(State state) {
        this.state = state;
    }

    public AccountU getAccount() {
        return this.account;
    }

    public void setAccount(AccountU accountU) {
        this.account = accountU;
    }

    public Cart account(AccountU accountU) {
        this.setAccount(accountU);
        return this;
    }

    public Set<CartItem> getItemses() {
        return this.itemses;
    }

    public void setItemses(Set<CartItem> cartItems) {
        if (this.itemses != null) {
            this.itemses.forEach(i -> i.setCart(null));
        }
        if (cartItems != null) {
            cartItems.forEach(i -> i.setCart(this));
        }
        this.itemses = cartItems;
    }

    public Cart itemses(Set<CartItem> cartItems) {
        this.setItemses(cartItems);
        return this;
    }

    public Cart addItems(CartItem cartItem) {
        this.itemses.add(cartItem);
        cartItem.setCart(this);
        return this;
    }

    public Cart removeItems(CartItem cartItem) {
        this.itemses.remove(cartItem);
        cartItem.setCart(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Cart)) {
            return false;
        }
        return getId() != null && getId().equals(((Cart) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Cart{" +
            "id=" + getId() +
            ", state='" + getState() + "'" +
            "}";
    }
}
