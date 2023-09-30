package ru.elena.bobr.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ru.elena.bobr.model.SimpleEntity;
import ru.elena.bobr.service.impl.SimpleServiceImpl;
import ru.elena.bobr.servlet.dto.IncomingDtoSimple;
import ru.elena.bobr.servlet.dto.OutGoingDto;
import ru.elena.bobr.servlet.mapper.SimpleDtoMapperImpl;
import ru.elena.bobr.servlet.mapper.SimpleDtomapper;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;


@WebServlet(name = "SimpleServlet", value = "/simple")
public class SimpleServlet extends HttpServlet {
    private  SimpleServiceImpl service = new SimpleServiceImpl();
    final SimpleDtomapper dtoMapper = new SimpleDtoMapperImpl();
    private Gson gson = new Gson();

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

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            if (req.getParameterMap().size() > 0) {
                UUID id = UUID.fromString(req.getParameter("id"));
                OutGoingDto map = dtoMapper.map(service.findById(id));

                sendAsJson(resp, map);

                resp.setStatus(200);
            } else {
                List<OutGoingDto> listDto = dtoMapper.mapListOut(service.findAll());
                sendAsJson(resp, listDto);
                resp.setStatus(200);
            }
        } catch (IOException e) {
            resp.setStatus(500);
        }

    }


    /**
     * add new element
     * fetch(
     * '/simple',
     * {
     * method: 'POST',
     * headers: { 'Content-Type': 'application/json' },
     * body: JSON.stringify({  owner:'dasha' })
     * }
     * ).then(result=>result.json())
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try {
            BufferedReader reader = req.getReader();

            StringBuilder buffer = new StringBuilder();

            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            String payload = buffer.toString();
            IncomingDtoSimple dto = gson.fromJson(payload, IncomingDtoSimple.class);
            SimpleEntity entity = dtoMapper.map(dto);
            SimpleEntity saved = service.save(entity);
            OutGoingDto outDto = dtoMapper.map(saved);
            resp.setStatus(200);
            sendAsJson(resp, outDto);
        } catch (IOException e) {
            resp.setStatus(500);
        }
    }

    /**
     * fetch('/simple?'+new URLSearchParams({
     * id: '2382018b-7959-4bc0-b821-2188f6307ffb', owner: 'Kolya'}),
     * { method: 'PUT' }).then(result => console.log(result))
     */
    @Override
    protected void doPut(HttpServletRequest request,
                         HttpServletResponse response) {
        IncomingDtoSimple in = new IncomingDtoSimple();
        in.setId(UUID.fromString(request.getParameter("id")));
        in.setOwner(request.getParameter("owner"));
        OutGoingDto outDto = dtoMapper.map((SimpleEntity) service.update(dtoMapper.map(in)));
        response.setStatus(200);
        try {
            sendAsJson(response, outDto);
        } catch (IOException e) {
            response.setStatus(500);
        }
    }

    /***fetch('/simple?'+new URLSearchParams({id: '2382018b-7959-4bc0-b821-2188f6307ffb'}),
     * { method: 'DELETE' })
     * .then(result => console.log(result))
     */
    @Override
    protected void doDelete(HttpServletRequest request,
                            HttpServletResponse response) {
        UUID uuid = UUID.fromString(request.getParameter("id"));
        service.delete(uuid);
    }
}
