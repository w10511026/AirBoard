package com.airboard.airbd.trading.exception;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

@Slf4j
public class TradingException extends RuntimeException {

    protected int number;

    public TradingException() {
        super();
    }

    public TradingException(String message) {
        super(resolveMessage(message));
        String num = resolveNumber(message);
        if (StringUtils.isNotEmpty(num)) {
            this.number = Integer.parseInt(num);
        }
    }

    public TradingException(String message, Throwable cause) {
        super(resolveMessage(message), cause);
        String num = resolveNumber(message);
        if (StringUtils.isNotEmpty(num)) {
            this.number = Integer.parseInt(num);
        }
    }

    public TradingException(Throwable cause) {
        super(cause);
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    static String resolveMessage(String message) {
        if (StringUtils.isNotEmpty(message)) {
            if (message.indexOf("-") != -1) {
                String[] msgs = message.split("-");
                return msgs[1];
            } else {
                return message;
            }
        }
        return null;
    }

    static String resolveNumber(String message) {
        if (StringUtils.isNotEmpty(message)) {
            if (message.indexOf("-") != -1) {
                String[] msgs = message.split("-");
                return msgs[0];
            } else {
                return null;
            }
        }
        return null;
    }
}
