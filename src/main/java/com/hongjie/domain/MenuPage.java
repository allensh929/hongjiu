package com.hongjie.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A MenuPage.
 */
@Entity
@Table(name = "menu_page")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MenuPage implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "url")
    private String url;
    
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

    public String getOrderTag() {
        return orderTag;
    }

    public void setOrderTag(String orderTag) {
        this.orderTag = orderTag;
    }
    
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MenuPage menuPage = (MenuPage) o;

        if ( ! Objects.equals(id, menuPage.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "MenuPage{" +
            "id=" + id +
            ", name='" + name + "'" +
            ", url='" + url + "'" +
            ", detailInfo='" + detailInfo + "'" +
            ", active='" + active + "'" +
            '}';
    }
}
