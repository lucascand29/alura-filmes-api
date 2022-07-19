import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {
        //Fazer uma conexão HTTP e buscar os top 250 fimes
        String url = "https://mocki.io/v1/9a7c1ca9-29b4-4eb3-8306-1adb9d159060";
        URI endereco = URI.create(url);
        var client = HttpClient.newHttpClient(); 
        var request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();


        // Pegar só os dados que interessam(titulo, poster, classificação)
        var parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);
        
        // exibir e manipular os dados
        for (Map<String,String> filme : listaDeFilmes) {
            String titulo = filme.get("title");
            String imagem = filme.get("image");
            String avaliacao = filme.get("imDbRating");

            System.out.println("\u001b[1m \u001b[30m \u001b[42m Nome do filme: \u001b[m"+ " \u001b[1m "+ titulo + "\u001b[m");
            System.out.println("\u001b[1m \u001b[30m \u001b[42m Poster: \u001b[m" +" \u001b[3m " + imagem +"\u001b[m");
            System.out.println("\u001b[1m \u001b[30m \u001b[42m Avaliacao: \u001b[m" + " \u001b[1m "+ avaliacao + "\u001b[m");
            System.out.println();
        }

    }
}
