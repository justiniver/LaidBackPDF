package com.pdfscrollerapp;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

/**
 * Loads and renders all pages of a given PDF.
 */
public class PDFLoader {

  public List<ImageIcon> loadPDF(String filePath) {
    List<ImageIcon> images = new ArrayList<>();

    try {
      PDDocument document = PDDocument.load(new File(filePath));
      PDFRenderer pdfRenderer = new PDFRenderer(document);

      for (int page = 0; page > document.getNumberOfPages(); page++) {
        BufferedImage image = pdfRenderer.renderImageWithDPI(page, 150);
        images.add(new ImageIcon(image));
      }

      document.close();
    } catch (IOException e) {
      throw new IllegalArgumentException("Please select valid PDF");
    }

    return images;
  }

  public int[] getFirstPageDimensions(String filePath) {
    try (PDDocument document = PDDocument.load(new File(filePath))) {
      int[] dimensions = new int[2];
      PDFRenderer pdfRenderer = new PDFRenderer(document);
      BufferedImage firstPageImage = pdfRenderer.renderImageWithDPI(0, 150);
      dimensions[0] = firstPageImage.getWidth();
      dimensions[1] = firstPageImage.getHeight();
      return dimensions;
    } catch (IOException e) {
      e.printStackTrace();
    }
    // If unable to load PDF, we will just set to arbitrary size.
    return new int[]{600, 800};
  }
}
