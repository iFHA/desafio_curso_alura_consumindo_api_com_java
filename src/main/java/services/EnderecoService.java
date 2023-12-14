package services;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import modelos.Endereco;
import modelos.EnderecoConverter;
import modelos.EnderecoDTO;
import proxies.EnderecoProxy;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class EnderecoService {
    private final EnderecoConverter converter = new EnderecoConverter();
    private final EnderecoProxy proxy = new EnderecoProxy();
    public EnderecoService() {
    }
    public Endereco getDadosDoCEP(String cep) throws IOException, InterruptedException {
        return this.converter.fromDTO(this.proxy.obterDadosDoCEP(cep));
    }
}
