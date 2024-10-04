package controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dto.EnderecoDTO;
import exception.CepInvalidoException;
import exception.CepNaoEncontradoException;
import model.Endereco;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsultaViaCEP {

    public EnderecoDTO consultarCEP(String cep) throws IOException, InterruptedException {
        validarCep(cep);

        String enderecoURL = "https://viacep.com.br/ws/" + cep + "/json";
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(enderecoURL))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        JsonObject jsonObject = JsonParser.parseString(response.body()).getAsJsonObject();
        if (jsonObject.has("erro")) {
            throw new CepNaoEncontradoException("CEP não encontrado.");
        }

        Gson gson = new Gson();
        Endereco endereco = gson.fromJson(response.body(), Endereco.class);

        return new EnderecoDTO(endereco.getCep(), endereco.getUf(), endereco.getLocalidade(),
                endereco.getBairro(), endereco.getLogradouro(), endereco.getComplemento());
    }

    private void validarCep(String cep) {
        if (!cep.matches("\\d{8}")) {
            throw new CepInvalidoException("CEP inválido. O CEP deve conter 8 dígitos numéricos.");
        }
    }
}
