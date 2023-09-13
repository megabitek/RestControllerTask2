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

import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;


@WebServlet(name = "SimpleServlet", value = "/simple")
public class SimpleServlet extends HttpServlet {
    private SimpleService service=new SimpleServiceImpl();
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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       /* resp.setContentType("text/html");
        PrintWriter printWriter = resp.getWriter();
        printWriter.write("Hello!");
        printWriter.close();
        http://localhost:8080/simple?uuid=2382018b-7959-4bc0-b821-2188f6307ffb*/
        UUID uuid = UUID.fromString(req.getParameter("uuid"));
        SimpleEntity byId = service.findById(uuid);
        sendAsJson(resp, byId);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SimpleEntity simpleEntity = dtomapper.map(new IncomingDto());
        SimpleEntity saved = service.save(simpleEntity);
        OutGoingDto map = dtomapper.map(saved);
        // return our DTO, not necessary
    }
}
