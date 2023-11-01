package co.edu.javeriana.ctai.tgsecurity.services.imp.payloads;

public class Img {

    String name;
    String type;
    String data;

    public Img() {

    }

    public Img(String name, String type, String data) {
        this.name = name;
        this.type = type;
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getData() {
        return data;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setData(String data) {
        this.data = data;
    }
}
