package com.tercerotest.controller.dao.implement;

import com.tercerotest.controller.tda.Lote;
import com.tercerotest.controller.tda.LinkedList;

public class LoteDao extends AdapterDao {
    private Lote lote;
    private LinkedList<Lote> listAll;

    public LoteDao() {
        super(Lote.class);
    }

    public void setLote(Lote lote) {
        this.lote = lote;
    }

    public Lote getLote() {
        if (this.lote == null) {
            this.lote = new Lote();
        }
        return this.lote;
    }

    public LinkedList<Lote> getListAll() {
        if(listAll == null){
            this.listAll = listAll();
        }
        return this.listAll;
    }

    public Boolean save() throws Exception {
        Integer id = getListAll().getSize() + 1;
        lote.setId(id);
        this.persist(this.lote);
        this.listAll = listAll();
        return true;
    }

    public Boolean update() throws Exception {
        Integer index = getLote().getIdLote() - 1;

        if (index < 0 || index >= getListAll().getSize()) {
            throw new Exception("Índice de Lote inválido");
        }

        this.merge(getLote(), index);
        this.listAll = listAll();

        return true;
    }
}
