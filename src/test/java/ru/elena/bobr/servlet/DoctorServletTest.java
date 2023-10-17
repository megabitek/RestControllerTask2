package ru.elena.bobr.servlet;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.elena.bobr.model.AnotherEntity;
import ru.elena.bobr.model.Doctor;
import ru.elena.bobr.model.SimpleEntity;
import ru.elena.bobr.service.impl.AnotherCrudServiceImpl;
import ru.elena.bobr.service.impl.DoctorService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class DoctorServletTest {

    private final  DoctorServlet servlet = new DoctorServlet();
    private DoctorService service = Mockito.mock(DoctorService.class);

    public final UUID UuidCorrect = UUID.fromString("74ac3d23-6b19-4db4-ad08-ca9469a98e9c");

    public final UUID UuidNotExist = UUID.fromString("75468c6e-1124-47d8-9c20-84833249a9c4");

    public final String NameExist = "ivan";

    public final String LastNameExist = "ivanov";

    public final String NameNew = "Boris";

    public final String LastNameNew = "Fedorov";



    @BeforeEach
    void setUp() {
        servlet.setService(service);
    }


    @Test
    void doGetById() {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        PrintWriter writer = mock(PrintWriter.class);
        try {
            when(response.getWriter()).thenReturn(writer);
            HashMap parameterMap = new HashMap<>();
            parameterMap.put("id", UuidCorrect);
            when(request.getParameterMap()).thenReturn(parameterMap);
            when(request.getParameter("id")).thenReturn(UuidCorrect.toString());
            Doctor entity = new Doctor();
            entity.setName(NameExist);
            entity.setUuid(UUID.randomUUID());
            when(service.findById(UuidCorrect)).thenReturn(entity);
            servlet.doGet(request, response);
            verify(service, times(1)).findById(any(UUID.class));
            verify(response, times(1)).setContentType("application/json");
            verify(writer, times(1)).print(anyString());
        } catch (IOException | ServletException e) {
            Assert.assertTrue(false);
        }
    }
    @Test
    void doGetAll() {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        PrintWriter writer = mock(PrintWriter.class);
        try {
            when(response.getWriter()).thenReturn(writer);
            HashMap parameterMap = new HashMap<>();
            when(request.getParameterMap()).thenReturn(parameterMap);
            servlet.doGet(request, response);
            verify(service, times(1)).findAll();
            verify(response, times(1)).setContentType("application/json");
            verify(writer, times(1)).print(anyString());
        } catch (IOException | ServletException e) {
            Assert.assertTrue(false);
        }
    }

    @Test
    void doPost() {
        try {HttpServletRequest request = mock(HttpServletRequest.class);
            HttpServletResponse response =mock(HttpServletResponse.class);
            PrintWriter writer = mock(PrintWriter.class);
            BufferedReader reader = mock(BufferedReader.class);
            when(request.getReader()).thenReturn(reader);
            when(reader.readLine()).thenReturn("{  name:'boris', lastname : 'fedorov' }", null);
           Doctor entity = new Doctor( UUID.randomUUID(), NameNew, LastNameNew);
            when(service.save(any())).thenReturn(mock(Doctor.class));
            when(response.getWriter()).thenReturn(writer);
            servlet.doPost(request, response);
            verify(service, times(1)).save(any());
        } catch (IOException e) {
            Assert.assertTrue(false);
        }
    }

    @Test
    void doPut(){
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response =mock(HttpServletResponse.class);
        HashMap parameterMap = new HashMap<>();
       /* parameterMap.put("id", UuidCorrect);
        parameterMap.put("name", NameExist);
        parameterMap.put("lastname", LastNameNew);*/
        when(request.getParameterMap()).thenReturn(parameterMap);
        Doctor up= new Doctor();
        up.setName(NameExist);
        up.setUuid(UuidCorrect);
        when(request.getParameter("id")).thenReturn(UuidCorrect.toString());
        when(request.getParameter("ownername")).thenReturn(NameExist);
        when(request.getParameter("lastname")).thenReturn(LastNameNew);
        when(service.update(any())).thenReturn(up);
        PrintWriter writer = mock(PrintWriter.class);
        try {
            when(response.getWriter()).thenReturn(writer);
            servlet.doPut(request, response);
        } catch (IOException e) {
            Assert.assertTrue(false);
        }
        verify(service, times(1)).update(any());
    }
}
