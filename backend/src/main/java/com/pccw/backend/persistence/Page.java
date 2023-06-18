package com.pccw.backend.persistence;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import java.io.Serializable;
import java.util.Collection;

@Data
@AllArgsConstructor
public class Page<T> implements Serializable {

    @NonNull
    Collection<T> collection;
    @NonNull
    Integer page;
    @NonNull
    Long count;

}
