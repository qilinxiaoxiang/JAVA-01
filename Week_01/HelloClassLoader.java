import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class HelloClassLoader extends ClassLoader {

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] bytes = decode(name);
        if (bytes == null) {
            return null;
        }
        return defineClass("Hello", bytes, 0, bytes.length);
    }

    private byte[] decode(String filePath) {
        File file = new File(filePath);
        try (FileInputStream inputStream = new FileInputStream(file)) {
            try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
                int byteValue;
                while ((byteValue = inputStream.read()) != -1) {
                    outputStream.write(255 - byteValue);
                }
                return outputStream.toByteArray();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        String filePath = "Hello.xlass";
        try {
            Class<?> helloClass = new HelloClassLoader().findClass(filePath);
            Method helloMethod = helloClass.getMethod("hello");
            helloMethod.invoke(helloClass.newInstance());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
