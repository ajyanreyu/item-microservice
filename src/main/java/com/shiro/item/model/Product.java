package com.shiro.item.model;

import lombok.Data;

import java.util.Date;

@Data
public class Product {
    private Long id;
    private String name;
    private Double price;
    private Date createAt;
    private Integer port;

}
