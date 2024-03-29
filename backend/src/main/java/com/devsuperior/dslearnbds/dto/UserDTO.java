package com.devsuperior.dslearnbds.dto;

import java.io.Serializable;
//import java.util.HashSet;
//import java.util.Set;

//import javax.validation.constraints.Email;
//import javax.validation.constraints.NotBlank;

////import com.devsuperior.dscatalog.entities.Role;
import com.devsuperior.dslearnbds.entities.User;

public class UserDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    //@NotBlank(message = "Campo obrigatório")
    //private String firstName;
    //@Email(message = "Favor entrar um email válido")
    private String name;
    private String email;
    
    public UserDTO(){        
    }

    public UserDTO(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public UserDTO(User entity) {
        id = entity.getId();
        name = entity.getName();        
        email = entity.getEmail();
    }    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
