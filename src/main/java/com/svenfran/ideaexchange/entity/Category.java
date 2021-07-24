package com.svenfran.ideaexchange.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "category")
public class Category implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    /*
    mappedBy attributes defines the owner of the relationship.
    The relationship has been defined and owned by the categories property of the Idea class.
    Therefore in the Category class it is declared with mappedBy=”categories”.
    This will tells that the relationship has been mapped and owned by the categories property of the Idea class.
    */
    @ManyToMany(mappedBy = "categories")
    @JsonBackReference
    private Set<Idea> ideas;

    public Category(Long id, String name, Set<Idea> ideas) {
        this.id = id;
        this.name = name;
        this.ideas = ideas;
    }

    public Category(){}

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<Idea> getIdeas() {
        return ideas;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIdeas(Set<Idea> ideas) {
        this.ideas = ideas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Category category = (Category) o;

        return id != null ? id.equals(category.id) : category.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", ideas=" + ideas +
                '}';
    }
}
