package com.example.grad1.domain;

import com.example.grad1.domain.baseModel.AbstractBaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "voices")
@NoArgsConstructor
public class Voice extends AbstractBaseEntity {

    @Column(name = "datetime", nullable = false)
    @NotNull
    private LocalDateTime dateTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Restaurant restaurant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private User user;

    public Voice(LocalDateTime dateTime, Restaurant restaurant, User user) {
        this(null, dateTime, restaurant, user);
    }
    public Voice(Integer id, LocalDateTime dateTime, Restaurant restaurant, User user) {
        super(id);
        this.dateTime = dateTime;
        this.restaurant = restaurant;
        this.user = user;
    }
    public Voice(Voice voice){
        this(voice.getId(), voice.getDateTime(), voice.getRestaurant(), voice.getUser());
    }

    @Override
    public String toString() {
        return "Voice{" +
                "datetime=" + dateTime +
                ", restaurant=" + restaurant +
                ", user=" + user +
                ", id=" + id +
                '}';
    }
}
