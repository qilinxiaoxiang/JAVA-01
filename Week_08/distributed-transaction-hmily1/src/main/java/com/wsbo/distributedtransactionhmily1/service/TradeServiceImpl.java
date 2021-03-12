package com.wsbo.distributedtransactionhmily1.service;

import com.wsbo.distributedtransactionhmily1.dal.mapper.TradeMapper;
import com.wsbo.distributedtransactionhmily1.dal.model.Trade;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Random;

@Service
public class TradeServiceImpl implements TradeService {
    @Resource
    private TradeMapper tradeMapper;
    private final Random random = new Random();

    @Override
    @Transactional
    public void insert(Long tradeId) {
        tradeMapper.insertSelective(Trade.builder()
                .tradeId(tradeId)
                .uid((long) random.nextInt(16*1000))
                .build());
        if (random.nextBoolean()) {
            throw new RuntimeException();
        }
    }
}
