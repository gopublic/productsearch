package com.kv.productsearch.common.searcher.impl;

import com.kv.productsearch.common.model.AbstractEntity;
import com.kv.productsearch.common.model.impl.KVProduct;
import com.kv.productsearch.common.searcher.AbstractSearcher;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.SimpleFSDirectory;
import org.apache.lucene.util.Version;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: kungwang
 * Date: 9/3/12
 * Time: 11:06 AM
 * To change this template use File | Settings | File Templates.
 */
public class KVProductSearcherImpl extends AbstractSearcher<KVProduct> implements KVProductSearcher {

    protected String indexFileName = null;

    public String getIndexFileName() {
        return indexFileName;
    }

    public void setIndexFileName(String indexFileName) {
        this.indexFileName = indexFileName;
    }

    protected Analyzer getAnalyzerForReader(Version version) {
        return new StandardAnalyzer(version);
    }

    protected Query getQuery(String query) {
        Analyzer analyzer = getAnalyzerForReader(this.kvConstant.getLuceneVersion());
        Query q = null;
        if (analyzer != null) {
            try {
                q = new QueryParser(this.kvConstant.getLuceneVersion(), this.kvConstant.getDefaultSearchField(), analyzer).parse(query);
            } catch (ParseException e) {
                logger.error(e.getMessage());
            }
        }
        return q;
    }

    protected Directory getIndexDirectory(String indexFileName) {
        Directory indexDirectory = null;
        File f = new File(indexFileName);
        if (f.exists()) {
            try {
                indexDirectory = new SimpleFSDirectory(f);
            } catch (IOException e) {
                logger.error(e.getMessage());
            }
        }
        return indexDirectory;
    }

    protected IndexSearcher getSearcher() {
        if (this.searcher == null) {
            if (this.getIndexFileName() == null) {
                this.setIndexFileName(this.kvConstant.getKvProductIndexFileName());
            }
            if (this.getIndexFileName() != null) {
                Directory dir = getIndexDirectory(this.getIndexFileName());
                try {
                    IndexReader reader = IndexReader.open(dir);
                    this.searcher = new IndexSearcher(reader);
                } catch(CorruptIndexException e) {
                    logger.error(e.getMessage());
                } catch (IOException e) {
                    logger.error(e.getMessage());
                }
            }
        }
        return this.searcher;
    }

    protected KVProduct getEntity(Document doc) {
        KVProduct p = new KVProduct();
        //p.setId(Long.valueOf(doc.get(AbstractEntity.ID)));
        p.setEtailerName(doc.get(KVProduct.ETAILER_NAME));
        p.setSku(doc.get(KVProduct.SKU));
        p.setProductName(doc.get(KVProduct.PRODUCT_NAME));
        p.setManufacturer(doc.get(KVProduct.MANUFACTURER));
        p.setBrand(doc.get(KVProduct.BRAND));
        p.setProductUrl(doc.get(KVProduct.PRODUCT_URL));
        p.setImageUrl(doc.get(KVProduct.IMAGE_URL));
        String price = doc.get(KVProduct.PRICE);
        String originalPrice = doc.get(KVProduct.ORIGINAL_PRICE);
        if (price != null) {
            try {
                p.setPrice(Float.valueOf(price));
            } catch (NumberFormatException e) {
                logger.warn(e.getMessage());
            }
        }
        if (originalPrice != null) {
            try {
                p.setOriginalPrice(Float.valueOf(originalPrice));
            } catch (NumberFormatException e) {
                logger.warn(e.getMessage());
            }
        }
        return p;
    }

    public static void main (String[] args) {
        KVProductSearcherImpl kvProductSearcher = new KVProductSearcherImpl();
        if (kvProductSearcher.kvConstant == null) {
            logger.warn("kvProductIndexer.kvConstant is null.");
        } else {
            String query = "";
            String indexFile = kvProductSearcher.kvConstant.getKvProductIndexFileName();
            if (args.length >= 1) {
                query = args[0];
            }
            if (args.length >= 2) {
                indexFile = args[1];
                kvProductSearcher.setIndexFileName(indexFile);
            }
            System.out.println("Index File: " + indexFile);
            System.out.println("Lucene Version: " + kvProductSearcher.kvConstant.getLuceneVersion().toString());
            System.out.println("Query: " + query);
            List<KVProduct> ap = kvProductSearcher.search(query, 0, 20);
            for (KVProduct p : ap) {
                String json = kvProductSearcher.gson.toJson(p);
                System.out.println(json);
            }
        }
    }
}
