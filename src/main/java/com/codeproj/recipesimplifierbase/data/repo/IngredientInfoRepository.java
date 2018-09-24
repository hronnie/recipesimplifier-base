package com.codeproj.recipesimplifierbase.data.repo;

import com.codeproj.recipesimplifierbase.model.IngredientInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IngredientInfoRepository extends CrudRepository<IngredientInfo, Long> {

    public IngredientInfo findIngredientInfoByName(String name);

    public IngredientInfo findIngredientInfoByIngredientInfoId(Long ingredientId);

    public IngredientInfo save(IngredientInfo ingredientInfo);

    public List<IngredientInfo> findAll();

    @Transactional
    public void deleteByIngredientInfoId(Long ingredientId);

    @Transactional
    public void deleteByName(String name);

    public boolean existsById(Long ingredientId);

    public List<IngredientInfo> findByIngredientInfoIdIn(List<Long> ingredientIdList);

}
