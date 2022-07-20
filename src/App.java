import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner (System.in);
        //Fazer uma conexão HTTP e buscar os top 250 fimes
        String url = "https://api.mocki.io/v2/549a5d8b/MostPopularMovies";
        //"https://mocki.io/v1/9a7c1ca9-29b4-4eb3-8306-1adb9d159060";
        URI endereco = URI.create(url);
        var client = HttpClient.newHttpClient(); 
        var request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();


        // Pegar só os dados que interessam(titulo, poster, classificação)
        var parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);
        
        // exibir e manipular os dados
        var geradora = new GeradoraDeFigurinhas();

        System.out.println("\u001b[1m \u001b[30m \u001b[42m       Catálogo de Filmes       \u001b[m");
        System.out.println();
       
        for (Map<String,String> filme : listaDeFilmes) {
            
            String titulo = filme.get("title");
            String imagem = filme.get("image");
            String avaliacao = filme.get("imDbRating");
            
            String urlImagem = imagem;
            InputStream inputStream = new URL(urlImagem).openStream();    
            String nomeArquivo = titulo + ".png";

            geradora.cria(inputStream, nomeArquivo);

            System.out.println("\u001b[1m \u001b[30m \u001b[42m Nome do filme: \u001b[m"+ " \u001b[1m "+ titulo + "\u001b[m");
            //System.out.println("\u001b[1m \u001b[30m \u001b[42m Poster: \u001b[m" +" \u001b[3m " + imagem +"\u001b[m");
            //System.out.println("\u001b[1m \u001b[30m \u001b[42m Avaliacao: \u001b[m" + " \u001b[1m "+ avaliacao + "\u001b[m");
            //int nota;
            //nota = (int) Double.parseDouble(avaliacao);
            //System.out.println("  \u2B50".repeat(nota));
            //System.out.println();
            
        }

        //System.out.println("Avalie um filme da posição 1 a 3");
        //    int posicao = sc.nextInt();
        //    posicao = posicao - 1;

        //    String titulo = listaDeFilmes.get(posicao).get("title");
        //    String imagem = listaDeFilmes.get(posicao).get("image");

        //    if(posicao < 1 || posicao > 3){
        //        System.out.println("Digito invalido!");
        //    }else{
        //        System.out.println("\u001b[1m \u001b[30m \u001b[42m Nome do filme: \u001b[m"+ " \u001b[1m "+ titulo + "\u001b[m");
        //        System.out.println();
        //        System.out.println("Qual a sua avaliação para esse filme? De 0 a 10");
        //        int nota = sc.nextInt();
        //        String avaliacao = String.valueOf(nota); 
        //        if(nota < 0 || nota > 10){
        //            System.out.println("Nota inválida!");
        //        }else{
        //            System.out.println("\u001b[1m \u001b[30m \u001b[42m Nome do filme: \u001b[m"+ " \u001b[1m "+ titulo + "\u001b[m");
        //            System.out.println("\u001b[1m \u001b[30m \u001b[42m Poster: \u001b[m" +" \u001b[3m " + imagem +"\u001b[m");
        //            System.out.println("\u001b[1m \u001b[30m \u001b[42m Nova Avaliação: \u001b[m" + " \u001b[1m "+ avaliacao + "\u001b[m");
        //            System.out.println("  \u2B50".repeat(nota));
        //        }
        //   }


    }
}
