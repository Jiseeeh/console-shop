package Model;

public class Customer extends User{
    private final Cart myCart = new Cart();
    private Double balance = 0.0;
    public Customer (String firstName , String lastName, String username, String password) {
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setUsername(username);
        this.setPassword(password);
    }

    public Double getBalance() {
        return balance;
    }
    public void setBalance(double newBalance) {
        balance = newBalance;
    }

    public Cart getMyCart() {
        return myCart;
    }
}
