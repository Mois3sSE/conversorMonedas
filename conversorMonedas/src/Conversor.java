public class Conversor {
    String divisaBase,divisaConversion,tasaDeConversion; 
    float conversion,saldo; 

    public Conversor(ConversorRecord conversorRecord,float saldo){
        this.divisaBase = conversorRecord.base_code(); 
        this.divisaConversion = conversorRecord.target_code(); 
        this.tasaDeConversion = conversorRecord.conversion_rate(); 
        this.conversion = Float.parseFloat(conversorRecord.conversion_result());
        this.saldo = saldo;  
    } 
    @Override
    public String toString() {
        return saldo + " " + divisaBase + " = " + conversion + " " + divisaConversion +
        "\nA una tasa de cambio de " + tasaDeConversion;
    }
}
