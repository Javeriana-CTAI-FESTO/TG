package co.edu.javeriana.tg;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
public class DataSourceConfig {

    @Profile(value = {"dev"})
    @Bean
    public DataSource createDataSourceDev() throws Exception {
        ComboPooledDataSource ds = new ComboPooledDataSource();        
       // ds.setJdbcUrl("jdbc:ucanaccess://"+"C:\\Users\\aulasingenieria\\Desktop\\ModeloWilson\\Mes"+"/FestoMES_be.accdb;showSchema=true");
       ds.setJdbcUrl("jdbc:ucanaccess://"+"/home/capitan/Documentos/GitHub/TG/BackEnd/Festo"+"/FestoMES_2_be.accdb;showSchema=true");
        ds.setDriverClass("net.ucanaccess.jdbc.UcanaccessDriver");
        return ds;
    }

    @Profile(value = {"test"})
    @Bean
    public DataSource createDataSourceTest() throws Exception {
        ComboPooledDataSource ds = new ComboPooledDataSource();        
        //ds.setJdbcUrl("jdbc:ucanaccess://"+"C:\\Users\\aulasingenieria\\Desktop\\ModeloWilson\\Mes"+"/FestoMES_test.accdb;showSchema=true");
        ds.setJdbcUrl("jdbc:ucanaccess://"+"/home/capitan/Documentos/GitHub/TG/BackEnd/Festo"+"/FestoMES_test.accdb;showSchema=true");
        ds.setDriverClass("net.ucanaccess.jdbc.UcanaccessDriver");
        return ds;
    }
    
}
