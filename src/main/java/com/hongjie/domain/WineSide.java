package com.hongjie.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A WineSide.
 */
@Entity
@Table(name = "wine_side")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class WineSide implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "title")
    private String title;

    @NotNull
    @Column(name = "price", precision=10, scale=2, nullable = false)
    private BigDecimal price;

    @Column(name = "image")
    private String image;

    @Column(name = "description")
    private String description;

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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

        WineSide wineSide = (WineSide) o;

        if ( ! Objects.equals(id, wineSide.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "WineSide{" +
            "id=" + id +
            ", name='" + name + "'" +
            ", title='" + title + "'" +
            ", price='" + price + "'" +
            ", image='" + image + "'" +
            ", description='" + description + "'" +
            ", detailInfo='" + detailInfo + "'" +
            ", active='" + active + "'" +
            ", orderTag='" + orderTag + "'" +
            ", placeholder1='" + placeholder1 + "'" +
            ", placeholder2='" + placeholder2 + "'" +
            '}';
    }
}
