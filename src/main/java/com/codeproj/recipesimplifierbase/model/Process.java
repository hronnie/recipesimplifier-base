package com.codeproj.recipesimplifierbase.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Process")
public class Process {

    @Id
    @Column(name = "process_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long processId;

    @Column(name = "description")
    private String description;

    @Column(name = "duration")
    private Integer duration;

    @ManyToOne
    @JoinColumn(name="id", nullable=false)
    private Recipe recipe;
}
