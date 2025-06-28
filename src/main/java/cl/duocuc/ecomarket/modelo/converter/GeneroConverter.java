package cl.duocuc.ecomarket.modelo.converter;

import cl.duocuc.ecomarket.tipodatos.Genero;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class GeneroConverter implements AttributeConverter<Genero, String> {

    @Override
    public String convertToDatabaseColumn(Genero genero) {
        return genero != null ? String.valueOf(genero.toChar()) : null;
    }

    @Override
    public Genero convertToEntityAttribute(String dbData) {
        return (dbData != null && !dbData.isEmpty()) ? Genero.valueOf(dbData.charAt(0)) : null;
    }
}