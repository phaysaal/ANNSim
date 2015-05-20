package fsannsim;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.text.AttributedCharacterIterator;

/**
 * <p>Title: Fs ANN Sim</p>
 *
 * <p>Description: Artificial Neural Network Simulator</p>
 *
 * <p>Copyright: Open Source and Open Resource </p>
 *
 * <p>Company: F Sharp</p>
 *
 * @author Mahmudul Faisal Al Ameen
 * @version 1.0
 */
public class FsGraphics extends Graphics {
    public FsGraphics() {
    }

    /**
     * Clears the specified rectangle by filling it with the background color
     * of the current drawing surface.
     *
     * @param x the <i>x</i> coordinate of the rectangle to clear.
     * @param y the <i>y</i> coordinate of the rectangle to clear.
     * @param width the width of the rectangle to clear.
     * @param height the height of the rectangle to clear.
     * @todo Implement this java.awt.Graphics method
     */
    public void clearRect(int x, int y, int width, int height) {
    }

    /**
     * Intersects the current clip with the specified rectangle.
     *
     * @param x the x coordinate of the rectangle to intersect the clip with
     * @param y the y coordinate of the rectangle to intersect the clip with
     * @param width the width of the rectangle to intersect the clip with
     * @param height the height of the rectangle to intersect the clip with
     * @todo Implement this java.awt.Graphics method
     */
    public void clipRect(int x, int y, int width, int height) {
    }

    /**
     * Copies an area of the component by a distance specified by
     * <code>dx</code> and <code>dy</code>.
     *
     * @param x the <i>x</i> coordinate of the source rectangle.
     * @param y the <i>y</i> coordinate of the source rectangle.
     * @param width the width of the source rectangle.
     * @param height the height of the source rectangle.
     * @param dx the horizontal distance to copy the pixels.
     * @param dy the vertical distance to copy the pixels.
     * @todo Implement this java.awt.Graphics method
     */
    public void copyArea(int x, int y, int width, int height, int dx, int dy) {
    }

    /**
     * Creates a new <code>Graphics</code> object that is a copy of this
     * <code>Graphics</code> object.
     *
     * @return a new graphics context that is a copy of this graphics
     *   context.
     * @todo Implement this java.awt.Graphics method
     */
    public Graphics create() {
        return null;
    }

    /**
     * Disposes of this graphics context and releases any system resources
     * that it is using.
     *
     * @todo Implement this java.awt.Graphics method
     */
    public void dispose() {
    }

    /**
     * Draws the outline of a circular or elliptical arc covering the
     * specified rectangle.
     *
     * @param x the <i>x</i> coordinate of the upper-left corner of the arc
     *   to be drawn.
     * @param y the <i>y</i> coordinate of the upper-left corner of the arc
     *   to be drawn.
     * @param width the width of the arc to be drawn.
     * @param height the height of the arc to be drawn.
     * @param startAngle the beginning angle.
     * @param arcAngle the angular extent of the arc, relative to the start
     *   angle.
     * @todo Implement this java.awt.Graphics method
     */
    public void drawArc(int x, int y, int width, int height, int startAngle,
                        int arcAngle) {
    }

    /**
     * Draws as much of the specified area of the specified image as is
     * currently available, scaling it on the fly to fit inside the specified
     * area of the destination drawable surface.
     *
     * @param img the specified image to be drawn
     * @param dx1 the <i>x</i> coordinate of the first corner of the
     *   destination rectangle.
     * @param dy1 the <i>y</i> coordinate of the first corner of the
     *   destination rectangle.
     * @param dx2 the <i>x</i> coordinate of the second corner of the
     *   destination rectangle.
     * @param dy2 the <i>y</i> coordinate of the second corner of the
     *   destination rectangle.
     * @param sx1 the <i>x</i> coordinate of the first corner of the source
     *   rectangle.
     * @param sy1 the <i>y</i> coordinate of the first corner of the source
     *   rectangle.
     * @param sx2 the <i>x</i> coordinate of the second corner of the source
     *   rectangle.
     * @param sy2 the <i>y</i> coordinate of the second corner of the source
     *   rectangle.
     * @param bgcolor the background color to paint under the non-opaque
     *   portions of the image.
     * @param observer object to be notified as more of the image is scaled
     *   and converted.
     * @return <code>true</code> if the current output representation is
     *   complete; <code>false</code> otherwise.
     * @todo Implement this java.awt.Graphics method
     */
    public boolean drawImage(Image img, int dx1, int dy1, int dx2, int dy2,
                             int sx1, int sy1, int sx2, int sy2, Color bgcolor,
                             ImageObserver observer) {
        return false;
    }

    /**
     * Draws as much of the specified image as is currently available.
     *
     * @param img the specified image to be drawn.
     * @param x the <i>x</i> coordinate.
     * @param y the <i>y</i> coordinate.
     * @param observer object to be notified as more of the image is
     *   converted.
     * @return <code>true</code> if the image is completely loaded;
     *   <code>false</code> otherwise.
     * @todo Implement this java.awt.Graphics method
     */
    public boolean drawImage(Image img, int x, int y, ImageObserver observer) {
        return false;
    }

    /**
     * Draws as much of the specified image as is currently available.
     *
     * @param img the specified image to be drawn.
     * @param x the <i>x</i> coordinate.
     * @param y the <i>y</i> coordinate.
     * @param bgcolor the background color to paint under the non-opaque
     *   portions of the image.
     * @param observer object to be notified as more of the image is
     *   converted.
     * @return <code>true</code> if the image is completely loaded;
     *   <code>false</code> otherwise.
     * @todo Implement this java.awt.Graphics method
     */
    public boolean drawImage(Image img, int x, int y, Color bgcolor,
                             ImageObserver observer) {
        return false;
    }

    /**
     * Draws as much of the specified image as has already been scaled to fit
     * inside the specified rectangle.
     *
     * @param img the specified image to be drawn.
     * @param x the <i>x</i> coordinate.
     * @param y the <i>y</i> coordinate.
     * @param width the width of the rectangle.
     * @param height the height of the rectangle.
     * @param bgcolor the background color to paint under the non-opaque
     *   portions of the image.
     * @param observer object to be notified as more of the image is
     *   converted.
     * @return <code>true</code> if the current output representation is
     *   complete; <code>false</code> otherwise.
     * @todo Implement this java.awt.Graphics method
     */
    public boolean drawImage(Image img, int x, int y, int width, int height,
                             Color bgcolor, ImageObserver observer) {
        return false;
    }

    /**
     * Draws as much of the specified area of the specified image as is
     * currently available, scaling it on the fly to fit inside the specified
     * area of the destination drawable surface.
     *
     * @param img the specified image to be drawn
     * @param dx1 the <i>x</i> coordinate of the first corner of the
     *   destination rectangle.
     * @param dy1 the <i>y</i> coordinate of the first corner of the
     *   destination rectangle.
     * @param dx2 the <i>x</i> coordinate of the second corner of the
     *   destination rectangle.
     * @param dy2 the <i>y</i> coordinate of the second corner of the
     *   destination rectangle.
     * @param sx1 the <i>x</i> coordinate of the first corner of the source
     *   rectangle.
     * @param sy1 the <i>y</i> coordinate of the first corner of the source
     *   rectangle.
     * @param sx2 the <i>x</i> coordinate of the second corner of the source
     *   rectangle.
     * @param sy2 the <i>y</i> coordinate of the second corner of the source
     *   rectangle.
     * @param observer object to be notified as more of the image is scaled
     *   and converted.
     * @return <code>true</code> if the current output representation is
     *   complete; <code>false</code> otherwise.
     * @todo Implement this java.awt.Graphics method
     */
    public boolean drawImage(Image img, int dx1, int dy1, int dx2, int dy2,
                             int sx1, int sy1, int sx2, int sy2,
                             ImageObserver observer) {
        return false;
    }

    /**
     * Draws as much of the specified image as has already been scaled to fit
     * inside the specified rectangle.
     *
     * @param img the specified image to be drawn.
     * @param x the <i>x</i> coordinate.
     * @param y the <i>y</i> coordinate.
     * @param width the width of the rectangle.
     * @param height the height of the rectangle.
     * @param observer object to be notified as more of the image is
     *   converted.
     * @return <code>true</code> if the current output representation is
     *   complete; <code>false</code> otherwise.
     * @todo Implement this java.awt.Graphics method
     */
    public boolean drawImage(Image img, int x, int y, int width, int height,
                             ImageObserver observer) {
        return false;
    }

    /**
     * Draws a line, using the current color, between the points
     * <code>(x1,&nbsp;y1)</code> and <code>(x2,&nbsp;y2)</code> in this
     * graphics context's coordinate system.
     *
     * @param x1 the first point's <i>x</i> coordinate.
     * @param y1 the first point's <i>y</i> coordinate.
     * @param x2 the second point's <i>x</i> coordinate.
     * @param y2 the second point's <i>y</i> coordinate.
     * @todo Implement this java.awt.Graphics method
     */
    public void drawLine(int x1, int y1, int x2, int y2) {
    }

    /**
     * Draws the outline of an oval.
     *
     * @param x the <i>x</i> coordinate of the upper left corner of the oval
     *   to be drawn.
     * @param y the <i>y</i> coordinate of the upper left corner of the oval
     *   to be drawn.
     * @param width the width of the oval to be drawn.
     * @param height the height of the oval to be drawn.
     * @todo Implement this java.awt.Graphics method
     */
    public void drawOval(int x, int y, int width, int height) {
    }

    /**
     * Draws a closed polygon defined by arrays of <i>x</i> and <i>y</i>
     * coordinates.
     *
     * @param xPoints a an array of <code>x</code> coordinates.
     * @param yPoints a an array of <code>y</code> coordinates.
     * @param nPoints a the total number of points.
     * @todo Implement this java.awt.Graphics method
     */
    public void drawPolygon(int[] xPoints, int[] yPoints, int nPoints) {
    }

    /**
     * Draws a sequence of connected lines defined by arrays of <i>x</i> and
     * <i>y</i> coordinates.
     *
     * @param xPoints an array of <i>x</i> points
     * @param yPoints an array of <i>y</i> points
     * @param nPoints the total number of points
     * @todo Implement this java.awt.Graphics method
     */
    public void drawPolyline(int[] xPoints, int[] yPoints, int nPoints) {
    }

    /**
     * Draws an outlined round-cornered rectangle using this graphics
     * context's current color.
     *
     * @param x the <i>x</i> coordinate of the rectangle to be drawn.
     * @param y the <i>y</i> coordinate of the rectangle to be drawn.
     * @param width the width of the rectangle to be drawn.
     * @param height the height of the rectangle to be drawn.
     * @param arcWidth the horizontal diameter of the arc at the four
     *   corners.
     * @param arcHeight the vertical diameter of the arc at the four corners.
     * @todo Implement this java.awt.Graphics method
     */
    public void drawRoundRect(int x, int y, int width, int height, int arcWidth,
                              int arcHeight) {
    }

    /**
     * Draws the text given by the specified iterator, using this graphics
     * context's current color.
     *
     * @param iterator the iterator whose text is to be drawn
     * @param x the <i>x</i> coordinate.
     * @param y the <i>y</i> coordinate.
     * @todo Implement this java.awt.Graphics method
     */
    public void drawString(AttributedCharacterIterator iterator, int x, int y) {
    }

    /**
     * Draws the text given by the specified string, using this graphics
     * context's current font and color.
     *
     * @param str the string to be drawn.
     * @param x the <i>x</i> coordinate.
     * @param y the <i>y</i> coordinate.
     * @todo Implement this java.awt.Graphics method
     */
    public void drawString(String str, int x, int y) {
    }

    /**
     * Fills a circular or elliptical arc covering the specified rectangle.
     *
     * @param x the <i>x</i> coordinate of the upper-left corner of the arc
     *   to be filled.
     * @param y the <i>y</i> coordinate of the upper-left corner of the arc
     *   to be filled.
     * @param width the width of the arc to be filled.
     * @param height the height of the arc to be filled.
     * @param startAngle the beginning angle.
     * @param arcAngle the angular extent of the arc, relative to the start
     *   angle.
     * @todo Implement this java.awt.Graphics method
     */
    public void fillArc(int x, int y, int width, int height, int startAngle,
                        int arcAngle) {
    }

    /**
     * Fills an oval bounded by the specified rectangle with the current
     * color.
     *
     * @param x the <i>x</i> coordinate of the upper left corner of the oval
     *   to be filled.
     * @param y the <i>y</i> coordinate of the upper left corner of the oval
     *   to be filled.
     * @param width the width of the oval to be filled.
     * @param height the height of the oval to be filled.
     * @todo Implement this java.awt.Graphics method
     */
    public void fillOval(int x, int y, int width, int height) {
    }

    /**
     * Fills a closed polygon defined by arrays of <i>x</i> and <i>y</i>
     * coordinates.
     *
     * @param xPoints a an array of <code>x</code> coordinates.
     * @param yPoints a an array of <code>y</code> coordinates.
     * @param nPoints a the total number of points.
     * @todo Implement this java.awt.Graphics method
     */
    public void fillPolygon(int[] xPoints, int[] yPoints, int nPoints) {
    }

    /**
     * Fills the specified rectangle.
     *
     * @param x the <i>x</i> coordinate of the rectangle to be filled.
     * @param y the <i>y</i> coordinate of the rectangle to be filled.
     * @param width the width of the rectangle to be filled.
     * @param height the height of the rectangle to be filled.
     * @todo Implement this java.awt.Graphics method
     */
    public void fillRect(int x, int y, int width, int height) {
    }

    /**
     * Fills the specified rounded corner rectangle with the current color.
     *
     * @param x the <i>x</i> coordinate of the rectangle to be filled.
     * @param y the <i>y</i> coordinate of the rectangle to be filled.
     * @param width the width of the rectangle to be filled.
     * @param height the height of the rectangle to be filled.
     * @param arcWidth the horizontal diameter of the arc at the four
     *   corners.
     * @param arcHeight the vertical diameter of the arc at the four corners.
     * @todo Implement this java.awt.Graphics method
     */
    public void fillRoundRect(int x, int y, int width, int height, int arcWidth,
                              int arcHeight) {
    }

    /**
     * Gets the current clipping area.
     *
     * @return a <code>Shape</code> object representing the current clipping
     *   area, or <code>null</code> if no clip is set.
     * @todo Implement this java.awt.Graphics method
     */
    public Shape getClip() {
        return null;
    }

    /**
     * Returns the bounding rectangle of the current clipping area.
     *
     * @return the bounding rectangle of the current clipping area, or
     *   <code>null</code> if no clip is set.
     * @todo Implement this java.awt.Graphics method
     */
    public Rectangle getClipBounds() {
        return null;
    }

    /**
     * Gets this graphics context's current color.
     *
     * @return this graphics context's current color.
     * @todo Implement this java.awt.Graphics method
     */
    public Color getColor() {
        return null;
    }

    /**
     * Gets the current font.
     *
     * @return this graphics context's current font.
     * @todo Implement this java.awt.Graphics method
     */
    public Font getFont() {
        return null;
    }

    /**
     * Gets the font metrics for the specified font.
     *
     * @return the font metrics for the specified font.
     * @param f the specified font
     * @todo Implement this java.awt.Graphics method
     */
    public FontMetrics getFontMetrics(Font f) {
        return null;
    }

    /**
     * Sets the current clip to the rectangle specified by the given
     * coordinates.
     *
     * @param x the <i>x</i> coordinate of the new clip rectangle.
     * @param y the <i>y</i> coordinate of the new clip rectangle.
     * @param width the width of the new clip rectangle.
     * @param height the height of the new clip rectangle.
     * @todo Implement this java.awt.Graphics method
     */
    public void setClip(int x, int y, int width, int height) {
    }

    /**
     * Sets the current clipping area to an arbitrary clip shape.
     *
     * @param clip the <code>Shape</code> to use to set the clip
     * @todo Implement this java.awt.Graphics method
     */
    public void setClip(Shape clip) {
    }

    /**
     * Sets this graphics context's current color to the specified color.
     *
     * @param c the new rendering color.
     * @todo Implement this java.awt.Graphics method
     */
    public void setColor(Color c) {
    }

    /**
     * Sets this graphics context's font to the specified font.
     *
     * @param font the font.
     * @todo Implement this java.awt.Graphics method
     */
    public void setFont(Font font) {
    }

    /**
     * Sets the paint mode of this graphics context to overwrite the
     * destination with this graphics context's current color.
     *
     * @todo Implement this java.awt.Graphics method
     */
    public void setPaintMode() {
    }

    /**
     * Sets the paint mode of this graphics context to alternate between this
     * graphics context's current color and the new specified color.
     *
     * @param c1 the XOR alternation color
     * @todo Implement this java.awt.Graphics method
     */
    public void setXORMode(Color c1) {
    }

    /**
     * Translates the origin of the graphics context to the point
     * (<i>x</i>,&nbsp;<i>y</i>) in the current coordinate system.
     *
     * @param x the <i>x</i> coordinate.
     * @param y the <i>y</i> coordinate.
     * @todo Implement this java.awt.Graphics method
     */
    public void translate(int x, int y) {
    }
}
