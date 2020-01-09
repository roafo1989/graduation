package com.example.grad1.util;

import com.example.grad1.domain.Lunch;
import com.example.grad1.to.LunchTo;

import java.util.List;
import java.util.stream.Collectors;

public class LunchUtil {
    public static LunchTo asTo(Lunch lunch){
        return new LunchTo(lunch);
    }
    public static List<LunchTo> asTo(List<Lunch> lunches){
        return lunches.stream().map(LunchTo::new).collect(Collectors.toList());
    }
}
