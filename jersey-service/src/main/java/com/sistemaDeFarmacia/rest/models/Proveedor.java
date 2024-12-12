package com.sistemaDeFarmacia.rest.models;
import com.sistemaDeFarmacia.rest.models.enumerador.TipoProducto;
public class Proveedor extends Persona {
    private Integer id;
    private String nombreEmpresa;
    private Enum tipoProductos;
    private String pedidos;
    private Boolean productosDisponibles;
    
    public Proveedor() {
    }

    public Proveedor(Integer id, String nombreEmpresa, Enum tipoProductos, String pedidos, Boolean productosDisponibles) {
        this.id = id;
        this.nombreEmpresa = nombreEmpresa;
        this.tipoProductos = tipoProductos;
        this.pedidos = pedidos;
        this.productosDisponibles = productosDisponibles;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreEmpresa() {
        return this.nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public Enum getTipoProductos() {
        return this.tipoProductos;
    }

    public void setTipoProductos(Enum tipoProductos) {
        this.tipoProductos = tipoProductos;
    }

    public String getPedidos() {
        return this.pedidos;
    }

    public void setPedidos(String pedidos) {
        this.pedidos = pedidos;
    }

    public Boolean isProductosDisponibles() {
        return this.productosDisponibles;
    }

    public Boolean getProductosDisponibles() {
        return this.productosDisponibles;
    }

    public void setProductosDisponibles(Boolean productosDisponibles) {
        this.productosDisponibles = productosDisponibles;
    }
    public TipoProducto getTipoProducto() {
        return getTipoProducto();
    }
    public void setTipoProducto(TipoProducto tipoProducto) {
        this.tipoProductos = tipoProducto;
    }
    
    
}
