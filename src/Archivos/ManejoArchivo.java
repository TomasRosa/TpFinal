package Archivos;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class ManejoArchivo <T>{
    public void escribirArchivoList (String path, List<T> lista) throws IOException
    {
        File file = new File(path);
        ObjectMapper mapper = new ObjectMapper();

        if (!file.exists())
        {
            file.createNewFile();
            throw new IOException();
        }

        try {
            mapper.writeValue(file, lista);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    public void leerArchivoList (String path) throws IOException
    {
        File file = new File(path);
        ObjectMapper mapper = new ObjectMapper();
        List<T> Lista;

        if(!file.exists()) throw new IOException();

        try {
            Lista = mapper.readValue(file, List.class);
            System.out.println(Lista);
        } catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }
    public void escribirArchivoSet (String path, Set<T> set) throws IOException
    {
        File file = new File(path);
        ObjectMapper mapper = new ObjectMapper();

        if (!file.exists())
        {
            file.createNewFile();
            throw new IOException();
        }

        try {
            mapper.writeValue(file, set);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    public void leerArchivoSet (String path) throws IOException
    {
        File file = new File(path);
        ObjectMapper mapper = new ObjectMapper();
        Set<T> set;

        if(!file.exists()) throw new IOException();

        try {
            set = mapper.readValue(file, Set.class);
            System.out.println(set);
        } catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }
    public void escribirArchivoLinked (String path, LinkedHashMap<T, T> linked) throws IOException
    {
        File file = new File(path);
        ObjectMapper mapper = new ObjectMapper();

        if (!file.exists())
        {
            file.createNewFile();
            throw new IOException();
        }

        try {
            mapper.writeValue(file, linked);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    public void leerArchivoLinked (String path) throws IOException
    {
        File file = new File(path);
        ObjectMapper mapper = new ObjectMapper();
        LinkedHashMap<T, T> linked;

        if(!file.exists()) throw new IOException();

        try {
            linked = mapper.readValue(file, LinkedHashMap.class);
            System.out.println(linked);
        } catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }
}
