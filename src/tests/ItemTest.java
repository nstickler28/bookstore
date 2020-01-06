
package tests;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;
import model.Item;
import org.junit.Before;
import org.junit.Test;

/**
 * Test cases for the Item class.
 * 
 * @author Nathan Stickler
 * @version 10/7/19
 */
public class ItemTest {

    /** A default name for testing Item units. */
    private static final String DEFAULT_NAME = "itemName";
    /**
     * A BD can be created from a String using the constructor. Represents
     * default price.
     */
    private static final BigDecimal DEFAULT_PRICE = new BigDecimal("10.00");

    /** A BD used to represent bulk pricing. */
    private static final BigDecimal BULK_PRICE = new BigDecimal(".50");

    /** A NumberFormat used in toString() to display prices. */
    private static final NumberFormat CURRENCY_FORMAT =
                    NumberFormat.getCurrencyInstance(Locale.US);

    /** Used to test items - text fixtures. */
    private Item myItem;

    /** This method runs before EVERY test case. Used to re initialize item. */
    @Before
    public void setUp() {
        myItem = new Item(DEFAULT_NAME, DEFAULT_PRICE, 5, BULK_PRICE);
    }

    /** Throw IllegalArgumentException if name field is empty. */
    @Test(expected = IllegalArgumentException.class)
    public void testItemNoName() {
        myItem = new Item("", new BigDecimal("0.0"));
    }

    /** Throw IllegalArgumentException if sent a negative price. */
    @Test(expected = IllegalArgumentException.class)
    public void testItemNegativeItemPrice() {
        myItem = new Item(DEFAULT_NAME, new BigDecimal("-3.00"));
    }

    /** Throw IllegalArgumentException if sent negative bulk quantity. */
    @Test(expected = IllegalArgumentException.class)
    public void testItemNegativeBulkQuantity() {
        myItem = new Item(DEFAULT_NAME, DEFAULT_PRICE, -3, BULK_PRICE);
    }

    /** Throw IllegalArgumentException if sent negative bulk price. */
    @Test(expected = IllegalArgumentException.class)
    public void testItemNegativeItemBulkPrice() {
        myItem = new Item(DEFAULT_NAME, DEFAULT_PRICE, 5, new BigDecimal("-.50"));
    }

    /** Test the accessor for Price. */
    @Test
    public void testGetPrice() {
        final BigDecimal expected = DEFAULT_PRICE;
        final BigDecimal actual = myItem.getPrice();
        assertEquals("test accessor for price", expected, actual);
    }

    /** Test to make sure item returns correct bulk quantity. */
    @Test
    public void testGetBulkQuantity() {
        final int expected = 5;
        final int actual = myItem.getBulkQuantity();
        assertEquals("make sure item returns correct bulk quantity", expected, actual);
    }

    /** Test for return of bulk price in BD format. */
    @Test
    public void testGetBulkPrice() {
        final BigDecimal expected = BULK_PRICE;
        final BigDecimal actual = myItem.getBulkPrice();
        assertEquals("test for correct bulk pricing", expected, actual);
    }

    /** Test to see if item has bulk pricing. */
    @Test
    public void testIsBulk() {
        final boolean expected = true;
        final boolean actual = myItem.isBulk();
        assertEquals("test if item has bulk pricing", expected, actual);
    }

    /** Test if bulk item does not have bulk pricing. */
    @Test
    public void testIsBulkNotBulk() {
        myItem = new Item(DEFAULT_NAME, DEFAULT_PRICE);
        final boolean expected = false;
        final boolean actual = myItem.isBulk();
        assertEquals("test item has no bulk pricing", expected, actual);
    }

    /** Test for correct output from toString for non bulk item. */
    @Test
    public void testToStringNotBulk() {
        myItem = new Item(DEFAULT_NAME, new BigDecimal("1.00"));
        final String expected =
                        DEFAULT_NAME + ", " + CURRENCY_FORMAT.format(new BigDecimal("1.00"));
        assertEquals("test correct output from toString for non bulk items", expected,
                     myItem.toString());
    }

    /** Test for correct output from toString for bulk item. */
    @Test
    public void testToStringIsBulk() {
        final String expected =
                        DEFAULT_NAME + ", " + CURRENCY_FORMAT.format(DEFAULT_PRICE) + " (" + 5
                                + " for " + CURRENCY_FORMAT.format(BULK_PRICE) + ")";
        assertEquals("test correct output from toString for bulk items", expected,
                     myItem.toString());
    }

    /**
     * Test case for .equals looking only at cases where a non item object is
     * passed.
     */
    @Test
    public void testEqualsFalseNonItemCases() {
        final Item test = null;
        assertNotEquals("testing .equals for false non item cases", myItem, test);
        assertNotEquals("testing .equals for false non item cases", myItem, DEFAULT_NAME);
        assertNotEquals("testing .equals for false non item cases", myItem, DEFAULT_PRICE);
    }

    /**
     * Test case for .equals looking only at false cases for bulk and non bulk
     * items.
     */
    @Test
    public void testEqualsFalseCases() {
        final Item differentNameSamePrice = new Item("Different", DEFAULT_PRICE);
        final Item differentPriceSameName = new Item(DEFAULT_NAME, new BigDecimal("99.99"));
        final Item differentNameAndPrice = new Item("Different", new BigDecimal("99.99"));
        final Item differentBulkQuantity =
                        new Item(DEFAULT_NAME, DEFAULT_PRICE, 6, BULK_PRICE);
        final Item differentBulkPrice =
                        new Item(DEFAULT_NAME, DEFAULT_PRICE, 5, new BigDecimal(".75"));
        assertNotEquals("testing .equals for false cases for bulk and non bulk items", myItem,
                        differentNameSamePrice);
        assertNotEquals("testing .equals for false cases for bulk and non bulk items", myItem,
                        differentPriceSameName);
        assertNotEquals("testing .equals for false cases for bulk and non bulk items", myItem,
                        differentNameAndPrice);
        assertNotEquals("testing .equals for false cases for bulk and non bulk items", myItem,
                        differentBulkQuantity);
        assertNotEquals("testing .equals for false cases for bulk and non bulk items", myItem,
                        differentBulkPrice);
    }

    /** Test case for .equals looking only at true cases. */
    @Test
    public void testEqualsTrueCases() {
        assertEquals("test case for .equals for true cases.", myItem, myItem);
        final Item item1 = new Item(DEFAULT_NAME, DEFAULT_PRICE);
        final Item item2 = new Item(DEFAULT_NAME, DEFAULT_PRICE);
        assertEquals("test case for .equals for true cases.", item1, item2);
        final Item differentButTheSame = new Item(DEFAULT_NAME, DEFAULT_PRICE, 5, BULK_PRICE);
        assertEquals("test case for .equals for true cases.", myItem, differentButTheSame);
    }

    /** Test for correct hash code of two equal non bulk items. */
    @Test
    public void testHashCodeNotBulk() {
        myItem = new Item(DEFAULT_NAME, new BigDecimal("1.00"));
        assertEquals("test for correct hash code for non bulk items.", myItem.hashCode(),
                     myItem.hashCode());
    }

    /** Test for correct hash code of two equal bulk items. */
    @Test
    public void testHashCodeBulk() {
        assertEquals("test for correct hash code for two equal bulk items", myItem.hashCode(),
                     myItem.hashCode());

        final Item differentButTheSame = new Item(DEFAULT_NAME, DEFAULT_PRICE, 5, BULK_PRICE);
        assertEquals("test for correct hash code for two equal bulk items", myItem.hashCode(),
                     differentButTheSame.hashCode());
    }
}
