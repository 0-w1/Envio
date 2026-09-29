package logica;

public class EnvioInternacional implements Envio {
    private String codigo;
    private String destinatario;
    private double peso;
    private String paisDestino;

    public EnvioInternacional(String codigo, String destinatario, double peso, String paisDestino) {
        this.codigo = codigo;
        this.destinatario = destinatario;
        this.peso = peso;
        this.paisDestino = paisDestino;
    }

    @Override
    public double calcularCosto() {
        double costoBase = peso * 12000;
        double impuesto = costoBase * 0.10;
        double seguro = costoBase * 0.05;
        return costoBase + impuesto + seguro;
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
        return "Internacional";
    }

    @Override
    public String getDatoAdicional() {
        return paisDestino;
    }
}
