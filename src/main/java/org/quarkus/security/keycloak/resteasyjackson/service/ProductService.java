package org.quarkus.security.keycloak.resteasyjackson.service;

import org.quarkus.security.keycloak.resteasyjackson.model.Product;

import javax.enterprise.context.ApplicationScoped;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
public class ProductService {

    private static final Map<Long, Product> PRODUCT_MAP = new HashMap<>();

    static {
        PRODUCT_MAP.put(1L, Product.build(1L, "Bat", "Cricket Bat"));
        PRODUCT_MAP.put(2L, Product.build(2L, "Ball", "Volley Ball"));
        PRODUCT_MAP.put(3L, Product.build(3L, "Pool", "Pool Table"));
        PRODUCT_MAP.put(4L, Product.build(4L, "Racket", "Badminton"));
    }

    public Collection<Product> getAllProduct() {
        return PRODUCT_MAP.values();
    }

    public Product addProduct(final Product product) {
        return PRODUCT_MAP.put(product.getId(), product);
    }
}
