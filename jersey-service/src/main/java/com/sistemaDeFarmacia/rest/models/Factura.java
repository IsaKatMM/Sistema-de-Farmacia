package com.sistemaDeFarmacia.rest.models;
import java.util.Date;
import com.sistemaDeFarmacia.rest.models.enumerador.MetodoPago;

public class Factura {
    private Integer id;
    private Date fechaEmision;
    private MetodoPago metodoPago;
    private String observaciones;
    private float subTotal;
    private float IVA;
    private float total_USD;
    //private String nro_order;
    private Integer id_persona;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getId_persona() {
        return this.id_persona;
    }

    public void setId_persona(Integer id_persona) {
        this.id_persona = id_persona;
    }
}