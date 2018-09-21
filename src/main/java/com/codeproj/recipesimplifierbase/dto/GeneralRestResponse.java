package com.codeproj.recipesimplifierbase.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sun.tools.tree.BooleanExpression;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name="Ingredient")
public class GeneralRestResponse {

    @XmlElement(name = "message")
    private String message;

    @XmlElement(name = "success")
    private Boolean success;

}
