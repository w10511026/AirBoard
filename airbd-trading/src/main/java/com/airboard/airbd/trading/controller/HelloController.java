package com.airboard.airbd.trading.controller;

import com.airboard.airbd.trading.command.ICommand;
import com.airboard.airbd.trading.enums.TradingType;
import com.airboard.airbd.trading.model.TradingBO;
import com.airboard.airbd.trading.model.TradingContext;
import com.airboard.airbd.trading.enums.ReceiveTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class HelloController {

    @Autowired
    ICommand proxyCommand;

    @RequestMapping("")
    public void hello() {
        TradingContext tradingContext = new TradingContext();
        TradingBO tradingBO = new TradingBO();
        tradingContext.setTradingBO(tradingBO);
        tradingContext.setTradingType(TradingType.RECEIVE);
        tradingContext.setReceiveType(ReceiveTypeEnum.RECEIVE_DEFAULT);
        proxyCommand.execute(tradingContext);
    }

}
