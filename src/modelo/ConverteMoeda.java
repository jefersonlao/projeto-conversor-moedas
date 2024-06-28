package modelo;

import java.util.Scanner;

public class ConverteMoeda {
    Scanner leitura = new Scanner(System.in);
    ImportaApi importaApi = new ImportaApi();
    private String outraMoedaFinal;
    private String outraMoedaInicial;

    public void menuPrincipal(){
        int opcao = 0;
        
        while(opcao != 10) {
            System.out.println("""
                    =====================================
                    Seja bem vindo ao conversor de moeda!
                    =====================================
                    Selecione uma opção:
                    
                    1 - Peso Argentino(ARS) para Dólar(USD).
                    2 - Peso Argentino(ARS) para Real Brasileiro(BRL).                    
                    3 - Peso chileno(CLP) para Dólar(USD).
                    4 - Peso chileno(CLP) para Real(BRL).
                    5 - Real brasileiro(BRL) para Dólar(USD).
                    6 - Real brasileiro(BRL) para peso Chileno(CLP).
                    7 - Peso colombiano(COP) para Dólar(USD).
                    8 - Peso boliviano(BOB) para Dólar(USD).
                    9 - Converter outras moedas.
                    10 - SAIR
                    """);
            System.out.println("Digite a opção desejada: ");
            opcao = leitura.nextInt();

            switch(opcao) {
                case 1:
                    realizarConversao("ARS", "USD", "Peso Argentino", "Dólar");
                    break;
                case 2:
                    realizarConversao("ARS", "BRL", "Peso Argentino", "Real Brasileiro" );
                    break;
                case 3:
                    realizarConversao("CLP", "USD", "Peso Chileno", "Dólar");
                    break;
                case 4:
                    realizarConversao("CLP", "BRL", "Peso Chileno", "Real Brasileiro");
                    break;
                case 5:
                    realizarConversao("BRL", "USD", "Real Brasileiro", "Dólar");
                    break;
                case 6:
                    realizarConversao("BRL", "CLP", "Real Brasileiro", "Peso Chileno");
                    break;
                case 7:
                    realizarConversao("COP", "USD", "Peso Colombiano", "Dólar");
                    break;
                case 8:
                    realizarConversao("BOB", "USD", "Peso Boliviano", "Dólar");
                    break;
                case 9:
                    outraConversao(outraMoedaInicial, outraMoedaFinal);
                    break;
                case 10:
                    System.out.println("Fechando o programa...");
                    System.exit(0);
                default:
                    System.out.println("Opção inválida! Selecione outra opção.");
                    break;
            }
        }
    }
    private void outraConversao(String moedaInicial, String moedaFinal) {
        this.outraMoedaInicial = moedaInicial;
        this.outraMoedaFinal = moedaFinal;

        System.out.println("Digite a sigla da moeda (ISO4217) que deseja converter: ");
        moedaInicial = leitura.next();
        System.out.println("Digite a sigla da moeda para a qual deseja converter (ISO4217): ");
        moedaFinal = leitura.next();
        System.out.println("Digite o valor que deseja converter: ");
        double valor = leitura.nextDouble();

        var moeda = importaApi.consultaMoeda(moedaInicial, moedaFinal);
        double moedaConvertida = Double.parseDouble(moeda.conversion_rate());
        double conversaoFinal = moedaConvertida * valor;

        System.out.printf("Valor de %s %f convertido para %s é de: %f %s ", moedaInicial, valor, moedaFinal, conversaoFinal, moedaFinal);
    }
    private void realizarConversao(String moedaInicial, String moedaFinal, String nomeDaInicial, String nomeDaFinal) {
        System.out.println(nomeDaInicial + " ===> " + nomeDaFinal);
        System.out.println("Digite o valor para converter? ");
        double valor = leitura.nextDouble();

        var moeda = importaApi.consultaMoeda(moedaInicial, moedaFinal);
        double moedaConvertida = Double.parseDouble(moeda.conversion_rate());

        double valorConvertido = valor * moedaConvertida;

        System.out.printf("O valor de %.2f %s convertido para %s é igual a %.5f %s. ",
                valor, nomeDaInicial, nomeDaFinal, valorConvertido, nomeDaFinal);
    }
}
