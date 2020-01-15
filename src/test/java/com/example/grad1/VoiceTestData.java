package com.example.grad1;

import com.example.grad1.to.voiceTo.VoiceTo;
import org.springframework.test.web.servlet.ResultMatcher;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.List;

import static com.example.grad1.domain.baseModel.AbstractBaseEntity.START_SEQ;
import static java.time.LocalDateTime.of;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class VoiceTestData {
    private static final int VOICE1 = START_SEQ + 13; //20
    private static final int VOICE2 = START_SEQ + 14;
    private static final int VOICE3 = START_SEQ + 15;
    private static final int VOICE4 = START_SEQ + 16;
    private static final int VOICE5 = START_SEQ + 17;
    private static final int VOICE6 = START_SEQ + 18;

    public static final VoiceTo VOICE1_TO = new VoiceTo(VOICE1, of(2019, Month.DECEMBER, 23, 10, 0), 100004);
    public static final VoiceTo VOICE2_TO = new VoiceTo(VOICE2, of(2019, Month.DECEMBER, 23, 10, 0), 100004);
    public static final VoiceTo VOICE3_TO = new VoiceTo(VOICE3, of(2019, Month.DECEMBER, 24, 10, 0), 100003);
    public static final VoiceTo VOICE4_TO = new VoiceTo(VOICE4, of(2019, Month.DECEMBER, 24, 10, 0), 100003);
    public static final VoiceTo VOICE5_TO = new VoiceTo(VOICE5, of(LocalDate.now(), LocalTime.MIN), 100003);
    public static final VoiceTo VOICE6_TO = new VoiceTo(VOICE6, of(LocalDate.now(), LocalTime.MIN), 100004);

    public static VoiceTo getCreated() {
        return new VoiceTo(LocalDateTime.of(LocalDate.now(), LocalTime.of(0, 0)), 100003);
    }

    public static VoiceTo getUpdated() {
        return new VoiceTo(VOICE5, LocalDateTime.of(LocalDate.now(), LocalTime.of(10, 0)), 100004);
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