package com.justLearn.pojo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;


@Alias("product")
@Data
public class ProductPo implements Serializable {
    private static final long serialVersionUID = 2312115454564L;
    private Long id;
    private String productName;
    private int stock;
    private double price;
    private int version;
    private String note;
}
