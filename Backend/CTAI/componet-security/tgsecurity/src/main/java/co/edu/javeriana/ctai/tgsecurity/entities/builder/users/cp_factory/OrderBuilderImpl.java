package co.edu.javeriana.ctai.tgsecurity.entities.builder.users.cp_factory;

import co.edu.javeriana.ctai.tgsecurity.entities.builder.IBuilder;
import co.edu.javeriana.ctai.tgsecurity.entities.users.Cliente;
import co.edu.javeriana.ctai.tgsecurity.entities.users.cp_factory.Order;

public class OrderBuilderImpl implements IBuilder<Order> {
    private long id;
    private Cliente cliente;
    private int id_part;
    private int id_workPlan;
    private String title;
    private long orderNumber;
    private String image_filePath;

    public OrderBuilderImpl() {
        // Puedes establecer valores por defecto aquí si es necesario.
    }

    public OrderBuilderImpl id(long id) {
        this.id = id;
        return this;
    }

    public OrderBuilderImpl cliente(Cliente cliente) {
        this.cliente = cliente;
        return this;
    }

    public OrderBuilderImpl id_part(int id_part) {
        this.id_part = id_part;
        return this;
    }

    public OrderBuilderImpl id_workPlan(int id_workPlan) {
        this.id_workPlan = id_workPlan;
        return this;
    }

    public OrderBuilderImpl title(String title) {
        this.title = title;
        return this;
    }

    public OrderBuilderImpl orderNumber(long orderNumber) {
        this.orderNumber = orderNumber;
        return this;
    }

    public OrderBuilderImpl image_filePath(String image_filePath) {
        this.image_filePath = image_filePath;
        return this;
    }

    @Override
    public Order build() {
        return new Order(id, cliente, id_part, id_workPlan, title, orderNumber, image_filePath);
    }
}

