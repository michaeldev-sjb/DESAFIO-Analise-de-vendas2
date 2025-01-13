
import entities.Sale;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Entre com o caminho do arquivo: ");
        String path = "/Users/michaelgoncalves/Developer/DevSuperior/DesafioAnaliseDeVendas2/in.csv";
        System.out.println();
        List<Sale> sales = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {

            String line = br.readLine();
            while (line != null) {
                String[] fields = line.split(",");
                sales.add(new Sale(
                        Integer.parseInt(fields[0]),
                        Integer.parseInt(fields[1]),
                        fields[2],
                        Integer.parseInt(fields[3]),
                        Double.parseDouble(fields[4])));
                line = br.readLine();
            }

            Map<String, Double> newList = sales.stream()
                    .collect(Collectors.groupingBy(
                            Sale::getSeller,
                            Collectors.summingDouble(Sale::getTotal)));

            newList.forEach((name, total) -> {
                System.out.println(name + " - R$ " + String.format("%.2f", total));
            }

            );

        } catch (Exception e) {
            System.out.println("Erro: " + path + " (O sistema não pode encontrar o arquivo especificado)");
        }
        scanner.close();
    }
}
