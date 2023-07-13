package com.app.crudappbackend.service;

import com.app.crudappbackend.datamodel.Product;
import com.app.crudappbackend.dto.ProductInDTO;
import com.app.crudappbackend.dto.ProductOutDTO;

import java.util.List;

public interface IProductService {

    public List<Product> findAll();

    ProductOutDTO create(ProductInDTO productInDTO);
    ProductOutDTO getById(int idProduct);
    ProductOutDTO update(int idProduct, ProductInDTO productInDTO);
    boolean deleteById(int idProduct);

}
