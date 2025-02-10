package com.rr.desafio_anota_ai.service;

import com.rr.desafio_anota_ai.domain.category.exception.CategoryNotFoundException;
import com.rr.desafio_anota_ai.domain.product.Product;
import com.rr.desafio_anota_ai.domain.product.ProductDto;
import com.rr.desafio_anota_ai.domain.product.exception.ProductNotFoundException;
import com.rr.desafio_anota_ai.repository.ProductRepository;
import com.rr.desafio_anota_ai.service.aws.AwsSnsService;
import com.rr.desafio_anota_ai.service.aws.MessageDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final CategoryService categoryService;
    private final ProductRepository productRepository;
    private final AwsSnsService snsService;

    public ProductService(CategoryService categoryService, ProductRepository productRepository, AwsSnsService snsService) {
        this.categoryService = categoryService;
        this.productRepository = productRepository;
        this.snsService = snsService;
    }

    public Product insert(ProductDto productData){
        this.categoryService.getById(productData.categoryId())
                .orElseThrow(CategoryNotFoundException::new);
        Product newProduct = new Product();
        newProduct.setTitle(productData.title());
        newProduct.setDescription(productData.description());
        newProduct.setOwnerId(productData.ownerId());
        newProduct.setPrice(productData.price());

        productRepository.save(newProduct);

        snsService.publish(new MessageDto(newProduct.getOwnerId()));

        return newProduct;
    }

    public Product update(String id, ProductDto productData){
        Product product = productRepository.findById(id)
                .orElseThrow(ProductNotFoundException::new);

        this.categoryService.getById(productData.categoryId())
                .orElseThrow(CategoryNotFoundException::new);

        if(!productData.title().isEmpty()) product.setTitle(productData.title());
        if(!productData.description().isEmpty()) product.setDescription(productData.description());
        if(!(productData.price() == null)) product.setPrice(productData.price());
//        if(!(productData.categoryId() == null)) product.setCategory(productData.categoryId());

        productRepository.save(product);

        snsService.publish(new MessageDto(product.getOwnerId()));

        return product;
    }

    public void delete(String id){
        Product product = productRepository.findById(id)
                .orElseThrow(ProductNotFoundException::new);

        productRepository.delete(product);
    }

    public List<Product> getAll(){
        return productRepository.findAll();
    }
}
