package com.kv.productsearch.common.indexer;

import au.com.bytecode.opencsv.CSVReader;
import com.kv.productsearch.common.model.AbstractEntity;
import com.kv.productsearch.common.utils.AppContext;
import com.kv.productsearch.common.utils.KVConstant;
import org.apache.log4j.Logger;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.NIOFSDirectory;
import org.apache.lucene.store.SimpleFSDirectory;
import org.apache.lucene.util.Version;
import org.springframework.context.ApplicationContext;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;


/**
 * Created with IntelliJ IDEA.
 * User: kungwang
 * Date: 9/2/12
 * Time: 8:35 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractIndexer<T extends AbstractEntity> implements GenericIndexer<T> {

    protected static Logger logger = Logger.getLogger(AbstractIndexer.class);

    protected KVConstant kvConstant;

    protected AbstractIndexer() {
        ApplicationContext ctx = AppContext.getApplicationContext();
        if (ctx == null) {
            logger.warn("Application context is null.");
        } else {
            kvConstant = (KVConstant) ctx.getBean("kvConstant");
        }
    }

    protected abstract Document getDocument(T entity);

    protected abstract Document getDocument(String[] line);

    protected CSVReader openCSVFile(String fileName, Character seperator) {
        CSVReader reader = null;
        File file = new File(fileName);
        Boolean exists = file.exists();
        if (exists) {
            try {
                reader = new CSVReader(new FileReader(fileName), seperator);
            } catch (IOException e) {
                logger.error(e.getMessage());
            }
        } else {
            logger.warn("CSV file " + fileName + " does not exist.");
        }
        return reader;
    }

    protected void closeCSVReader(CSVReader reader) {
        if (reader != null) {
            try {
                reader.close();
            } catch (IOException e) {
                logger.error(e.getMessage());
            }
        }
    }

    protected Analyzer getAnalyzerForWriter(Version version) {
        return new StandardAnalyzer(version);
    }

    protected Directory getIndexDirectory(String indexFileName, IndexWriterConfig.OpenMode openMode) {
        Directory indexDirectory = null;
        File f = new File(indexFileName);
        if (f.exists()) {
            if (openMode.equals(IndexWriterConfig.OpenMode.CREATE)) {
                f.delete();
            }
        }
        try {
            indexDirectory = new SimpleFSDirectory(f);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return indexDirectory;
    }

    protected IndexWriter openIndexWriter(String indexFileName, IndexWriterConfig.OpenMode openMode) {
        Analyzer analyzer = getAnalyzerForWriter(this.kvConstant.getLuceneVersion());
        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(this.kvConstant.getLuceneVersion(), analyzer);
        Directory directory = getIndexDirectory(indexFileName, openMode);
        IndexWriter indexWriter = null;
        if (directory != null) {
            indexWriterConfig.setOpenMode(openMode);
            try {
                indexWriter = new IndexWriter(directory, indexWriterConfig);
            } catch (IOException e) {
                logger.error(e.getMessage());
            }
        }
        return indexWriter;
    }

    public Boolean createIndexFromCSV(String csvFileName, Character seperator, String indexFileName) {
        Boolean successful = Boolean.FALSE;
        CSVReader csvReader = openCSVFile(csvFileName, seperator);
        IndexWriter indexWriter = openIndexWriter(indexFileName, IndexWriterConfig.OpenMode.CREATE_OR_APPEND);
        String [] nextLine;
        if (csvReader != null && indexWriter != null) {
            try {
                while ((nextLine = csvReader.readNext()) != null) {
                    Document doc = getDocument(nextLine);
                    if (doc != null) {
                        indexWriter.addDocument(doc);
                    }
                }
                indexWriter.commit();
                indexWriter.close();
                csvReader.close();
                successful = Boolean.TRUE;
            } catch (IOException e) {
                logger.error(e.getMessage());
            }
        } else {
            if (csvReader == null) {
                logger.error("csvReader open failed.");
            }
            if (indexWriter == null) {
                logger.error("indexWriter open failed.");
            }
        }
        closeCSVReader(csvReader);
        return successful;
    }
}
