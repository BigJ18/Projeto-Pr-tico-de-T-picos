package dto;

public record EnderecoDTO(String cep, String uf, String cidade, String bairro, String logradouro, String complemento) {

    @Override
    public String toString() {
        return String.format("Endereço: %s, %s, %s - %s, CEP: %s, Complemento: %s",
                logradouro, bairro, cidade, uf, cep, (complemento != null && !complemento.isEmpty()) ? complemento : "N/A");
    }
}
