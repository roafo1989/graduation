package com.example.grad1.domain.baseModel;

import com.fasterxml.jackson.annotation.JsonIgnore;

public interface HasId {
    Integer getId();

    void setId(Integer id);

    @JsonIgnore
    default boolean isNew() {
        return getId() == null;
    }

}
