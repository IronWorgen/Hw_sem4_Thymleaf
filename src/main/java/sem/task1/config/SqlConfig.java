package sem.task1.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "db.sql")
@Component
@Data
public class SqlConfig {
    private String save;
    private String findAll;


}
