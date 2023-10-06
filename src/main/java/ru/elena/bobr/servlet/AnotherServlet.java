package ru.elena.bobr.servlet;

import com.google.gson.Gson;
import ru.elena.bobr.model.AnotherEntity;

import ru.elena.bobr.service.impl.AnotherCrudServiceImpl;

import ru.elena.bobr.servlet.dto.IncomingDtoAnother;

import ru.elena.bobr.servlet.dto.OutGoingDtoAnother;

import ru.elena.bobr.servlet.mapper.AnotherDtoMapperImpl;
import ru.elena.bobr.servlet.mapper.IDtoMapper;

import ru.elena.bobr.utils.JsonSender;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static javax.servlet.RequestDispatcher.*;


@WebServlet(name = "AnotherServlet", value = "/pet")
public class AnotherServlet extends HttpServlet {

    private   AnotherCrudServiceImpl service = new AnotherCrudServiceImpl();



    private static final Gson gson = new Gson();

    final IDtoMapper<AnotherEntity, IncomingDtoAnother, OutGoingDtoAnother> dtoMapper = new AnotherDtoMapperImpl();
    private static final   JsonSender sender = new JsonSender();

    public void setService(AnotherCrudServiceImpl service) {
        this.service = service;
    }

    /**
     * http://localhost:8080/doctor?id=74ac3d23-6b19-4db4-ad08-ca9469a98e9c
     *
     * http://localhost:8080/doctor
     * **/


@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

            if (request.getParameterMap().size() > 0) {
                UUID id = UUID.fromString(request.getParameter("id"));
                var anotherEntity = service.findById(id);
                if (anotherEntity != null ){
                OutGoingDtoAnother outDto = dtoMapper.mapOut(anotherEntity);
                    try {
                        sender.send(response, outDto);
                    } catch (IOException e) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    }
                }
                else {
                    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                  //  processError(request, response);
                   // throw new ServletException("entity UUID is not found!");
                }
            } else {
                var list = service.findAll();
                List<OutGoingDtoAnother> listDto = dtoMapper.mapListOut(list);
                try {
                    sender.send(response, listDto);
                } catch (IOException e) {
                    response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Creating failed " + e.getMessage());
                }

            }
    }
 /**
  * fetch(
  *       '/pet',
  *      {
  *       method: 'POST',
  *       headers: { 'Content-Type': 'application/json' },
  *       body: JSON.stringify({  nickname:'dasha', ownerId:'7c2aeb33-d95d-4359-94c7-fe23044580d2' })
  *      }).
  *      then(result=>result.json())
  * */

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            BufferedReader reader = request.getReader();

            StringBuilder buffer = new StringBuilder();

            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            String payload = buffer.toString();
            IncomingDtoAnother dto = gson.fromJson(payload, IncomingDtoAnother.class);
           AnotherEntity entity = dtoMapper.mapIn(dto);
            AnotherEntity saved = service.save(entity);
            OutGoingDtoAnother outDto = dtoMapper.mapOut(saved);
            sender.send(response, outDto);
        } catch (IOException e) {
            try {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Creating failed " + e.getMessage());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

        }
    }



/**   fetch('/pet?'+new URLSearchParams({
        id: '661e11ee-0793-470e-aed1-95dd04a79b6c', nickname: 'vasjka', ownerid: '11111111-6666-3333-4444-555555555555'}),
    { method: 'PUT' }).then(result => console.log(result))*/
    @Override
    protected void doPut(HttpServletRequest request,
                         HttpServletResponse response)  {
        IncomingDtoAnother in = new IncomingDtoAnother();
        in.setId(UUID.fromString(request.getParameter("id")));
        in.setNiсkname( request.getParameter("nickname"));
        in.setOwner(UUID.fromString(request.getParameter("ownerid")));
        OutGoingDtoAnother outDto = dtoMapper.mapOut(service.update(dtoMapper.mapIn(in)));
        try {
            sender.send(response, outDto);
        } catch (IOException e) {

        }

    }

/**такогоо id нет, подставить существующий в базе
//    fetch('/pet?'+new URLSearchParams({id: '9ac11aa0-3333-4444-5555-2d8588ac488e'}),
//    { method: 'DELETE' })
//            .then(result => console.log(result))*/
    @Override
    protected void doDelete(HttpServletRequest request,
                            HttpServletResponse response) throws IOException, ServletException {
        UUID uuid = UUID.fromString(request.getParameter("id"));
        Optional<AnotherEntity> deleted = service.delete(uuid);
        deleted.ifPresent(entity -> dtoMapper.mapOut(entity));
        deleted.orElseThrow(()->new ServletException("deleting not completed successfully!"));
    }

    private void processError(HttpServletRequest request,
                HttpServletResponse response) throws IOException {
            // Analyze the servlet exception

            try (PrintWriter writer = response.getWriter()) {
                writer.write("<html><head><title>Error description</title></head><body>");
                writer.write("<h2>Error description</h2>");
                writer.write("<ul>");
                Arrays.asList(
                                ERROR_STATUS_CODE,
                                ERROR_EXCEPTION_TYPE,
                                ERROR_MESSAGE)
                        .forEach(e ->
                                writer.write("<li>" + e + ":" + request.getAttribute(e) + " </li>")
                        );
                writer.write("</ul>");
                writer.write("</html></body>");
            };

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
