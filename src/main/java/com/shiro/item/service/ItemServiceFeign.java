package com.shiro.item.service;

import com.shiro.item.client.ProductClientRest;
import com.shiro.item.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("serviceFeign")
public class ItemServiceFeign  implements ItemServiceInterface{

    @Autowired
    private ProductClientRest productClientRest;
    @Override
    public List<Item> findAll() {
        return productClientRest.list().stream().map(product -> new Item(product, 1)).collect(Collectors.toList());
    }

    @Override
    public Item findById(Long id, Integer number) {
        return new Item(productClientRest.productById(id), number);
    }
}
