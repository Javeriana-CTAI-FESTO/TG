package co.edu.javeriana.ctai.tgsecurity.service.payload;

public class dbRouteRequest {
    private String dbRoute;
    private String rutaModuloJar;

    public dbRouteRequest(String dbRoute, String rutaModuloJar) {
        this.dbRoute = dbRoute;
        this.rutaModuloJar = rutaModuloJar;
    }

    public String getDbRoute() {
        return dbRoute;
    }

    public void setDbRoute(String dbRoute) {
        this.dbRoute = dbRoute;
    }

    public String getRutaModuloJar() {
        return rutaModuloJar;
    }

    public void setRutaModuloJar(String rutaModuloJar) {
        this.rutaModuloJar = rutaModuloJar;
    }
}
