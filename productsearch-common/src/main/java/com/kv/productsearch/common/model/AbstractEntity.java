package com.kv.productsearch.common.model;

import au.com.bytecode.opencsv.CSVWriter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.data.annotation.Id;
import com.google.gson.annotations.Expose;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.io.StringWriter;
import java.lang.reflect.Type;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: kungwang
 * Date: 9/2/12
 * Time: 8:45 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractEntity implements Serializable {

    public static final String ID = "id";

    @Id
    @Expose
    Long id;

    @Expose
    @Field("created_date")
    Date createdDate;

    @Expose
    @Field("updated_date")
    Date updatedDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String[] toStringArray() {
        String[] saCsv = new String[3];
        saCsv[0] =  (getId() == null ? "" : String.valueOf(getId()));
        saCsv[1] =  (getCreatedDate() == null ? "" : String.valueOf(getCreatedDate()));
        saCsv[2] =  (getUpdatedDate() == null ? "" : String.valueOf(getUpdatedDate()));
        return saCsv;
    }

    @Override
    public String toString() {
        Gson gson = new Gson();
        Type myType = new TypeToken<AbstractEntity>() {}.getType();
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
