package com.shiro.item.controller;

import com.shiro.item.model.Item;
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

    @GetMapping("/details")
    public Item details(@RequestParam(name = "id") Long id, @RequestParam(name = "number") Integer number) {
        return itemService.findById(id, number);
    }

}
