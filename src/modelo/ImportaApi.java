package modelo;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

public class ImportaApi {

    public Conversor consultaMoeda(String moedaInicial, String moedaFinal) {
        String chave = "cdd7dbe45be2876ae905fe5d";
        String url = "https://v6.exchangerate-api.com/v6/" + chave + "/pair/" + moedaInicial + "/" + moedaFinal;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            //Teste de conexão com a api:
            if(response.statusCode() != 200) {
                throw new RuntimeException("Falha na requisicao para API. Código de erro: " + response.statusCode());
            }

            //Convertendo para Json
            JsonObject json = JsonParser.parseString(response.body()).getAsJsonObject();

            String baseCode = json.get("base_code").getAsString();
            String targetCode = json.get("target_code").getAsString();
            double conversionRate = json.get("conversion_rate").getAsDouble();

            return new Conversor(baseCode, targetCode, String.valueOf(conversionRate));

        } catch (InterruptedException | IOException e) {
            throw new RuntimeException("Moeda inexistente.", e);
        } catch (JsonSyntaxException e) {
            throw new RuntimeException("Erro com o retorno da API. Verifique e tente novamente.", e);
        }

    }
}

