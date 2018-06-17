package com.codeproj.recipesimplifierbase.model;

import jdk.nashorn.internal.objects.annotations.Constructor;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Recipe {

    private String name;
    private String[] ingredients;
    private String preparation;
    private String[] processes;

}
