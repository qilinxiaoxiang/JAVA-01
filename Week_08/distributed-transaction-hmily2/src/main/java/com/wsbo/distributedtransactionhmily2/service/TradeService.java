package com.wsbo.distributedtransactionhmily2.service;

import com.wsbo.distributedtransactionhmily2.dal.model.Trade;

public interface TradeService {
    Trade readFromReadDataSource(int id);
    void insert(Long tradeId);
    void confirm(Long tradeId);
    void cancel(Long tradeId);

}
