package com.airboard.core.base;

import lombok.Data;

import java.io.Serializable;

@Data
public class BasePage implements Serializable {

    private Integer pageIndex;
    private Integer pageSize;
    private String sortField;
    private String sortOrder;

}
