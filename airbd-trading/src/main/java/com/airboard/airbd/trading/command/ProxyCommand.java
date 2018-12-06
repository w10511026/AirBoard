package com.airboard.airbd.trading.command;

import com.airboard.airbd.trading.exception.TradingException;
import com.airboard.airbd.trading.model.TradingContext;
import com.airboard.airbd.trading.util.ApplicationContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
public class ProxyCommand implements ICommand {

    @Override
    public Map<String, Object> execute(TradingContext tradingContext) {
        if (null == tradingContext.getTardingType()) {
            throw new TradingException("交易类型不能为空");
        }
        ICommand command = (ICommand) ApplicationContextUtil.getApplicationContext().getBean(tradingContext.getTardingType().getBeanName());
        Map<String, Object> result = null;
        try {
            result = command.execute(tradingContext);
        } catch (TradingException e) {
            log.info(e.getMessage(), e);
            throw new TradingException(e.getMessage(), e);
        }
        return result;
    }

}
