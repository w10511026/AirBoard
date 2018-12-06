package com.airboard.airbd.trading.model;

import com.airboard.airbd.trading.enums.TradingType;
import com.airboard.airbd.trading.enums.ReceiveTypeEnum;

import java.io.Serializable;
import java.util.HashMap;

public class TradingContext extends HashMap<String, Object> implements Serializable {

    private static final long serialVersionUID = 5964698990244446424L;

    public static final String TRADING_TYPE = "trading_type";

    public static final String TRADING_EXPRESS = "trading_express";

    public static final String RECEIVE_TYPE = "receive_type";

    public static final String BEGINTIME = "begintime";

    public TradingContext() {
        this.put(BEGINTIME, System.currentTimeMillis());
    }

    public void setTradingType(TradingType type) {
        this.put(TRADING_TYPE, type);
    }

    public TradingType getTardingType() {
        return (TradingType) this.get(TRADING_TYPE);
    }

    public void setTradingBO(TradingBO expressBO) {
        this.put(TRADING_EXPRESS, expressBO);
    }

    public TradingBO getTradingBO() {
        return (TradingBO) this.get(TRADING_EXPRESS);
    }

    public void setReceiveType(ReceiveTypeEnum receiveTypeEnum) {
        this.put(RECEIVE_TYPE, receiveTypeEnum);
    }

    public ReceiveTypeEnum getReceiveType() {
        return (ReceiveTypeEnum) this.get(RECEIVE_TYPE);
    }
}
