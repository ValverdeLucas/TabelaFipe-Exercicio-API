package one.programa.TabelaFipeExercicio.main;

import one.programa.TabelaFipeExercicio.model.DadosVeiculo;
import one.programa.TabelaFipeExercicio.model.MenuModelos;
import one.programa.TabelaFipeExercicio.model.MenuNavegacao;
import one.programa.TabelaFipeExercicio.service.ConverteDados;
import one.programa.TabelaFipeExercicio.service.RequisicaoAPI;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static int validarOpcao(Scanner input) {
        while (!input.hasNextInt()) {
            System.out.println("Por favor, insira um valor númerico válido:");
            input.next();
        }
        return input.nextInt();
    }

    private Scanner input = new Scanner(System.in);
    private RequisicaoAPI requisicaoAPI = new RequisicaoAPI();
    private ConverteDados conversor = new ConverteDados();
    private final String ENDERECO = "https://parallelum.com.br/fipe/api/v1/";

    public void exibeMenu() {

        var opcaoConsulta = "carros";
        var menu = """
                *************** Bem vindo! ***************
                                
                1 - Carro
                2 - Moto
                3 - Caminhão
                                
                Qual tipo de veículo deseja consultar na tabela FIPE?
                """;

        System.out.println(menu);
        var opcaoVeiculo = validarOpcao(input);

        switch (opcaoVeiculo) {
            case 1:
                opcaoConsulta = "carros";
                break;
            case 2:
                opcaoConsulta = "motos";
                break;
            case 3:
                opcaoConsulta = "caminhoes";
                break;
        }

        var json = requisicaoAPI.obterDados(ENDERECO + opcaoConsulta + "/marcas");

        var menuNavegacao = conversor.obterLista(json, MenuNavegacao.class);

        menuNavegacao.stream()
                .sorted(Comparator.comparing(MenuNavegacao::nome))
                .forEach(System.out::println);

        System.out.println("Informe o código da marca para consulta:");
        var opcaoMarca = validarOpcao(input);

        json = requisicaoAPI.obterDados(ENDERECO + opcaoConsulta + "/marcas/" + opcaoMarca + "/modelos");
        var modeloLista = conversor.obterDados(json, MenuModelos.class);
        System.out.println("Modelos da marca escolhida:");

        modeloLista.modelos().stream()
                .sorted(Comparator.comparing(MenuNavegacao::nome))
                .forEach(System.out::println);

        System.out.println("\nDigite um trecho do nome do carro desejado");
        var nomeModelo = input.next();

        List<MenuNavegacao> modeloBuscado = modeloLista.modelos().stream()
                .filter(m -> m.nome().toLowerCase().contains(nomeModelo.toLowerCase()))
                .collect(Collectors.toList());

        System.out.println("\nModelos filtrados com o nome de '" + nomeModelo + "':");
        modeloBuscado.forEach(System.out::println);

        System.out.println("\nDigite o código do modelo desejado para consulta dos valores:");
        var opcaoModelo = validarOpcao(input);


        json = requisicaoAPI.obterDados(ENDERECO + opcaoConsulta + "/marcas/" + opcaoMarca + "/modelos/" + opcaoModelo + "/anos");
        List<MenuNavegacao> modelosAnos = conversor.obterLista(json, MenuNavegacao.class);
        List<DadosVeiculo> veiculos = new ArrayList<>();

        for (int i = 0; i < modelosAnos.size(); i++) {
            var enderecoAnos = ENDERECO + opcaoConsulta + "/marcas/" + opcaoMarca + "/modelos/" + opcaoModelo + "/anos/" + modelosAnos.get(i).codigo();
            json = requisicaoAPI.obterDados(enderecoAnos);
            DadosVeiculo veiculo = conversor.obterDados(json, DadosVeiculo.class);
            veiculos.add(veiculo);
        }

        System.out.println("\nTodos os veículos filtrados com avaliações por ano:");
        veiculos.forEach(System.out::println);


    }
}
