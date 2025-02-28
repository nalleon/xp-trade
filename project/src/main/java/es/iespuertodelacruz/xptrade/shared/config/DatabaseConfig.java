package es.iespuertodelacruz.xptrade.shared.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

/**
 * @author Nabil Leon Alvarez @nalleon
 * @author Jose Maximiliano Boada Martin @mackstm
 */
@Configuration
public class DatabaseConfig {
    @Bean
    @Profile("!test")
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/xptrade?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC");
        dataSource.setUsername("root");
        dataSource.setPassword("1q2w3e4r");
        return dataSource;
    }
    @Bean
    @Profile("test")
    public DataSource dataSourceTest() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:file:/tmp/xptradetest;DB_CLOSE_DELAY=-1");
        dataSource.setUsername("root");
        dataSource.setPassword("1q2w3e4r");
        return dataSource;
    }

    @Bean
    public MongoClient mongoClientProd(){
        return MongoClients.create("mongodb://root:1q2w3e4r@localhost:27017");
    }


    @Bean(name = "mongoTemplate")
    public MongoTemplate mongoTemplateProd(){
        return new MongoTemplate(new SimpleMongoClientDatabaseFactory(mongoClientProd(), "xptrade"));
    }
}
