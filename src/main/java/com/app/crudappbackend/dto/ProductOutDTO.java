package com.app.crudappbackend.dto;


import com.app.crudappbackend.datamodel.Product;

public class ProductOutDTO {

    private int id;
    private String name;
    private String description;
    private float price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public ProductOutDTO load(Product product){
        ProductOutDTO productOutDTO = new ProductOutDTO();
        productOutDTO.setId(product.getId());
        productOutDTO.setName(product.getName());
        productOutDTO.setDescription(product.getDescription());
        productOutDTO.setPrice(product.getPrice());

        return productOutDTO;
    }

}
