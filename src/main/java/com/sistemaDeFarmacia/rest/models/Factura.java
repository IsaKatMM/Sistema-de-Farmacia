package com.sistemaDeFarmacia.rest.models;
import java.util.Date;
import com.sistemaDeFarmacia.models.enumerador.MetodoPago;

public class Factura {
    private Integer id_factura;
    private Date fechaEmision;
    private MetodoPago metodoPago;
    private String observaciones;
    private float subTotal;
    private float IVA;
    private float total_USD;

    public Factura(Integer id_factura, Date fechaEmision, MetodoPago metodoPago, String observaciones, float subTotal, float IVA, float total_USD) {
        this.id_factura = id_factura;
        this.fechaEmision = fechaEmision;
        this.metodoPago = metodoPago;
        this.observaciones = observaciones;
        this.subTotal = subTotal;
        this.IVA = IVA;
        this.total_USD = total_USD;
    }

    public Integer getId_factura() {
        return this.id_factura;
    }

    public void setId_factura(Integer id_factura) {
        this.id_factura = id_factura;
    }

    public Date getFechaEmision() {
        return this.fechaEmision;
    }

    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public MetodoPago getMetodoPago() {
        return this.metodoPago;
    }

    public void setMetodoPago(MetodoPago metodoPago) {
        this.metodoPago = metodoPago;
    }

    public String getObservaciones() {
        return this.observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public float getSubTotal() {
        return this.subTotal;
    }

    public void setSubTotal(float subTotal) {
        this.subTotal = subTotal;
    }

    public float getIVA() {
        return this.IVA;
    }

    public void setIVA(float IVA) {
        this.IVA = IVA;
    }

    public float getTotal_USD() {
        return this.total_USD;
    }

    public void setTotal_USD(float total_USD) {
        this.total_USD = total_USD;
    }

}