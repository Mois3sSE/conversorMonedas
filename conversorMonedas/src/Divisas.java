public class Divisas {
    String divisas[] = new String[2]; 
    String divisaAceptadas [] = {"USD","ARS","BRL","COP","MXN"}; 

    private boolean comprobacionDivisas(String divisa){
        int i; 
        for ( i = 0; i < divisaAceptadas.length ; i++){
            if(divisa.equals(divisaAceptadas[i]))
            return true; 
        }

        return false; 
    }

    public void setDivisa1(String divisa){
        this.divisas[0] = divisa;
        if(!comprobacionDivisas(this.divisas[0])){
            System.out.println("El valor ingresado para la divisa no es valida\nComprueba la lista " +
            "de conversiones validas ");
            System.exit(0);  
        }
    }
    public void setDivisa2(String divisa){
        this.divisas[1] = divisa;
        if(!comprobacionDivisas(this.divisas[1])){
            System.out.println("El valor ingresado para la divisa no es valida\nComprueba la lista " +
            "de conversiones validas ");
            System.exit(0); 
        } 
    }
    public String getDivisa1(){
        return divisas[0]; 
    }
    public String getDivisa2(){
        return divisas[1]; 
    }
    
}
