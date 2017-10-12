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
public class UserFacade extends AbstractFacade<AuctionUser> {

    @PersistenceContext(unitName = "EnterpriceProjectPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserFacade() {
        super(AuctionUser.class);
    }
    
    public AuctionUser getByUsername(String userName) {
        List<AuctionUser> resultList = getEntityManager()
                .createQuery("SELECT u FROM AuctionUser u WHERE u.username = :uName", 
                        AuctionUser.class).setParameter("uName", userName).getResultList();
        // TODO handle other cases
        return resultList.get(0);
    }
    
    public Boolean checkPasswordForUser(long id, String password){
    AuctionUser u = this.find(id);
    if (u.getPassword().equals(password))
        return true;
    return false;
    }
    
    
    public Boolean aprovePassword(String pass){
        if (pass.length()<5)
            return false;
        
        boolean val=false;
        for (int i=0; i<pass.length();i++)     
            if (Character.isUpperCase(pass.charAt(i))){
                val = true;
                continue;
            }
        return val;
    }
}
