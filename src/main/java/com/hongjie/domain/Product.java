package com.hongjie.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * A Product.
 */
@Entity
@Table(name = "product")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "number")
    private String number;

    @Column(name = "name")
    private String name;

    @Column(name = "price", precision=10, scale=2)
    private BigDecimal price;

    @Column(name = "produce_date")
    private LocalDate produceDate;

    @Column(name = "producer")
    private String producer;

    @Column(name = "image")
    private String image;

    @Column(name = "favorate")
    private String favorate;

    @Column(name = "news")
    private String news;

    @Column(name = "title")
    private String title;
    
    @Column(name = "tag")
    private String tag;
    
    @Column(name = "full_name")
    private String fullName;
    
    @Column(name = "image1")
    private String image1;
    
    @Column(name = "award1")
    private String award1;
    
    @Column(name = "award2")
    private String award2;
    
    @Column(name = "award3")
    private String award3;
    
    @Column(name = "description_title")
    private String descriptionTitle;
    
    @Column(name = "description")
    private String description;
    
    @Column(name = "detail_info")
    private String detailInfo;
    
    @Column(name = "jdurl")
    private String jdurl;
    
    @OneToMany(mappedBy = "product", cascade={CascadeType.REMOVE})
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Xref> xrefs = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDate getProduceDate() {
        return produceDate;
    }

    public void setProduceDate(LocalDate produceDate) {
        this.produceDate = produceDate;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getFavorate() {
        return favorate;
    }

    public void setFavorate(String favorate) {
        this.favorate = favorate;
    }

    public String getNews() {
        return news;
    }

    public void setNews(String news) {
        this.news = news;
    }

    public Set<Xref> getXrefs() {
        return xrefs;
    }

    public void setXrefs(Set<Xref> xrefs) {
        this.xrefs = xrefs;
    }

    public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getImage1() {
		return image1;
	}

	public void setImage1(String image1) {
		this.image1 = image1;
	}

	public String getAward1() {
		return award1;
	}

	public void setAward1(String award1) {
		this.award1 = award1;
	}

	public String getAward2() {
		return award2;
	}

	public void setAward2(String award2) {
		this.award2 = award2;
	}

	public String getAward3() {
		return award3;
	}

	public void setAward3(String award3) {
		this.award3 = award3;
	}

	public String getDescriptionTitle() {
		return descriptionTitle;
	}

	public void setDescriptionTitle(String descriptionTitle) {
		this.descriptionTitle = descriptionTitle;
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

	public String getJdurl() {
		return jdurl;
	}

	public void setJdurl(String jdurl) {
		this.jdurl = jdurl;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Product product = (Product) o;

        if ( ! Objects.equals(id, product.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Product{" +
            "id=" + id +
            ", number='" + number + "'" +
            ", name='" + name + "'" +
            ", price='" + price + "'" +
            ", produceDate='" + produceDate + "'" +
            ", producer='" + producer + "'" +
            ", image='" + image + "'" +
            ", favorate='" + favorate + "'" +
            ", news='" + news + "'" +
            '}';
    }
}
