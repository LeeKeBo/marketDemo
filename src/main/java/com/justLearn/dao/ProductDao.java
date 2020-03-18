package com.justLearn.dao;

import com.justLearn.pojo.ProductPo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Mapper
@Component(value = "ProductDao")
public interface ProductDao {
    public ProductPo getProduct(Long id);
    public int decreaseProduct(@Param("id") Long id, @Param("quantity") int quantity,@Param("version") int version);
}
