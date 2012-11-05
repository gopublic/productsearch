package com.kv.productsearch.common.model.impl;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.reflect.TypeToken;
import com.kv.productsearch.common.model.AbstractEntity;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.lang.reflect.Type;

/**
 * Created with IntelliJ IDEA.
 * User: kungwang
 * Date: 9/2/12
 * Time: 8:49 PM
 * To change this template use File | Settings | File Templates.
 */
@Document(collection="product")
@CompoundIndexes({
    @CompoundIndex(name = "etailer_sku_idx", def = "{'sku': 1, 'etailer_name': 1}")
})
public class KVProduct extends AbstractEntity {

    public static final String ETAILER_NAME = "etailer_name";
    public static final String SKU = "sku";
    public static final String PRODUCT_NAME = "product_name";
    public static final String MANUFACTURER = "manufacturer";
    public static final String BRAND = "brand";
    public static final String PRODUCT_URL = "product_url";
    public static final String IMAGE_URL = "image_url";
    public static final String PRICE = "price";
    public static final String ORIGINAL_PRICE = "original_price";

    @Expose
    @Field("etailer_name")
    String etailerName;

    @Expose
    @Indexed
    @Field("sku")
    String sku;

    @Expose
    @Field("product_name")
    String productName;

    @Expose
    @Indexed
    @Field("manufacturer")
    String manufacturer;

    @Expose
    @Indexed
    @Field("brand")
    String brand;

    @Expose
    @Field("product_url")
    String productUrl;

    @Expose
    @Field("image_url")
    String imageUrl;

    @Expose
    @Field("price")
    Float price;

    @Expose
    @Field("original_price")
    Float originalPrice;

    public String getEtailerName() {
        return etailerName;
    }

    public void setEtailerName(String etailerName) {
        this.etailerName = etailerName;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getProductUrl() {
        return productUrl;
    }

    public void setProductUrl(String productUrl) {
        this.productUrl = productUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Float getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(Float originalPrice) {
        this.originalPrice = originalPrice;
    }

    /*
    public static final String ETAILER_NAME = "etailer_name";
    public static final String SKU = "sku";
    public static final String PRODUCT_NAME = "product_name";
    public static final String MANUFACTURER = "manufacturer";
    public static final String BRAND = "brand";
    public static final String PRODUCT_URL = "product_url";
    public static final String IMAGE_URL = "image_url";
    public static final String PRICE = "price";
    public static final String ORIGINAL_PRICE = "original_price";
     */
    @Override
    public String[] toStringArray() {
        String[] saCsv = new String[9];
//        saCsv[0] =  (getId() == null ? "" : String.valueOf(getId()));
//        saCsv[1] =  (getCreatedDate() == null ? "" : String.valueOf(getCreatedDate()));
//        saCsv[2] =  (getUpdatedDate() == null ? "" : String.valueOf(getUpdatedDate()));
        saCsv[0] =  (getEtailerName() == null ? "" : getEtailerName());
        saCsv[1] =  (getSku() == null ? "" : getSku());
        saCsv[2] =  (getProductName() == null ? "" : getProductName());
        saCsv[3] =  (getManufacturer() == null ? "" : getManufacturer());
        saCsv[4] =  (getBrand() == null ? "" : getBrand());
        saCsv[5] =  (getProductUrl() == null ? "" : getProductUrl());
        saCsv[6] =  (getImageUrl() == null ? "" : getImageUrl());
        saCsv[7] =  (getPrice() == null ? "" : String.valueOf(getPrice()));
        saCsv[8] =  (getOriginalPrice() == null ? "" : String.valueOf(getOriginalPrice()));
        return saCsv;
    }

    @Override
    public String toString() {
        Gson gson = new Gson();
        Type myType = new TypeToken<KVProduct>() {}.getType();
        return gson.toJson(this, myType);
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }
}
