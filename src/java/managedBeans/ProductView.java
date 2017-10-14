/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import entities.AuctionProduct;
import entities.AuctionUser;
import entities.ProductFacade;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author sigri
 */
@Named(value = "ProductView")
@SessionScoped
public class ProductView implements Serializable {
    
    @EJB
    private ProductFacade productFacade;
    AuctionProduct product;
    private double currentBid=0;
    private long id=0;
    
    

    /**
     * Creates a new instance of ProductView
     */
    public ProductView() {
        
        this.product = new AuctionProduct();
    }
    //TODO
    //This should add time, user, product and bid amount into a table - AuctionUSer joined with AuctionProduct.
    // I am confident that we do not need a class for Bids - this is just a number, deadline can be set on product, 
    // bid time here..
    
    public String createBid() {
        AuctionProduct p = this.productFacade.findById(id);
        //compare new bid with old, if old is 0 then startbid
        p.setCurrentBid(this.currentBid);
        this.productFacade.edit(p);
        
        return null; //otherwise it fills in the bid in every new bid column in the table in mainPage
    }
    
    public void createProduct() throws IOException {
        this.productFacade.create(product);
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletResponse response = (HttpServletResponse)context.getExternalContext().getResponse();
        response.sendRedirect("mainPage.xhtml");
    }
    

    
    public List<AuctionProduct> readProducts() {
        List<AuctionProduct> list = this.productFacade.findAll();
        return list;
        
    }
    
    public AuctionProduct getProduct() {
        return product;
    }

    public ProductFacade getProductFacade() {
        return productFacade;
    }

    public void setProductFacade(ProductFacade productFacade) {
        this.productFacade = productFacade;
    }

    public double getCurrentBid() {
        return currentBid;
    }

    public void setCurrentBid(double currentBid) {
        this.currentBid = currentBid;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
}
