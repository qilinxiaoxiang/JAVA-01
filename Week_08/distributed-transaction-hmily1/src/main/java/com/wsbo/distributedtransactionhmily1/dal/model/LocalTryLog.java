package com.wsbo.distributedtransactionhmily1.dal.model;

import java.util.Date;

public class LocalTryLog {
    private String txNo;

    private Date createTime;

    public String getTxNo() {
        return txNo;
    }

    public void setTxNo(String txNo) {
        this.txNo = txNo == null ? null : txNo.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", txNo=").append(txNo);
        sb.append(", createTime=").append(createTime);
        sb.append("]");
        return sb.toString();
    }
}