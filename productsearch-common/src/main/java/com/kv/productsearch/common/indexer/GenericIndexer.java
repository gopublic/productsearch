package com.kv.productsearch.common.indexer;

import com.kv.productsearch.common.model.AbstractEntity;
import org.apache.lucene.document.Document;

import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: kungwang
 * Date: 9/2/12
 * Time: 8:35 PM
 * To change this template use File | Settings | File Templates.
 */
public interface GenericIndexer<T extends AbstractEntity> {

    public Boolean createIndexFromCSV(String csvFileName, Character seperator, String indexFileName);

}
