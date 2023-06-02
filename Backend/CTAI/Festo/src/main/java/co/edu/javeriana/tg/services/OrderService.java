package co.edu.javeriana.tg.services;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.javeriana.tg.entities.dtos.OrderDTO;
import co.edu.javeriana.tg.repositories.OrderPositionRepository;

@Service
public class OrderService {
    @Autowired
    private OrderPositionRepository orderPositionRepository;

    public List<OrderDTO> getAll(){
        return orderPositionRepository.findAll().stream().map(OrderDTO::new).collect(Collectors.toList());
    }

    public List<Date> getAllPlannedEnds(){
        return orderPositionRepository.findPlannedEnd();
    }
}
