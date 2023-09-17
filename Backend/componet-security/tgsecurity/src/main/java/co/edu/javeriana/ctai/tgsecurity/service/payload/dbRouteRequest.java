package co.edu.javeriana.ctai.tgsecurity.service.payload;

public class dbRouteRequest {
    private String dbRoute;

    public dbRouteRequest() {
    }

    public dbRouteRequest(String dbRoute) {
        this.dbRoute = dbRoute;
    }

    public String getDbRoute() {
        return dbRoute;
    }

    public void setDbRoute(String dbRoute) {
        this.dbRoute = dbRoute;
    }
}
