/*package com.sistemaDeFarmacia.rest;
import com.sistemaDeFarmacia.rest.models.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {
    private static final String FILE_PATH = "media/Users.json";
    private ObjectMapper objectMapper = new ObjectMapper();

    public List<User> findAll() throws IOException {
        return objectMapper.readValue(new File(FILE_PATH), new TypeReference<List<User>>() {});
    }

    public Optional<User> findByUsername(String username) throws IOException {
        return findAll().stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst();
    }

    public Optional<User> findByEmail(String email) throws IOException {
        return findAll().stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst();
    }

    public void saveAll(List<User> users) throws IOException {
        objectMapper.writeValue(new File(FILE_PATH), users);
    }
}*/

