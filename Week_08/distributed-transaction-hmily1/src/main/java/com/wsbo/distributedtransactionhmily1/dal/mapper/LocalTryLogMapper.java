package com.wsbo.distributedtransactionhmily1.dal.mapper;

import com.wsbo.distributedtransactionhmily1.dal.model.LocalTryLog;
import com.wsbo.distributedtransactionhmily1.dal.model.LocalTryLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface LocalTryLogMapper {
    long countByExample(LocalTryLogExample example);

    int deleteByExample(LocalTryLogExample example);

    int deleteByPrimaryKey(String txNo);

    int insert(LocalTryLog record);

    int insertSelective(LocalTryLog record);

    List<LocalTryLog> selectByExampleWithRowbounds(LocalTryLogExample example, RowBounds rowBounds);

    List<LocalTryLog> selectByExample(LocalTryLogExample example);

    LocalTryLog selectByPrimaryKey(String txNo);

    int updateByExampleSelective(@Param("record") LocalTryLog record, @Param("example") LocalTryLogExample example);

    int updateByExample(@Param("record") LocalTryLog record, @Param("example") LocalTryLogExample example);

    int updateByPrimaryKeySelective(LocalTryLog record);

    int updateByPrimaryKey(LocalTryLog record);
}