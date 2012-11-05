package com.kv.productsearch.web.views;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;
import com.kv.productsearch.common.model.AbstractEntity;
import com.kv.productsearch.common.utils.AppContext;
import com.kv.productsearch.common.utils.KVConstant;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.StringWriter;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: kungwang
 * Date: 9/3/12
 * Time: 7:31 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractTsvView extends AbstractView {

    protected static Logger logger = Logger.getLogger(AbstractTsvView.class);

    protected abstract String[] getStringArray(AbstractEntity entity);

    // openCSVFile(csvFileName, seperator)
    public void render(Map<String, ?> model,
                       HttpServletRequest request,
                       HttpServletResponse response)
            throws Exception {
        CSVWriter csvWriter = new CSVWriter(response.getWriter(), '\t');
        List<AbstractEntity> entities = (List<AbstractEntity>) model.get("RESULT");
        for (AbstractEntity entity : entities) {
            String[] stringArray = getStringArray(entity);
            csvWriter.writeNext(stringArray);
        }
        csvWriter.close();
        response.getWriter().flush();
        response.getWriter().close();
    }

}
