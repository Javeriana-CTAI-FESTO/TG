package co.edu.javeriana.tg.entities.dtos;

import co.edu.javeriana.tg.entities.managed.Client;
import io.swagger.v3.oas.annotations.media.Schema;

public class ClientDTO {
    @Schema(name = "clientNumber", example = "20271125", description = "Numero que representa un cliente en el sistema", required = true)
    private Long clientNumber;
    @Schema(name = "nombre", example = "Juan", description = "Nombre del cliente", required = true)
    private String firstName;
    @Schema(name = "apellido", example = "Perez", description = "Apellido del cliente", required = true)
    private String lastName;
    @Schema(name = "direccion", example = "Calle falsa 123", description = "Direccion del cliente", required = true)
    private String address;
    @Schema(name = "telefono", example = "3192752726", description = "Telefono del cliente", required = true)
    private String phone;
    @Schema(name = "email", example = "q6m9Q@example.com", description = "Email del cliente", required = true)
    private String email;
    @Schema(name = "empresa", example = "Javeriana", description = "Empresa del cliente", required = true)
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((clientNumber == null) ? 0 : clientNumber.hashCode());
        result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
        result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
        result = prime * result + ((address == null) ? 0 : address.hashCode());
        result = prime * result + ((phone == null) ? 0 : phone.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((company == null) ? 0 : company.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ClientDTO other = (ClientDTO) obj;
        if (clientNumber == null) {
            if (other.clientNumber != null)
                return false;
        } else if (!clientNumber.equals(other.clientNumber))
            return false;
        if (firstName == null) {
            if (other.firstName != null)
                return false;
        } else if (!firstName.equals(other.firstName))
            return false;
        if (lastName == null) {
            if (other.lastName != null)
                return false;
        } else if (!lastName.equals(other.lastName))
            return false;
        if (address == null) {
            if (other.address != null)
                return false;
        } else if (!address.equals(other.address))
            return false;
        if (phone == null) {
            if (other.phone != null)
                return false;
        } else if (!phone.equals(other.phone))
            return false;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        if (company == null) {
            if (other.company != null)
                return false;
        } else if (!company.equals(other.company))
            return false;
        return true;
    }
}
