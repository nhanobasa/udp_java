package com.example.utils;

import java.io.*;

public class IOFileUtil {
    public static <T> T bytesToObject(byte[] bytes) {
        T result = null;
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        try (ObjectInputStream ois = new ObjectInputStream(bais);) {
            result = (T) ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static <T> byte[] objectToBytes(T t) {
        byte[] result = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (ObjectOutputStream oos = new ObjectOutputStream(baos);) {
            oos.flush();
            oos.writeObject(t);
            oos.flush();
            result = baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
