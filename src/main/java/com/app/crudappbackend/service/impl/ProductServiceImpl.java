package com.app.crudappbackend.service.impl;


import com.app.crudappbackend.datamodel.Product;
import com.app.crudappbackend.dto.ProductInDTO;
import com.app.crudappbackend.dto.ProductOutDTO;
import com.app.crudappbackend.repository.IProductRepository;
import com.app.crudappbackend.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    IProductRepository productRepository;


    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public ProductOutDTO create(ProductInDTO productInDTO) {
        // If exists Product
        ProductOutDTO resultProductOutDTO = new ProductOutDTO();
        Product product = this.mapProduct(productInDTO);
        // Guardamos en bd
        product = productRepository.save(product);
        resultProductOutDTO = resultProductOutDTO.load(product);
        return resultProductOutDTO;
    }

    @Override
    public ProductOutDTO getById(int idProduct) {
        ProductOutDTO productOutDTO = new ProductOutDTO();
        Optional<Product> optionalProduct = productRepository.findById(idProduct);
        if(optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            productOutDTO = productOutDTO.load(product);
            return productOutDTO;
        }
        return null;
    }

    @Override
    public ProductOutDTO update(int idProduct, ProductInDTO productInDTO) {
        ProductOutDTO productOutDTO = new ProductOutDTO();
        Product updatedProduct;
        Optional<Product> productOptional= productRepository.findById(idProduct);
        if(productOptional.isPresent()){
            updatedProduct = productOptional.get();
            updatedProduct.setName(productInDTO.getName());
            updatedProduct.setDescription(productInDTO.getDescription());
            updatedProduct.setPrice(productInDTO.getPrice());

            updatedProduct = productRepository.save(updatedProduct);

            productOutDTO = productOutDTO.load(updatedProduct);
            return productOutDTO;
        }
        return null;

    }

    @Override
    public boolean deleteById(int idProduct) {
        if(productRepository.existsById(idProduct)){
            productRepository.deleteById(idProduct);
            return true;
        }
        return false;
    }

    public Product mapProduct(ProductInDTO productInDTO){
        Product product = new Product();
        product.setName(productInDTO.getName());
        product.setDescription(productInDTO.getDescription());
        product.setPrice(productInDTO.getPrice());
        return product;
    }
}
