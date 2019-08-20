package com.shiro.item.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.shiro.item.model.Item;
import com.shiro.item.model.Product;
import com.shiro.item.service.ItemServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ItemController {
    @Autowired
    @Qualifier("serviceFeign")
    private ItemServiceInterface itemService;

    @GetMapping("/list")
    public List<Item> list() {
        return itemService.findAll();
    }

    @HystrixCommand(fallbackMethod = "alternativeMethod")
    @GetMapping("/details")
    public Item details(@RequestParam(name = "id") Long id, @RequestParam(name = "number") Integer number) {
        return itemService.findById(id, number);
    }

    /**
     * If an error occurs, this function will be called as an alternative path
     * @param id product identifier
     * @param number number of product
     * @return Item
     * @author Albano Yanes <ajyanreyu@gmail.com>
     */
    public Item alternativeMethod(Long id, Integer number) {
        Item item = new Item();
        Product product = new Product();
        item.setProductQuantity(number);
        product.setId(id);
        product.setName("Fake product");
        product.setPrice(999.99);
        item.setProduct(product);
        return item;
    }

}
