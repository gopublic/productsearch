package com.kv.productsearch.web.views.impl;

import com.google.gson.Gson;
import com.kv.productsearch.web.views.AbstractJsonView;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: kungwang
 * Date: 9/2/12
 * Time: 10:10 PM
 * To change this template use File | Settings | File Templates.
 */
public class KVProductSearchJsonErrorView extends AbstractJsonView {

    static Logger logger = Logger.getLogger(KVProductSearchJsonErrorView.class);

    protected void renderMergedOutputModel(Map model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        sendHeaders(response);
        response.setStatus(500);
        Map<String, String> errorMap = new LinkedHashMap<String, String>();
        Exception exception = (Exception) model.get("exception");
        logger.warn("error was found", exception);
        errorMap.put("exception", exception.getMessage());

        gson = new Gson();
        writeResponse(errorMap, response);
    }

}
