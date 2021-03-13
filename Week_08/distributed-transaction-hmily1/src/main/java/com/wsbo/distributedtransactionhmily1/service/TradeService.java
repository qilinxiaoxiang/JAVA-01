package com.wsbo.distributedtransactionhmily1.service;

import com.wsbo.distributedtransactionhmily1.dal.model.Trade;

public interface TradeService {
    void insert(Long tradeId);
    void confirm(Long tradeId);
    void cancel(Long tradeId);
}
