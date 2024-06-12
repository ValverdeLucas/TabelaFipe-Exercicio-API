package one.programa.TabelaFipeExercicio.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosVeiculo(@JsonAlias("Modelo") String modelo,
                           @JsonAlias("Marca") String marca,
                           @JsonAlias("Valor") String valor,
                           @JsonAlias("AnoModelo") Integer anoModelo,
                           @JsonAlias("Combustivel") String combustivel,
                           @JsonAlias("MesReferencia") String mesReferencia) {

    @Override
    public String toString() {
        return "Modelo: " + modelo +
                "\nMarca: " + marca +
                "\nValor: " + valor +
                "\nAno do modelo: " + anoModelo +
                "\nTipo de combustível: " + combustivel +
                "\nMês de referência: " + mesReferencia + "\n";
    }
}


