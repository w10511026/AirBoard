package com.airboard.airbd.trading.receive;

import com.airboard.airbd.trading.command.ICommand;
import com.airboard.airbd.trading.model.TradingContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
public class ReceiveCommand implements ICommand {
	
	@Autowired
	private ReceiveStrategy proxyReceiveStrategy;

	@Override
	public Map<String, Object> execute(TradingContext tradingContext) {
		log.info("receive express called");
		proxyReceiveStrategy.validate(tradingContext);
		return proxyReceiveStrategy.create(tradingContext);
	}

}
