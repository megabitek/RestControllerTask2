package ru.elena.bobr.utils;

import com.google.gson.Gson;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class JsonSender {
    private Gson gson = new Gson();

    public void send(
            HttpServletResponse response,
            Object dto) throws IOException {

        response.setContentType("application/json");

        String res = gson.toJson(dto);
        PrintWriter out = response.getWriter();
        response.setStatus(200);

        out.print(res);
        out.flush();
    }

}
