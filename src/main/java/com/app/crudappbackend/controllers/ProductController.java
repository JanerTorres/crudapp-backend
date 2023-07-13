package com.app.crudappbackend.controllers;

import com.app.crudappbackend.datamodel.Product;
import com.app.crudappbackend.dto.ProductInDTO;
import com.app.crudappbackend.dto.ProductOutDTO;
import com.app.crudappbackend.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    IProductService productService;

    @PostMapping(value = "/create")
    public ProductOutDTO create(@RequestBody ProductInDTO productInDTO){
        return productService.create(productInDTO);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductOutDTO> getProduct(@PathVariable("id") int idProduct){
        ProductOutDTO productOutDTO = productService.getById(idProduct);
        if (productOutDTO == null) return ResponseEntity.notFound().build();
            return new ResponseEntity(productOutDTO, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ProductOutDTO> updateProduct(@PathVariable("id") int idProduct, @ RequestBody ProductInDTO productInDTO){
        ProductOutDTO productOutDTO = productService.update(idProduct, productInDTO);
        if (productOutDTO == null) return ResponseEntity.notFound().build();
        return new ResponseEntity(productService.getById(idProduct), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteProduct(@PathVariable("id") int idProduct){
        boolean resultDelete = productService.deleteById(idProduct);
        if(resultDelete) return ResponseEntity.noContent().build();
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/getall")
    public List<Product> getAll(){
        return productService.findAll();
    }

}
