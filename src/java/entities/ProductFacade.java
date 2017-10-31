/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Timestamp;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author sigri
 */
@Stateless
public class ProductFacade extends AbstractFacade<AuctionProduct> {

    Timestamp timestamp;
    @PersistenceContext(unitName = "EnterpriceProjectPU")
    private EntityManager em;

    public ProductFacade() {
        super(AuctionProduct.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public AuctionProduct findById(Long id) {
        return this.em.find(AuctionProduct.class, id);
    }

    public void updateCurrentBid(Double newBid, long pid) {
        em.createQuery("UPDATE p FROM AuctionProduct p SET p.currentbid = newBid1 WHERE p.id = :productID",
                AuctionUser.class).setParameter("productID", pid).setParameter("newBid1", newBid).executeUpdate();
    }
    

    public double getCurrentProductPrice(long id) {

        AuctionProduct ap = this.find(id);
        if (ap.getCurrentBid() > ap.getStartPrice()) {
            return (ap.getCurrentBid());
        }
        return ap.getStartPrice();
    }
    public List<AuctionProduct> getAllActiveProducts(){
        java.util.Date today = new java.util.Date();
	Timestamp t = new java.sql.Timestamp(today.getTime());

        System.out.println("timee " +t);
        
       List<AuctionProduct> resultList = getEntityManager()
                .createQuery("SELECT i FROM AuctionProduct i WHERE i.timestamp > :now",
                        AuctionProduct.class).setParameter("now", t).getResultList(); 

        return resultList;
    }
    
    public List<AuctionProduct> getAllUnactiveProducts(){
        java.util.Date today = new java.util.Date();
	Timestamp t = new java.sql.Timestamp(today.getTime());

        System.out.println("timee " +t);
        
       List<AuctionProduct> resultList = getEntityManager()
                .createQuery("SELECT i FROM AuctionProduct i WHERE i.timestamp < :now",
                        AuctionProduct.class).setParameter("now", t).getResultList();

        return resultList;
    }
    
    
    
    
}

//Product have current bidder.
//method that before findAll() checks the soldAtTime - if expired, set SOLD to true.