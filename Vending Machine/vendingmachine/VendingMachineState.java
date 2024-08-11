package vendingmachine;

import coin.Coin;
import item.Item;
import java.util.*;

public class VendingMachineState {
    public VendingMachineState() {
        System.out.println("Currently machine is in Idle state");
    }

    public void clickOnStartProductSelectionButton() {
        System.out.println("Vending machine is in HasMoneyState");
    }

    public void insertCoin(VendingMachine vendingMachine, Coin coin) {
        if(isValidcoin(coin)){
            System.out.println("Vending machine is in HasMoneyState");
            System.out.println("Accepted the coin");
            vendingMachine.getCoinList().add(coin);
        }
        else{
            System.out.println("Invalid coin");
        }
    }
    public boolean isValidcoin(Coin coin){    
        for(Coin c : Coin.values()){
            if(c == coin){
                return true;
            }
        }
        return false;
    }
    
    public List<Coin> refundFullMoney(VendingMachine vendingMachine) {
        System.out.println("Returned the full amount back in the coin dispense tray");
        vendingMachine.setVendingMachinState(new VendingMachineState());
        return vendingMachine.getCoinList();
    }  
    
    public void updateInventory(VendingMachine vendingMachine, Item item, int itemCodeNumber) {
        vendingMachine.getInventory().addItem(item, itemCodeNumber);
    }

    public void dispenseProduct(VendingMachine vendingMachine, int itemCodeNumber) {
        System.out.println("Currently vending machine is in Dispense State");
        System.out.println("Product has been dispense");
        vendingMachine.getInventory().updateSoldOutItem(itemCodeNumber);
        vendingMachine.setVendingMachinState(new VendingMachineState());
    }

    public void chooseProduct(VendingMachine vendingMachine, int itemCodeNumber) {

        // 1. Get item for itemCodeNumber
        Item item = vendingMachine.getInventory().getItem(itemCodeNumber);

        // 2. Total amount paid by User
        int paidByUser = 0;
        for (Coin coin: vendingMachine.getCoinList()) {
            paidByUser += coin.getValue();
        }

        // 3. Compare the product and amount paid by the user
        if (item.getPrice() > paidByUser) {
            refundFullMoney(vendingMachine);
            return;
        } else {
            getChange(item.getPrice(), paidByUser);
            // 4. Dispense the product
            dispenseProduct(vendingMachine, itemCodeNumber);
            return;
        }

    }

    public void getChange(int itemPrice, int paidByUser) {
        System.out.println("Returned the change in the Coin Dispense Tray: " + (paidByUser- itemPrice));

    }    
}