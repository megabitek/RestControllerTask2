package org.example.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.example.model.SimpleEntity;
import org.example.service.SimpleService;
import org.example.service.impl.SimpleServiceImpl;
import org.example.servlet.dto.IncomingDto;
import org.example.servlet.dto.OutGoingDto;
import org.example.servlet.mapper.SimpleDtomapper;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@WebServlet(name = "SimpleServlet", value = "/simple")
public class SimpleServlet extends HttpServlet {
    private SimpleService service = new SimpleServiceImpl();
    private SimpleDtomapper dtomapper;
    private Gson gson = new Gson();

    private void sendAsJson(
            HttpServletResponse response,
            Object obj) throws IOException {

        response.setContentType("application/json");

        String res = gson.toJson(obj);

        PrintWriter out = response.getWriter();

        out.print(res);
        out.flush();
    }

    @Override
    /**Find one
     * fetch('/simple?'+ new URLSearchParams({
     *     uuid: '2382018b-7959-4bc0-b821-2188f6307ffb'})).then(response=>response.json())*
     *
     * Find all
     *    fetch('/simple').then(response => response.json().then(console.log))
     *
     **/

     protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<SimpleEntity> entityList = new ArrayList<>();
        if (req.getParameterMap().size()>0) {
            UUID uuid = UUID.fromString(req.getParameter("uuid"));
            SimpleEntity byId = service.findById(uuid);
            sendAsJson(resp, byId);
        } else{
            entityList = service.findAll();
            sendAsJson(resp, entityList);
 }}


/**add new element
 * fetch(
 *   '/simple',
 *   {
 *     method: 'POST',
 *     headers: { 'Content-Type': 'application/json' },
 *     body: JSON.stringify({  uuid:'2382018b-7959-4bc0-b821-2188f6307ffb',
 *    name:'dasha' })
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
        SimpleEntity entity = gson.fromJson(payload, SimpleEntity.class);

       // SimpleEntity simpleEntity = dtomapper.map(new IncomingDto());
        SimpleEntity saved = service.save(entity);
        sendAsJson(resp, saved);
        //OutGoingDto map = dtomapper.map(saved);
        // return our DTO, not necessary
    }

    @Override
protected  void doPut(HttpServletRequest request,
    HttpServletResponse response)
            throws IOException, ServletException {

    }}
