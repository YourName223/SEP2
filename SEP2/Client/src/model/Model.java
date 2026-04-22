package model;

import utility.UnnamedPropertyChangeSubject;

public interface Model extends UnnamedPropertyChangeSubject
{
  public void createOrder();
  public void placeOrder();
  public void fireProperty(String line);
}
