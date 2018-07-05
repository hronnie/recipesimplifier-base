package com.codeproj.recipesimplifierbase.common;

import java.util.EnumSet;

public enum RecipeCategory {

  SOUP("soup"),
  MAIN_COURSE("mainCourse"),
  SALAD("salad"),
  DESSERT("dessert");

  private final String text;

  RecipeCategory(String text) {
    this.text = text;
  }

  public static boolean isCategoryValid(String category) {
    for (RecipeCategory cat : RecipeCategory.values()) {
      if (category.equals(cat.text)) {
        return true;
      }
    }
    return false;
  }

}
