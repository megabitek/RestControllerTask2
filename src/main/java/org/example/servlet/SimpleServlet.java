package org.example.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.example.model.SimpleEntity;
import org.example.service.ISimpleService;
import org.example.service.impl.SimpleServiceImpl;
import org.example.servlet.dto.IncomingDtoSimple;
import org.example.servlet.dto.OutGoingDto;
import org.example.servlet.mapper.SimpleDtoMapperImpl;
import org.example.servlet.mapper.SimpleDtomapper;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;


@WebServlet(name = "SimpleServlet", value = "/simple")
public class SimpleServlet extends HttpServlet {
    private final ISimpleService service = new SimpleServiceImpl();

    private final SimpleDtomapper dtoMapper=new SimpleDtoMapperImpl();
    private Gson gson = new Gson();

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

     protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<SimpleEntity> entityList;
        if (req.getParameterMap().size()>0) {
            UUID id = UUID.fromString(req.getParameter("id"));
            OutGoingDto map  =  dtoMapper.map(service.findById(id));
            sendAsJson(resp, map);
        } else{
            List<OutGoingDto> listDto = dtoMapper.mapListOut(service.findAll());
              sendAsJson(resp, listDto);
            }

 }



/**add new element
 * fetch(
 *   '/simple',
 *   {
 *     method: 'POST',
 *     headers: { 'Content-Type': 'application/json' },
 *     body: JSON.stringify({  owner:'dasha' })
 *   }
 * ).then(result=>result.json())*/
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader reader = req.getReader();
        StringBuilder buffer = new StringBuilder();

        String line;
        while ((line = reader.readLine()) != null) {
            buffer.append(line);}
        String payload = buffer.toString();
        IncomingDtoSimple dto = gson.fromJson(payload, IncomingDtoSimple.class);

        SimpleEntity entity = dtoMapper.map(dto);

        SimpleEntity saved = service.save(entity);

        OutGoingDto outDto = dtoMapper.map(saved);

        sendAsJson(resp, outDto);

    }
/**
 * fetch('/simple?'+new URLSearchParams({
 *           id: '2382018b-7959-4bc0-b821-2188f6307ffb', owner: 'Kolya'}),
 *           { method: 'PUT' }).then(result => console.log(result))*/
    @Override
protected  void doPut(HttpServletRequest request,
    HttpServletResponse response)
            throws IOException, ServletException {
        UUID uuid = UUID.fromString(request.getParameter("id"));
        IncomingDtoSimple in = new IncomingDtoSimple();
        in.setId(UUID.fromString(request.getParameter("id")));
        in.setOwner(request.getParameter("owner"));
        OutGoingDto outDto=dtoMapper.map((SimpleEntity) service.update(dtoMapper.map(in)));
        sendAsJson(response, outDto);
    }

    /***fetch('/simple?'+new URLSearchParams({id: '2382018b-7959-4bc0-b821-2188f6307ffb'}),
     * { method: 'DELETE' })
     * .then(result => console.log(result))
     */
    @Override
    protected void doDelete(HttpServletRequest request,
                            HttpServletResponse response){
    UUID uuid = UUID.fromString(request.getParameter("id"));
    service.delete(uuid);
    }
}
