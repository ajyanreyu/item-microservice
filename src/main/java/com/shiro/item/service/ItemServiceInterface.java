package com.shiro.item.service;

import com.shiro.item.model.Item;

import java.util.List;

public interface ItemServiceInterface {

    /**
     * Find all products
     * @return List
     * @author Albano Yanes <ajyanreyu@gmail.com>
     */
    public List<Item> findAll();

    /**
     * Find a product by id
     * @param id product identifier
     * @param number number of product
     * @return Item
     * @author Albano Yanes <ajyanreyu@gmail.com>
     */
    public Item findById(Long id, Integer number);
}
