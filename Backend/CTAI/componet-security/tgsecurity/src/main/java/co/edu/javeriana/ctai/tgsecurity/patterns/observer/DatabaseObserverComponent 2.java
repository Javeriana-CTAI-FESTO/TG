package co.edu.javeriana.ctai.tgsecurity.patterns.observer;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import co.edu.javeriana.ctai.tgsecurity.service.imp.MailService;
import co.edu.javeriana.ctai.tgsecurity.service.intf.IClientService;
import co.edu.javeriana.ctai.tgsecurity.service.intf.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


@Component
public class DatabaseObserverComponent implements DatabaseObserver {
    @Override
    public void notifyNewInsertion() {

    }
/*
    @Qualifier("clientServiceImp")
    @Autowired
    private IClientService clientService;

    @Autowired
    private MailService mailService;
    public DatabaseObserverComponent(@Qualifier("userServiceImp") IUserService userService) {
        userService.registerObserver(this);
    }
    @Override
    public void notifyNewInsertion() {
        System.out.println("Se ha registrado un nuevo usuario en la base de datos");
        mailService.sendEmail("soportequickparked@gmail.com", clientService.findByCedula(1234567890L).getCorreoElectronico(), "ALERTA: Registro de cuenta",
                "Se ha registrado una cuenta con el correo: " + clientService.findByCedula(1234567890L).getCorreoElectronico() + " a las: "
                        + ZonedDateTime.now(ZoneId.of("America/Bogota")).toLocalDateTime() + " del dia: " + LocalDate.now());
    }
*/
}
