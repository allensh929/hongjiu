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

    public String getWeboUrl() {
        return weiboUrl;
    }

    public void setWeboUrl(String weboUrl) {
        this.weiboUrl = weboUrl;
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
            ", weboUrl='" + weiboUrl + "'" +
            ", qqUrl='" + qqUrl + "'" +
            ", placeholder1='" + placeholder1 + "'" +
            ", placeholder2='" + placeholder2 + "'" +
            '}';
    }
}
