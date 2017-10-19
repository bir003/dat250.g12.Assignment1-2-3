/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.AuctionBid;
import entities.BidFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author past
 */
@Named(value = "navigationController")
@ApplicationScoped
public class NavigationController {

    @EJB
    private BidFacade bidFacade;

    List<AuctionBid> ab;
    String user = "";
    Double currentBid = 0.0;
    Double newBid = 0.0;
    boolean firstbid = true;
    Long productid;

    /**
     * Creates a new instance of NavigationController
     */
    public NavigationController() {
    }

    public String goToBidHistory(Long id) {
        ab = bidFacade.getById(id);
        return "bidHistory.xhtml";
    }

    public List<AuctionBid> getAb() {
        return ab;
    }

    public BidFacade getBidFacade() {
        return bidFacade;
    }

    public void setBidFacade(BidFacade bidFacade) {
        this.bidFacade = bidFacade;
    }

    public void setAb(List<AuctionBid> ab) {
        this.ab = ab;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        System.out.print(user + "   UUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUSER");
        this.user = user;
    }

    public Double getCurrentBid() {
        return currentBid;
    }

    public void setCurrentBid(Double currentBid) {
        if (currentBid==0.0)
            return;
        System.out.print(currentBid + "   CURRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUSER");
        this.newBid = currentBid;
        currentBid = 0.0;
        
    }

    public boolean isFirstbid() {
        return firstbid;
    }

    public void setFirstbid(boolean firstbid) {
        this.firstbid = firstbid;
    }

    public Long getProductid() {
        return productid;
    }

    public void setProductid(Long productid) {
        this.productid = productid;
    }

    public Double getNewBid() {
        return newBid;
    }

    public void setNewBid(Double newBid) {
        this.newBid = newBid;
    }
    

}
