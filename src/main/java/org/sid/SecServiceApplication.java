package org.sid;

import org.sid.entities.AppRole;
import org.sid.service.AccountService;
import org.sid.service.AccountServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.stream.Stream;


@SpringBootApplication
@ComponentScan(value = "org.sid.*")
@EnableJpaRepositories(value = "org.sid.*")
public class SecServiceApplication {



    public static void main(String[] args) {
        SpringApplication.run(SecServiceApplication.class, args);
    }



    @Bean
    CommandLineRunner start(AccountService accountService){

        return args ->{
            accountService.save(new AppRole(null,"USER"));
            accountService.save(new AppRole(null,"ADMIN"));

            Stream.of("user1","user2","user3","admin").forEach(un->{
                accountService.saveUser(un,"1234","1234");
            });

            accountService.addRoleToUser("admin","ADMIN");

        };
    }



    @Bean
    BCryptPasswordEncoder getBCPE(){
        return new BCryptPasswordEncoder();
    }


}





/*
    @Bean
    public CommandLineRunner run(ApplicationContext appContext) {
        return args -> {

            String[]beans = appContext.getBeanDefinitionNames();
            Arrays.stream(beans).sorted().forEach(System.out::println);

        };
    }
*/