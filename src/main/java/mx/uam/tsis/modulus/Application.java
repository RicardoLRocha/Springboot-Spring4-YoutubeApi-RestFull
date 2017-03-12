package mx.uam.tsis.modulus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



/**
 * Clase principal
 * 
 * http://howtodoinjava.com/spring/spring-core/how-to-use-spring-component-repository-service-and-controller-annotations/
 * https://spring.io/guides/tutorials/bookmarks/
 * https://spring.io/guides/gs/rest-service/
 * https://spring.io/guides/gs/serving-web-content/
 * https://spring.io/guides/gs/spring-boot/#scratch
 * 
 */

/** =====================================================================================================
 * para WAR
 * 
 * 
 * 
 

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class Application extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	    return application.sources(Application.class);
    }
	
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
    
 * 
 * 
 * ===================================================================================================== */



/*/ Correr
 * 
 * mvn package
 * java -jar target/SpringBootProyectoReproductor-1.1.0.jar
 * o
 * java -jar SpringBootProyectoReproductor-1.1.0.jar
 * 
 * URL vistas
 * 
 * 
 * USO CURL
 * 
 * */

 
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}


