package com.wsbo.distributedtransactionhmily2.controller;

import com.wsbo.distributedtransactionhmily2.client.Hmily1Client;
import com.wsbo.distributedtransactionhmily2.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HmilyController {
    @Autowired
    private TradeService tradeService;

    @RequestMapping("/trade/{tradeId}")
    public String work(@PathVariable Long tradeId) {
        tradeService.insert(tradeId);
        return "ok";
    }
}
