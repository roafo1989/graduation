package com.example.grad1;

import com.example.grad1.domain.Lunch;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import static java.time.LocalDate.of;

import static com.example.grad1.RestaurantTestData.RESTAURANT1;
import static com.example.grad1.domain.baseModel.AbstractBaseEntity.START_SEQ;
import static org.assertj.core.api.Assertions.assertThat;

public class LunchTestData {
    public static final int LUNCH1_ID = START_SEQ + 6;
    private static final int LUNCH2_ID = START_SEQ + 7;
    private static final int LUNCH3_ID = START_SEQ + 8;
    private static final int LUNCH4_ID = START_SEQ + 9;
    private static final int LUNCH5_ID = START_SEQ + 10;
    private static final int LUNCH6_ID = START_SEQ + 11;
    private static final int LUNCH7_ID = START_SEQ + 12;

    public static final Lunch LUNCH1 = new Lunch(LUNCH1_ID, "Lunch1-Rest1", of(2020, Month.JANUARY, 10), 100);
    public static final Lunch LUNCH2 = new Lunch(LUNCH2_ID, "Lunch2-Rest1", of(2020, Month.JANUARY, 10), 200);
    public static final Lunch LUNCH3 = new Lunch(LUNCH3_ID, "Lunch3-Rest1", of(2020, Month.JANUARY, 13), 300);
    static final Lunch LUNCH4 = new Lunch(LUNCH4_ID, "Lunch4-Rest2", LocalDate.now(), 30);
    static final Lunch LUNCH5 = new Lunch(LUNCH5_ID, "Lunch5-Rest2", LocalDate.now(), 200);
    static final Lunch LUNCH6 = new Lunch(LUNCH6_ID, "Lunch6-Rest3", LocalDate.now(), 120);
    static final Lunch LUNCH7 = new Lunch(LUNCH7_ID, "Lunch7-Rest3", LocalDate.now(), 10);


    static final Lunch LUNCHNEW = new Lunch(START_SEQ + 15, "New", of(2019, Month.JANUARY, 16), 111);

    static {
        LUNCHNEW.setRestaurant(RESTAURANT1);
    }

    public static Lunch getCreated() {
        return new Lunch("NewLunch", of(2020, Month.JANUARY, 16), 111);
    }

    public static Lunch getUpdated() {
        return new Lunch(LUNCH1_ID, LUNCH1.getDate(), "LUNCH1_NewName",  LUNCH1.getPrice(), LUNCH1.getRestaurant());
    }

/*    public static void assertMatch(Iterable<Lunch> actual, Lunch... expected) {
        assertMatch(actual, List.of(expected));
    }*/

    public static void assertMatch(Iterable<Lunch> actual, Iterable<Lunch> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("restaurant").isEqualTo(expected);
    }

    public static void assertMatch(Lunch actual, Lunch expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "dateTime");
    }

}
