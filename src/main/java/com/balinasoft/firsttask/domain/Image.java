package com.balinasoft.firsttask.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "image")
@Setter
@Getter
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column
    private String url;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @Column
    double lat;

    @Column
    double lng;

    @ManyToOne
    private User user;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "image_category", joinColumns = @JoinColumn(name = "image_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories = new HashSet<>();

    public void addCategory(Category category){
        categories.add(category);
        category.getImages().add(this);
    }
}
