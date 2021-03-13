package com.wsbo.distributedtransactionhmily1.service;

import com.wsbo.distributedtransactionhmily1.dal.mapper.TradeMapper;
import com.wsbo.distributedtransactionhmily1.dal.model.Trade;
import com.wsbo.distributedtransactionhmily1.dal.model.TradeExample;
import org.dromara.hmily.annotation.HmilyTCC;
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
    @HmilyTCC(confirmMethod = "confirm", cancelMethod = "cancel")
    @Transactional
    public void insert(Long tradeId) {
        tradeMapper.insertSelective(Trade.builder()
                .tradeId(tradeId)
                .uid((long) random.nextInt(16*1000))
                .build());
        if (random.nextBoolean()) {
            throw new RuntimeException("hmily1 error");
        }
    }



    @Override
    public void confirm(Long tradeId) {
        System.out.println("confirm");
    }

    @Override
    public void cancel(Long tradeId) {
        System.out.println("cancel");
        TradeExample param = new TradeExample();
        param.createCriteria().andTradeIdEqualTo(tradeId);
        tradeMapper.deleteByExample(param);
    }
}
