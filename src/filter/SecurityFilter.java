package filter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SecurityFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Inizializzazione del filtro, se necessario
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String uri = httpRequest.getRequestURI();
        
        // Controlla se l'URI contiene i file sensibili
        if (uri.contains("web.xml") || uri.contains("context.xml")) {
            // Reindirizza a una pagina di errore o un'altra pagina specifica
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/access-denied.html");
        } else {
            // Continua la catena di filtri
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
        // Cleanup, se necessario
    }
}
