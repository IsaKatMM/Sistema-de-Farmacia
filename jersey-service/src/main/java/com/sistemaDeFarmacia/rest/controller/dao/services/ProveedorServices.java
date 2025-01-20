package com.sistemaDeFarmacia.rest.controller.dao.services;

import com.sistemaDeFarmacia.rest.controller.dao.ProveedorDao;
import com.sistemaDeFarmacia.rest.controller.tda.list.LinkedList;
import com.sistemaDeFarmacia.rest.models.Proveedor;
import com.sistemaDeFarmacia.rest.models.enumerador.TipoProducto;
import java.io.FileReader;
import java.nio.file.Paths;
import java.util.HashMap;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;

public class ProveedorServices {
    private static final String FILE_PATH = "media/Proveedor.json";
    private ProveedorDao obj;

    public ProveedorServices() {
        obj = new ProveedorDao();
    }

    public Boolean save() throws Exception {
        return obj.save();
    }

    public LinkedList listAll() {
        return obj.getListAll();
    }

    public Proveedor getProveedor() {
        return obj.getProveedor();
    }

    public void setProveedor(Proveedor proveedor) {
        obj.setProveedor(proveedor);
    }

    public Proveedor get(Integer id) throws Exception {
        return obj.get(id);
    }

    public TipoProducto getTipoProducto(String tipo) {
        return obj.getTipoProductos(tipo);
    }

    public TipoProducto[] getTipos() {
        return obj.getTipos();
    }
    
    public boolean telefonoEmpresaExiste(String telefono, String nombreEmpresa) {
        boolean existe = false;

        try {
            Gson gson = new Gson();
            Type listType = new TypeToken<List<HashMap<String, Object>>>() {}.getType();
            List<HashMap<String, Object>> proveedores = gson.fromJson(new FileReader(Paths.get(FILE_PATH).toFile()), listType);

            for (HashMap<String, Object> proveedor : proveedores) {
                if (telefono.equals(proveedor.get("telefono").toString()) || nombreEmpresa.equals(proveedor.get("nombreEmpresa").toString())) {
                    existe = true;
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return existe;
    }
}

