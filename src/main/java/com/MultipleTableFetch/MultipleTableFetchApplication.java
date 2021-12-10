package com.MultipleTableFetch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
public class MultipleTableFetchApplication {

    public static void main(String[] args) {
        SpringApplication.run(MultipleTableFetchApplication.class, args);
    }

       @Bean
       public Docket api() {
           return new Docket(DocumentationType.SWAGGER_2)
                   .select()
                   .apis(RequestHandlerSelectors.basePackage("com.MultipleTableFetch.Controller"))
                   .paths(PathSelectors.any())
                   .build();
       }
    /* @Bean
     public Docket api() {
         Docket build = new Docket(DocumentationType.SWAGGER_2)
                 .select(com.MultipleTableFetch.Controller.EmployeeController)
                 .apis(RequestHandlerSelectors.any())
                 .paths(PathSelectors.any())
                 .build();
         return build;
     }*/
    /*@Bean
    public Docket postsApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("Multiple-Fetch APIs Page").select()
                .paths(regex("/department.*")).build();
        .paths(regex("/employee.*")).build();
               .paths(regex("/gender.*")).build();
    }
    }*/
}