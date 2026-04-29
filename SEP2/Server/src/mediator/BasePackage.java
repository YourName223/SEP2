package mediator;

public abstract class BasePackage
{
  String type;

  public BasePackage(String type)
  {
    this.type = type;
  }

  public String getType()
  {
    return type;
  }
}
