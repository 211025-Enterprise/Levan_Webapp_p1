package service;

import Persistence.genericDAO;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

public class servletService {

    public static void readAll(HttpServletRequest req, HttpServletResponse resp, Class<?> clazz) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        genericDAO orm = new genericDAO();
        List<Object> oList = orm.getAll(clazz);
        for (Object obj : oList){
            resp.getWriter().println(mapper.writeValueAsString(obj));
        }
        resp.setStatus(200);
    }

    public static void createTable(HttpServletRequest req, HttpServletResponse resp, Class<?> clazz) throws IOException {
        String cType = req.getHeader("Content-Type");
        String body = req.getReader().lines().collect(Collectors.joining("\n"));
        ObjectMapper mapper = new ObjectMapper();
        genericDAO orm = new genericDAO();
        boolean valueIn = false;
        Object obj = new Object();

        if (cType.equals("application/json")){
            try{
                obj = mapper.readValue(body, clazz);
                orm.create(clazz,obj);
                valueIn = true;
                resp.getWriter().println("Created table/added record");

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if (valueIn){
            resp.setStatus(201);
            resp.setHeader("Content-Type", "application/json");
            resp.getWriter().println(mapper.writeValueAsString(obj));
        }
        else {
            resp.setStatus(200);
            resp.getOutputStream().println("Can not add that object");
        }
    }

    public static void updateTable(HttpServletRequest req, HttpServletResponse resp, Class<?> clazz) {
    }

    public static void deleteTable(HttpServletRequest req, HttpServletResponse resp, Class<?> clazz) {
    }
}
