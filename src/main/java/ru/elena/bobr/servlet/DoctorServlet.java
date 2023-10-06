package ru.elena.bobr.servlet;

import com.google.gson.Gson;
import ru.elena.bobr.model.AnotherEntity;
import ru.elena.bobr.model.Doctor;
import ru.elena.bobr.model.SimpleEntity;
import ru.elena.bobr.service.impl.AnotherCrudServiceImpl;
import ru.elena.bobr.service.impl.DoctorService;
import ru.elena.bobr.servlet.dto.*;
import ru.elena.bobr.servlet.mapper.AnotherDtoMapperImpl;
import ru.elena.bobr.servlet.mapper.DoctorDtoMapper;
import ru.elena.bobr.servlet.mapper.IDtoMapper;
import ru.elena.bobr.utils.JsonSender;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@WebServlet(name = "DoctorServlet", value = "/doctor")
public class DoctorServlet extends HttpServlet {
    private DoctorService service = new DoctorService();

    final IDtoMapper<Doctor, IncomingDtoDoctor, OutGoingDtoDoctor> dtoMapper = new DoctorDtoMapper();


    private Gson gson = new Gson();
    private JsonSender sender = new JsonSender();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        if (request.getParameterMap().size() > 0) {
            UUID id = UUID.fromString(request.getParameter("id"));
            var Doctor = service.findById(id);
            if (Doctor != null ){
                OutGoingDtoDoctor outDto = dtoMapper.mapOut(Doctor);
                sender.send(response, outDto);
            }
            else {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);

                // throw new ServletException("entity UUID is not found!");
            }
        } else {
            var list = service.findAll();
            List<OutGoingDtoDoctor> listDto = dtoMapper.mapListOut(list);
            sender.send(response, listDto);
            //        processError(request, response);
        }
    }
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
            IncomingDtoDoctor dto = gson.fromJson(payload, IncomingDtoDoctor.class);
            Doctor entity = dtoMapper.mapIn(dto);
            Doctor saved = service.save(entity);
            OutGoingDtoDoctor outDto = dtoMapper.mapOut(saved);
            sender.send(response, outDto);
        } catch (IOException e) {
           response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
/**
 * fetch('/simple?'+new URLSearchParams({id: '75468c6e-1124-47d8-9c20-84833249a9c4'}),
 *      * { method: 'DELETE' })
 *      * .then(result => console.log(result))
 *
 * **/
    @Override
    protected void doDelete(HttpServletRequest request,
                            HttpServletResponse response) throws IOException {
        UUID uuid = UUID.fromString(request.getParameter("id"));
        Optional<Doctor> deleted = service.delete(uuid);
        deleted.ifPresent(entity -> dtoMapper.mapOut(entity));
        if(deleted.isEmpty())
        response.sendError(500, "deleted not complited");
            ///processError(request, response);
        //  deleted.orElseGet(processError(request,response));
        // OutGoingDtoSimple outDto = dtoMapper.mapOut(service.delete(uuid));

           sender.send(response,deleted.isPresent() );

    }
    /**fetch('/doctor?'+new URLSearchParams({
        id: '75468c6e-1124-47d8-9c20-84833249a9c4', name: 'Kolya', lastname: 'bobrovski'}),
    { method: 'PUT' }).then(result => console.log(result))*/


    @Override
    protected void doPut(HttpServletRequest request,
                         HttpServletResponse response) throws IOException {
        IncomingDtoDoctor in = new IncomingDtoDoctor();
        in.setId(UUID.fromString(request.getParameter("id")));
        in.setName(request.getParameter("name"));
        in.setLastname(request.getParameter("lastname"));
        OutGoingDtoDoctor outDto = dtoMapper.mapOut(service.update(dtoMapper.mapIn(in)));
        response.setStatus(200);
        sender.send(response, outDto);
    }


    public void setService(DoctorService service) {
        this.service= service;
    }
}
