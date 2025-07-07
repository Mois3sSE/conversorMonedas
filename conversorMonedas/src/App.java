import java.net.URI;
import java.net.http.*;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Scanner;


public class App {
    static String [] divisas = new String[2]; 
    private static void mensajeBienvenida(){
        Scanner scan = new Scanner (System.in); 
        int seleccion = 0 ; 

        do {
        System.out.println("============== Conversor de Divisas ==============");
        System.out.println("Selecciona una opcion\n1.- Manual de uso\n2.- Iniciar Programa");
        seleccion = scan.nextInt(); 
     
        switch (seleccion) {
            case 1: 
                manualDeUso();
            break; 
            case 2: 
                seleccionDivisas();
            break; 
            default: 
                 System.out.println("El valor ingresado no es valido\nSolo son validos 1 y 2 ");
        }
        } while ( seleccion != 2 ); 

    }
    private static void manualDeUso(){
        System.out.println("La siguiente aplicacion funciona de la manera siguiente" +
        "\nPrimero se debe de ingresar la divisa de la que se quiere tomar como base de cambio," +
        "posteriormente se debe de colocar el valor de la divisa a realizar el cambio, ademas\n" +
        "los valores de las monedas hacen unso de ISO 4217 con 3 letras como codigo" + 
        "Ejemplo :\n\nIngresa la divisa de base : USD\nIngresa la divisa a cambiar : MXN\nSalida: "
        + "1 USD = 18.69 MXN " );
    }
    private static void seleccionDivisas (){
       
        Scanner scan = new Scanner (System.in); 
        System.out.print("Ingresa la divisa base a utilizar : ");
        divisas[0] = scan.nextLine();
        System.out.print("Ingresa la divisa a la que se busca realizar el cambio : ");
        divisas[1] = scan.nextLine();
            
    }

    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner (System.in); 
        mensajeBienvenida();
    // Realizacion del cambio de las divisas y muestra de conexion entre DIVISAS
        
        String direccion = "https://v6.exchangerate-api.com/v6/400da96517ef351effc4900a/pair/" + divisas[0] + "/" + divisas[1] ; 

         HttpClient client = HttpClient.newHttpClient();
         HttpRequest request = HttpRequest.newBuilder()
         .uri(URI.create(direccion))
         .build();
         HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

         System.out.println(response.body());

         scan.close();

    }
}
