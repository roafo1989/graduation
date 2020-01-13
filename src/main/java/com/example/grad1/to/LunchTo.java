package com.example.grad1.to;

import com.example.grad1.domain.Lunch;
import com.example.grad1.domain.Voice;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class LunchTo extends BaseTo {
    private LocalDate date;

    @NotNull
    private Integer restaurantId;

    @NotNull
    private String restaurantName;

    @NotNull
    private String name;

    @NotNull
    private int price;

    public LunchTo(LocalDate date, Integer restaurantId, String restaurantName, String userName, int price) {
        this(null, date, restaurantId, restaurantName, userName, price);
    }

    public LunchTo(Integer id, LocalDate date, Integer restaurantId, String restaurantName, String name, int price) {
        super(id);
        this.date = date;
        this.restaurantId = restaurantId;
        this.restaurantName = restaurantName;
        this.name = name;
        this.price = price;
    }

    public LunchTo(Lunch lunch) {
        super(lunch.getId());
        this.date = (lunch.getDate());
        this.restaurantId = lunch.getRestaurant().getId();
        this.restaurantName = lunch.getRestaurant().getName();
        this.name = lunch.getName();
        this.price = lunch.getPrice();
    }

    @Override
    public String toString() {
        return "VoiceTo{" +
                "date=" + date +
                ", restaurantId=" + restaurantId +
                ", restaurantName=" + restaurantName +
                ", name=" + name +
                ", price=" + price +
                '}';
    }
}
