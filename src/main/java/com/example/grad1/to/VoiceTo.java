package com.example.grad1.to;

import com.example.grad1.domain.Voice;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class VoiceTo extends BaseTo {

    private LocalDateTime dateTime;

    @NotNull
    private Integer restaurantId;

    @NotNull
    private String restaurantName;

    @NotNull
    private String userName;

    public VoiceTo(LocalDateTime dateTime, Integer restaurantId, String restaurantName, String userName) {
        this(null, dateTime, restaurantId, restaurantName, userName);
    }

    public VoiceTo(Integer id, LocalDateTime dateTime, Integer restaurantId, String restaurantName, String userName) {
        super(id);
        this.dateTime = dateTime;
        this.restaurantId = restaurantId;
        this.restaurantName = restaurantName;
        this.userName = userName;
    }

    public VoiceTo(Voice voice) {
        super(voice.getId());
        this.dateTime = (voice.getDateTime());
        this.restaurantId = voice.getRestaurant().getId();
        this.restaurantName = voice.getRestaurant().getName();
        this.userName = voice.getUser().getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VoiceTo voiceTo = (VoiceTo) o;

        if (!dateTime.equals(voiceTo.dateTime)) return false;
        if (!restaurantId.equals(voiceTo.restaurantId)) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = dateTime.hashCode();
        result = 31 * result + restaurantId.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "VoiceTo{" +
                "dateTime=" + dateTime +
                ", restaurantId=" + restaurantId +
                ", restaurantName=" + restaurantName +
                ", userName=" + userName +
                '}';
    }
}
