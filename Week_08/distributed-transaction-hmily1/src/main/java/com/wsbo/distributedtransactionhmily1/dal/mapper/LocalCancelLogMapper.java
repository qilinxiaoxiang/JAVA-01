package com.wsbo.distributedtransactionhmily1.dal.mapper;

import com.wsbo.distributedtransactionhmily1.dal.model.LocalCancelLog;
import com.wsbo.distributedtransactionhmily1.dal.model.LocalCancelLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface LocalCancelLogMapper {
    long countByExample(LocalCancelLogExample example);

    int deleteByExample(LocalCancelLogExample example);

    int deleteByPrimaryKey(String txNo);

    int insert(LocalCancelLog record);

    int insertSelective(LocalCancelLog record);

    List<LocalCancelLog> selectByExampleWithRowbounds(LocalCancelLogExample example, RowBounds rowBounds);

    List<LocalCancelLog> selectByExample(LocalCancelLogExample example);

    LocalCancelLog selectByPrimaryKey(String txNo);

    int updateByExampleSelective(@Param("record") LocalCancelLog record, @Param("example") LocalCancelLogExample example);

    int updateByExample(@Param("record") LocalCancelLog record, @Param("example") LocalCancelLogExample example);

    int updateByPrimaryKeySelective(LocalCancelLog record);

    int updateByPrimaryKey(LocalCancelLog record);
}