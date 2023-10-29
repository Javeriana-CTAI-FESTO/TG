package co.edu.javeriana.ctai.tgsecurity.entities;

import javax.persistence.*;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    private int id_part;
    private int id_workPlan;
    private String title;
    private long orderNumber;

    private String image_filePath;

    // Constructor vacío necesario para JPA
    public Order() {
    }

    public Order(long id, Cliente cliente, int id_part, int id_workPlan, String title, long orderNumber, String image_filePath) {
        this.id = id;
        this.cliente = cliente;
        this.id_part = id_part;
        this.id_workPlan = id_workPlan;
        this.title = title;
        this.orderNumber = orderNumber;
        this.image_filePath = image_filePath;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public int getId_part() {
        return id_part;
    }

    public void setId_part(int id_part) {
        this.id_part = id_part;
    }

    public int getId_workPlan() {
        return id_workPlan;
    }

    public void setId_workPlan(int id_workPlan) {
        this.id_workPlan = id_workPlan;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(long orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getImage_filePath() {
        return image_filePath;
    }

    public void setImage_filePath(String image_filePath) {
        this.image_filePath = image_filePath;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", cliente=" + cliente +
                ", id_part=" + id_part +
                ", id_workPlan=" + id_workPlan +
                ", title='" + title + '\'' +
                ", orderNumber=" + orderNumber +
                ", image_filePath='" + image_filePath + '\'' +
                '}';
    }
}

