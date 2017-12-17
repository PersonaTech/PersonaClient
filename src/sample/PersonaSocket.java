package sample;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by itamarfredavrahami on 17/12/2017.
 */


public class PersonaSocket {

    public static final String SUCCESS ="OK";
    public static final String FAIL ="BAD";
    public static final String SERVER = "35.196.100.247";
    public static final String LOCALHOST = "localhost";

    public static ObjectOutputStream objectOutputStream ;
    public static ObjectInputStream objectInputStream;


    public PersonaSocket() throws IOException {

        Socket socket = new Socket(LOCALHOST, 8080);

        objectOutputStream = new ObjectOutputStream(socket.getOutputStream());

        objectInputStream = new ObjectInputStream(socket.getInputStream());



    }






}


