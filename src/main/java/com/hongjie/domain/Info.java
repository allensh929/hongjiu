package com.hongjie.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Info.
 */
@Entity
@Table(name = "info")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Info implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "qr_code")
    private String qrCode;

    @Column(name = "wechat_subscribe_code")
    private String wechatSubscribeCode;

    @Column(name = "wechat_service_code")
    private String wechatServiceCode;

    @Column(name = "weibo_url")
    private String weiboUrl;

    @Column(name = "qq_url")
    private String qqUrl;

    @Column(name = "placeholder1")
    private String placeholder1;

    @Column(name = "placeholder2")
    private String placeholder2;
    
    @Column(name = "placeholder3")
    private String placeholder3;
    
    @Column(name = "placeholder4")
    private String placeholder4;
    
    @Column(name = "placeholder5")
    private String placeholder5;

    @Column(name = "placeholder6")
    private String placeholder6;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public String getWechatSubscribeCode() {
        return wechatSubscribeCode;
    }

    public void setWechatSubscribeCode(String wechatSubscribeCode) {
        this.wechatSubscribeCode = wechatSubscribeCode;
    }

    public String getWechatServiceCode() {
        return wechatServiceCode;
    }

    public void setWechatServiceCode(String wechatServiceCode) {
        this.wechatServiceCode = wechatServiceCode;
    }

    public String getWeiboUrl() {
        return weiboUrl;
    }

    public void setWeiboUrl(String weiboUrl) {
        this.weiboUrl = weiboUrl;
    }

    public String getQqUrl() {
        return qqUrl;
    }

    public void setQqUrl(String qqUrl) {
        this.qqUrl = qqUrl;
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
    
    public String getPlaceholder3() {
        return placeholder3;
    }

    public void setPlaceholder3(String placeholder3) {
        this.placeholder3 = placeholder3;
    }
    
    public String getPlaceholder4() {
        return placeholder4;
    }

    public void setPlaceholder4(String placeholder4) {
        this.placeholder4 = placeholder4;
    }
    
    public String getPlaceholder5() {
        return placeholder5;
    }

    public void setPlaceholder5(String placeholder5) {
        this.placeholder5 = placeholder5;
    }
    
    public String getPlaceholder6() {
        return placeholder6;
    }

    public void setPlaceholder6(String placeholder6) {
        this.placeholder6 = placeholder6;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Info info = (Info) o;

        if ( ! Objects.equals(id, info.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Info{" +
            "id=" + id +
            ", qrCode='" + qrCode + "'" +
            ", wechatSubscribeCode='" + wechatSubscribeCode + "'" +
            ", wechatServiceCode='" + wechatServiceCode + "'" +
            ", weiboUrl='" + weiboUrl + "'" +
            ", qqUrl='" + qqUrl + "'" +
            ", placeholder1='" + placeholder1 + "'" +
            ", placeholder2='" + placeholder2 + "'" +
            '}';
    }
}
