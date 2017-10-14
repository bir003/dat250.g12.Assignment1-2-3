/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import entities.AuctionUser;
import entities.UserFacade;
import java.io.IOException;
import javax.inject.Named;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author sigri
 */
@Named(value = "signin")
@RequestScoped
public class Signin implements Serializable {

    @EJB
    private UserFacade userFacade;
    AuctionUser user;
    

    /**
     * Creates a new instance of Signup
     */
    public Signin() {
        this.user = new AuctionUser();
    }

    
    public void confirmUser() throws IOException{

    
    String pass = user.getPassword();
    user = userFacade.getByUsername(user.getUsername());
        if (user!=null){
            if (userFacade.checkPasswordForUser(user.getId(), pass)==true)
            directToPage("mainPage.xhtml");
        }
    }
    
    public void createUser() throws IOException {
        
        if (user.getName() != null && user.getPhoneNo()!=0 &&
                user.getEmailadr()!=null && user.getUsername()!=null) {
                if (userFacade.aprovePassword(user.getPassword())){
                    this.userFacade.create(user);
                    directToPage("products.xhtml");
                }
                //WELCOMEPAGE?
        }
    }


    public AuctionUser getUser() {
        return user;
    }
    
    
    public void directToPage(String src) throws IOException{
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletResponse response = (HttpServletResponse)context.getExternalContext().getResponse();
        response.sendRedirect(src);
    }
}
