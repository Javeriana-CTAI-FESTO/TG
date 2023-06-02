package co.edu.javeriana.tg.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.javeriana.tg.entities.dtos.OrderDTO;
import co.edu.javeriana.tg.services.OrderService;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    
    @Autowired
    private OrderService operationService;
    
    @GetMapping
    public ResponseEntity<List<OrderDTO>> getAll() {
        List<OrderDTO> workPlans = operationService.getAll();
        HttpStatus status = HttpStatus.OK;
        if (workPlans.isEmpty())
            status = HttpStatus.NO_CONTENT;
        return new ResponseEntity<List<OrderDTO>>(workPlans, status);
    }

    @GetMapping("/ends")
    public ResponseEntity<List<Date>> getAllPlannedEnds(){
        List<Date> workPlans = operationService.getAllPlannedEnds();
        HttpStatus status = HttpStatus.OK;
        if (workPlans.isEmpty())
            status = HttpStatus.NO_CONTENT;
        return new ResponseEntity<List<Date>>(workPlans, status);
    }
}
