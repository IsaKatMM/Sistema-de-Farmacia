package com.tercerotest.controller.dao.services;

import com.tercerotest.controller.dao.implement.LoteDao;
import com.tercerotest.controller.tda.Lote;
import com.tercerotest.controller.tda.LinkedList;

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
