package co.edu.javeriana.ctai.tgsecurity.service.intf;

import java.io.IOException;

public interface IModuleExecutionService {
    void executeModule(String dbRoute) throws IOException;
    void stopModule();
}
