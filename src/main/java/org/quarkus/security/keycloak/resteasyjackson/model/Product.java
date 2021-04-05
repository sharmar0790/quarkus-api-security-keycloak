package org.quarkus.security.keycloak.resteasyjackson.model;

public class Product {

    private Long id;
    private String name;
    private String descr;

    public Product() {}

    public Product(Long id, String name, String descr) {
        this.id = id;
        this.name = name;
        this.descr = descr;
    }

    public static Product build(Long id, String name, String descr) {
        return new Product(id, name, descr);
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

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }
}
