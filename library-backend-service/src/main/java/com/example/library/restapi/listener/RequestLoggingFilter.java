package com.example.library.restapi.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.UUID;

@Component
@Slf4j
public class RequestLoggingFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpReq = ((HttpServletRequest)request);
        String uri = httpReq.getRequestURI();
        if (isStatic(uri)) {
            chain.doFilter(request, response);
            return;
        }

        long start = System.currentTimeMillis();
        UUID uuid = UUID.randomUUID();
        String userName = httpReq.getRemoteUser();
        String requestIdentifier = "[" + userName + "]" + "[" + uuid + "]";

        log.info(String.format("%s start", requestIdentifier));
        log.info(String.format("%s URI: %s", requestIdentifier, uri));

        Map<String, String[]> params = httpReq.getParameterMap();
        for (Map.Entry<String, String[]> param : params.entrySet()) {
            log.info(String.format("%s PARAM_KEY: %s, PARAM_VALUE: %s"
                    , requestIdentifier
                    , param.getKey()
                    , Arrays.toString(param.getValue())));
        }

        chain.doFilter(request, response);

        int status = ((HttpServletResponse)response).getStatus();

        log.info(String.format("%s end in %d millisec. STATUS %d", requestIdentifier, System.currentTimeMillis() - start, status));
    }

    private boolean isStatic(String uri) {
        return uri.contains("/js/")
                || uri.contains("/css/")
                || uri.contains("/fonts/");
    }

    @Override
    public void destroy() {
    }
}
