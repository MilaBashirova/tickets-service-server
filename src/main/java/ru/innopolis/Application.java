package ru.innopolis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ru.innopolis.services.UserService;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.util.concurrent.Executors.newSingleThreadExecutor;

@SpringBootApplication
@EnableConfigurationProperties
//@ComponentScan(basePackages = "ru.innopolis")
class Application implements WebMvcConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods("HEAD", "GET", "PUT", "POST", "DELETE", "PATCH");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        if (System.getProperty("os.name").toLowerCase().indexOf("linux") > -1) {
            registry.addResourceHandler("/**")
                    .addResourceLocations("file:" + System.getProperty("user.dir") + "dist/tickets-service/");
        } else if (System.getProperty("os.name").toLowerCase().indexOf("windows") > -1) {
            registry.addResourceHandler("/**")
                    .addResourceLocations("file:///" + System.getProperty("user.dir").substring(0, System.getProperty("user.dir").lastIndexOf('\\')) + "/tickets-service-front/dist/tickets-service/");
        }
    }
}
