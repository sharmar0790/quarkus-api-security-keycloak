package org.quarkus.security.keycloak.resteasyjackson.controller;

import io.quarkus.security.Authenticated;
import io.quarkus.security.identity.SecurityIdentity;
import org.jboss.resteasy.annotations.cache.NoCache;
import org.quarkus.security.keycloak.resteasyjackson.model.Product;
import org.quarkus.security.keycloak.resteasyjackson.service.ProductService;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.security.Principal;
import java.util.Collection;

@Path("/api/products")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductController {

    @Inject
    SecurityIdentity identity;

    @Inject
    private ProductService productService;

    @GET
    @Path("/me")
    @RolesAllowed({"user_role","admin_role"})
    @NoCache
    public Principal me() {
        return identity.getPrincipal();
    }

    @GET
    @NoCache
    @RolesAllowed({"user_role","admin_role"})
    public Collection<Product> getAllProduct() {
        return productService.getAllProduct();
    }


    @POST
    @RolesAllowed({"admin_role"})
//    @Authenticated
    public Product addProduct(Product product) {
        return productService.addProduct(product);
    }

}
