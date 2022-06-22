package com.zkdlu.proxy.app.v2;

public class OrderServiceV2 {

    private final OrderRepositoryV2 repository;

    public OrderServiceV2(final OrderRepositoryV2 repository) {
        this.repository = repository;
    }

    public void orderItem(String itemId) {
        repository.save(itemId);
    }
}
