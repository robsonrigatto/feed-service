package br.com.rr.feed.model;

import br.com.rr.feed.enumeration.DescriptionType;

import javax.persistence.*;

@Entity
@Table
public class ItemDescription {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    @Enumerated(EnumType.STRING)
    private DescriptionType type;

    @Lob
    private String content;

    public ItemDescription() { }

    public ItemDescription(Item item, DescriptionType type, String content) {
        this.item = item;
        this.type = type;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public DescriptionType getType() {
        return type;
    }

    public void setType(DescriptionType type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
