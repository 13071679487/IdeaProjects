package com.rifu.springbootcrud.component;

import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

/**
 * @Author Rifu
 * @Date 2018/9/16  20:20
 */

@Component
public class CustomErrorAttributes extends DefaultErrorAttributes {
    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
        Map<String, Object> errorAttributes = super.getErrorAttributes(webRequest, includeStackTrace);
        errorAttributes.put("company","com.rifu");

        Map<String,Object> ext = (Map<String, Object>) webRequest.getAttribute("ext",0);   //第二个参数代表的是作用域,0是request
        errorAttributes.put("ext",ext);
        return errorAttributes;
    }


}
