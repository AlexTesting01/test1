package com.example.utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class ImageComparator {

    /**
     * Compares two images and returns true if the number of differing pixels is
     * within the allowed tolerance.
     *
     * @param baselinePath Path to the baseline image
     * @param currentPath  Path to the current screenshot
     * @param tolerance    Allowed number of differing pixels
     * @return true if difference is within tolerance, false otherwise
     */
    public static boolean compareImages(String baselinePath, String currentPath, int tolerance) {
        try {
            BufferedImage baseline = ImageIO.read(new File(baselinePath));
            BufferedImage current = ImageIO.read(new File(currentPath));

            if (baseline.getWidth() != current.getWidth() || baseline.getHeight() != current.getHeight()) {
                throw new IllegalArgumentException("Image sizes do not match.");
            }

            int width = baseline.getWidth();
            int height = baseline.getHeight();
            int diffPixels = 0;

            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    if (baseline.getRGB(x, y) != current.getRGB(x, y)) {
                        diffPixels++;
                    }
                }
            }

            System.out.printf("Diff pixels: %d (tolerance: %d)%n", diffPixels, tolerance);
            return diffPixels <= tolerance;

        } catch (Exception e) {
            throw new RuntimeException("Error comparing images: " + e.getMessage(), e);
        }
    }
}
