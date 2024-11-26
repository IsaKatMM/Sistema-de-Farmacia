package com.sistemaDeFarmacia.rest.controller.dao.implement;

import com.sistemaDeFarmacia.rest.controller.tda.list.LinkedList;

public interface InterfazDao <T> {
    public void persist(T object) throws Exception;
    public void merge(T object, Integer index) throws Exception;
    public LinkedList listAll();
    public T get(Integer id) throws Exception;  
      
}

