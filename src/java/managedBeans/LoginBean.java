package managedBeans;

/*
 * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package login;
//
//import entities.UserFacade;
//import javax.inject.Named;
//import javax.faces.bean.SessionScoped;
//import java.io.Serializable;
//import javax.ejb.EJB;
//import javax.faces.context.FacesContext;
//import javax.servlet.http.HttpSession;
//
//
///**
// *
// * @author sigri
// */
//
//
//@Named(value = "LoginBean")
//@SessionScoped
//
//public class LoginBean implements Serializable {
//
//    @EJB
//    private UserFacade userFacade;
//
//    public UserFacade getUserFacade() {
//        return userFacade;
//    }
//
//    public void setUserFacade(UserFacade userFacade) {
//        this.userFacade = userFacade;
//    }
//    
//    private String usernameInput ="2";
//    private String passwordInput="3";
//    
//    String response;
//    /**
//     * Creates a new instance of LoginBean
//     */
//    public LoginBean() {}
//    
//    
//
//    public String getUsernameInput() {
//        return usernameInput;
//    }
//
//    public void setUsernameInput(String usernameInput) {
//        this.usernameInput = usernameInput;
//    }
//
//    public String getPasswordInput() {
//        return passwordInput;
//    }
//
//    public void setPasswordInput(String passwordInput) {
//        this.passwordInput = passwordInput;
//    }
//
//      
//    public String getResponse() {
//
//            //invalidate user session
//        FacesContext context;
//        context = FacesContext.getCurrentInstance();
//        HttpSession session;
//        session = (HttpSession) context.getExternalContext().getSession(false);
//        session.invalidate();
//            
//         
//            
//            return "Welcome";
//    }
//}
//    
//    
//    
