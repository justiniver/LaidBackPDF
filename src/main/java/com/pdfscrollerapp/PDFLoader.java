package com.pdfscrollerapp;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

/**
 * Loads and renders all pages of a PDF file.
 * This class is responsible for loading a PDF file from a specified path,
 * rendering each page as an {@link ImageIcon}, and returning these images for display.
 * It also provides functionality to retrieve the dimensions of the first page
 * to dynamically set up the GUI dimensions.
 */
public class PDFLoader {

  /**
   * Loads and renders each page of the PDF as an image.
   * Opens the PDF document from the specified file path and uses {@link PDFRenderer} to
   * render each page as a {@link BufferedImage}, converting each image to an {@link ImageIcon}.
   * The rendered images are added to a list, allowing the PDF to be displayed one page at a time.
   *
   * @param filePath the path to the PDF file to be loaded
   * @return a list of {@link ImageIcon} objects, each representing a page of the PDF
   * @throws IllegalArgumentException if the PDF cannot be loaded due to an invalid file path
   */
  public List<ImageIcon> loadPDF(String filePath) {
    List<ImageIcon> images = new ArrayList<>();

    try {
      PDDocument document = PDDocument.load(new File(filePath));
      PDFRenderer pdfRenderer = new PDFRenderer(document);
      int numPagesInDocument = document.getNumberOfPages();

      for (int page = 0; page < numPagesInDocument; page++) {
        BufferedImage image = pdfRenderer.renderImageWithDPI(page, 150);
        images.add(new ImageIcon(image));
      }

      document.close();
    } catch (IOException e) {
      throw new IllegalArgumentException("Please select valid PDF");
    }

    return images;
  }

  /**
   * Retrieves the dimensions of the first page of the PDF.
   * This method returns the width and height of the first page, which can be useful for setting
   * the initial size of a viewer to match the dimensions of the PDF.
   *
   * @param filePath the path to the PDF file
   * @return an array containing the width and height of the first page, respectively
   * @throws IllegalArgumentException if the PDF cannot be loaded due to an invalid file path
   */
  public int[] getFirstPageDimensions(String filePath) {
    try (PDDocument document = PDDocument.load(new File(filePath))) {
      int[] dimensions = new int[2];
      PDFRenderer pdfRenderer = new PDFRenderer(document);
      BufferedImage firstPageImage = pdfRenderer.renderImageWithDPI(0, 150);
      dimensions[0] = firstPageImage.getWidth();
      dimensions[1] = firstPageImage.getHeight();
      return dimensions;
    } catch (IOException e) {
      throw new IllegalArgumentException("Please select valid PDF");
    }
  }
}
