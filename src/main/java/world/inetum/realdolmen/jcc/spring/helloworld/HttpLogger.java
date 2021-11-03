package world.inetum.realdolmen.jcc.spring.helloworld;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HttpLogger implements HandlerInterceptor {

    Logger logger = Logger.getLogger(HttpLogger.class.getName());

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        logger.log(Level.INFO, "REQ: {0}", request.getRequestURI());
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        logger.log(Level.INFO, "RES: {0}", response.getStatus());
    }
}
