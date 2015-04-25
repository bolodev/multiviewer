package org.bolodev.mv.mvc;
/**
 * <p>
 * The Controller interface is the interface which must be implemented by all classes which wish to take the role of a Controller.
 * </p>
 * <p>
 * The primary role of a Controller within the MVC is to determine what should happen in response to user output.
 * </p>
 */
public interface Controller{
  
  /**
   * @param model set the application Model
   */
  void setModel(Model model);
  
  /**
   * @return get the application Model
   */
  Model getModel();
  
  /**
   * @return get the main application View
   */
  View getView();
  
  /**
   * @param view set the application Model
   */
  void setView(View view);
}
