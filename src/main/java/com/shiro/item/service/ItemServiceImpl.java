package com.shiro.item.service;

import com.shiro.item.model.Item;
import com.shiro.item.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service("serviceRestTemplate")
public class ItemServiceImpl implements ItemServiceInterface {
    @Autowired
    private RestTemplate restClient;

    @Override
    public List<Item> findAll() {
        List<Product> products = Arrays.asList(restClient.getForObject("http://product-service/list", Product[].class));
        return products.stream().map(product -> new Item(product, 1)).collect(Collectors.toList());
    }

    @Override
    public Item findById(Long id, Integer number) {
        Product product = restClient.getForObject("http://product-service/details?id=" + id.toString(), Product.class);
        return new Item(product, number);
    }
}
