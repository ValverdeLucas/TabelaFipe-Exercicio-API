package one.programa.TabelaFipeExercicio.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record MenuNavegacao(String codigo, String nome) {
}
