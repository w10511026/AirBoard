package com.airboard.airbd.trading.receive;

import com.airboard.airbd.trading.model.TradingContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
public class ProxyReceiveStrategy extends AbstractReceiveStrategy implements ReceiveStrategy {

	@Override
	public boolean validate(TradingContext tradingContext) {
		return this.getReceiveStrategy(tradingContext).validate(tradingContext);
	}

	@Override
	public Map<String, Object> create(TradingContext tradingContext) {
		return this.getReceiveStrategy(tradingContext).create(tradingContext);
	}
}
