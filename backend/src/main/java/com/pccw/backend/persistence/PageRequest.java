package com.pccw.backend.persistence;

import lombok.Data;

@Data
public class PageRequest {

    private int page = 0;
    private int limit = 50;

}
