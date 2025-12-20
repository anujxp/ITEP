package com.info.bootjpa.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "categories")
public class Category {

    private int id;
    private String category_Name;




}
