package service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.EnderecoDTO;

import java.io.FileWriter;
import java.io.IOException;

public class CriaArquivoJSON {

    public void salvarEnderecoEmArquivo(EnderecoDTO enderecoDTO) throws IOException {
        String nomeArquivo = enderecoDTO.cep() + ".json";
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(enderecoDTO);

        try (FileWriter writer = new FileWriter(nomeArquivo)) {
            writer.write(json);
        }
        System.out.println("Endereço salvo no arquivo: " + nomeArquivo);
    }
}
