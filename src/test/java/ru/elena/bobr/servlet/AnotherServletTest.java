package ru.elena.bobr.servlet;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.elena.bobr.model.AnotherEntity;
import ru.elena.bobr.model.SimpleEntity;
import ru.elena.bobr.service.impl.AnotherCrudServiceImpl;
import ru.elena.bobr.service.impl.SimpleServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

 class AnotherServletTest {

    private final AnotherServlet servlet = new AnotherServlet();
    private AnotherCrudServiceImpl service = Mockito.mock(AnotherCrudServiceImpl.class);

    public final UUID uuid = UUID.fromString("7f9e408a-1b30-47f6-aace-482dc0854115");

    public final String NAME = "bob";

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
            parameterMap.put("id", uuid);
            when(request.getParameterMap()).thenReturn(parameterMap);
            when(request.getParameter("id")).thenReturn(uuid.toString());
            AnotherEntity entity = new AnotherEntity();
            entity.setName(NAME);
            entity.setUuid(UUID.randomUUID());
            when(service.findById(uuid)).thenReturn(entity);
            servlet.doGet(request, response);
            verify(service, times(1)).findById(any(UUID.class));
            verify(response, times(1)).setContentType("application/json");
            verify(writer, times(1)).print(anyString());
        } catch (IOException e) {
            Assert.assertTrue(false); ;
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
        } catch (IOException e) {
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
            when(reader.readLine()).thenReturn("{  nickname:'bobik' }", null);
            AnotherEntity entity = new AnotherEntity( UUID.randomUUID(), NAME);
            when(service.save(any())).thenReturn(mock(AnotherEntity.class));
            when(response.getWriter()).thenReturn(writer);
            servlet.doPost(request, response);
            verify(service, times(1)).save(any());

        } catch (IOException e) {
            Assert.assertTrue(false);
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
        AnotherEntity entity = new AnotherEntity();
        entity.setName(NAME);
        entity.setUuid(UUID.randomUUID());
        Optional<AnotherEntity> deleted = Optional.ofNullable(entity);
        when(service.findById(uuid)).thenReturn(entity);
        when(service.delete(uuid)).thenReturn(deleted);
        try {
            servlet.doDelete(request, response);
        } catch (IOException | ServletException e) {
            Assert.assertTrue(false);
        }
        verify(service, times(1)).delete(any());


    }
}
