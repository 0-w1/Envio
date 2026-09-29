package logica;

public class EnvioExpress implements Envio {
    private String codigo;
    private String destinatario;
    private double peso;
    private String horaLimite;

    public EnvioExpress(String codigo, String destinatario, double peso, String horaLimite) {
        this.codigo = codigo;
        this.destinatario = destinatario;
        this.peso = peso;
        this.horaLimite = horaLimite;
    }

    @Override
    public double calcularCosto() {
        return (peso * 5000) + 15000;
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
        return "Express";
    }

    @Override
    public String getDatoAdicional() {
        return horaLimite;
    }
}
