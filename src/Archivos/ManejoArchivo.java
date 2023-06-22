package Archivos;
import Empleados.Conserje;
import Empleados.Recepcionista;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;

public class ManejoArchivo <T> implements Serializable {
    public void escribirArchivoList (String path, List<T> lista) throws IOException
    {
        File file = new File(path);
        ObjectMapper mapper = new ObjectMapper();

        if (!file.exists())
        {
            boolean flag = file.createNewFile();
            if (!flag) System.out.println("\nCreando archivo...");
            throw new IOException();
        }

        try
        {
            mapper.writeValue(file, lista);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    public List<T> leerArchivoList (String path, Object tipoObjeto) throws IOException
    {
        File file = new File(path);
        ObjectMapper mapper = new ObjectMapper();
        List<T> Lista = new ArrayList<>();

        if(!file.exists()) throw new IOException();

        try
        {
            JavaType type = mapper.getTypeFactory().constructParametricType(ArrayList.class, tipoObjeto.getClass());
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
            boolean flag = file.createNewFile();
            if (!flag) System.out.println("\nCreando archivo...");
            throw new IOException();
        }

        try {
            mapper.writeValue(file, set);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    public Set<T> leerArchivoSet (String path, Object tipoObjeto) throws IOException
    {
        File file = new File(path);
        ObjectMapper mapper = new ObjectMapper();
        Set<T> set = new HashSet<>();

        if(!file.exists())
        {
            throw new IOException();
        }

        try
        {
            JavaType type = mapper.getTypeFactory().constructParametricType(Set.class, tipoObjeto.getClass());
            set = mapper.readValue(file, type);
        } catch (IOException e)
        {
            System.out.println(e.getMessage());
        }

        return set;
    }
    public void escribirArchivoLinked (String path, LinkedHashMap<T, T> linked) throws IOException
    {
        File file = new File(path);
        ObjectMapper mapper = new ObjectMapper();

        if (!file.exists())
        {
            boolean flag = file.createNewFile();
            if (!flag) System.out.println("\nCreando archivo...");
            throw new IOException();
        }

        try {
            mapper.writeValue(file, linked);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    public LinkedHashMap<T, T> leerArchivoLinked (String path, Object tipoObjeto) throws IOException
    {
        File file = new File(path);
        ObjectMapper mapper = new ObjectMapper();
        LinkedHashMap<T, T> linked = new LinkedHashMap<>();

        if(!file.exists()) throw new IOException();

        try
        {
            JavaType type = mapper.getTypeFactory().constructParametricType(LinkedHashMap.class, tipoObjeto.getClass());
            linked = mapper.readValue(file, type);
        } catch (IOException e)
        {
            System.out.println(e.getMessage());
        }

        return linked;
    }

    public void escribirArchivoRecepcionista (String path, Recepcionista recepcionista) throws IOException
    {
        File file = new File(path);
        ObjectMapper mapper = new ObjectMapper();

        if (!file.exists())
        {
            boolean flag = file.createNewFile();
            if (!flag) System.out.println("\nCreando archivo...");
            throw new IOException();
        }

        try {
            mapper.writeValue(file, recepcionista);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public Recepcionista leerArchivoRecepcionista (String path) throws IOException
    {
        File file = new File(path);
        ObjectMapper mapper = new ObjectMapper();
        Recepcionista recepcionista = new Recepcionista();

        if(!file.exists()) throw new IOException();

        try
        {
            recepcionista = mapper.readValue(file, Recepcionista.class);
        } catch (IOException e)
        {
            System.out.println(e.getMessage());
        }

        return recepcionista;
    }

    public void escribirArchivoConserje (String path, Conserje conserje) throws IOException
    {
        File file = new File(path);
        ObjectMapper mapper = new ObjectMapper();

        if (!file.exists())
        {
            boolean flag = file.createNewFile();
            if (!flag) System.out.println("\nCreando archivo...");
            throw new IOException();
        }

        try {
            mapper.writeValue(file, conserje);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public Conserje leerArchivoConserje (String path) throws IOException
    {
        File file = new File(path);
        ObjectMapper mapper = new ObjectMapper();
        Conserje conserje = new Conserje();

        if(!file.exists()) throw new IOException();

        try
        {
            conserje = mapper.readValue(file, Conserje.class);
        } catch (IOException e)
        {
            System.out.println(e.getMessage());
        }

        return conserje;
    }
}
