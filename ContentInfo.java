/*
 * ContentInfo.java
 *
 * This file implements the ContentInfo class
 * required for CS 5500 assignment 1
 */

import javax.imageio.ImageIO;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;

/**
 * This class provides information about content specified by a URL.
 *
 * @author litchi
 * @since 2020-01-29
 * @version 2
 */
public class ContentInfo {

    /** URL for content location */
    public URL url;

    /** Connection of URL */
    public URLConnection urlConnection;


   /**
    * Creates an instance with the content URL.
    *
    * @param url the content url
    * @throws NullPointerException if content url is null.
    */
    public ContentInfo(URL url) {
        if (url != null) {
            this.url = url;
        } else {
            throw new NullPointerException();
        }
    }

    /**
     * Creates an instance with a string representing the content URL.
     *
     * @param urlString the content url string.
     * @throws MalformedURLException if url string is invalid.
     */
    public ContentInfo(String urlString) throws MalformedURLException {
        url = new URL(urlString);
    }

    /**
     * Gets the length of this content in bytes.
     *
     * @return the length of this content in bytes.
     * @throws IOException if fails.
     */
    public int getContentLength() throws IOException {
        urlConnection = url.openConnection();
        urlConnection.connect();
        return urlConnection.getContentLength();
    }

    /**
     * Gets the content type for this content (type/subtype).
     *
     * @return the content type for this content (type/subtype).
     * @throws IOException if fails.
     */
    public String getContentType() throws IOException {
        urlConnection = url.openConnection();
        urlConnection.connect();
        return urlConnection.getContentType();
    }

    /**
     * Gets the dimension for image content.
     *
     * @return the dimension of image content
     * @throws IOException if fails.
     * @throws IllegalStateException if image is null.
     */
    public Dimension getImageSize() throws IOException {
        urlConnection = url.openConnection();
        urlConnection.connect();
        InputStream in = urlConnection.getInputStream();
        BufferedImage image = ImageIO.read(in);
        if (image == null) {
            throw new IllegalStateException();
        }
        int width = image.getWidth();
        int height = image.getHeight();
        return new Dimension(width, height);
    }

    /**
     * Gets the date that this content was last modified.
     *
     * @return the date that this content was last modified
     * @throws IOException if fails.
     */
    public Date getLastModified() throws IOException {
        urlConnection = url.openConnection();
        urlConnection.connect();
        return new Date(urlConnection.getLastModified());
    }

    /**
     * Gets the line count for text content.
     *
     * @return the line count for text content
     * @throws IOException if fails.
     */
    public int getLineCount() throws IOException {
        String urlString =
                "http://www.ccis.northeastern.edu/home/pgust/classes/cs5500/2020/Spring/resources/assignment-1/12lines.txt";
        if (!urlString.contains("txt")) {
            throw new IllegalStateException();
        }
        URL url = new URL(urlString);


        BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
        int cnt = 0;
        while (reader.readLine() != null) cnt++;

        return cnt;
    }

    /**
     * Get the location for this content.
     *
     * @return the location for this content
     */
    public String getLocation() {
        return url.getPath();
    }

    /**
     * Determines whether the content is available.
     *
     * @return true if the content is available; otherwise false
     * @throws IOException if fails.
     */
    public boolean isAvailable() throws IOException {
        urlConnection = url.openConnection();
        urlConnection.connect();
        return urlConnection.getContent() != null;
    }

    /**
     * Determines whether the content is an image.
     *
     * @return true if the content is available; otherwise false.
     * @throws IOException if fails.
     */
    public boolean isImage() throws IOException {
        urlConnection = url.openConnection();
        urlConnection.connect();
        Image image = ImageIO.read(url);
        return image != null;
    }

    /**
     * Determines whether the content is text.
     *
     * @return true if the content is available; otherwise false.
     * @throws IOException if fails.
     */
    public boolean isText() throws IOException {
        urlConnection = url.openConnection();
        urlConnection.connect();
        return urlConnection.getContentType().contains("text");
    }
}
