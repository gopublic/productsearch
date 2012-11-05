package com.kv.productsearch.web.views.impl;

import com.kv.productsearch.common.model.AbstractEntity;
import com.kv.productsearch.common.model.impl.KVProduct;
import com.kv.productsearch.web.views.AbstractTsvView;
import com.kv.productsearch.web.views.AbstractXmlView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: kungwang
 * Date: 9/3/12
 * Time: 8:38 PM
 * To change this template use File | Settings | File Templates.
 */
public class KVProductSearchTsvView extends AbstractTsvView {

    /**
     * Default content type. Overridable as bean property.
     */
    public static final String DEFAULT_CONTENT_TYPE = "text/plain";

    @Override
    public String getContentType() {
        return DEFAULT_CONTENT_TYPE;
    }

    @Override
    protected String[] getStringArray(AbstractEntity entity) {
        return ((KVProduct) entity).toStringArray();
    }

    @Override
    protected void renderMergedOutputModel(Map<String, Object> stringObjectMap
            , HttpServletRequest httpServletRequest
            , HttpServletResponse httpServletResponse) throws Exception {
        httpServletResponse.setContentType(DEFAULT_CONTENT_TYPE);
        if("GET".equals(httpServletRequest.getMethod().toUpperCase())) {
            super.render(stringObjectMap, httpServletRequest, httpServletResponse);
        } else {
            super.render(stringObjectMap, httpServletRequest, httpServletResponse);
        }
    }

}
