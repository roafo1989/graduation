package com.example.grad1;

import com.example.grad1.domain.Restaurant;

import java.util.Arrays;

import static com.example.grad1.LunchTestData.*;
import static com.example.grad1.domain.baseModel.AbstractBaseEntity.START_SEQ;
import static org.assertj.core.api.Assertions.assertThat;

public class RestaurantTestData {
    public static final int RESTAURANT1_ID = START_SEQ + 3;
    private static final int RESTAURANT2_ID = START_SEQ + 4;
    private static final int RESTAURANT3_ID = START_SEQ + 5;

    public static final Restaurant RESTAURANT1 = new Restaurant(RESTAURANT1_ID, "Restaurant1");
    public static final Restaurant RESTAURANT2 = new Restaurant(RESTAURANT2_ID, "Restaurant2");
    public static final Restaurant RESTAURANT3 = new Restaurant(RESTAURANT3_ID, "Restaurant3");

    private static final Restaurant RESTAURANT1UPDATE = new Restaurant(RESTAURANT1_ID, "Restaurant1_Update");

    static {
        RESTAURANT1.setLunches(Arrays.asList(LUNCH1, LUNCH2, LUNCH3));
        RESTAURANT2.setLunches(Arrays.asList(LUNCH4, LUNCH5));
        RESTAURANT3.setLunches(Arrays.asList(LUNCH6, LUNCH7));
        RESTAURANT1UPDATE.setLunches(Arrays.asList(LUNCH1, LUNCH2, LUNCH3, LUNCHNEW));
    }

    public static Restaurant getCreated() {
        return new Restaurant("New Restaurant");
    }

    public static Restaurant getUpdated() {
        Restaurant updated = new Restaurant(RESTAURANT1_ID, "Restaurant1_updated");
        updated.setLunches(Arrays.asList(LUNCH1, LUNCH2, LUNCH3));
        return updated;
    }

    public static void assertMatch(Restaurant actual, Restaurant expected) {
        assertThat(actual).isEqualToComparingFieldByFieldRecursively(expected);
    }

    public static void assertMatch(Iterable<Restaurant> actual, Iterable<Restaurant> expected) {
        assertThat(actual).isEqualTo(expected);
    }

}