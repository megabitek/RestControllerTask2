package ru.elena.bobr.servlet;

import com.google.gson.Gson;
import ru.elena.bobr.model.AnotherEntity;
import ru.elena.bobr.model.SimpleEntity;
import ru.elena.bobr.service.impl.AnotherCrudServiceImpl;
import ru.elena.bobr.service.impl.SimpleServiceImpl;
import ru.elena.bobr.servlet.dto.IncomingDtoAnother;
import ru.elena.bobr.servlet.dto.IncomingDtoSimple;
import ru.elena.bobr.servlet.dto.OutGoingDtoAnother;
import ru.elena.bobr.servlet.dto.OutGoingDtoSimple;
import ru.elena.bobr.servlet.mapper.AnotherDtoMapperImpl;
import ru.elena.bobr.servlet.mapper.IDtoMapper;
import ru.elena.bobr.servlet.mapper.SimpleDtoMapperImpl;
import ru.elena.bobr.utils.JsonSender;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;

@WebServlet(name = "AnotherServlet", value = "/pet")
public class AnotherServlet extends HttpServlet {

    private AnotherCrudServiceImpl service = new AnotherCrudServiceImpl();



    private Gson gson = new Gson();

    final IDtoMapper<AnotherEntity, IncomingDtoAnother, OutGoingDtoAnother> dtoMapper = new AnotherDtoMapperImpl();
    private JsonSender sender = new JsonSender();

    public void setService(AnotherCrudServiceImpl service) {
        this.service = service;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

            if (request.getParameterMap().size() > 0) {
                UUID id = UUID.fromString(request.getParameter("id"));
                var anotherEntity = service.findById(id);
                OutGoingDtoAnother outDto = dtoMapper.mapOut(anotherEntity);
                sender.send(response, outDto);
              //  processError(request, response);

            } else {
                var list = service.findAll();
                List<OutGoingDtoAnother> listDto = dtoMapper.mapListOut(list);
                sender.send(response, listDto);
        //        processError(request, response);
            }
    }

        private void processError(HttpServletRequest request,
                HttpServletResponse response) throws IOException {
            // Analyze the servlet exception
            Throwable throwable = (Throwable) request
                    .getAttribute("javax.servlet.error.exception");
            Integer statusCode = (Integer) request
                    .getAttribute("javax.servlet.error.status_code");
            String servletName = (String) request
                    .getAttribute("javax.servlet.error.servlet_name");
            if (servletName == null) {
                servletName = "Unknown";
            }
            String requestUri = (String) request
                    .getAttribute("javax.servlet.error.request_uri");
            if (requestUri == null) {
                requestUri = "Unknown";
            }

            // Set response content type
            response.setContentType("text/html");

            PrintWriter out = response.getWriter();
            out.write("<html><head><title>Exception/Error Details</title></head><body>");
            if(statusCode != 500){
                out.write("<h3>Error Details</h3>");
                out.write("<strong>Status Code</strong>:"+statusCode+"<br>");
                out.write("<strong>Requested URI</strong>:"+requestUri);
            }else{
                out.write("<h3>Exception Details</h3>");
                out.write("<ul><li>Servlet Name:"+servletName+"</li>");
                out.write("<li>Exception Name:"+throwable.getClass().getName()+"</li>");
                out.write("<li>Requested URI:"+requestUri+"</li>");
                out.write("<li>Exception Message:"+throwable.getMessage()+"</li>");
                out.write("</ul>");
            }

            out.write("<br><br>");
            out.write("<a href=\"index.html\">Home Page</a>");
            out.write("</body></html>");
        }
}
