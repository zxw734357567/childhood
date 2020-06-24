package com.zxw.childhood.passport.common.filter;

import com.zxw.childhood.passport.common.contants.PassportConstants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zxw
 * @date 2020-06-24 16:04
 */
public class PassportServerContextFilter extends GenericFilterBean {
    private String serviceParamName;

    private String targetParamName;

    public void setServiceParamName(String serviceParamName) {
        this.serviceParamName = serviceParamName;
    }

    public void setTargetParamName(String targetParamName) {
        this.targetParamName = targetParamName;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String service = request.getParameter(getServiceParamName());
        String target  = request.getParameter(getTargetParamName());
        if(!StringUtils.isEmpty(service)){
            request.setAttribute(getServiceParamName(),service);
        }
        if(!StringUtils.isEmpty(target)){
            request.setAttribute(getTargetParamName(),target);
        }
        chain.doFilter(request, response);
    }


    protected String getServiceParamName() {
        return !StringUtils.isEmpty(serviceParamName) ? serviceParamName : PassportConstants.DEFAULT_SERVICE_PARAM;
    }

    protected String getTargetParamName() {
        return !StringUtils.isEmpty(targetParamName) ? targetParamName : PassportConstants.DEFAULT_TARGET_PARAM;
    }
}
