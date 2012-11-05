package com.kv.productsearch.web.views;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kv.productsearch.common.model.AbstractEntity;
import com.kv.productsearch.common.utils.DateFormat;
import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: kungwang
 * Date: 9/2/12
 * Time: 5:59 PM
 * To change this template use File | Settings | File Templates.
 */

public abstract class AbstractJsonView extends AbstractView {

    //may be better to set this via spring
    protected Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().serializeNulls().setDateFormat(DateFormat.DATE_FORMAT_PATTERN).create();

    protected void sendHeaders(HttpServletResponse response) {
        response.addHeader("pragma", "no-cache");
        response.addHeader("Cache-control", "no-cache");
        response.addHeader("Cache-control", "no-store");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/plain; charset=utf-8");
    }

    protected void writeResponse(Map model, HttpServletResponse response) throws IOException {
        String jsonString = gson.toJson(model);
        response.getWriter().write(jsonString);
        response.getWriter().close();
    }

    public void render(Map<String, ?> model,
                       HttpServletRequest request,
                       HttpServletResponse response)
            throws Exception {
        List<AbstractEntity> entity = (List<AbstractEntity>) model.get("RESULT");
        String jsonString = gson.toJson(entity);
        response.getWriter().write(jsonString);
        response.getWriter().flush();
        response.getWriter().close();
    }

}
