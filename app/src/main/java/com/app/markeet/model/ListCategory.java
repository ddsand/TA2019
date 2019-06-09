package com.app.markeet.model;

public class ListCategory {
    public Long id;
    public String name;
    public String icon;
    public Integer draft;
    public String brief;
    public String color;
    public Long created_at;
    public Long last_update;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getDraft() {
        return draft;
    }

    public void setDraft(Integer draft) {
        this.draft = draft;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Long getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Long created_at) {
        this.created_at = created_at;
    }

    public Long getLast_update() {
        return last_update;
    }

    public void setLast_update(Long last_update) {
        this.last_update = last_update;
    }
    @Override
    public String toString(){
        return
                "Categories{" +
                        "id = '" + String.valueOf(id) + '\'' +
                        ",name = '" + name + '\'' +
                        ",brief = '" + brief + '\'' +
                        "}";
    }
}
