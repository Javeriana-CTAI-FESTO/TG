package co.edu.javeriana.ctai.tgsecurity.service.external;

import com.google.gson.annotations.SerializedName;

public class OrderRequestFESTO {

    @SerializedName("partNumber")
    private String partNumber;

    @SerializedName("clientNumber")
    private int clientNumber;

    @SerializedName("positions")
    private int positions;

    public OrderRequestFESTO() {
        // Constructor predeterminado sin argumentos
    }

    public OrderRequestFESTO(String partNumber) {
        this.partNumber = partNumber;
        this.clientNumber = 0;
        this.positions = 1;
    }

    public String getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
    }

    public int getClientNumber() {
        return clientNumber;
    }

    public void setClientNumber(int clientNumber) {
        this.clientNumber = clientNumber;
    }

    public int getPositions() {
        return positions;
    }

    public void setPositions(int positions) {
        this.positions = positions;
    }
}

