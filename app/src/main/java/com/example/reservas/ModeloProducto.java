package com.example.reservas;

public class ModeloProducto {

    private int id, telefono;
    private String nombre, carnet, departamento;
    private float monto, saldo;

    public ModeloProducto(int id, String nombre, String carnet, int telefono,
                          String departamento, float monto, float saldo) {
        this.id = id;
        this.telefono = telefono;
        this.nombre = nombre;
        this.carnet = carnet;
        this.departamento = departamento;
        this.monto = monto;
        this.saldo = saldo;
    }

    public ModeloProducto(String nombre, String carnet, int telefono,
                          String departamento, float monto, float saldo) {
        this.telefono = telefono;
        this.nombre = nombre;
        this.carnet = carnet;
        this.departamento = departamento;
        this.monto = monto;
        this.saldo = saldo;
    }

    public int getId() {
        return id;
    }

    public int getTelefono() {
        return telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCarnet() {
        return carnet;
    }

    public String getDepartamento() {
        return departamento;
    }

    public float getMonto() {
        return monto;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCarnet(String carnet) {
        this.carnet = carnet;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public void setMonto(float monto) {
        this.monto = monto;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    @Override
    public String toString() {
        return "ModeloProducto{" +
                "id=" + id +
                ", telefono=" + telefono +
                ", nombre='" + nombre + '\'' +
                ", carnet='" + carnet + '\'' +
                ", departamento='" + departamento + '\'' +
                ", monto=" + monto +
                ", saldo=" + saldo +
                '}';
    }
}
