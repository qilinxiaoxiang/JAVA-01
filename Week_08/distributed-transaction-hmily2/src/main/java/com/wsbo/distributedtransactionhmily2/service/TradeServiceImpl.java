package com.wsbo.distributedtransactionhmily2.service;

import com.wsbo.distributedtransactionhmily2.client.Hmily1Client;
import com.wsbo.distributedtransactionhmily2.dal.mapper.LocalCancelLogMapper;
import com.wsbo.distributedtransactionhmily2.dal.mapper.LocalConfirmLogMapper;
import com.wsbo.distributedtransactionhmily2.dal.mapper.LocalTryLogMapper;
import com.wsbo.distributedtransactionhmily2.dal.mapper.TradeMapper;
import com.wsbo.distributedtransactionhmily2.dal.model.Trade;
import com.wsbo.distributedtransactionhmily2.dal.model.TradeExample;
import org.dromara.hmily.annotation.Hmily;
import org.dromara.hmily.annotation.HmilyTCC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Random;

@Service
public class TradeServiceImpl implements TradeService {
    @Resource
    private TradeMapper tradeMapper;
    @Autowired
    private Hmily1Client hmily1Client;
    private final Random random = new Random();

    @Override
    public Trade readFromReadDataSource(int id) {
        return tradeMapper.selectByPrimaryKey(id);
    }

    @Override
    @HmilyTCC(confirmMethod = "confirm", cancelMethod = "cancel")
    public void insert(Long tradeId) {
        tradeMapper.insertSelective(Trade.builder()
                .tradeId(tradeId)
                .uid((long) random.nextInt(16*1000))
                .build());
        hmily1Client.work(tradeId);
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
