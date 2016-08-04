package bg.softuni.data_structures;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import bg.softuni.contract.SimpleOrderedBag;

public class SimpleOrderedBagTests {

    private SimpleOrderedBag<String> names;

    @Before
    public void setUp() {
        this.names = new SimpleSortedList<>(String.class);
    }

    @Test
    public void testEmptyCtor() {
        Assert.assertEquals(16, this.names.capacity());
        Assert.assertEquals(0, this.names.size());
    }

    @Test
    public void testEmptyCtorWithInitialCapacity() {
        this.names = new SimpleSortedList<>(String.class, 20);
        Assert.assertEquals(20, this.names.capacity());
        Assert.assertEquals(0, this.names.size());
    }

    @Test
    public void testEmptyCtorWithInitialCamparer() {
        this.names = new SimpleSortedList<>(String.class, String.CASE_INSENSITIVE_ORDER);
        Assert.assertEquals(16, this.names.capacity());
        Assert.assertEquals(0, this.names.size());
    }

    @Test
    public void testEmptyCtorWithAllParams() {
        this.names = new SimpleSortedList<>(String.class, String.CASE_INSENSITIVE_ORDER, 30);
        Assert.assertEquals(30, this.names.capacity());
        Assert.assertEquals(0, this.names.size());
    }

    @Test
    public void testAddIncreasesSize() {
        this.names.add("Nasko");
        Assert.assertEquals(1, this.names.size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddNullThrowExeption() {
        this.names.add(null);
    }

    @Test
    public void testAddUnsortedDataIsHeldSorted() {
        this.names.add("Rosen");
        this.names.add("Georgi");
        this.names.add("Balkan");

        String expecteds[] = { "Balkan", "Georgi", "Rosen" };
        String actuals[] = new String[3];
        int index = 0;
        for (String str : names) {
            actuals[index++] = str;
        }

        Assert.assertArrayEquals(expecteds, actuals);
    }

    @Test
    public void testAddingMoreThanInitialCapacity() {
        this.names.add("Rosen");
        this.names.add("Georgi");
        this.names.add("Balkan");
        this.names.add("Rosen");
        this.names.add("Georgi");
        this.names.add("Balkan");
        this.names.add("Rosen");
        this.names.add("Georgi");
        this.names.add("Balkan");
        this.names.add("Rosen");
        this.names.add("Georgi");
        this.names.add("Balkan");
        this.names.add("Rosen");
        this.names.add("Georgi");
        this.names.add("Balkan");
        this.names.add("Rosen");
        this.names.add("Georgi");
        // 17 elements

        Assert.assertNotEquals(16, names.capacity());
        Assert.assertEquals(17, names.size());
    }

    @Test
    public void testAddingAllFromCollectionIncreasesSize() {
        List<String> testList = Arrays.asList("Nasko", "Avraam");
        this.names.addAll(testList);

        Assert.assertEquals(2, this.names.size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddingAllFromNullThrowsException() {
        this.names.addAll(null);
    }

    @Test
    public void testAddAllKeepsSorted() {
        this.names.addAll(Arrays.asList("Tcezar", "Buda", "David", "Evklit", "Avraam"));

        String expecteds[] = { "Avraam", "Buda", "David", "Evklit", "Tcezar" };
        String actuals[] = new String[5];
        int index = 0;
        for (String str : names) {
            actuals[index++] = str;
        }

        Assert.assertArrayEquals(expecteds, actuals);
    }

    @Test
    public void testRemoveValidElementDecreasesSize() {
        this.names.addAll(Arrays.asList("Tcezar", "Buda", "David", "Evklit", "Avraam"));
        this.names.remove("Buda");
        Assert.assertEquals(4, names.size());
    }

    @Test
    public void testRemoveValidElementRemovesSelectedOne() {
        this.names.addAll(Arrays.asList("Tcezar", "Buda", "David", "Evklit", "Avraam"));
        this.names.remove("Buda");
        Assert.assertFalse(names.remove("Buda"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemovingNullThrowsException() {
        this.names.addAll(Arrays.asList("Tcezar", "Buda", "David", "Evklit", "Avraam"));
        this.names.remove(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testJoinWithNull() {
        this.names.addAll(Arrays.asList("Tcezar", "Buda", "David", "Evklit", "Avraam"));
        this.names.joinWith(null);
    }

    @Test
    public void testJoinWorksFine() {
        this.names.addAll(Arrays.asList("Tcezar", "Buda", "David", "Evklit", "Avraam"));

        String expecteds = "Avraam, Buda, David, Evklit, Tcezar";

        Assert.assertEquals(expecteds, names.joinWith(", "));
    }
}
