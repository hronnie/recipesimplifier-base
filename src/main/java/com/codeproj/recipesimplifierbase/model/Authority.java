package com.codeproj.recipesimplifierbase.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Setter;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Table(name="AUTHORITY")
public class Authority implements GrantedAuthority {

	private static final long serialVersionUID = -1604526622204580333L;

	@Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Setter
    Long id;

	@Setter
    @Column(name="name")
    String name;

    @Override
    public String getAuthority() {
        return name;
    }

    @JsonIgnore
    public String getName() {
        return name;
    }

    @JsonIgnore
    public Long getId() {
        return id;
    }

}
