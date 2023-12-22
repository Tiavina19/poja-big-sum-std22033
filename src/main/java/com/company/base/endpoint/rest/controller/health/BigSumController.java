import static java.nio.file.Paths.get;
import static spark.Spark.*;

import java.math.BigInteger;

public class Main {
    public static void main(String[] args) {
        port(8080); // Port sur lequel votre application va tourner

        // Endpoint GET /big-sum
        get("/big-sum", (req, res) -> {
            String aStr = req.queryParams("a");
            String bStr = req.queryParams("b");

            // Vérifier si les paramètres sont présents
            if (aStr != null && bStr != null) {
                try {
                    // Convertir les paramètres en BigInteger
                    BigInteger a = new BigInteger(aStr);
                    BigInteger b = new BigInteger(bStr);

                    // Effectuer la somme
                    BigInteger result = a.add(b);

                    // Retourner le résultat en tant que chaîne de caractères
                    return "Result: " + result.toString();
                } catch (NumberFormatException e) {
                    res.status(400); // Bad Request si les paramètres ne sont pas des nombres valides
                    return "Invalid input";
                }
            } else {
                res.status(400); // Bad Request si les paramètres sont manquants
                return "Missing parameters";
            }
        });
    }
}