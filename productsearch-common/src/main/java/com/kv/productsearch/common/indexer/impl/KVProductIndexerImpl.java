package com.kv.productsearch.common.indexer.impl;

import au.com.bytecode.opencsv.CSVReader;
import com.google.gson.Gson;
import com.kv.productsearch.common.indexer.AbstractIndexer;
import com.kv.productsearch.common.model.AbstractEntity;
import com.kv.productsearch.common.model.impl.KVProduct;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;

/**
 * Created with IntelliJ IDEA.
 * User: kungwang
 * Date: 9/2/12
 * Time: 8:37 PM
 * To change this template use File | Settings | File Templates.
 */
public class KVProductIndexerImpl extends AbstractIndexer<KVProduct> implements KVProductIndexer {

    private Gson gson = new Gson();

    protected Document getDocument(KVProduct entity) {
        Document doc = null;
        String keyword = (entity.getEtailerName() == null ? "" : entity.getEtailerName()) + " "
                + (entity.getSku() == null ? "" : entity.getSku()) + " "
                + (entity.getProductName() == null ? "" : entity.getProductName()) + " "
                + (entity.getBrand() == null ? "" : entity.getBrand());
        if (! keyword.trim().isEmpty()) {
            doc = new Document();
            //doc.add(new Field(AbstractEntity.ID, entity.getId().toString(), Field.Store.YES, Field.Index.ANALYZED));
            doc.add(new Field(this.kvConstant.getDefaultSearchField(), keyword.trim(), Field.Store.YES, Field.Index.ANALYZED));
            if (!(entity.getEtailerName() == null || entity.getEtailerName().trim().isEmpty())) {
                doc.add(new Field(KVProduct.ETAILER_NAME, entity.getEtailerName(), Field.Store.YES, Field.Index.ANALYZED));
            }
            if (!(entity.getSku() == null || entity.getSku().trim().isEmpty())) {
                doc.add(new Field(KVProduct.SKU, entity.getSku(), Field.Store.YES, Field.Index.ANALYZED));
            }
            if (!(entity.getProductName() == null || entity.getProductName().trim().isEmpty())) {
                doc.add(new Field(KVProduct.PRODUCT_NAME, entity.getProductName(), Field.Store.YES, Field.Index.ANALYZED));
            }
            if (!(entity.getManufacturer() == null || entity.getManufacturer().trim().isEmpty())) {
                doc.add(new Field(KVProduct.MANUFACTURER, entity.getManufacturer(), Field.Store.YES, Field.Index.ANALYZED));
            }
            if (!(entity.getBrand() == null || entity.getBrand().trim().isEmpty())) {
                doc.add(new Field(KVProduct.BRAND, entity.getBrand(), Field.Store.YES, Field.Index.ANALYZED));
            }
            if (!(entity.getProductUrl() == null || entity.getProductUrl().trim().isEmpty())) {
                doc.add(new Field(KVProduct.PRODUCT_URL, entity.getProductUrl(), Field.Store.YES, Field.Index.ANALYZED));
            }
            if (!(entity.getImageUrl() == null || entity.getImageUrl().trim().isEmpty())) {
                doc.add(new Field(KVProduct.IMAGE_URL, entity.getImageUrl(), Field.Store.YES, Field.Index.ANALYZED));
            }
            if (!(entity.getPrice() == null || entity.getPrice().toString().trim().isEmpty())) {
                doc.add(new Field(KVProduct.PRICE, entity.getPrice().toString(), Field.Store.YES, Field.Index.ANALYZED));
            }
            if (!(entity.getOriginalPrice() == null || entity.getOriginalPrice().toString().trim().isEmpty())) {
                doc.add(new Field(KVProduct.ORIGINAL_PRICE, entity.getOriginalPrice().toString(), Field.Store.YES, Field.Index.ANALYZED));
            }
        }
        return doc;
    }

    protected Document getDocument(String[] line) {
        // 0: etailer_name
        // 1: sku
        // 2: product_name
        // 3: manufacturer
        // 4: brand
        // 5: product_url
        // 6: image_url
        // 7: price
        // 8: original_price
        Document doc = null;
        String keyword = (line[0] == null ? "" : line[0]) + " "
                            + (line[1] == null ? "" : line[1]) + " "
                            + (line[2] == null ? "" : line[2]) + " "
                            + (line[3] == null ? "" : line[3]);
        if (! keyword.trim().isEmpty()) {
            int lineLength = line.length;
            doc = new Document();
            doc.add(new Field(this.kvConstant.getDefaultSearchField(), keyword.trim(), Field.Store.YES, Field.Index.ANALYZED));
            if (lineLength > 0 && !(line[0] == null || line[0].trim().isEmpty())) {
                doc.add(new Field("etailer_name", line[0], Field.Store.YES, Field.Index.NOT_ANALYZED_NO_NORMS));
            }
            if (lineLength > 1 && !(line[1] == null || line[1].trim().isEmpty())) {
                doc.add(new Field("sku", line[1], Field.Store.YES, Field.Index.NOT_ANALYZED_NO_NORMS));
            }
            if (lineLength > 2 && !(line[2] == null || line[2].trim().isEmpty())) {
                doc.add(new Field("product_name", line[2], Field.Store.YES, Field.Index.NOT_ANALYZED_NO_NORMS));
            }
            if (lineLength > 3 && !(line[3] == null || line[3].trim().isEmpty())) {
                doc.add(new Field("manufacturer", line[3], Field.Store.YES, Field.Index.NOT_ANALYZED_NO_NORMS));
            }
            if (lineLength > 4 && !(line[4] == null || line[4].trim().isEmpty())) {
                doc.add(new Field("brand", line[4], Field.Store.YES, Field.Index.NOT_ANALYZED_NO_NORMS));
            }
            if (lineLength > 5 && !(line[5] == null || line[5].trim().isEmpty())) {
                doc.add(new Field("product_url", line[5], Field.Store.YES, Field.Index.NOT_ANALYZED_NO_NORMS));
            }
            if (lineLength > 6 && !(line[6] == null || line[6].trim().isEmpty())) {
                doc.add(new Field("image_url", line[6], Field.Store.YES, Field.Index.NOT_ANALYZED_NO_NORMS));
            }
            if (lineLength > 7 && !(line[7] == null || line[7].trim().isEmpty())) {
                doc.add(new Field("price", line[7], Field.Store.YES, Field.Index.NOT_ANALYZED_NO_NORMS));
            }
            if (lineLength > 8 && !(line[8] == null || line[8].trim().isEmpty())) {
                doc.add(new Field("original_price", line[8], Field.Store.YES, Field.Index.NOT_ANALYZED_NO_NORMS));
            }
        }
        return doc;
    }

    public static void main (String[] args) {
        KVProductIndexerImpl kvProductIndexer = new KVProductIndexerImpl();
        if (kvProductIndexer.kvConstant == null) {
            logger.warn("kvProductIndexer.kvConstant is null.");
        } else {
            String tsvFile = kvProductIndexer.kvConstant.getKvProductTSVFileName();
            String indexFile = kvProductIndexer.kvConstant.getKvProductIndexFileName();
            if (args.length >= 1) {
                tsvFile = args[0];
            }
            if (args.length >= 2) {
                indexFile = args[1];
            }
            System.out.println("TSV File  : " + tsvFile);
            System.out.println("Index File: " + indexFile);
            System.out.println("Lucene Version: " + kvProductIndexer.kvConstant.getLuceneVersion().toString());
            if (kvProductIndexer.createIndexFromCSV(tsvFile, '\t', indexFile)) {
                System.out.println("index file: " + indexFile + " had successfully created.");
            } else {

            }
        }
    }

}
