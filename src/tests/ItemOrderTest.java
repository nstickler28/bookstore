
package tests;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import model.Item;
import model.ItemOrder;
import org.junit.Before;
import org.junit.Test;

/**
 * Test cases for the itemOrder class.
 * 
 * @author Nathan Stickler
 * @version 10/8/19
 */
public class ItemOrderTest {

    /** Test fixtures. */
    private ItemOrder myItemOrder;
    /** Test fixtures. */
    private Item myItem;
    /** Test fixtures. */
    private int myItemQuantity;

    /**
     * This method runs before EVERY test case. Used to re initialize test
     * fixtures.
     */
    @Before
    public void setUp() {
        myItem = new Item("itemName", new BigDecimal("1.00"), 5, new BigDecimal(".50"));
        myItemQuantity = 1;
        myItemOrder = new ItemOrder(myItem, myItemQuantity);
    }

    /**
     * Test for IllegalArgumentException when sending a negative quantity for
     * item order.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testItemOrderNegativeQuantity() {
        myItemOrder = new ItemOrder(myItem, -3);
    }

    /** Test for return of a reference to the item in this ItemOrder. */
    @Test
    public void testGetItem() {
        final Item expected = myItem;
        final Item actual = myItemOrder.getItem();
        assertEquals("return reference to item in this ItemOrder", expected, actual);
    }

    /** Test for number of items in this ItemOrder. */
    @Test
    public void testGetQuantity() {
        final int expected = myItemQuantity;
        final int actual = myItemOrder.getQuantity();
        assertEquals("get number of items in ItemOrder", expected, actual);
    }

    /** Test for correct output from toString for the itemOrder. */
    @Test
    public void testToString() {
        final String expected = "Item: " + myItem + ", quantity is: " + myItemQuantity;
        assertEquals("Test correct output from toString for ItemOrder", expected,
                     myItemOrder.toString());
    }
}
