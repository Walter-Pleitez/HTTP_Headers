package outlook.pleitez.apiservlet.webapp.headers.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

@WebServlet("/cabeceras-request")
public class CabecerasHttpRequestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");

        String metodoHttp = req.getMethod();//Devuelve Post, Put, Delete, Get acorde al metodo (aqui get)
        String requestUri = req.getRequestURI(); //Devuelve lo que va despues del puerto y del Localhost
        String requestUrl = req.getRequestURL().toString(); //Devuelve la URL completa
        String contextPath = req.getContextPath(); //Devuelve el nombre del proyecto (o contexto)
        String servletPath = req.getServletPath();//Devuelve solo la ruta del Servlet (obtener de forma dinamica la cabecera Request)

        //CONSTRUCCION DE RUTA URL. Necesitamos: IP de Servidor, Puerto de Servidor Local y el Esquema.
        String ipCliente = req.getRemoteAddr();  //direccion de usuario que visita la aplicacion (IP Cliente)
        String ip = req.getLocalAddr(); //IP del servidor Tomcat
        int puerto = req.getLocalPort();
        String esquema = req.getScheme();
        String host = req.getHeader("host"); //nombre completo
        String url = esquema + "://" + host + contextPath + servletPath;
        String url2 = esquema + "://"+ ip + ":" + puerto + contextPath + servletPath;

        try (PrintWriter out = resp.getWriter()) {

            out.print("<!DOCTYPE html>");
            out.print("<html>");
            out.print("       <head>");
            out.print("                 <meta charset = \"UTF-8\">");
            out.print("                 <title>Cabeceras HTTP</title>");
            out.print("       </head>");
            out.print("       <body>");
            out.print("                 <h1>Cabeceras HTTP Request</h1>");
            out.println("<ul>");
            out.print("<li>Metodo HTTP: " + metodoHttp + "</li>");
            out.print("<li>Request URI: " + requestUri + "</li>");
            out.print("<li>Request URL: " + requestUrl + "</li>");
            out.print("<li>Context Path: " + contextPath + "</li>");
            out.print("<li>Servlet Path: " + servletPath + "</li>");
            out.print("<li>IP Local: " + ip + "</li>");
            out.print("<li>IP Cliente: " + ipCliente + "</li>");
            out.print("<li>Puerto Local: " + puerto + "</li>");
            out.print("<li>Esquema: " + esquema + "</li>");
            out.print("<li>Host: " + host + "</li>");
            out.print("<li>URL: " + url + "</li>");
            out.print("<li>URL2: " + url2 + "</li>");
            Enumeration<String> headerNames = req.getHeaderNames();
            while(headerNames.hasMoreElements()){
                String cabecera = headerNames.nextElement();
                out.print("<li>Cabecera " + cabecera + ": " + req.getHeader(cabecera) + "</li>");
            }
            out.println("</ul>");
            out.print("       </body>");
            out.print("</html>");
        }
    }
}
