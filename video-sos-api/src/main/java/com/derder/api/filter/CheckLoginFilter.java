package com.derder.admin.filter;

import com.derder.common.Constants;
import com.derder.common.exception.ApecRuntimeException;
import com.derder.common.redis.CacheService;
import com.derder.common.util.ErrorCode;
import com.derder.common.util.JsonUtil;
import com.derder.common.util.ResultData;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by zhaolei  on 2016/12/18.
 */
@Component
public class CheckLoginFilter implements Filter {
    private static final Log LOG = LogFactory.getLog(CheckLoginFilter.class);

    @Autowired
    private CacheService cacheService;

    @Value("${loginUrl}")
    private String loginUrl;

    @Value("${registerUrl}")
    private String registerUrl;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String url = request.getRequestURI();
        boolean isPass = true;
        if (url.endsWith(loginUrl) || url.endsWith(registerUrl)) {

        } else {
            isPass = appLogTimeOut(request, response);
        }
        if (isPass) {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }

    private boolean appLogTimeOut(HttpServletRequest request, HttpServletResponse response) {
        String token = request.getHeader(Constants.TOKEN);
        String tokenKey = Constants.CACHE_PREFIX_TOKEN + token;
        Long userId = (Long) cacheService.get(tokenKey);
        return handleSessionInfo(response, userId, request);
    }

    /**
     * 设置session超时信息
     *
     * @param response 响应信息
     * @param userId   用户ID
     * @param request  request 当前请求信息
     */
    private boolean handleSessionInfo(HttpServletResponse response, Long userId, HttpServletRequest request) {
        ResultData<Object> resultData = new ResultData<>();
        if (null == userId || userId == 0) {
            try {
                PrintWriter out = response.getWriter();
                resultData.setErrorCode(ErrorCode.NO_LOGIN_EXCEPTION);
                resultData.setErrorMsg("session超时");
                resultData.setSucceed(false);
                out.print(JsonUtil.toJSONString(resultData));
                out.close();

            } catch (IOException e) {
                LOG.error("sessionTimeOutInterceptor.webLogTimeOut PrintWriter exception case:" + e.getMessage());
                throw new ApecRuntimeException("sessionTimeOutInterceptor.webLogTimeOut PrintWriter exception case:",
                        e);
            }
            return false;
        } else {
            request.setAttribute(Constants.USER_ID, userId);
            return true;
        }
    }
}
