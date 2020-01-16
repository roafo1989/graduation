package com.example.grad1.testData;

import com.example.grad1.domain.Voice;
import com.example.grad1.to.voiceTo.VoiceTo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;

import static com.example.grad1.domain.baseModel.AbstractBaseEntity.START_SEQ;
import static com.example.grad1.testData.RestaurantTestData.*;
import static com.example.grad1.testData.UserTestData.*;
import static java.time.LocalDateTime.of;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class VoiceTestData {
    private static final int V1_ID = START_SEQ + 13;
    private static final int V2_ID = START_SEQ + 14;
    private static final int V3_ID = START_SEQ + 15;
    private static final int V4_ID = START_SEQ + 16;
    private static final int V5_ID = START_SEQ + 17;
    private static final int V6_ID = START_SEQ + 18;

    public static final Voice VOICE1 = new Voice(V1_ID, LocalDateTime.of(LocalDate.now(), LocalTime.of(10, 0)),RESTAURANT2,USER);
    public static final Voice VOICE2 = new Voice(V2_ID, of(2020, Month.JANUARY, 14, 10, 0), RESTAURANT2, ADMIN);
    public static final Voice VOICE3 = new Voice(V3_ID, of(2020, Month.JANUARY, 13, 10, 0), RESTAURANT1,USER);
    public static final Voice VOICE4 = new Voice(V4_ID, of(2020, Month.JANUARY, 12, 10, 0), RESTAURANT1,ADMIN);
    public static final Voice VOICE5 = new Voice(V5_ID, of(LocalDate.now(), LocalTime.MIN), RESTAURANT1,USER2);


    public static Voice getCreated() {
        return new Voice(LocalDateTime.now(),RESTAURANT2,ADMIN);
    }

    public static VoiceTo getUpdated() {
        return new VoiceTo(V5_ID, LocalDateTime.of(LocalDate.now(), LocalTime.of(10, 0)), 100004);
    }



}