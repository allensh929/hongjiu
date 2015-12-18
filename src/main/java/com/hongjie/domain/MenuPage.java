package com.hongjie.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

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

    @Column(name = "page_id")
    private Integer pageId = new Integer(0);

    @Column(name = "url")
    private String url;

    @Column(name = "detail_info")
    private String detailInfo;

    @Column(name = "active")
    private Boolean active;

    @OneToMany(mappedBy = "menuPage")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Slide> slides = new HashSet<>();

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

    public Integer getPageId() {
        return pageId;
    }

    public void setPageId(Integer pageId) {
        this.pageId = pageId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    public Set<Slide> getSlides() {
        return slides;
    }

    public void setSlides(Set<Slide> slides) {
        this.slides = slides;
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
            ", pageId='" + pageId + "'" +
            ", url='" + url + "'" +
            ", detailInfo='" + detailInfo + "'" +
            ", active='" + active + "'" +
            '}';
    }
}
