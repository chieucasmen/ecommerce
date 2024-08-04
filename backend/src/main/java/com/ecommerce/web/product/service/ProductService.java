package com.ecommerce.web.product.service;

import com.ecommerce.web.category.dto.CategoryDTO;
import com.ecommerce.web.category.entity.Category;
import com.ecommerce.web.category.exception.ResourceNotFoundException;
import com.ecommerce.web.category.repository.CategoryRepository;
import com.ecommerce.web.product.dto.CreateProductDTO;
import com.ecommerce.web.product.dto.ProductDTO;
import com.ecommerce.web.product.entity.Product;
import com.ecommerce.web.product.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class ProductService {

    private ModelMapper _modelMapper;
    private ProductRepository _repository;
    private CategoryRepository _categoryRepository;

    public Product create(CreateProductDTO dto) {
        return _repository.save(_modelMapper.map(dto, Product.class));
    }

    public List<ProductDTO> getAll() {
        List<Product> a =_repository.findA();
        List<Product> productList = _repository.findAll();
        List<ProductDTO> productDtoList = new ArrayList<>();
        productList.forEach((product -> productDtoList.add( this.getById( product.getId() ) ) ));
        return productDtoList;
    }

    public ProductDTO getById(String id) {
        Product findProduct = _repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not Found Id: " + id));

        String categoryId = findProduct.getCategoryId();

        ProductDTO finalProductDto = _modelMapper.map( findProduct, ProductDTO.class );

        Category findCateGory = _categoryRepository.findById(categoryId)
            .orElseThrow(() -> new ResourceNotFoundException("Not Found categoryId: " + categoryId));

        finalProductDto.setCategory( _modelMapper.map(findCateGory, CategoryDTO.class ) );

        return finalProductDto;
    }
}
