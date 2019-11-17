package ru.plotnikov.model;

import org.springframework.security.core.GrantedAuthority;

public class Role implements GrantedAuthority{

    private Integer id;

    private String name;

    public Role() {

    }

    public Role(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (obj == null)
            return false;
        Role r = (Role) obj;
        return  this.name.equals(r.getName());
    }

    @Override
    public String getAuthority() {
        return name;
    }

}
