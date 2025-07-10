import java.net.URI;
import java.net.http.*;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.InputMismatchException;
import java.util.Scanner;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class App {
  
    private static void mensajeBienvenida(){
        System.out.println("============== Conversor de Divisas ==============");
        System.out.println("Selecciona una opcion\n1.- Manual de uso\n2.- Iniciar Programa");
    }
    private static void manualDeUso(){
        System.out.println("La siguiente aplicacion funciona de la manera siguiente" +
        "\nPrimero se debe de ingresar la divisa de la que se quiere tomar como base de cambio," +
        "posteriormente se debe de colocar el valor de la divisa a realizar el cambio, ademas\n" +
        "los valores de las monedas hacen unso de ISO 4217 con 3 letras como codigo\n" + 
        "Ejemplo :\nIngresa la divisa de base : USD\nIngresa la divisa a cambiar : MXN\nSalida: "
        + "1 USD = 18.69 MXN " );
         System.out.println("Por el momento solo se puede hacer uso de Dolar (USD), Peso argentino (ARS),\n" +
        "Real brasileño (BRL),Peso colombiano(COP) y Peso mexicano (MXN)");

    }

    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner (System.in); 
        int seleccion,rep = 1;
        float saldo = 0; 
        Divisas divisas = new Divisas();
        String divisaTemp; 
        boolean entradaValida = false ;  

        do{
        do {
        mensajeBienvenida();
        seleccion = scan.nextInt();
        if ( seleccion == 1 ){
            manualDeUso();
        } 
        } while (seleccion == 1); 
        scan.nextLine(); 

        System.out.print("Ingresa la divisa de base : ");
        divisaTemp = scan.nextLine().toUpperCase(); 
        divisas.setDivisa1(divisaTemp);

        System.out.print("Ingresa la divisa a cambiar : ");
        divisaTemp = scan.nextLine().toUpperCase(); 
        divisas.setDivisa2(divisaTemp); 
        
        while(!entradaValida){
            System.out.println("¿Cuantos " + divisas.getDivisa1() + " quieres cambiar a " 
            + divisas.getDivisa2() +" ? ");
            try{
                saldo = scan.nextFloat(); 
                entradaValida = true; 
            } catch (InputMismatchException e ){
                System.out.println("El dato ingresado para el saldo no es correcto\n" +
                "Coloca un saldo correcto");
                scan.nextLine(); 
            } 
        }   
        
        String direccion = "https://v6.exchangerate-api.com/v6/400da96517ef351effc4900a/pair/" + 
        divisas.getDivisa1()  + "/" + divisas.getDivisa2() + "/" + saldo  ; 

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(direccion))
        .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String json = response.body(); 
        // System.out.println(json);
         
        Gson gson = new GsonBuilder().setPrettyPrinting().create(); 
        ConversorRecord conversion = gson.fromJson(json,ConversorRecord.class); 
        // System.out.println(conversion);
        Conversor divisaConvertida = new Conversor(conversion,saldo); 
        System.out.println(divisaConvertida.toString()); 
        System.out.println("¿Quieres realizar otra conversion 1.- SI/0.- NO");
        rep = scan.nextInt(); 
        } while (rep == 1); 
        scan.close();

    }
}
