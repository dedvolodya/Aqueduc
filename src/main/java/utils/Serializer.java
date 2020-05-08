package utils;

import algorithm.IntermediateResult;
import graph.Graph;

import java.io.*;
import java.util.HashSet;

public class Serializer {

    public static void serializeGraph(Graph g, String path) {
        serializeObject(g, path);
    }

    public static Graph loadGraph(String path) {
        return (Graph) loadObject(path);
    }

    public static void serializeIntermediateResult(IntermediateResult result, String path) {
        serializeObject(result, path);
    }

    public static IntermediateResult loadIntermediateResult(String path) {
        return (IntermediateResult)loadObject(path);
    }

    public static <T extends Serializable> void serializeSet(HashSet<T> set, String path) {
       serializeObject(set, path);
    }

    public static <T extends Serializable> HashSet<T> loadSet(String path) {
        return (HashSet<T>) loadObject(path);
    }


    private static void serializeObject(Object obj, String path) {
        try
        {
            FileOutputStream file = new FileOutputStream(path);
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(obj);
            out.close();
            file.close();
        } catch(IOException ex)
        {
            System.err.println("While serialization IOException is caught");
            ex.printStackTrace();
        }
    }

    private static Object loadObject(String path) {
        Object res = null;
        try
        {
            FileInputStream file = new FileInputStream(path);
            ObjectInputStream in = new ObjectInputStream(file);
            res = in.readObject();
            in.close();
            file.close();
        } catch(IOException ex) {
            System.err.println("While deserialization IOException is caught");
            ex.printStackTrace();
        } catch(ClassNotFoundException ex)  {
            System.err.println("While deserialization ClassNotFoundException is caught");
            ex.printStackTrace();
        }
        return res;
    }
}
