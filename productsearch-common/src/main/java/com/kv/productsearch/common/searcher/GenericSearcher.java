package com.kv.productsearch.common.searcher;

import com.kv.productsearch.common.model.AbstractEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: kungwang
 * Date: 9/3/12
 * Time: 11:02 AM
 * To change this template use File | Settings | File Templates.
 */
public interface GenericSearcher<T extends AbstractEntity> {

    public List<T> search(String query, Integer startAt, Integer rows);

}
