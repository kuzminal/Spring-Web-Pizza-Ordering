package com.kuzmin.pizzaordering.domain;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
public class PizzaOrder implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @CreatedDate
    @Column(name = "placed_at")
    private Date placedAt;
    @NotBlank(message = "{orders.delivery.name.validation}")
    private String deliveryName;
    @NotBlank(message = "{orders.delivery.street.validation}")
    private String deliveryStreet;
    @NotBlank(message = "{orders.delivery.city.validation}")
    private String deliveryCity;
    @NotBlank(message = "{orders.delivery.state.validation}")
    @Size(min = 2, max = 2)
    private String deliveryState;
    @NotBlank(message = "{orders.delivery.zip.validation}")
    private String deliveryZip;
    @Digits(integer = 12, fraction = 0, message = "{orders.delivery.ccNumber.validation}")
    //@CreditCardNumber(message = "{orders.delivery.ccNumber.validation}")
    private String ccNumber;
    @Pattern(regexp = "^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$",
            message = "{orders.delivery.ccExpiration.validation}")
    private String ccExpiration;
    @Column(name = "cc_cvv")
    @Digits(integer = 3, fraction = 0, message = "{orders.delivery.ccCVV.validation}")
    private String ccCVV;
    @OneToMany(cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY,
            mappedBy = "pizzaOrder")
    private List<Pizza> pizzas = new ArrayList<>();
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public void addPizza(Pizza pizza) {
        this.pizzas.add(pizza);
    }
}
