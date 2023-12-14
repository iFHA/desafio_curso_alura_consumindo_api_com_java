package principal;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

public class Principal {
    public static void main(String[] args) throws IOException, InterruptedException {
        Programa p = new Programa();
        p.play();
    }
}
