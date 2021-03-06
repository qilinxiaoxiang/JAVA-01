package com.wsbo.batchinsert;

import com.wsbo.batchinsert.dal.mapper.TradeMapper;
import com.wsbo.batchinsert.dal.model.Trade;
import com.wsbo.batchinsert.inserter.SimpleInserter;
import com.wsbo.batchinsert.inserter.ThreadPoolSimpleInserter;
import com.wsbo.batchinsert.inserter.ThreadPoolSqlBatchInserter;
import com.wsbo.batchinsert.inserter.ThreadPoolSqlSessionBatchInserter;
import com.wsbo.batchinsert.util.ApplicationContextUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;

@SpringBootApplication
@MapperScan("com.wsbo.batchinsert.dal.mapper")
public class BatchinsertApplication {

	public static void main(String[] args) {
		SpringApplication.run(BatchinsertApplication.class, args);
		ThreadPoolSqlSessionBatchInserter.insert();
	}

}
