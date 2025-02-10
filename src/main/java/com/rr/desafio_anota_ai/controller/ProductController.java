package com.rr.desafio_anota_ai.controller;

import com.rr.desafio_anota_ai.domain.product.Product;
import com.rr.desafio_anota_ai.domain.product.ProductDto;
import com.rr.desafio_anota_ai.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<Product> insert(@RequestBody ProductDto productData){
        Product newProduct = productService.insert(productData);
        return ResponseEntity.ok().body(newProduct);
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAll(){
        List<Product> products = productService.getAll();
        return ResponseEntity.ok().body(products);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable("id") String id, @RequestBody ProductDto productData){
        Product updatedProduct = productService.update(id, productData);
        return ResponseEntity.ok().body(updatedProduct);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> delete(@PathVariable("id") String id){
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
