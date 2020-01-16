package com.example.grad1;

import com.example.grad1.to.voiceTo.VoiceTo;
import org.springframework.test.web.servlet.ResultMatcher;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;

import static com.example.grad1.domain.baseModel.AbstractBaseEntity.START_SEQ;
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

    public static final VoiceTo VOICE1_TO = new VoiceTo(V1_ID, of(2020, Month.JANUARY, 15, 10, 0), 100004);
    public static final VoiceTo VOICE2_TO = new VoiceTo(V2_ID, of(2020, Month.JANUARY, 15, 10, 0), 100004);
    public static final VoiceTo VOICE3_TO = new VoiceTo(V3_ID, of(2020, Month.JANUARY, 16, 10, 0), 100003);
    public static final VoiceTo VOICE4_TO = new VoiceTo(V4_ID, of(2020, Month.JANUARY, 16, 10, 0), 100003);
    public static final VoiceTo VOICE5_TO = new VoiceTo(V5_ID, of(LocalDate.now(), LocalTime.MIN), 100003);
    public static final VoiceTo VOICE6_TO = new VoiceTo(V6_ID, of(LocalDate.now(), LocalTime.MIN), 100004);

    public static VoiceTo getCreated() {
        return new VoiceTo(LocalDateTime.of(LocalDate.now(), LocalTime.of(0, 0)), 100003);
    }

    public static VoiceTo getUpdated() {
        return new VoiceTo(V5_ID, LocalDateTime.of(LocalDate.now(), LocalTime.of(10, 0)), 100004);
    }

    public static void assertMatch(VoiceTo actual, VoiceTo expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "dateTime");
        assertEquals(actual.getDateTime().toLocalDate(), expected.getDateTime().toLocalDate());
    }

/*    public static ResultMatcher contentJson(VoiceTo... expected) {
        return contentJson(List.of(expected));
    }*/

    private static ResultMatcher contentJson(Iterable<VoiceTo> expected) {
        return result -> assertThat(TestUtil.readListFromJsonMvcResult(result, VoiceTo.class)).isEqualTo(expected);
    }

}