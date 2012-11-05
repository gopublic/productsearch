package com.kv.productsearch.web.views;

import com.kv.productsearch.common.model.AbstractEntity;
import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.beans.XMLEncoder;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: kungwang
 * Date: 9/3/12
 * Time: 7:30 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractXmlView extends AbstractView {

    // openCSVFile(csvFileName, seperator)
    public void render(Map<String, ?> model,
                       HttpServletRequest request,
                       HttpServletResponse response)
            throws Exception {

        List<AbstractEntity> entities = (List<AbstractEntity>) model.get("RESULT");

        if (entities == null) {
            logger.error("RESULT field not found");
        }
        else {
            PrintWriter writer = null;
            try {
                writer = response.getWriter();
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                XMLEncoder encoder = new XMLEncoder(byteArrayOutputStream);
                encoder.writeObject(entities);
                encoder.close();
                writer.write(byteArrayOutputStream.toString());
            } finally {
                if (writer != null) {
                    writer.flush();
                    writer.close();
                }
            }
        }

    }

}
