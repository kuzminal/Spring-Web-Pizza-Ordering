package com.kuzmin.pizzaordering.domain;

import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

@Data
public class PizzaOrder {
    @NotBlank(message = "{orders.delivery.name.validation}")
    private String deliveryName;
    @NotBlank(message = "{orders.delivery.street.validation}")
    private String deliveryStreet;
    @NotBlank(message = "{orders.delivery.city.validation}")
    private String deliveryCity;
    @NotBlank(message = "{orders.delivery.state.validation}")
    private String deliveryState;
    @NotBlank(message = "{orders.delivery.zip.validation}")
    private String deliveryZip;
    @CreditCardNumber(message = "{orders.delivery.ccNumber.validation}")
    private String ccNumber;
    @Pattern(regexp = "^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$",
            message = "{orders.delivery.ccExpiration.validation}")
    private String ccExpiration;
    @Digits(integer = 3, fraction = 0, message = "{orders.delivery.ccCVV.validation}")
    private String ccCVV;
    private List<Pizza> pizzas = new ArrayList<>();

    public void addPizza(Pizza pizza) {
        this.pizzas.add(pizza);
    }
}
