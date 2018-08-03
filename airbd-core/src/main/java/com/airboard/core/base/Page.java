package com.airboard.core.base;

import lombok.Data;

import java.io.Serializable;

@Data
public class Page implements Serializable {

    private Integer pageIndex;

    private Integer pageSize;

    private Integer totalRows;

}
