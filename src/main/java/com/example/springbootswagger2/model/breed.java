package com.example.springbootswagger2.model;

import io.swagger.annotations.ApiModelProperty;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class breed {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(readOnly = true)
    private int id_breed;
    @ApiModelProperty
    private String name;
    @ApiModelProperty(example = "99.99")
    private String price;

    public breed() {
    }

    public breed(String name, String price) {
        this.name = name;
        this.price = price;
    }

    public int getId_breed() { return id_breed; }

    public void setId_breed(int id_breed) { this.id_breed = id_breed; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getPrice() { return price; }

    public void setPrice(String price) { this.price = price; }

    @Override
    public String toString() {
        return "breed{" +
                "id_breed=" + id_breed +
                ", name='" + name + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}