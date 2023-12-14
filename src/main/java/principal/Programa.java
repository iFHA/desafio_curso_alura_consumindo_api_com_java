package principal;

import com.google.gson.Gson;
import modelos.Endereco;
import services.EnderecoService;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Programa {
    private final Scanner scanner;
    private final EnderecoService service;
    private final List<Endereco> listaCeps = new ArrayList<>();
    private final Gson gson = new Gson();

    public Programa() {
        this.scanner = new Scanner(System.in);
        this.service = new EnderecoService();
    }

    public void play() throws IOException, InterruptedException {
        while (true) {
            try {
                System.out.println("Informe o CEP ou digite \"Sair\" para finalizar o programa:");
                String input = this.scanner.nextLine();
                if ("sair".equalsIgnoreCase(input)) {
                    break;
                }
                Endereco endereco = this.service.getDadosDoCEP(input);
                this.listaCeps.add(endereco);
            } catch (Exception e) {
                System.out.println("Ocorreu um erro: " + e.getMessage());
            }
        }
        String nomeDoArquivo = "ceps_consultados_"
                + System.currentTimeMillis()
                + ".json";
        escreveNoArquivo(nomeDoArquivo);
    }

    private void escreveNoArquivo(String arquivo) {
        try (FileWriter fw = new FileWriter(arquivo)) {
            String json = gson.toJson(this.listaCeps);
            fw.write(json);
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
