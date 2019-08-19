package com.shiro.item.service;

import com.shiro.item.model.Item;
import com.shiro.item.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpl implements ItemServiceInterface {
    @Autowired
    private RestTemplate restClient;

    @Override
    public List<Item> findAll() {
        List<Product> products = Arrays.asList(restClient.getForObject("http://127.0.0.1:8000/list", Product[].class));
        return products.stream().map(product -> new Item(product, 1)).collect(Collectors.toList());
    }

    @Override
    public Item findById(Long id, Integer number) {
        Product product = restClient.getForObject("http://127.0.0.1:8000/details?id=" + id.toString(), Product.class);
        return new Item(product, number);
    }
}
