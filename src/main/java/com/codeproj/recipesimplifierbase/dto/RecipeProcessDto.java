package com.codeproj.recipesimplifierbase.dto;

import lombok.*;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name="RecipeProcess")
public class RecipeProcessDto {

    public RecipeProcessDto(String description, Integer duration) {
        this.description = description;
        this.duration = duration;
    }

    @XmlElement(name = "id")
    private Long processId;

    @XmlElement(name = "description")
    private String description;

    @XmlElement(name = "duration")
    private Integer duration;

}
