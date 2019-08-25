package com.shiro.item.client;

import com.shiro.item.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "product-service")
public interface ProductClientRest {
    /**
     * list all products
     * GetMapping indicates the route of the endpoint to be consumed
     *
     * @return List<Product>
     * @author Albano Yanes <ajyanreyu@gmail.com>
     */
    @GetMapping("/list")
    public List<Product> list();

    /**
     * Ger product details by product id
     *
     * @param id product identifier
     * @return Product
     * @author Albano Yanes <ajyanreyu@gmail.com>
     */
    @GetMapping("/details")
    public Product productById(@RequestParam(name = "id") Long id);

    /**
     * Create product
     *
     * @param product product data
     * @return Product
     * @author Albano Yanes <ajyanreyu@gmail.com>
     */
    @PostMapping("/product")
    public Product save(@RequestBody Product product);

    /**
     * Update product by product id
     *
     * @param id      product id
     * @param product product data
     * @return Product
     * @author Albano Yanes <ajyanreyu@gmail.com>
     */
    @PutMapping("/product")
    public Product udpate(@RequestParam(name = "id") Long id, @RequestBody Product product);


    /**
     * Delete product by product id
     *
     * @param id
     * @author Albano Yanes <ajyanreyu@gmail.com>
     */
    @DeleteMapping("/product")
    public void delete(@RequestParam(name = "id") Long id);


}
