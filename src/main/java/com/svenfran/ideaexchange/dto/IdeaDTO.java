package com.svenfran.ideaexchange.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.svenfran.ideaexchange.entity.Category;

import java.util.Date;
import java.util.Set;

public class IdeaDTO {

    private Long id;
    private String title;
    private String description;
    @JsonProperty
    private boolean isOpen;
    @JsonProperty
    private boolean isIdea;
    private Date dateCreated;
    private Date lastUpdated;
    private Set<Category> categories;


    public IdeaDTO(Long id, String title, String description, boolean isOpen, boolean isIdea, Date dateCreated, Date lastUpdated, Set<Category> categories) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.isOpen = isOpen;
        this.isIdea = isIdea;
        this.dateCreated = dateCreated;
        this.lastUpdated = lastUpdated;
        this.categories = categories;
    }

    public IdeaDTO() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public boolean isIdea() {
        return isIdea;
    }

    public void setIdea(boolean idea) {
        isIdea = idea;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IdeaDTO ideaDTO = (IdeaDTO) o;

        return id.equals(ideaDTO.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
