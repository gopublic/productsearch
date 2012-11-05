package com.kv.productsearch.service.impl;

import com.kv.productsearch.common.model.impl.KVProduct;
import com.kv.productsearch.common.searcher.impl.KVProductSearcherImpl;
import com.kv.productsearch.service.AbstractSearchService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: kungwang
 * Date: 9/2/12
 * Time: 6:35 PM
 * To change this template use File | Settings | File Templates.
 */
public class KVProductSearchServiceImpl extends AbstractSearchService<KVProduct> implements KVProductSearchService {

    @Autowired
    KVProductSearcherImpl productIndexSearcher;

    public List<KVProduct> searchProduct(String query, Integer startAt, Integer rows) {
        return productIndexSearcher.search(query, startAt, rows);
    }

}
