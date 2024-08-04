package com.ecommerce.web.category.service;

import com.ecommerce.web.category.dto.CategoryDTO;
import com.ecommerce.web.category.entity.Category;
import com.ecommerce.web.category.exception.ResourceNotFoundException;
import com.ecommerce.web.category.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class CategoryService {

    private CategoryRepository _repository;
    private ModelMapper _modelMapper;

    /**
     * @param {CategoryDTO} category
     * @return {CategoryDTO}
     */
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Category categoryEntity = _modelMapper.map( categoryDTO, Category.class );
        Category saveCategory = _repository.save(categoryEntity);

        return _modelMapper.map( saveCategory, CategoryDTO.class );
    }

    /**
     * @return {List<CategoryEntity>}
     */
    public List<CategoryDTO> getCategories() {
        return _modelMapper.map( _repository.findAll(), new TypeToken<>() {}.getType());
    }

    /**
     * @param {String} categoryId
     * @return {List<CategoryEntity>}
     */
    public CategoryDTO getCategory( String categoryId ) {
        Category findCategory = _repository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Not Found ID:" + categoryId ));

//        TypeMap<CategoryEntity, CategoryDTO> typeMap = _modelMapper.createTypeMap(CategoryEntity.class, CategoryDTO.class);
//        typeMap.addMappings(
//                mapper -> {
//                    mapper.map(CategoryEntity::getCategoryName, CategoryDTO::setCategoryName );
//                    mapper.map(CategoryEntity::getId, CategoryDTO::setCategoryId );
//                }
//        );

        return _modelMapper.map( findCategory, CategoryDTO.class );
    }

    /**
     @param {CategoryEntity} category
     * @return {List<CategoryEntity>}
     */
    public CategoryDTO updateCategory ( CategoryDTO category ) {
        String id = category.getId();
        Category existed = _repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Not Found ID:" + id ));
        existed.setCategoryName(category.getCategoryName());
        return _modelMapper.map( existed, CategoryDTO.class );
    }

    /**
     * @param {String} categoryId
     * @return {void}
     */
    public void deleteCategory( String categoryId ) {
        _repository.deleteById(categoryId);
    }
}
