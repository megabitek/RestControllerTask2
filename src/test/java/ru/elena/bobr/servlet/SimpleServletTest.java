package ru.elena.bobr.servlet;

import ru.elena.bobr.model.SimpleEntity;
import ru.elena.bobr.service.impl.SimpleServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.*;
import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


class SimpleServletTest {
    public   final String NAME = "vova";
    public   final UUID uuid = UUID.fromString("76bde8dd-f961-4653-85f5-bcdc4ac171f0");

private final SimpleServlet servlet  = new  SimpleServlet();
private SimpleServiceImpl service =   Mockito.mock(SimpleServiceImpl.class);


    @BeforeEach
    void setUp() {
        servlet.setService(service);
    }

    @Test
      void doGetById() {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response =mock(HttpServletResponse.class);
        PrintWriter writer = mock(PrintWriter.class);
        try {
        when(response.getWriter()).thenReturn(writer);
        HashMap parameterMap = new HashMap<>();
        parameterMap.put("id", uuid);
        when(request.getParameterMap()).thenReturn(parameterMap);
        when(request.getParameter("id")).thenReturn(uuid.toString());
        SimpleEntity entity = new SimpleEntity();
        entity.setName(NAME);
        entity.setUuid(UUID.randomUUID());
        when(service.findById(uuid)).thenReturn(entity);

        servlet.doGet(request, response);

        verify(service, times(1)).findById(any(UUID.class));
        verify(response, times(1)).setContentType("application/json");
        verify(writer, times(1)).print(anyString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
     void doGetAll() {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response =mock(HttpServletResponse.class);
        PrintWriter writer = mock(PrintWriter.class);
        try {
            when(response.getWriter()).thenReturn(writer);
            HashMap parameterMap = new HashMap<>();
            when(request.getParameterMap()).thenReturn(parameterMap);
            servlet.doGet(request, response);

            verify(service, times(1)).findAll();
            verify(response, times(1)).setContentType("application/json");
            verify(writer, times(1)).print(anyString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void doPost() {
        try {HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response =mock(HttpServletResponse.class);
        PrintWriter writer = mock(PrintWriter.class);
        BufferedReader reader = mock(BufferedReader.class);
        when(request.getReader()).thenReturn(reader);
        when(reader.readLine()).thenReturn("{  owner:'dasha' }", null);
        SimpleEntity entity = new SimpleEntity( UUID.randomUUID(), NAME);
        when(service.save(any())).thenReturn(mock(SimpleEntity.class));
        when(response.getWriter()).thenReturn(writer);
        servlet.doPost(request, response);
        verify(service.save(any()));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void doDelete(){
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response =mock(HttpServletResponse.class);
        HashMap parameterMap = new HashMap<>();
        parameterMap.put("id", uuid);
        when(request.getParameterMap()).thenReturn(parameterMap);
        when(request.getParameter("id")).thenReturn(uuid.toString());
        SimpleEntity entity = new SimpleEntity();
        entity.setName(NAME);
        entity.setUuid(UUID.randomUUID());
        when(service.findById(uuid)).thenReturn(entity);
        try {
            servlet.doDelete(request, response);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}