package com.codeproj.recipesimplifierbase.dto;

import lombok.*;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name="Preparation")
public class PreparationDto {

    public PreparationDto(String description, Integer duration) {
        this.description = description;
        this.duration = duration;
    }

    @XmlElement(name = "id")
    private Long preparationId;

    @XmlElement(name = "description")
    private String description;

    @XmlElement(name = "duration")
    private Integer duration;

}