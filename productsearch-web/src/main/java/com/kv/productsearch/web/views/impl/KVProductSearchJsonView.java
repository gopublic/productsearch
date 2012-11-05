package com.kv.productsearch.web.views.impl;

import com.kv.productsearch.web.views.AbstractJsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: kungwang
 * Date: 9/3/12
 * Time: 8:27 PM
 * To change this template use File | Settings | File Templates.
 */
public class KVProductSearchJsonView extends AbstractJsonView {

    /**
     * Default content type. Overridable as bean property.
     */
    public static final String DEFAULT_CONTENT_TYPE = "application/json";

    @Override
    public String getContentType() {
        return DEFAULT_CONTENT_TYPE;
    }

    @Override
    protected void renderMergedOutputModel(Map<String, Object> stringObjectMap
                                         , HttpServletRequest httpServletRequest
                                         , HttpServletResponse httpServletResponse) throws Exception {
        httpServletResponse.setContentType(DEFAULT_CONTENT_TYPE);
        if("GET".equals(httpServletRequest.getMethod().toUpperCase())) {
            @SuppressWarnings("unchecked")
            Map<String, String[]> params = httpServletRequest.getParameterMap();
            super.render(stringObjectMap, httpServletRequest, httpServletResponse);
        }
        else {
            super.render(stringObjectMap, httpServletRequest, httpServletResponse);
        }
    }
}
