import org.geepacific.daniel.countingpoints.CountingPoints;
import org.junit.Assert;
import org.junit.Test;

public class CountingPointsTest {

    @Test
    public void sampleCase() {
        String s1 = "1&2&3|7&8";
        String s2 = "17-02,03-38,05-37,27";
        CountingPoints countingPoints = new CountingPoints();
        Assert.assertEquals(countingPoints.countingPointsMain(s1, s2), 11);
    }

    @Test
    public void myCase1() {
        String s1 = "1&2&3|7&8";
        String s2 = "17-02,02-17";
        CountingPoints countingPoints = new CountingPoints();
        Assert.assertEquals(countingPoints.countingPointsMain(s1, s2), 2);
    }

    @Test
    public void myCase2() {
        String s1 = "1&2&3|7&8";
        String s2 = "17-02,02-17,17-02-40,,,---,--,17-";
        CountingPoints countingPoints = new CountingPoints();
        Assert.assertEquals(countingPoints.countingPointsMain(s1, s2), 2);
    }

    @Test
    public void myCase3() {
        String s1 = "";
        String s2 = "17-02,02-17,17-02-40,,,---,--,17-";
        CountingPoints countingPoints = new CountingPoints();
        Assert.assertEquals(countingPoints.countingPointsMain(s1, s2), -1);
    }

    @Test
    public void myCase4() {
        String s1 = "1&2&3|7&8";
        String s2 = "";
        CountingPoints countingPoints = new CountingPoints();
        Assert.assertEquals(countingPoints.countingPointsMain(s1, s2), 0);
    }

    @Test
    public void myCase5() {
        String s1 = "1&2&3";
        String s2 = "17-02,02-17,17-02-40,,,---,--,17-";
        CountingPoints countingPoints = new CountingPoints();
        Assert.assertEquals(countingPoints.countingPointsMain(s1, s2), -1);
    }

    @Test
    public void myCase6() {
        String s1 = "1&2&3&4|7&8&&9";
        String s2 = "37-04,14-01,05-37,17,,,---,--,17-";
        CountingPoints countingPoints = new CountingPoints();
        Assert.assertEquals(countingPoints.countingPointsMain(s1, s2), 10);
    }

    @Test
    public void myCase7() {
        String s1 = "1&2&3|7&8";
        String s2 = "17-02,03-38,38-03,05-37,27,,,,,,,,,,,,,,,---,,--,02-17";
        CountingPoints countingPoints = new CountingPoints();
        Assert.assertEquals(countingPoints.countingPointsMain(s1, s2), 11);
    }
}
