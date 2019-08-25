package com.shiro.item.service;

import com.shiro.item.client.ProductClientRest;
import com.shiro.item.model.Item;
import com.shiro.item.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("serviceFeign")
public class ItemServiceFeign implements ItemService {

    @Autowired
    private ProductClientRest feignClientRest;

    @Override
    public List<Item> findAll() {
        return feignClientRest.list().stream().map(product -> new Item(product, 1)).collect(Collectors.toList());
    }

    @Override
    public Item findById(Long id, Integer number) {
        return new Item(feignClientRest.productById(id), number);
    }

    @Override
    public Product save(Product product) {
        return feignClientRest.save(product);
    }

    @Override
    public Product update(Long id, Product product) {
        return feignClientRest.udpate(id, product);
    }

    @Override
    public void delete(Long id) {
        feignClientRest.delete(id);
    }
}
