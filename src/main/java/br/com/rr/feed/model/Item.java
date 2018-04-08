package br.com.rr.feed.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class Item {

    @Id
    @GeneratedValue
    private Long id;

    private String title;

    private String link;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "item", orphanRemoval = true)
    private List<ItemDescription> descriptions;

    public Item() {
        descriptions = new ArrayList<>();
    }

    public Item(String title, String link) {
        this();
        this.title = title;
        this.link = link;
    }

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
    public String getLink() {
        return link;
    }
    public void setLink(String link) {
        this.link = link;
    }
    public List<ItemDescription> getDescriptions() {
        return descriptions;
    }
    public void setDescriptions(List<ItemDescription> descriptions) {
        this.descriptions = descriptions;
    }
}
