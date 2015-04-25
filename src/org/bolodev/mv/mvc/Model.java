package org.bolodev.mv.mvc;
/**
 * <p>
 * This interface must be implemented by all classes that wish to play the Model role within the MVC framework.
 * </p>
 * <p>
 * The only method specified by the interface is the <code>notify changed()</code> method.
 * </p>
 */
public interface Model{
  
  /**
   * @param event Event to notify view/s of change
   */
  void notifyChanged(ModelEvent event);
}
