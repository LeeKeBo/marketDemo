package com.justLearn.dao;

import com.justLearn.pojo.PurchaseRecordPo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component(value = "PurchaseRecordDao")
public interface PurchaseRecordDao {
    public int insertPurchaseRecord(PurchaseRecordPo pr);

}
