package com.example.grad1.domain;

import com.example.grad1.domain.baseModel.AbstractNamedEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.List;

import static com.example.grad1.domain.Restaurant.GRAPH_WITH_LUNCHES;

@Getter
@Setter
@NoArgsConstructor
@Entity
@NamedEntityGraph(name = GRAPH_WITH_LUNCHES, attributeNodes = {@NamedAttributeNode("lunches")})
@Table(name = "restaurants")
public class Restaurant extends AbstractNamedEntity {

    public static final String GRAPH_WITH_LUNCHES = "Restaurant.withLunches";

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
    @OrderBy("name DESC")
    private List<Lunch> lunches;


    public Restaurant(String name) {
        this(null, name);
    }
    public Restaurant(Integer id, String name) {
        super(id, name);
    }
    public Restaurant(Restaurant restaurant) {
        super(restaurant.getId(), restaurant.getName());
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
