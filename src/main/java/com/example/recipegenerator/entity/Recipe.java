package com.example.recipegenerator.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Entity
@Table(name="m_recipe")
public class Recipe {
    @GenericGenerator(strategy = "uuid2", name = "system-uuid")
    @GeneratedValue(generator = "system-uuid")
    @Id
    @Column(name = "id")
    private String recipeId;
    private String name;
    private String mainIngredient;

    @ElementCollection
    @CollectionTable(name = "accompaniments", joinColumns = @JoinColumn(name = "recipe_id"))
    @Column(name = "accompaniment")
    private List<String> accompaniments;
    private String instructions;

    @ManyToOne
    private User author;
}
