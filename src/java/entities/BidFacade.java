/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author sigri
 */
@Stateless
public class BidFacade extends AbstractFacade<AuctionBid> {

    @PersistenceContext(unitName = "EnterpriceProjectPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BidFacade() {
        super(AuctionBid.class);
    }

    public List<AuctionBid> getById(Long id) {
        List<AuctionBid> resultList = getEntityManager()
                .createQuery("SELECT i FROM AuctionBid i WHERE i.productId = :iID",
                        AuctionBid.class).setParameter("iID", id).getResultList();
        // TODO handle other cases
        return resultList;
    }
}
