package com.wsbo.distributedtransactionhmily1.dal.mapper;

import com.wsbo.distributedtransactionhmily1.dal.model.LocalConfirmLog;
import com.wsbo.distributedtransactionhmily1.dal.model.LocalConfirmLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface LocalConfirmLogMapper {
    long countByExample(LocalConfirmLogExample example);

    int deleteByExample(LocalConfirmLogExample example);

    int insert(LocalConfirmLog record);

    int insertSelective(LocalConfirmLog record);

    List<LocalConfirmLog> selectByExampleWithRowbounds(LocalConfirmLogExample example, RowBounds rowBounds);

    List<LocalConfirmLog> selectByExample(LocalConfirmLogExample example);

    int updateByExampleSelective(@Param("record") LocalConfirmLog record, @Param("example") LocalConfirmLogExample example);

    int updateByExample(@Param("record") LocalConfirmLog record, @Param("example") LocalConfirmLogExample example);
}