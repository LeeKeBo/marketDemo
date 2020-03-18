package com.justLearn.service.impl;

import com.justLearn.dao.ProductDao;
import com.justLearn.dao.PurchaseRecordDao;
import com.justLearn.pojo.ProductPo;
import com.justLearn.pojo.PurchaseRecordPo;
import com.justLearn.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PurchaseServiceImpl implements PurchaseService {
    @Autowired
    private ProductDao productDao = null;
    @Autowired
    private PurchaseRecordDao purchaseRecordDao = null;

    @Override
    @Transactional
    public boolean purchase(Long userId, Long productId, int quantity) {
        // 限定5次尝试机会，减少失败次数
//        for(int i=0;i<3;i++){
        long startTime = System.currentTimeMillis();
        // 使用计时来减少失败次数
        while(true){
            long endTime = System.currentTimeMillis();
            if(endTime - startTime > 200)
                break;
            ProductPo productPo = productDao.getProduct(productId);
            if(productPo.getStock() < quantity){
                return  false;
            }
            // 获取版本号，使用乐观锁解决并发问题
            int version = productPo.getVersion();
            int result = productDao.decreaseProduct(productId,quantity,version);
            if(result == 0)
                continue;
            PurchaseRecordPo purchaseRecordPo = this.initPurchaseRecord(userId,productPo,quantity);
            purchaseRecordDao.insertPurchaseRecord(purchaseRecordPo);
            return true;
        }
        return false;
    }

    private PurchaseRecordPo initPurchaseRecord(Long userId,ProductPo product,int quantity){
        PurchaseRecordPo pr = new PurchaseRecordPo();
        pr.setNote("购买日志，时间："+System.currentTimeMillis());
        pr.setProductId(product.getId());
        pr.setQuantity(quantity);
        double sum = product.getPrice() * quantity;
        pr.setSum(sum);
        pr.setUserId(userId);;
        return pr;
    }

}
