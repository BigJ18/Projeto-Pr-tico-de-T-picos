import controller.ConsultaViaCEP;
import dto.EnderecoDTO;
import exception.CepInvalidoException;
import exception.CepNaoEncontradoException;
import service.CriaArquivoJSON;

import java.io.IOException;
import java.util.Scanner;

public class PrincipalComBusca {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ConsultaViaCEP consultaViaCEP = new ConsultaViaCEP();
        CriaArquivoJSON criaArquivoJson = new CriaArquivoJSON();

        while (true) {
            System.out.println("Digite um CEP ou SAIR: ");
            String busca = scanner.nextLine();

            if (busca.equalsIgnoreCase("SAIR")) {
                break;
            }

            try {
                EnderecoDTO enderecoDTO = consultaViaCEP.consultarCEP(busca);
                System.out.println(enderecoDTO);
                criaArquivoJson.salvarEnderecoEmArquivo(enderecoDTO);
            } catch (CepInvalidoException | CepNaoEncontradoException e) {
                System.out.println(e.getMessage());
            } catch (IOException | InterruptedException e) {
                System.out.println("Erro ao consultar o CEP: " + e.getMessage());
            }
        }
        scanner.close();
        System.out.println("Sistema fechado.");
    }
}
