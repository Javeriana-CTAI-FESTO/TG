package co.edu.javeriana.tg;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
public class DataSourceConfig {
    @Bean
    public DataSource createDataSource() throws Exception {
        ComboPooledDataSource ds = new ComboPooledDataSource();        
        ds.setJdbcUrl("jdbc:ucanaccess://C:/Users/nbayo/Desktop/TG/Festo/FestoMES.accdb;showSchema=true");
        ds.setDriverClass("net.ucanaccess.jdbc.UcanaccessDriver");
        return ds;
    }

}
