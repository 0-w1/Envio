package logica;

public class EnvioEstandar implements Envio {
    private String codigo;
    private String destinatario;
    private double peso;
    private String direccion;

    public EnvioEstandar(String codigo, String destinatario, double peso, String direccion) {
        this.codigo = codigo;
        this.destinatario = destinatario;
        this.peso = peso;
        this.direccion = direccion;
    }

    @Override
    public double calcularCosto() {
        return peso * 5000;
    }

    @Override
    public String getCodigo() {
        return codigo;
    }

    @Override
    public String getDestinatario() {
        return destinatario;
    }

    @Override
    public double getPeso() {
        return peso;
    }

    @Override
    public String getTipo() {
        return "Estándar";
    }

    @Override
    public String getDatoAdicional() {
        return direccion;
    }
}
