package com.shiro.item.controller;

import com.shiro.item.model.Item;
import com.shiro.item.service.ItemServiceInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    private ItemServiceInterface itemService;

    @Value("${config.text}")
    private String text;

    @GetMapping("/list")
    public List<Item> list() {
        return itemService.findAll();
    }

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

}
