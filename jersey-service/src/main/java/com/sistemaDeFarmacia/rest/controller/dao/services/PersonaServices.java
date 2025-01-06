package com.sistemaDeFarmacia.rest.controller.dao.services;

import com.sistemaDeFarmacia.rest.controller.dao.PersonaDao;
import com.sistemaDeFarmacia.rest.controller.tda.list.LinkedList;
import com.sistemaDeFarmacia.rest.models.Persona;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class PersonaServices {
    private PersonaDao obj;
    private static final String JSON_FILE_PATH = "media/Persona.json";

    public boolean isCedulaDuplicada(String cedula) {
        boolean duplicada = false;

        try (FileReader reader = new FileReader(JSON_FILE_PATH)) {
            // Leer el archivo JSON y convertirlo a una lista de objetos Persona
            Gson gson = new Gson();
            Type personaListType = new TypeToken<List<Persona>>() {
            }.getType();
            List<Persona> personas = gson.fromJson(reader, personaListType);

            // Verificar si la cédula ya está en la lista de personas
            for (Persona persona : personas) {
                if (cedula != null && cedula.equals(persona.getCedula())) {
                    duplicada = true;
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return duplicada;
    }

    public PersonaServices() {
        obj = new PersonaDao();
    }

    public Boolean save() throws Exception {
        return obj.save();
    }

    public LinkedList listAll() {
        return obj.getListAll();
    }

    public Persona getPersona() {
        return obj.getPersona();
    }

    public void setPersona(Persona persona) {
        obj.setPersona(persona);
    }

    public Persona get(Integer id) throws Exception {
        return obj.get(id);
    }

}
