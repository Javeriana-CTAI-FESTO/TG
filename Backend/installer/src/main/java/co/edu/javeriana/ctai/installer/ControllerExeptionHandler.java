package co.edu.javeriana.ctai.installer;

import java.util.UUID;

public class ControllerExeptionHandler {

    Exception exception(Exception e) {

        String idFailture = UUID.randomUUID().toString();


        System.out.println("Error: " + idFailture);
        return null;
    }
}
