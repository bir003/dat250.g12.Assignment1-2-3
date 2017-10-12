/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author sigri
 */
@Stateless
public class ProductFacade extends AbstractFacade<AuctionProduct> {

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
}
