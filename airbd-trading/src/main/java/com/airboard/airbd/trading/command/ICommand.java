package com.airboard.airbd.trading.command;


import com.airboard.airbd.trading.model.TradingContext;

import java.util.Map;

public interface ICommand {
	
	Map<String, Object> execute(TradingContext tradingContext);

}
