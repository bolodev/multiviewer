package org.bolodev.mv.mvc;
/**
 * The root of the Controller class hierarchy is the AbstractController class.
 * This class defines all the basic facilities required to implement a
 * controller. That is, allows a view and model to be linked to the controller.
 * <br />
 * It also provides a set of common and set and get methods for views and models.
 */
public abstract class AbstractController implements Controller{

  private View view;
  private Model model;

  /*
   * (non-Javadoc)
   * @see org.lamora.mvc.Controller#setModel(org.lamora.mvc.Model)
   */
  public void setModel(Model model){
    this.model = model;
  }

  /*
   * (non-Javadoc)
   * @see org.lamora.mvc.Controller#getModel()
   */
  public Model getModel(){
    return model;
  }

  /*
   * (non-Javadoc)
   * @see org.lamora.mvc.Controller#setView(org.lamora.mvc.View)
   */
  public void setView(View view){
    this.view = view;
  }

  /*
   * (non-Javadoc)
   * @see org.lamora.mvc.Controller#getView()
   */
  public View getView(){
    return view;
  }

}

