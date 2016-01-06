package com.hongjie.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A BrandStory.
 */
@Entity
@Table(name = "brand_story")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class BrandStory implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "title")
    private String title;

    @Column(name = "slide1")
    private String slide1;

    @Column(name = "slide2")
    private String slide2;

    @Column(name = "slide3")
    private String slide3;

    @Column(name = "detail_info")
    private String detailInfo;

    @Column(name = "active")
    private Boolean active;

    @Column(name = "order_tag")
    private String orderTag;

    @Column(name = "placeholder1")
    private String placeholder1;

    @Column(name = "placeholder2")
    private String placeholder2;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSlide1() {
        return slide1;
    }

    public void setSlide1(String slide1) {
        this.slide1 = slide1;
    }

    public String getSlide2() {
        return slide2;
    }

    public void setSlide2(String slide2) {
        this.slide2 = slide2;
    }

    public String getSlide3() {
        return slide3;
    }

    public void setSlide3(String slide3) {
        this.slide3 = slide3;
    }

    public String getDetailInfo() {
        return detailInfo;
    }

    public void setDetailInfo(String detailInfo) {
        this.detailInfo = detailInfo;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getOrderTag() {
        return orderTag;
    }

    public void setOrderTag(String orderTag) {
        this.orderTag = orderTag;
    }

    public String getPlaceholder1() {
        return placeholder1;
    }

    public void setPlaceholder1(String placeholder1) {
        this.placeholder1 = placeholder1;
    }

    public String getPlaceholder2() {
        return placeholder2;
    }

    public void setPlaceholder2(String placeholder2) {
        this.placeholder2 = placeholder2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        BrandStory brandStory = (BrandStory) o;

        if ( ! Objects.equals(id, brandStory.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "BrandStory{" +
            "id=" + id +
            ", name='" + name + "'" +
            ", title='" + title + "'" +
            ", slide1='" + slide1 + "'" +
            ", slide2='" + slide2 + "'" +
            ", slide3='" + slide3 + "'" +
            ", detailInfo='" + detailInfo + "'" +
            ", active='" + active + "'" +
            ", orderTag='" + orderTag + "'" +
            ", placeholder1='" + placeholder1 + "'" +
            ", placeholder2='" + placeholder2 + "'" +
            '}';
    }
}
