package com.example.grad1.to.converter;

import com.example.grad1.domain.Lunch;
import com.example.grad1.to.model.LunchTo;

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
