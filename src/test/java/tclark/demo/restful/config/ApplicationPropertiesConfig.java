package tclark.demo.restful.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class ApplicationPropertiesConfig {

        @Value("${spring.datasource.url}")
        private String url;

        @Value("${spring.datasource.username}")
        private String username;

        @Value("${spring.datasource.password}")
        private String password;

}