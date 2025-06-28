package cl.duocuc.ecomarket.tipodatos;

public enum TipoCuenta {
    CLIENTE((byte) 1),
    EMPLEADO((byte) 2);

    final byte id;

    TipoCuenta(byte id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
