package com.shiro.item.client;

import com.shiro.item.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "product-service")
public interface ProductClientRest {
    /**
     * list all products
     * GetMapping indicates the route of the endpoint to be consumed
     * @return List<Product>
     */
    @GetMapping("/list")
    public List<Product> list();

    @GetMapping("/details")
    public Product productById(@RequestParam(name = "id") Long id);

}
