
package tests;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import model.Cart;
import model.Item;
import model.ItemOrder;
import org.junit.Before;
import org.junit.Test;

/**
 * Test cases for the cart class.
 * 
 * @author Nathan Stickler
 * @version 10/9/19
 */
public class CartTest {

    /** Test fixtures. */
    private Cart myCart;

    /** Test fixtures. */
    private ItemOrder myItemOrder;

    /**
     * This method runs before EVERY test case. Used to re initialize test
     * fixtures.
     */
    @Before
    public void setUp() {
        myCart = new Cart();
        final Item item = new Item("itemName", new BigDecimal("1.00"));
        myItemOrder = new ItemOrder(item, 1);
        myCart.add(myItemOrder);
    }

    /**
     * Test to calculate the total for all items in cart. Make sure previous
     * orders update for equivalent item when adding to cart.
     */
    @Test
    public void testAdd() {
        myCart.add(myItemOrder);
    }

    /** Test whether the member has store membership or not. */
    @Test
    public void testSetMembership() {
        myCart.setMembership(true);
    }

    /**
     * Tests for total cost of cart for non bulk pricing as a BD. Should use
     * ROUND_HALF_EVEN rounding.
     */
    @Test
    public void testCalculateTotalNonBulk() {
        final Item item = new Item("itemName", new BigDecimal("3.00"));
        final ItemOrder itemOrder = new ItemOrder(item, 4);
        myCart.add(itemOrder);
        final BigDecimal expected = new BigDecimal("13.00");
        final BigDecimal actual = myCart.calculateTotal();
        assertEquals("calculate total for non bulk items", expected, actual);
    }

    /**
     * Tests for total cost of cart with bulk pricing. Should use
     * ROUND_HALF_EVEN rounding.
     */
    @Test
    public void testCalculateTotalBulk() {
        myCart.setMembership(true);
        final Item item = new Item("itemName", new BigDecimal("0.95"), 10,
                                   new BigDecimal("5.00"));
        final ItemOrder itemOrder = new ItemOrder(item, 10);
        myCart.add(itemOrder);
        final BigDecimal expected = new BigDecimal("6.00");
        final BigDecimal actual = myCart.calculateTotal();
        assertEquals("calculate total for bulk items", expected, actual);
    }

    /** Test to remove all items in cart. */
    @Test
    public void testClear() {
        assertSame("test cart size equals passed value", myCart.getCartSize(), 1);
        myCart.clear();
        assertSame("clear cart and test for empty cart with passed value",
                   myCart.getCartSize(), 0);
    }

    /** Tests for the number of ItemOrders currently in cart. */
    @Test
    public void testGetCartSize() {
        final int expected = 1;
        final int actual = myCart.getCartSize();
        assertEquals("test number of ItemOrders in cart", expected, actual);
    }

    /** Tests for correct output from toString for the cart. */
    @Test
    public void testToString() {
        final String expected = "[" + myItemOrder.toString() + "]";
        assertEquals("Test for correct toString output for cart", expected, myCart.toString());
    }
}
