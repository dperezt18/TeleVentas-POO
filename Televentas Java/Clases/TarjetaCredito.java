package Clases;

import java.time.LocalDate;

public class TarjetaCredito extends Pago {

    private String numero;
    private String titular;
    private LocalDate fechaVencimiento;
    private String cvv;

    public TarjetaCredito(int idPago, double monto, LocalDate fecha,
                          String numero, String titular,
                          LocalDate fechaVencimiento, String cvv) {
        super(idPago, monto, fecha);
        this.numero = numero;
        this.titular = titular;
        this.fechaVencimiento = fechaVencimiento;
        this.cvv = cvv;
    }

    @Override
    public boolean procesar() {
        System.out.println("Pago procesado correctamente");
        return true;
    }

    @Override
    public String obtenerEstado() {
        return "Aprobado";
    }

    public boolean validar() {
        System.out.println("Tarjeta validada correctamente");
        return true;
    }

    public boolean autorizar() {
        System.out.println("Pago autorizado");
        return true;
    }
}
