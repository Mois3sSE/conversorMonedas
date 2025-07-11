public class Conversor {
    String divisaBase,divisaConversion,tasaDeConversion,fechaActual; 
    float conversion,saldoConvertido; 

    public Conversor(ConversorRecord conversorRecord,float saldo,String fecha){
        this.divisaBase = conversorRecord.base_code(); 
        this.divisaConversion = conversorRecord.target_code(); 
        this.tasaDeConversion = conversorRecord.conversion_rate(); 
        this.conversion = Float.parseFloat(conversorRecord.conversion_result());
        this.saldoConvertido = saldo;
        this.fechaActual = fecha;   
    } 
    @Override
    public String toString() {
        return fechaActual +"\n"+ saldoConvertido + " " + divisaBase + " = " + conversion + " " + divisaConversion +
        "\nA una tasa de cambio de " + tasaDeConversion ;
    }
}
