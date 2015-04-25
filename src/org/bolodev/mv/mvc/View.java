package org.bolodev.mv.mvc;
/**
 * <p>
 * This interface must be implemented by all classes that wish to take the role of a View within the MVC framework.
 * </p>
 * 
 * <p>
 * The role of a View is the display of information and the capture of data entered.
 * </p>
 */
public interface View{
  
  /**
   * @return the application Controller
   */
  Controller getController();
  
  /**
   * @param controller the application Controller
   */
  void setController(Controller controller);
  
  /**
   * @return the application Model
   */
  Model getModel();
  
  /**
   * @param model the application Model
   */
  void setModel(Model model);
}
