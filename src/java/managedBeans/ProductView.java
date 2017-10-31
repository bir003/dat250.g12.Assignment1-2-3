/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import entities.AuctionBid;
import entities.AuctionProduct;
import entities.BidFacade;
import entities.ProductFacade;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author sigri
 */
@Named(value = "ProductView")
@RequestScoped
public class ProductView implements Serializable {

    @EJB
    private BidFacade bidFacade;
    @EJB
    private ProductFacade productFacade;


    AuctionProduct product;
    private double currentBid = 0;
    private long id = 0;
    private AuctionBid ab;
    private String username;

    /**
     * Creates a new instance of ProductView
     */
    public ProductView() {

        this.product = new AuctionProduct();
        this.ab = new AuctionBid();
    }
    //TODO
    //This should add time, user, product and bid amount into a table - AuctionUSer joined with AuctionProduct.
    // I am confident that we do not need a class for Bids - this is just a number, deadline can be set on product, 
    // bid time here..

    public String createBid() throws Exception {
        if (currentBid==0.0)
            throw new Exception();

        AuctionProduct p = this.productFacade.findById(id);
        //compare new bid with old, if old is 0 then startbid

        if (this.currentBid<=p.getCurrentBid() || this.currentBid <=p.getStartPrice())
             return null;
                
        p.setCurrentBid(this.currentBid);
        this.productFacade.edit(p);

        //compare new bid with old, if old is 0 then startbid

        ab.setAmount(this.currentBid);
        ab.setProductId(p.getId());
        ab.setProductName(p.getName());
        System.out.println("USERNAME  " + username);
        ab.setUsername(username);
        
//        username = userThatBids;
//        if(username!=null){
//            AuctionUser au = signin.getUser();
//            ab.setUserId(au.getId());
//        }

//private static java.sql.Timestamp getCurrentTimeStamp() {

	java.util.Date today = new java.util.Date();
	Timestamp t = new java.sql.Timestamp(today.getTime());
        ab.setTime(t);

        this.bidFacade.create(ab);
        //.setBidInHistory(this.currentBid, p.getId(), au.getId());

        return null;
    }

    public void createProduct() throws IOException {
        this.productFacade.create(product);
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
        response.sendRedirect("mainPage.xhtml");
    }

    public List<AuctionProduct> readProducts() {
      //  List<AuctionProduct> list = this.productFacade.findAll();
        List<AuctionProduct> list = this.productFacade.getAllActiveProducts();
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

    public BidFacade getBidFacade() {
        return bidFacade;
    }

    public void setBidFacade(BidFacade bidFacade) {
        this.bidFacade = bidFacade;
    }

    public AuctionBid getAb() {
        return ab;
    }

    public void setAb(AuctionBid ab) {
        this.ab = ab;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    
}
