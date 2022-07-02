# ConsoleShop

![GitHub last commit](https://img.shields.io/github/last-commit/Jiseeeh/ConsoleShop?logo=Java&logoColor=red) ![GitHub code size in bytes](https://img.shields.io/github/languages/code-size/Jiseeeh/ConsoleShop)

## What's a console shop?
- A console application where you can `register` as a user and add products as the `owner` of the shop.
  - Basically simulating an **online shop.**
- As the `owner` you can do the following:
  - Add a product.
  - View transactions.
  - View customers details.
  - View your products.
  - Remove a customer.
  - Your **_added products_**,**_transactions_**, and **_customers_** are stored into a **CSV** file so that when you exit the program, you can **still see those informations.**
- And the `customers` can do the following:
  - Cash in.
  - Go shopping. (can add to cart or buy)
  - View Info.
  - View balance.
  - View your cart.
  - Clear your cart.
  - Checkout.
  - View your bought products.
  - Customers **_money_**, **_cart_** , and **_bought products_** are also stored into a **CSV** file for later use.

## Steps to get started

- Clone the **Repository**

  ```git
  git clone https://github.com/Jiseeeh/ConsoleShop.git
  ```

- Open it in your ***favorite editor***

- Run **Main.java**

- Login using this:

    - `username:` **owner**

    - `password:` **123**

- You can now add products!

- Register customers and test it!

---

## My prof's suggestion
- Make a separate class for the `lists`
- Remove product's quantity properties, because it represents a single product.
  - because having that property there, it would look like an inventory.
