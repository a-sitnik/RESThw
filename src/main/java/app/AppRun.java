package app;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
@EnableAutoConfiguration
public class AppRun {
        public static void main(String[] args) {
            SpringApplication.run(AppRun.class, args);
        }
}
// http://localhost:8080/schedule
// http://localhost:8080/lectors
// http://localhost:8080/subjects
