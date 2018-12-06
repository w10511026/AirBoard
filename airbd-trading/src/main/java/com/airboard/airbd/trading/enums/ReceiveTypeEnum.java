package com.airboard.airbd.trading.enums;


import com.airboard.airbd.trading.receive.impl.DefaultReceiveStrategy;
import com.airboard.airbd.trading.receive.impl.ProjectReceiveStrategy;

import java.io.Serializable;

public enum ReceiveTypeEnum implements Serializable {

    RECEIVE_DEFAULT(1, DefaultReceiveStrategy.class),
    PROJECT_DEFAULT(2, ProjectReceiveStrategy.class);

    public final int type;
    public final Class<?> clzz;

    ReceiveTypeEnum(int type, Class<?> clzz) {
        this.type = type;
        this.clzz = clzz;
    }

}
