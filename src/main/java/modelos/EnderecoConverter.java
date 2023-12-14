package modelos;

public class EnderecoConverter {
    public final Endereco fromDTO(EnderecoDTO dto) {
        return new Endereco(
                dto.cep(),
                dto.logradouro(),
                dto.complemento(),
                dto.bairro(),
                dto.localidade(),
                dto.uf()
        );
    }
}
