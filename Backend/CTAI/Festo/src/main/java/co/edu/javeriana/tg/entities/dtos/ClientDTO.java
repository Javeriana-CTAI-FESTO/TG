package co.edu.javeriana.tg.entities.dtos;

import co.edu.javeriana.tg.entities.Client;

public class ClientDTO {
    private Long clientNumber;
    private String firstName;
    private String lastName;
    private String address;
    private String phone;
    private String email;
    private String company;
    public ClientDTO() {
    }
    public ClientDTO(Client client){
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
