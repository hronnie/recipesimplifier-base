package com.codeproj.recipesimplifierbase.rest.validator;

public class RecipeImageValidator extends ValidatorBase {

  public static boolean uploadRecipeImage(Integer index, Long recipeId, String contentType) {
    return isEntityIdValid(recipeId)
      && isIndexValid(index)
      && isContentTypeValid(contentType);
  }

  private static boolean isContentTypeValid(String contentType) {
    return contentType != null
      && !"".equals(contentType)
      && contentType.startsWith("image");
  }

  private static boolean isIndexValid(Integer index) {
    return index != null && index > 0 && index < 6;
  }

  public static boolean getRecipeImage(Integer index, Long recipeId) {
    return isEntityIdValid(recipeId)
      && isIndexValid(index);
  }

  public static boolean getRecipeImagesList(Long recipeId) {
    return isEntityIdValid(recipeId);
  }
}
