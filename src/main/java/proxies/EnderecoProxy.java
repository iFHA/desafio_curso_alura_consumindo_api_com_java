package proxies;

import com.google.gson.Gson;
import modelos.EnderecoDTO;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class EnderecoProxy {
    private final HttpClient client = HttpClient.newHttpClient();;
    private final String baseUrl = "https://viacep.com.br/ws";
    private final String regexCepValido = "^[0-9]{8}$";
    private final Gson gson = new Gson();

    public EnderecoDTO obterDadosDoCEP(String cep) throws IOException, InterruptedException {
        this.verificaCEP(cep);

        HttpResponse<String> response = this.get(this.montaUriDaRequisicao(cep));

        return this.gson.fromJson(response.body(), EnderecoDTO.class);
    }
    private void verificaCEP(String cep) {
        if(!cep.matches(this.regexCepValido)) {
            throw new IllegalArgumentException("CEP(" + cep + ") inválido!");
        }
    }
    private HttpResponse<String> get(String uri) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .build();

        return this.client.send(request, HttpResponse.BodyHandlers.ofString());
    }
    private String montaUriDaRequisicao(String cep) {
        return this.baseUrl
                + "/"
                + cep
                + "/json";
    }
}
