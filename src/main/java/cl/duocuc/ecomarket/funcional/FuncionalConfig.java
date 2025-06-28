package cl.duocuc.ecomarket.funcional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class FuncionalConfig {

    @Bean
    public CalculaMonto calculaMonto() {
        return new CalculaMonto();
    }

}
