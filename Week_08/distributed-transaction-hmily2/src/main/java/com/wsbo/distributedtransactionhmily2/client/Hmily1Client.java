package com.wsbo.distributedtransactionhmily2.client;

import org.dromara.hmily.annotation.Hmily;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "hmily1")
public interface Hmily1Client {
    @Hmily
    @RequestMapping("/trade/{tradeId}")
    String work(@PathVariable("tradeId")Long id);
}
