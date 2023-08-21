package co.edu.javeriana.tg.entities.dtos;

import co.edu.javeriana.tg.entities.managed.Client;
import io.swagger.v3.oas.annotations.media.Schema;

public class ClientDTO {
    @Schema(name = "Numero de cliente", example = "20271125", description = "Numero que representa un cliente en el sistema", required = true)
    private Long clientNumber;
    @Schema(name = "Nombre", example = "Juan", description = "Nombre del cliente", required = true)
    private String firstName;
    @Schema(name = "Apellido", example = "Perez", description = "Apellido del cliente", required = true)
    private String lastName;
    @Schema(name = "Direccion", example = "Calle falsa 123", description = "Direccion del cliente", required = true)
    private String address;
    @Schema(name = "Telefono", example = "3192752726", description = "Telefono del cliente", required = true)
    private String phone;
    @Schema(name = "Email", example = "q6m9Q@example.com", description = "Email del cliente", required = true)
    private String email;
    @Schema(name = "Empresa", example = "Javeriana", description = "Empresa del cliente", required = true)
    private String company;

    public ClientDTO() {
    }

    public ClientDTO(Client client) {
        this.clientNumber = client.getClientNumber();
        this.firstName = client.getFirstName();
        this.lastName = client.getLastName();
        this.address = client.getAddress();
        this.phone = client.getPhone();
        this.email = client.getEmail();
        this.company = client.getCompany();
    }

    public Long getClientNumber() {
        return clientNumber;
    }

    public void setClientNumber(Long clientNumber) {
        this.clientNumber = clientNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
