package com.kv.productsearch.common.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created with IntelliJ IDEA.
 * User: kungwang
 * Date: 9/2/12
 * Time: 9:53 PM
 * To change this template use File | Settings | File Templates.
 */
public class AppContext {

    private static ApplicationContext ctx = new ClassPathXmlApplicationContext("productsearch-common-config.xml");

    /**
     * Injected from the class "ApplicationContextProvider" which is automatically
     * loaded during Spring-Initialization.
     */
    public static void setApplicationContext(ApplicationContext applicationContext) {
        ctx = applicationContext;
    }

    /**
     * Get access to the Spring ApplicationContext from everywhere in your Application.
     *
     * @return
     */
    public static ApplicationContext getApplicationContext() {
        return ctx;
    }

}
