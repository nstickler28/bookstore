// Finish and comment me!

package model;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Objects;

/**
 * Item class that stores information on individual items.
 * @author Nathan Stickler
 * @version 10/5/19
 */

public final class Item {
    
    /** The name of the item. */
    private final String myItemName;
    
    /** The price of the item. */
    private final BigDecimal myItemPrice;
    
    /** The bulk quantity for the item. */
    private int myBulkQuantity;
    
    /** The bulk price for the item. */
    private BigDecimal myBulkPrice;

    /** Whether or not item has bulk option. */
    private boolean myBulkOption;

    /**
     * Constructor that takes a name and price as arguments.
     * @param theName assigns the name to the item.
     * @param thePrice assigns the price to the item.
     */
    public Item(final String theName, final BigDecimal thePrice) {
        if (theName.isEmpty()) {
            throw new IllegalArgumentException("Item name cannot be empty.");
        }
        if (thePrice.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Item price cannot be less than 0.");
        }
        myItemName = theName;
        myItemPrice = thePrice;
        myBulkOption = false;
    }

    /**
     * Constructor that takes a name, price, bulk quantity and bulk price
     * as arguments.
     * @param theName assigns the name to the item.
     * @param thePrice assigns the price to the item.
     * @param theBulkQuantity the amount required for bulk option.
     * @param theBulkPrice price for items in bulk.
     */
    public Item(final String theName, final BigDecimal thePrice, final int theBulkQuantity,
                final BigDecimal theBulkPrice) {
        this(theName, thePrice);
        if (theBulkQuantity < 0) {
            throw new IllegalArgumentException("Bulk quantity cannot be less than 0.");
        }
        if (theBulkPrice.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Bulk price cannot be less than 0.");
        }
        myBulkQuantity = theBulkQuantity;
        myBulkPrice = theBulkPrice;
        myBulkOption = true;
    }

    /** Returns the price of the item. 
     * @return item price.
     * */
    public BigDecimal getPrice() {
        return myItemPrice;
    }

    /** Returns the bulk quantity for the item. 
     * @return item bulk quantity
     * */
    public int getBulkQuantity() {
        return myBulkQuantity;
    }

    /** Returns the bulk price for the item. 
     * @return item bulk price
     * */
    public BigDecimal getBulkPrice() {
        return myBulkPrice;
    }

    /** Returns true if the item has bulk pricing. 
     * @return whether item has bulk pricing
     * */
    public boolean isBulk() {
        return myBulkOption;
    }

    @Override
    public String toString() {
        final NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.US);
        final StringBuilder sb = new StringBuilder();
        sb.append(myItemName);
        sb.append(", ");
        sb.append(nf.format(myItemPrice));
        if (isBulk()) {
            sb.append(" (" + myBulkQuantity + " for " + nf.format(myBulkPrice) + ")");
        }
        return sb.toString();
    }

    @Override
    public boolean equals(final Object theOther) {
        boolean returnValue = false;
        if (theOther != null && this.getClass() == theOther.getClass()) {
            final Item otherItem = (Item) theOther;
            returnValue = (Objects.deepEquals(myItemName, otherItem.myItemName))
                            && (myItemPrice.compareTo(otherItem.myItemPrice) == 0);
            
            if (isBulk()) {
                returnValue = (Objects.equals(myItemName, otherItem.myItemName))
                                && (myItemPrice.compareTo(otherItem.myItemPrice) == 0)
                                && (Integer.compare(myBulkQuantity, otherItem.
                                                    myBulkQuantity) == 0)
                                && (myBulkPrice.compareTo(otherItem.myBulkPrice) == 0);
            }
        }
        return returnValue;
    }

    @Override
    public int hashCode() {
        final int hash;
        if (isBulk()) {
            hash = Objects.hash(myItemName, myItemPrice);
        } else {
            hash = Objects.hash(myItemName, myItemPrice, myBulkQuantity, myBulkPrice);
        }
        return hash;
    }
}
