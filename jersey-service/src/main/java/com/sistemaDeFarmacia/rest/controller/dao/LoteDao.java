package com.sistemaDeFarmacia.rest.controller.dao;

import com.google.gson.Gson;
import com.sistemaDeFarmacia.rest.controller.dao.implement.AdapterDao;
import com.sistemaDeFarmacia.rest.controller.tda.list.LinkedList;
import com.sistemaDeFarmacia.rest.models.Lote;
import com.sistemaDeFarmacia.rest.models.Producto;
import com.sistemaDeFarmacia.rest.controller.dao.ProductoDao;

public class LoteDao extends AdapterDao<Lote> {
    private Lote lote;
    private LinkedList<Lote> listAll;
    private Gson g = new Gson();
    private ProductoDao productoDao;

    public LoteDao() {
        super(Lote.class);
        this.productoDao = new ProductoDao();
    }

    public Lote getLote() {
        if (this.lote == null) {
            this.lote = new Lote();
        }
        return this.lote;
    }

    public void setLote(Lote lote) {
        this.lote = lote;
    }

    public LinkedList<Lote> getListAll() throws Exception {
        if (listAll == null) {
            this.listAll = listAll();
        }
        return listAll;
    }

    public Boolean saveWithProduct(String nombreProducto) throws Exception {
        // Obtener el producto asociado al lote por nombre
        Producto producto = null;
        LinkedList<Producto> productos = productoDao.getListAll();
        for (int i = 0; i < productos.getSize(); i++) {
            Producto p = productos.get(i);
            if (p.getNombreProducto().equalsIgnoreCase(nombreProducto)) {
                producto = p;
                break;
            }
        }
        if (producto == null) {
            throw new Exception("Producto no encontrado");
        }
    
        // Asignar el producto al lote
        Lote lote = getLote();
        lote.setProducto(producto);
    
        // Generar un ID de lote (por ejemplo, basado en el tamaño de la lista actual)
        Integer idLote = getListAll().getSize() + 1;
    
        // Guardar el lote
        return save(idLote);
    }

    public Boolean save(Integer idLote) throws Exception {
        Lote lote = getLote(); // Obtener la instancia de Lote
        lote.setId(idLote); // Asignar el ID al lote (asegúrate de que setIdLote exista)
        this.persist(this.lote); // Persistir el lote
        this.listAll = listAll(); // Actualizar la lista de lotes
        return true;
    }

    public Boolean update() throws Exception {
        this.merge(getLote(), getLote().getIdLote() - 1);
        this.listAll = listAll();
        return true;
    }

    public LinkedList<Lote> order(Integer type_order, String atributo) {
        LinkedList<Lote> listita = listAll();
        if (!listAll().isEmpty()) {
            Lote[] lista = (Lote[]) listita.toArray();
            listita.reset();
            for (int i = 1; i < lista.length; i++) {
                Lote aux = lista[i];
                int j = i - 1;
                while (j >= 0 && (verify(lista[j], aux, type_order, atributo))) {
                    lista[j + 1] = lista[j--];
                }
                lista[j + 1] = aux;
            }
            listita.toList(lista);
        }
        return listita;
    }

    private Boolean verify(Lote a, Lote b, Integer type_order, String atributo) {
        if (type_order == 1) {
            if (atributo.equalsIgnoreCase("fechaEntrega")) {
                return a.getFechaEntrega().compareTo(b.getFechaEntrega()) > 0;
            } else if (atributo.equalsIgnoreCase("idLote")) {
                return a.getIdLote() > b.getIdLote();
            }
        } else {
            if (atributo.equalsIgnoreCase("fechaEntrega")) {
                return a.getFechaEntrega().compareTo(b.getFechaEntrega()) < 0;
            } else if (atributo.equalsIgnoreCase("idLote")) {
                return a.getIdLote() < b.getIdLote();
            }
        }
        return false;
    }

    public LinkedList<Lote> searchByProducto(String nombreProducto) throws Exception {
        LinkedList<Lote> result = new LinkedList<>();
        LinkedList<Lote> allLotes = getListAll();
    
        for (int i = 0; i < allLotes.getSize(); i++) {
            Lote lote = allLotes.get(i);
            if (lote.getProducto().getNombreProducto().equalsIgnoreCase(nombreProducto)) {
                result.add(lote);
            }
        }
    
        return result;
    }
}