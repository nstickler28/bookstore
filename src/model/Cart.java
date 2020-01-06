// Finish and comment me!

package model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A cart class that stores information on item purchases.
 * @author Nathan Stickler
 * @version 10/5/19
 */

public class Cart {
    
    /** Array list that stores items ordered. */
    private final List<ItemOrder> myItemOrders;
    /** Check if customer is a store member. */
    private boolean myMembership;
    
    /**
     * Constructs an empty cart.
     */
    public Cart() {
        myItemOrders = new ArrayList<ItemOrder>();
    }
    
    /**
     * Adds orders to shopping cart and updates previous orders for  
     * equivalent items with new order.
     * @param theOrder ItemOrder added to cart.
     */
    public void add(final ItemOrder theOrder) {
        for (int i = 0; i < myItemOrders.size(); i++) {
            if (theOrder.getItem() == myItemOrders.get(i).getItem()) {
                myItemOrders.set(i, theOrder);
                return;
            }
        }
        myItemOrders.add(Objects.requireNonNull(theOrder));
    }

    /** Sets whether or not the customer is a store member.
     * @param theMembership boolean value that determines membership.
     *  */
    public void setMembership(final boolean theMembership) {
        myMembership = theMembership;
    }

    /** 
     * Calculates and returns total cost of cart as a BigDecimal.
     * Returned value has scale of 2 and uses ROUND_HALF_EVEN rounding.
     * @return current total of cart.
     */
    public BigDecimal calculateTotal() {
        BigDecimal total = BigDecimal.ZERO;
        BigDecimal result = BigDecimal.ZERO;
        for (int i = 0; i < myItemOrders.size(); i++) {
            final BigDecimal quantity = new BigDecimal(myItemOrders.get(i).getQuantity());
            final BigDecimal bulkQuantity = new BigDecimal(myItemOrders.get(i).getItem().
                                                           getBulkQuantity());
            final BigDecimal price = myItemOrders.get(i).getItem().getPrice();
            final BigDecimal bulkPrice = myItemOrders.get(i).getItem().getBulkPrice(); 
            if (myMembership && (myItemOrders.get(i).getItem().isBulk())) {
                result = quantity.divideToIntegralValue(bulkQuantity);
                result = result.multiply(bulkPrice);
                result = result.add((quantity.remainder(bulkQuantity)).multiply(price));
                total = total.add(result);
            } else {
                result = price.multiply(quantity);
                total = total.add(result);
            }
        }
        return total.setScale(2, BigDecimal.ROUND_HALF_EVEN);
    }
    
    /** Clears cart. */
    public void clear() {
        myItemOrders.clear();
    }
    
    /** Returns number of items in cart. 
     * @return number of items in cart.
     * */
    public int getCartSize() {
        return myItemOrders.size();
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(myItemOrders);
        return sb.toString();
    }

}
