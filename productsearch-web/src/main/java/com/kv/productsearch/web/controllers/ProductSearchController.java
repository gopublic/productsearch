package com.kv.productsearch.web.controllers;

import com.kv.productsearch.common.model.AbstractEntity;
import com.kv.productsearch.common.model.impl.KVProduct;
import com.kv.productsearch.service.impl.KVProductSearchServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: kungwang
 * Date: 9/2/12
 * Time: 6:01 PM
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class ProductSearchController {

    public static final String QUERY_STRING = "q";
    public static final String START_AT = "s";
    public static final String ROWS_TO_RETURN = "r";

    static Logger logger = Logger.getLogger(ProductSearchController.class);

    @Autowired
    KVProductSearchServiceImpl productSearchService;

    //@RequestMapping(value="/search", method = RequestMethod.GET)
    @RequestMapping(value="/search")
    public Map searchProduct(
                @RequestParam(value = QUERY_STRING, required = true) String query
             ,  @RequestParam(value = START_AT, required = false) Integer startAt
             ,  @RequestParam(value = ROWS_TO_RETURN, required = false) Integer rows
             , ModelMap modelMap
             , HttpServletRequest httpServletRequest) throws Exception {
        String q = (query == null ? "" : query);
        Integer s = (startAt == null ? 0 : startAt);
        Integer r = (rows == null ? 20 : rows);
        List<KVProduct> p;
        p = productSearchService.searchProduct(q, s, r);
        if (p == null) {
            p = new ArrayList<KVProduct>();
        }
        modelMap.put("RESULT", p);
        return modelMap;
    }

}
