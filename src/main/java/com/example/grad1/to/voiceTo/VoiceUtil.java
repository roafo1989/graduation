package com.example.grad1.to.voiceTo;


import com.example.grad1.domain.Voice;
import com.example.grad1.to.voiceTo.VoiceTo;

import java.util.List;
import java.util.stream.Collectors;

public class VoiceUtil {

    public static VoiceTo asTo(Voice voice) {
        return new VoiceTo(voice);
    }

    public static List<VoiceTo> asTo(List<Voice> votes) {
        return votes.stream().map(VoiceTo::new).collect(Collectors.toList());
    }
}
