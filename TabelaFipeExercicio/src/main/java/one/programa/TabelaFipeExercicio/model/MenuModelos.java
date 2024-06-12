package one.programa.TabelaFipeExercicio.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record MenuModelos(List<MenuNavegacao> modelos) {
}
