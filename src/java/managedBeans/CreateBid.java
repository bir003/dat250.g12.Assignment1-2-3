/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import entities.AuctionProduct;
import entities.ProductFacade;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author sigri
 */
@Named(value = "CreateBid")
@RequestScoped
public class CreateBid {
    
    @EJB
    private ProductFacade productFacade;
    AuctionProduct product;
    
    private double currentBid = 0;

    @ManagedProperty(value="#{item}")
    private AuctionProduct item;
    
    
    /**
     * Creates a new instance of CreateBid
     */
    public CreateBid() {
    
    }
    
    
    public void updateBid(){
        
        AuctionProduct au = this.item;
        
        if (currentBid > this.productFacade.getCurrentProductPrice(au.getId())) {
            this.productFacade.updateCurrentBid(currentBid, au.getId());
            
        } else {
            System.out.print("Nope");
        }
    }

    public ProductFacade getProductFacade() {
        return productFacade;
    }

    public void setProductFacade(ProductFacade productFacade) {
        this.productFacade = productFacade;
    }

    public AuctionProduct getProduct() {
        return product;
    }

    public void setProduct(AuctionProduct product) {
        this.product = product;
    }

    public double getCurrentBid() {
        return currentBid;
    }

    public void setCurrentBid(double currentBid) {
        this.currentBid = currentBid;
    }

    
}
