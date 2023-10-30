package co.edu.javeriana.tg;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
public class DataSourceConfig {

    @Value("${datasource}")
    private String datasource;

    @Bean
    public DataSource createDataSourceDev() throws Exception {
        ComboPooledDataSource ds = new ComboPooledDataSource();        
        //ds.setJdbcUrl("jdbc:ucanaccess://"+datasource+";showSchema=true");
        ds.setJdbcUrl("jdbc:ucanaccess://"+"/home/capitan/Documentos/GitHub/TG/Backend/CTAI/CP_Factory/"+"FestoMES_be.accdb;showSchema=true");
        ds.setDriverClass("net.ucanaccess.jdbc.UcanaccessDriver");
        return ds;
    }
}
///home/capitan/Documentos/GitHub/TG/Backend/CTAI/CP_Factory/FestoMES_be.accdb