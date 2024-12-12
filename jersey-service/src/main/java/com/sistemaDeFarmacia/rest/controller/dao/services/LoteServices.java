package com.sistemaDeFarmacia.rest.controller.dao.services;
import com.sistemaDeFarmacia.rest.controller.dao.LoteDao;
import com.sistemaDeFarmacia.rest.controller.tda.list.LinkedList;
import com.sistemaDeFarmacia.rest.models.Lote;

public class LoteServices {
    private LoteDao obj;

    public LoteServices() {
        this.obj = new LoteDao();
    }

    public Boolean save() throws Exception {
        return this.obj.save();
    }

    public LinkedList<Lote> listAll() {
        return this.obj.listAll();
    }

    public Lote getLote() {
        return this.obj.getLote();
    }

    public void setLote(Lote lote) {
        this.obj.setLote(lote);
    }

    public LoteDao getLoteDao() {
        return this.obj;
    }

    public Lote get(Integer id) throws Exception {
        return (Lote) obj.get(id);
    }

    public Boolean update() throws Exception {
        return obj.update();
    }
}