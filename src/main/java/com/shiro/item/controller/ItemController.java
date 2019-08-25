package com.shiro.item.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.shiro.item.model.Item;
import com.shiro.item.model.Product;
import com.shiro.item.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RefreshScope
@RestController
public class ItemController {

    private static Logger log = LoggerFactory.getLogger(ItemController.class);

    @Autowired
    private Environment environment;

    @Autowired
    @Qualifier("serviceFeign")
    private ItemService itemService;

    @Value("${config.text}")
    private String text;

    @GetMapping("/list")
    public List<Item> list() {
        return itemService.findAll();
    }

    @HystrixCommand(fallbackMethod = "alternativeMethod")
    @GetMapping("/details")
    public Item details(@RequestParam(name = "id") Long id, @RequestParam(name = "number") Integer number) {
        return itemService.findById(id, number);
    }


    @GetMapping("/config")
    public ResponseEntity<?> getConfig(@Value("${server.port}") String port) {
        log.info(text);
        Map<String, String> json = new HashMap<>();
        json.put("text", text);
        json.put("port", port);
        if (environment.getActiveProfiles().length > 0 && environment.getActiveProfiles()[0].contains("dev")) {
            json.put("author.name", environment.getProperty("config.author.name"));
        }
        return new ResponseEntity<Map<String, String>>(json, HttpStatus.OK);
    }

    /**
     * If an error occurs, this function will be called as an alternative path
     *
     * @param id     product identifier
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

    @PostMapping("/product")
    @ResponseStatus(HttpStatus.CREATED)
    public Product createProduct(Product product) {
        return itemService.save(product);
    }

    @PutMapping("/product")
    @ResponseStatus(HttpStatus.CREATED)
    public Product update(@RequestParam(name = "id") Long id, @RequestBody Product product) {
        return itemService.update(id, product);
    }

    @DeleteMapping("product")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@RequestParam(name = "id") Long id) {
        itemService.delete(id);
    }


}
