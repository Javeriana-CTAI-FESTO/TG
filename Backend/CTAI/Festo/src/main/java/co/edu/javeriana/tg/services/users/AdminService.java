package co.edu.javeriana.tg.services.users;

import java.util.List;

import org.springframework.stereotype.Service;

import co.edu.javeriana.tg.entities.auxiliary.IndicatorAux;
import co.edu.javeriana.tg.entities.dtos.ClientDTO;
import co.edu.javeriana.tg.entities.dtos.MachineReportDTO;
import co.edu.javeriana.tg.entities.dtos.ReportDTO;
import co.edu.javeriana.tg.entities.managed.Client;
import co.edu.javeriana.tg.services.components.ClientService;
import co.edu.javeriana.tg.services.components.MachineReportService;
import co.edu.javeriana.tg.services.components.OrderService;

@Service
public class AdminService {

    private final MachineReportService reportService;

    private final ClientService clientService;

    private final OrderService orderService;

    public AdminService(MachineReportService reportService,  ClientService clientService, OrderService orderService) {
        this.reportService = reportService;
        this.clientService = clientService;
        this.orderService = orderService;
    }

    public List<MachineReportDTO> getAllReports() {
      return reportService.getAll();
    }

    public List<ReportDTO> getReportsForMachine(Long resourceId) {
      return reportService.getForMachine(resourceId);
    }

    public List<MachineReportDTO> getAllFails() {
      return reportService.getAllFails();
    }

    public List<ReportDTO> getAllFailsForMachine(Long resourceId) {
      return reportService.getAllFailsForMachine(resourceId);
    }

    public ClientDTO createClient(Client client) {
      return clientService.createClient(client);
    }

    public List<IndicatorAux> getProductionIndicators() {
    return orderService.getIndicators();
  }

}
