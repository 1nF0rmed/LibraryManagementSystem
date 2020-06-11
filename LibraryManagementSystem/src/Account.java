public abstract class Account {
  protected String id;
  protected String password;
  protected AccountStatus status;
  protected Person person;

  public abstract boolean resetPassword();
}