package com.kv.productsearch.service;

import com.kv.productsearch.common.model.AbstractEntity;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: kungwang
 * Date: 9/2/12
 * Time: 5:38 PM
 * To change this template use File | Settings | File Templates.
 */
public interface GenericSearchService<T extends AbstractEntity> {

    public List<T> searchProduct(String queryString, Integer startAt, Integer rows);

}
