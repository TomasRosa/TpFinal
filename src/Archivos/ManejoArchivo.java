package Archivos;

import Reservas.Pasajero;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.*;

public class ManejoArchivo <T> implements Serializable {
    public void escribirArchivoList (String path, ArrayList<T> lista) throws IOException
    {
        File file = new File(path);
        ObjectMapper mapper = new ObjectMapper();

        if (!file.exists())
        {
            file.createNewFile();
            //throw new IOException();
        }

        try
        {
            mapper.writeValue(file, lista);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    public ArrayList<T> leerArchivoList (String path) throws IOException
    {
        File file = new File(path);
        ObjectMapper mapper = new ObjectMapper();
        ArrayList<T> Lista = new ArrayList<>();

        if(!file.exists()) throw new IOException();

        try
        {
            JavaType type = mapper.getTypeFactory().constructParametricType(ArrayList.class, Pasajero.class);
            Lista = mapper.readValue(file, type);
        } catch (IOException e)
        {
            System.out.println(e.getMessage());
        }

        return Lista;
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
