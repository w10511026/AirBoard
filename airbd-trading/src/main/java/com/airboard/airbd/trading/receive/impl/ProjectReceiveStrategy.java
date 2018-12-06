package com.airboard.airbd.trading.receive.impl;

import com.airboard.airbd.trading.model.TradingBO;
import com.airboard.airbd.trading.model.TradingContext;
import com.airboard.airbd.trading.receive.AbstractReceiveStrategy;
import com.airboard.airbd.trading.receive.ReceiveStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
public class ProjectReceiveStrategy extends AbstractReceiveStrategy implements ReceiveStrategy {

    @Override
    public boolean validate(TradingContext tradingContext) {
        TradingBO tradingBO = tradingContext.getTradingBO();
        return true;
    }

    //@Transactional
    @Override
    public Map<String, Object> create(TradingContext tradingContext) {
        TradingBO tradingBO = tradingContext.getTradingBO();
        return null;
    }


}
