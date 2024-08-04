package com.ecommerce.web.product.controller;

import com.ecommerce.web.product.dto.CreateProductDTO;
import com.ecommerce.web.product.dto.ProductDTO;
import com.ecommerce.web.product.entity.Product;
import com.ecommerce.web.product.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/product")
public class ProductController {

    private ProductService _productService;

    @PostMapping
    public ResponseEntity<Product> create(@RequestBody CreateProductDTO createProductDto) {
        Product save = this._productService.create(createProductDto);
        return new ResponseEntity<Product>(save, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAll() {
        List<ProductDTO> list =  this._productService.getAll();
        return new ResponseEntity<List<ProductDTO>>(list, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable("id") String id) {
        ProductDTO productDto = this._productService.getById(id);
        return new ResponseEntity<ProductDTO>(productDto, HttpStatus.OK);
    }
}
