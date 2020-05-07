package com.lizavetakeura.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.FetchType;
import java.util.Set;

@Entity
@Table(name = "subcategory")
public class Subcategory {

    @Column
    private String name;

    @Id
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_category")
    private Category category;

    @OneToMany(mappedBy = "subcategory", fetch = FetchType.LAZY)
    private Set<Product> products;

    public Subcategory() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return getId() + " " + getName() + " category: " + getCategory().toString();
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Subcategory subcategory = (Subcategory) object;
        return name.equals(subcategory.getName()) &&
                id.equals(subcategory.getId()) &&
                category.equals(subcategory.getCategory());
    }

    @Override
    public int hashCode() {

        final int prime = 31;
        int result = 1;

        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((category == null) ? 0 : category.hashCode());
        result = prime * result + id;

        return result;
    }
}
