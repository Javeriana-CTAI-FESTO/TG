package co.edu.javeriana.ctai.tgsecurity.entities.auxillary;

public class OrderResponse {
    private int id_part;
    private int id_workPlan;
    private Long cliente_Cedula;
    private String title;
    private long orderNumber;
    private String image_filePath;


    public OrderResponse() {
    }

    public OrderResponse(int id_part, int id_workPlan, Long cliente_Cedula, String title, long orderNumber, String image_filePath) {
        this.id_part = id_part;
        this.id_workPlan = id_workPlan;
        this.cliente_Cedula = cliente_Cedula;
        this.title = title;
        this.orderNumber = orderNumber;
        this.image_filePath = image_filePath;
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

    public Long getCliente_Cedula() {
        return cliente_Cedula;
    }

    public void setCliente_Cedula(Long cliente_Cedula) {
        this.cliente_Cedula = cliente_Cedula;
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
}
