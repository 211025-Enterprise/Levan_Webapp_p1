package service;

import Persistence.genericDAO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Field;
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
        Object obj = null;

        if (cType.equals("application/json")){
            try{
                obj = mapper.readValue(body, clazz);
                System.out.println(obj.toString());
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

    public static void updateTable(HttpServletRequest req, HttpServletResponse resp, Class<?> clazz) throws IOException {
//        String content = req.getHeader("Content-Type");
//        String body = req.getReader().lines().collect(Collectors.joining("\n"));
//        String pk = req.getParameter("id");
//        ObjectMapper mapper = new ObjectMapper();
        genericDAO orm = new genericDAO();
//        boolean updated = false;
//        Object obj = null;
//
//        if (content.equals("application/json")){
//            try {
//                obj = mapper.readValue(body, clazz);
//                System.out.println(obj);
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//        }
        Map<String,String[]> parameterInput = req.getParameterMap();
        List<Object> vals = new ArrayList<>();
        List<Object> newvals = new ArrayList<>();
        List<Field> cols = new ArrayList<>();
        for (String k : parameterInput.keySet()){
            try{
                Field f = clazz.getDeclaredField(k);
                vals.add(parameterInput.get(k)[0]);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        }
        Field[] fields = new Field[cols.size()];
        ObjectMapper mapper = new ObjectMapper();
        String body = req.getReader().lines().collect(Collectors.joining("\n"));
        Object obj = null;
        try{
            obj = mapper.readValue(body, clazz);
        }catch (IOException e) {
            e.printStackTrace();
            resp.setStatus(500);
            return;
        }
        for (int i =0;i<clazz.getDeclaredFields().length;i++){
            newvals.add(fields[i]);
        }
        boolean updated = orm.update(clazz, vals.toArray(), cols.toArray(fields), newvals.toArray(), cols.toArray(fields));
        resp.getWriter().println(updated);

    }

    public static void deleteTable(HttpServletRequest req, HttpServletResponse resp, Class<?> clazz) {

    }
}
