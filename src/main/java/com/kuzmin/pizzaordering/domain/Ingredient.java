package com.kuzmin.pizzaordering.domain;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Ingredient {

    @Id
    private String id;
    private String name;
    @Enumerated(EnumType.STRING)
    private Type type;
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable( name = "pizza_ingredients",
            joinColumns = @JoinColumn( name = "pizza_id"),
            inverseJoinColumns = @JoinColumn( name = "ingredients_id"))
    private Set<Pizza> pizzas = new HashSet<>();

    public enum Type {
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }
}
