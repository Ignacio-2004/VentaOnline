package com.dam2.ventaonline.managers;

import com.dam2.ventaonline.objects.Product;

import java.util.ArrayList;

public class ProductMng {

    ArrayList<Product> products;

    public ProductMng(ArrayList<Product> products) {
        this.products = products;
    }

    public ProductMng() {products= new ArrayList<Product>();}

    /**
     * To add a Product in the ArrayList
     * @param product Product
     * @return true If the product enter in the arrayList   false If the product is already existing
     */
    public boolean addProduct(Product product) {

        if (exist(product.getId())) return false;

        products.add(product);

        return true;

    }

    /**
     * To verify if a Product exist in the manager
     * @param id id of the Product
     * @return true If the product don't exist  false If the product exist
     */
    public boolean exist (String id) {

        if (!products.isEmpty()){
            for (Product product: products){
                if (product.getId().equals(id)) return true;
            }
        }

        return false;
    }

    /**
     * Return a Product of the list
     * @param pos the position of the product
     * @return Product
     */
    public Product getProduct (int pos){

        return listProducts().get(pos);

    }

    /**
     * Return the list of Products
     * @return list of Products
     */
    public ArrayList<Product> listProducts(){

        return products;

    }
}
