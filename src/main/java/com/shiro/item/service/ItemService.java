package com.shiro.item.service;

import com.shiro.item.model.Item;
import com.shiro.item.model.Product;

import java.util.List;

public interface ItemService {

    /**
     * Find all products
     *
     * @return List
     * @author Albano Yanes <ajyanreyu@gmail.com>
     */
    public List<Item> findAll();

    /**
     * Find a product by id
     *
     * @param id     product identifier
     * @param number number of product
     * @return Item
     * @author Albano Yanes <ajyanreyu@gmail.com>
     */
    public Item findById(Long id, Integer number);

    /**
     * Create product
     *
     * @param product Product
     * @return Product
     * @author Albano Yanes <ajyanreyu@gmail.com>
     */
    public Product save(Product product);

    /**
     * Update product by id
     *
     * @param product Product
     * @return Product
     * @author Albano Yanes <ajyanreyu@gmail.com>
     */
    public Product update(Long id, Product product);

    /**
     * Delete product by id
     *
     * @param id product identifier
     * @author Albano Yanes <ajyanreyu@gmail.com>
     */
    public void delete(Long id);
}
