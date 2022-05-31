package com.demo.demo.dto;

import java.io.Serializable;
import java.util.Objects;

public class CategoryDto implements Serializable {
    private long id;
    private String name;
    private String describe;

    public CategoryDto(long id, String name, String describe) {
        this.id = id;
        this.name = name;
        this.describe = describe;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoryDto category = (CategoryDto) o;
        return id == category.id;
    }
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "CategoryDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", describe='" + describe + '\'' +
                '}';
    }
}
