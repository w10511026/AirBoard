package com.airboard.airbd.trading.receive;


import com.airboard.airbd.trading.model.TradingContext;

import java.util.Map;

public interface ReceiveStrategy {

	boolean validate(TradingContext tradingContext);

	Map<String, Object> create(TradingContext tradingContext);

}
