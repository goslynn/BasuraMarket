package cl.duocuc.ecomarket.util.encriptacion;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class EncriptadorConfig {

    @Bean
    @Primary
    public Encriptador<String> encriptadorEcomarket() {
        return EncriptadorEcomarket.createEncriptadorEcomarket(encriptadorVigenere(), encriptadorBinario());
    }

    @Bean
    public Encriptador<String> encriptadorBinario() {
        return new EncriptadorBinario();
    }

    @Bean
    public Encriptador<String> encriptadorVigenere() {
        return new EncriptadorVigenere();
    }

}
