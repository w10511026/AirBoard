package com.airboard.airbd.trading.enums;

import java.io.Serializable;

public enum TradingType implements Serializable {

    ASSIGN(3, "AssignCommand"),

    RECEIVE(15, "ReceiveCommand");

    public final int type;
    public String className;

    TradingType(int type, String className) {
        this.type = type;
        this.className = className;
    }

    public String getBeanName() {
        return className.substring(0, 1).toLowerCase() + className.substring(1);
    }

    public int getType() {
        return this.type;
    }

    public static TradingType getByString(String name) {
        TradingType[] types = TradingType.values();
        TradingType result = null;
        for (TradingType type : types) {
            if (type.name().equals(name)) {
                result = type;
                break;
            } else {
                continue;
            }
        }
        return result;
    }

}
