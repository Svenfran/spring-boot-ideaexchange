package com.svenfran.ideaexchange.entity;

import com.fasterxml.jackson.annotation.*;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "idea")
public class Idea implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "is_open")
    @JsonProperty
    private boolean isOpen;

    @Column(name = "is_idea")
    @JsonProperty
    private boolean isIdea;

    @Column(name = "date_created")
    @UpdateTimestamp
    private Date dateCreated;

    @Column(name = "last_updated")
    @UpdateTimestamp
    private Date lastUpdated;

    @ManyToMany()
    @JoinTable(
            name = "idea_category",
            joinColumns = {@JoinColumn(name = "idea_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "category_id", referencedColumnName = "id")}
    )
    private Set<Category> categories;


    public Idea(Long id, String title, String description, boolean isOpen, boolean isIdea, Date dateCreated, Date lastUpdated, Set<Category> categories) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.isOpen = isOpen;
        this.isIdea = isIdea;
        this.dateCreated = dateCreated;
        this.lastUpdated = lastUpdated;
        this.categories = categories;
    }

    public Idea() {}

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public boolean isIdea() {
        return isIdea;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setIsOpen(boolean isOpen) {
        this.isOpen = isOpen;
    }

    public void setIsIdea(boolean isIdea) {
        this.isIdea = isIdea;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Idea idea = (Idea) o;

        return id != null ? id.equals(idea.id) : idea.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Idea{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", isOpen=" + isOpen +
                ", isIdea=" + isIdea +
                ", dateCreated=" + dateCreated +
                ", lastUpdated=" + lastUpdated +
                ", categories=" + categories +
                '}';
    }
}
