package com.justLearn.service;

public interface PurchaseService {
    public boolean purchase(Long userId,Long productId,int quantity);
}
