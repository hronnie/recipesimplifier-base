package com.codeproj.recipesimplifierbase.dto;

import lombok.*;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@XmlRootElement(name="RecipeProcess")
public class RecipeProcessDto {


    @XmlElement(name = "id")
    private Long processId;

    @XmlElement(name = "description")
    private String description;

    @XmlElement(name = "duration")
    private Integer duration;

}
