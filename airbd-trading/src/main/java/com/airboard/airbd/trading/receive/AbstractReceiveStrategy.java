package com.airboard.airbd.trading.receive;


import com.airboard.airbd.trading.model.TradingContext;
import com.airboard.airbd.trading.enums.ReceiveTypeEnum;
import com.airboard.airbd.trading.util.ApplicationContextUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AbstractReceiveStrategy {

    public ReceiveStrategy getReceiveStrategy(TradingContext tradingContext) {
        ReceiveTypeEnum receivetype = tradingContext.getReceiveType();
        if (receivetype == null) {

        }
        return (ReceiveStrategy) ApplicationContextUtil.getApplicationContext().getBean(receivetype.clzz);
    }

}
