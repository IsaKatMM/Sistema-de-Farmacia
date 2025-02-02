/*package com.sistemaDeFarmacia.rest;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
import com.sistemaDeFarmacia.rest.models.User;
import com.sistemaDeFarmacia.rest.controller.dao.services.UserService;


@Path("/log")
public class LoginApi {
     
    @Autowired
    private UserService userService;
    @Path("/login")
    @POST
    public Response login(@FormParam("username") String username, @FormParam("password") String password) {
        try {
            User user = userService.findByUsername(username)
                    .orElseThrow(() -> new RuntimeException("User not found"));
            if (user.getPassword().equals(password)) {
                return Response.ok("Login successful").build();
            } else {
                return Response.status(Response.Status.BAD_REQUEST).entity("Invalid credentials").build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @Path("/reset-password")
    @POST
    public Response resetPassword(@FormParam("email") String email) {
        try {
            String username = userService.findByEmail(email)
                    .orElseThrow(() -> new RuntimeException("Email not found"))
                    .getUsername();
            return Response.ok(username).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @Path("/new-password")
    @POST
    public Response updatePassword(@FormParam("username") String username, @FormParam("newPassword") String newPassword) {
        try {
            userService.updatePassword(username, newPassword);
            return Response.ok("Password updated successfully").build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }
}*/
