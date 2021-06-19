package distribuidas.backend.errors;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;

@Component
public class ErrorResolver extends AbstractHandlerExceptionResolver {

    @Override
    protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
            Exception ex) {
                try {
                        return handleError(ex, response);
                } catch (Exception handlerException) {
                    logger.warn("Handling of [" + ex.getClass().getName() + "] resulted in Exception", handlerException);
                }
                return null;
    }

    private ModelAndView handleError(Exception ex, HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_BAD_REQUEST, ex.getMessage());
        return new ModelAndView();
    }
}
