package com.example.grad1.domain;

import com.example.grad1.domain.baseModel.AbstractNamedEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "lunches")
public class Lunch extends AbstractNamedEntity {

    @Column(name = "date", nullable = false)
    @NotNull
    private LocalDate date;

    @Column(name = "price", nullable = false)
    @NotNull
    private Integer price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @NotNull
    @JsonBackReference
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Restaurant restaurant;

    public Lunch(LocalDate date, String name, Integer price, Restaurant restaurant) {
        this(null, date, name, price, restaurant);
    }

    public Lunch(Integer id, LocalDate date, String name, Integer price, @NotNull Restaurant restaurant) {
        super(id, name);
        this.date = date;
        this.price = price;
        this.restaurant = restaurant;
    }
    public Lunch(Lunch lunch){
        this(lunch.getId(),lunch.getDate(), lunch.getName(),lunch.getPrice(), lunch.getRestaurant());
    }
    public Lunch(Integer id, String name, LocalDate date, Integer price) {
        super(id, name);
        this.date = date;
        this.price = price;
    }
    public Lunch(String name, LocalDate date, Integer price) {
        super(null, name);
        this.date = date;
        this.price = price;
    }


    @Override
    public String toString() {
        return "Lunch{" +
                "restaurant=" + restaurant.name +
                ", name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", id=" + id +
                '}';
    }
}
