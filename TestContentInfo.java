/*
 * TestContentInfo.java
 *
 * This file implements the TestContentInfo
 * JUNit class required for CS 5500 assignment 1.
 */
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import static org.junit.Assert.assertTrue;

/**
 * This class implements JUnit tests for the methods of
 * the ContentInfo class. The methods for this implementation
 * test all the ContentInfo methods for each type of content.
 *
 * @author litchi
 * @since 2020-01-29
 * @version 2
 */
public class TestContentInfo {

    /** Base URL String */
    public static final String URL_PATH =
            "http://www.ccis.northeastern.edu/home/pgust/classes/cs5500/2020/Spring/resources/assignment-1/";

    /** URL String of pdf file */
    public static final String URL_PDF = URL_PATH + "12lines.pdf";

    /** URL String of text file */
    public static final String URL_TXT = URL_PATH + "12lines.txt";

    /** URL String of image file */
    public static final String URL_PNG = URL_PATH + "500x200.png";

    /** Content Information of pdf */
    public ContentInfo pdfInfo;

    /** Content Information of text */
    public ContentInfo txtInfo;

    /** Content Information of image */
    public ContentInfo pngInfo;


    /**
     * Sets up testing instances.
     *
     * @throws MalformedURLException if fails
     */
    @Before
    public void setUp() throws MalformedURLException {
        pdfInfo = new ContentInfo(URL_PDF);
        txtInfo = new ContentInfo(URL_TXT);
        pngInfo = new ContentInfo(URL_PNG);
    }

    /**
     * Tests constructor throws Malformed URL Exception.
     *
     * @throws MalformedURLException if succeeds
     */
    @Test(expected = MalformedURLException.class)
    public void constructorThrowsMalformedURLException() throws MalformedURLException {
        new ContentInfo("");
    }

    /**
     * Tests constructor throws Null Pointer Exception.
     *
     * @throws NullPointerException if succeeds
     */
    @Test(expected = NullPointerException.class)
    public void constructorThrowsNullPointerException() {
        new ContentInfo((URL) null);
    }

    /**
     * Tests getContentLength function returns expected results.
     *
     * @throws IOException if fails
     */
    @Test
    public void testGetContentLength() throws IOException {
        int pdfContentLength = pdfInfo.getContentLength();
        assertTrue(pdfContentLength == 12811);

        int txtContentLength = txtInfo.getContentLength();
        assertTrue(txtContentLength == 649);

        int pngContentLength = pngInfo.getContentLength();
        assertTrue(pngContentLength == 68643);
    }

    /**
     * Tests getContentType function returns expected results.
     *
     * @throws IOException if fails
     */
    @Test
    public void testGetContentType() throws IOException {
        String pdfContentType = pdfInfo.getContentType();
        assertTrue(pdfContentType.equals("application/pdf"));

        String txtContentType = txtInfo.getContentType();
        assertTrue(txtContentType.contains("text/plain"));

        String pngContentType = pngInfo.getContentType();
        assertTrue(pngContentType.equals("image/png"));
    }

    /**
     * Tests getImageSize function returns expected results.
     *
     * @throws IOException if fails
     */
    @Test
    public void testGetImageSize() throws IOException {
        Dimension imageSize = pngInfo.getImageSize();
        assertTrue(imageSize.width == 500);
        assertTrue(imageSize.height == 200);
    }

    /**
     * Tests getLastModified returns expected results.
     *
     * @throws IOException if fails
     */
    @Test
    public void testGetLastModified() throws IOException {
        Date pdfLastModified = pdfInfo.getLastModified();
        Date txtLastModified = txtInfo.getLastModified();
        Date pngLastModified = pngInfo.getLastModified();

        assertTrue(pdfLastModified.toString().contains("Jan")
                & pdfLastModified.toString().contains("9")
                & pdfLastModified.toString().contains("2020"));
        assertTrue(txtLastModified.toString().contains("Jan")
                & txtLastModified.toString().contains("9")
                & txtLastModified.toString().contains("2020"));
        assertTrue(pngLastModified.toString().contains("Jan")
                & pngLastModified.toString().contains("9")
                & pngLastModified.toString().contains("2020"));
    }

    /**
     * Tests getLineCount function returns expected result.
     *
     * @throws IOException if fails
     */
    @Test
    public void testGetLineCount() throws IOException {
        assertTrue(txtInfo.getLineCount() == 12);
        assertTrue(pdfInfo.getLineCount() == 12);
        assertTrue(pngInfo.getLineCount() == 12);
    }

    /**
     * Tests getLocation function returns expected results.
     */
    @Test
    public void testGetLocation() {
        String base = "/home/pgust/classes/cs5500/2020/Spring/resources/assignment-1/";
        assertTrue(pdfInfo.getLocation().equals(base + "12lines.pdf"));
        assertTrue(txtInfo.getLocation().equals(base + "12lines.txt"));
        assertTrue(pngInfo.getLocation().equals(base + "500x200.png"));
    }

    /**
     * Tests isAvailable function returns expected results.
     *
     * @throws IOException if fails.
     */
    @Test
    public void testIsAvailable() throws IOException {
        assertTrue(pdfInfo.isAvailable() == true);
        assertTrue(txtInfo.isAvailable() == true);
        assertTrue(pngInfo.isAvailable() == true);
    }

    /**
     * Tests isImage function returns expected results.
     *
     * @throws IOException if fails.
     */
    @Test
    public void testIsImage() throws IOException {
        assertTrue(pdfInfo.isImage() == false);
        assertTrue(txtInfo.isImage() == false);
        assertTrue(pngInfo.isImage() == true);
    }

    /**
     * Tests isText function returns expected results
     *
     * @throws IOException if fails
     */
    @Test
    public void testIsText() throws IOException {
        assertTrue(pdfInfo.isText() == false);
        assertTrue(txtInfo.isText() == true);
        assertTrue(pngInfo.isText() == false);
    }
}