package com.kv.productsearch.common.utils;

import org.apache.lucene.util.Version;

/**
 * Created with IntelliJ IDEA.
 * User: kungwang
 * Date: 9/2/12
 * Time: 9:07 PM
 * To change this template use File | Settings | File Templates.
 */
public class KVConstant {

    public Version luceneVersion;
    public String kvProductTSVFileName;
    public String kvProductIndexFileName;
    public String defaultSearchField;
    public Integer defaultHitsPerPage;

    public String getKvProductTSVFileName() {
        return kvProductTSVFileName;
    }

    public void setKvProductTSVFileName(String kvProductTSVFileName) {
        this.kvProductTSVFileName = kvProductTSVFileName;
    }

    public String getKvProductIndexFileName() {
        return kvProductIndexFileName;
    }

    public void setKvProductIndexFileName(String kvProductIndexFileName) {
        this.kvProductIndexFileName = kvProductIndexFileName;
    }

    public Version getLuceneVersion() {
        return luceneVersion;
    }

    public void setLuceneVersion(Version luceneVersion) {
        this.luceneVersion = luceneVersion;
    }

    public void setLuceneVersion(String luceneVersion) {
        this.luceneVersion = Version.valueOf(luceneVersion);
    }

    public String getDefaultSearchField() {
        return defaultSearchField;
    }

    public void setDefaultSearchField(String defaultSearchField) {
        this.defaultSearchField = defaultSearchField;
    }

    public Integer getDefaultHitsPerPage() {
        return defaultHitsPerPage;
    }

    public void setDefaultHitsPerPage(Integer defaultHitsPerPage) {
        this.defaultHitsPerPage = defaultHitsPerPage;
    }
}
