package ru.elena.bobr.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ru.elena.bobr.model.SimpleEntity;
import ru.elena.bobr.service.impl.SimpleServiceImpl;
import ru.elena.bobr.servlet.dto.IncomingDtoSimple;
import ru.elena.bobr.servlet.dto.OutGoingDtoSimple;
import ru.elena.bobr.servlet.mapper.IDtoMapper;
import ru.elena.bobr.servlet.mapper.SimpleDtoMapperImpl;
import com.google.gson.Gson;
import ru.elena.bobr.utils.JsonSender;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@WebServlet(name = "SimpleServlet", value = "/owner")
public class SimpleServlet extends HttpServlet {
    private  SimpleServiceImpl service = new SimpleServiceImpl();
    private Gson gson = new Gson();

    final IDtoMapper<SimpleEntity, IncomingDtoSimple, OutGoingDtoSimple > dtoMapper = new SimpleDtoMapperImpl();
    private JsonSender sender = new JsonSender();

    public void setService(SimpleServiceImpl service) {
        this.service = service;
    }




    private void sendAsJson(
            HttpServletResponse response,
            Object dto) throws IOException {

        response.setContentType("application/json");

        String res = gson.toJson(dto);

        PrintWriter out = response.getWriter();

        out.print(res);
        out.flush();
    }

    @Override
    /**Find one
     * fetch('/simple?'+ new URLSearchParams({
     *     id: '2382018b-7959-4bc0-b821-2188f6307ffb'})).then(response=>response.json())*
     *
     * Find all
     *    fetch('/simple').then(response => response.json().then(console.log))
     *
     **/

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            if (request.getParameterMap().size() > 0) {
                UUID id = UUID.fromString(request.getParameter("id"));
                var simpleEntity = service.findById(id);
                OutGoingDtoSimple outDto = dtoMapper.mapOut(simpleEntity);
                sender.send(response, outDto);
            } else {
                var list = service.findAll();
                List<OutGoingDtoSimple> listDto = dtoMapper.mapListOut(list);
                sender.send(response, listDto);
            }
        } catch (IOException e) {
processError(request,response);
        }

    }


    /**
     * add new element
     * fetch(
     * '/simple',
     * {
     * method: 'POST',
     * headers: { 'Content-Type': 'application/json' },
     * body: JSON.stringify({  ownername:'dasha' })
     * }
     * ).then(result=>result.json())
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            BufferedReader reader = request.getReader();

            StringBuilder buffer = new StringBuilder();

            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            String payload = buffer.toString();
            IncomingDtoSimple dto = gson.fromJson(payload, IncomingDtoSimple.class);
            SimpleEntity entity = dtoMapper.mapIn(dto);
            SimpleEntity saved = service.save(entity);
            OutGoingDtoSimple outDto = dtoMapper.mapOut(saved);
            sender.send(response, outDto);
        } catch (IOException e) {
            processError(request,response);

    }
    }

    /**
     * fetch('/simple?'+new URLSearchParams({
     * id: '2382018b-7959-4bc0-b821-2188f6307ffb', ownername: 'Kolya'}),
     * { method: 'PUT' }).then(result => console.log(result))
     */
    @Override
    protected void doPut(HttpServletRequest request,
                         HttpServletResponse response) throws IOException {
        IncomingDtoSimple in = new IncomingDtoSimple();
        in.setId(UUID.fromString(request.getParameter("id")));
        in.setOwnerName(request.getParameter("ownername"));
        OutGoingDtoSimple outDto = dtoMapper.mapOut(service.update(dtoMapper.mapIn(in)));
        response.setStatus(200);
        sender.send(response, outDto);

    }

    /***fetch('/simple?'+new URLSearchParams({id: '2382018b-7959-4bc0-b821-2188f6307ffb'}),
     * { method: 'DELETE' })
     * .then(result => console.log(result))
     */
    @Override
    protected void doDelete(HttpServletRequest request,
                            HttpServletResponse response) throws IOException {
        UUID uuid = UUID.fromString(request.getParameter("id"));
       Optional<SimpleEntity>  deleted = service.delete(uuid);
        deleted.ifPresent(entity -> dtoMapper.mapOut(entity));
        if(deleted.isEmpty()) processError(request, response);
      //  deleted.orElseGet(processError(request,response));
       // OutGoingDtoSimple outDto = dtoMapper.mapOut(service.delete(uuid));

         //   sender.send(response, outDto);

    }

    private void processError(HttpServletRequest request,
                              HttpServletResponse response) throws IOException {
        // Analyze the servlet exception
      /*  Throwable throwable = (Throwable) request
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
}*/}}

