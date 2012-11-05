package com.kv.productsearch.common.searcher;

import com.google.gson.Gson;
import com.kv.productsearch.common.model.AbstractEntity;
import com.kv.productsearch.common.utils.AppContext;
import com.kv.productsearch.common.utils.KVConstant;
import org.apache.log4j.Logger;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopScoreDocCollector;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.SimpleFSDirectory;
import org.apache.lucene.util.Version;
import org.springframework.context.ApplicationContext;
import org.w3c.dom.DocumentType;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: kungwang
 * Date: 9/3/12
 * Time: 11:04 AM
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractSearcher<T extends AbstractEntity> implements GenericSearcher<T> {

    protected static Logger logger = Logger.getLogger(AbstractSearcher.class);

    protected KVConstant kvConstant;

    protected IndexSearcher searcher = null;

    protected Gson gson = new Gson();

    protected AbstractSearcher() {
        ApplicationContext ctx = AppContext.getApplicationContext();
        if (ctx == null) {
            logger.warn("Application context is null.");
        } else {
            kvConstant = (KVConstant) ctx.getBean("kvConstant");
        }
    }

    protected abstract Analyzer getAnalyzerForReader(Version version);

    protected abstract Query getQuery(String query);

    protected abstract T getEntity(Document doc);

    protected abstract IndexSearcher getSearcher();

    public List<T> search(String query, Integer startAt, Integer rows) {
        ArrayList<T> result = new ArrayList<T>();
        try {
            Query q = getQuery(query);
            IndexSearcher searcher = getSearcher();
            TopScoreDocCollector collector = TopScoreDocCollector.create(this.kvConstant.getDefaultHitsPerPage(), true);
            searcher.search(q, collector);
            ScoreDoc[] hits = collector.topDocs().scoreDocs;
            System.out.println("Found " + hits.length + " hits.");
            Integer endAt = startAt + rows;
            for(int i = 0; i < hits.length; i++) {
                if (i >= startAt) {
                    int docId = hits[i].doc;
                    Document doc = searcher.doc(docId);
                    T entity = getEntity(doc);
                    result.add(entity);
                }
                if (i >= endAt) {
                    break;
                }
            }
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return result;
    }

}
