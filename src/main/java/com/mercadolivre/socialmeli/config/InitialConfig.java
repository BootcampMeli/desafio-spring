package com.mercadolivre.socialmeli.config;

import com.mercadolivre.socialmeli.services.DataBaseInitService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InitialConfig {

    private final DataBaseInitService dataBaseInitService;

    public InitialConfig(DataBaseInitService dataBaseInitService){
        this.dataBaseInitService = dataBaseInitService;
    }

    @Bean
    public boolean populateDataBase(){
        dataBaseInitService.initTestDataBase();
        return true;
    }
}
