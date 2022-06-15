package Model;

public class Customer extends User{
    private Cart myCart = new Cart();
    private Double balance;
    public Customer (String firstName , String lastName, String username, String password) {
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setUsername(username);
        this.setPassword(password);
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Cart getMyCart() {
        return myCart;
    }

    public void setMyCart(Cart myCart) {
        this.myCart = myCart;
    }
}
