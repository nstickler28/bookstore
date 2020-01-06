// Finish and comment me!

package model;

/**
 * Item order class that stores a reference to the item and quantity.
 * @author Nathan Stickler
 * @version 10/5/19
 */

public final class ItemOrder {

    /** Reference to the item. */
    private final Item myItem;
    /** Reference to item quantity. */
    private final int myItemQuantity;
    
    /**
     * Constructor that creates an ItemOrder for given quantity of given item.
     * @param theItem reference to the item.
     * @param theQuantity amount ordered.
     */
    public ItemOrder(final Item theItem, final int theQuantity) {
        if (theQuantity < 0) {
            throw new IllegalArgumentException("Item quantity cannot be less than 0.");
        }
        myItem = theItem;
        myItemQuantity = theQuantity;
    }

    /** Returns item in this ItemOrder.
     * @return reference to the item in this ItemOrder.
     */
    public Item getItem() {
        return myItem;
    }
    
    /** Returns number of an item in this ItemOrder.
     * @return the number of items.
     */
    public int getQuantity() {
        return myItemQuantity;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(128);
        sb.append("Item: ");
        sb.append(myItem);
        sb.append(", quantity is: ");
        sb.append(myItemQuantity);
        return sb.toString();
    } 
}
 