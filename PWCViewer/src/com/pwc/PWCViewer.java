package com.pwc;

import java.awt.BorderLayout;
import java.awt.print.PrinterJob;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.PDFPageable;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.io.RandomAccessSourceFactory;
import com.itextpdf.text.pdf.Barcode128;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfDictionary;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.RandomAccessFileOrArray;
import com.pwc.constants.PWCViewerConstant;
import com.pwc.util.PWCViewerUtil;


public class PWCViewer {

/*	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				final JFrame f  = new JFrame();
				f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				final JFileChooser fileChooser = new JFileChooser();
				
				JPanel gui = new JPanel(new BorderLayout());
				final JEditorPane document = new JEditorPane();
				gui.add(new JScrollPane(document) , BorderLayout.CENTER);
			    
				JButton open = new JButton("Open");
				open.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						int result = fileChooser.showOpenDialog(f);
						if(result == JFileChooser.APPROVE_OPTION) {
							File file = fileChooser.getSelectedFile();
							try
							{
								document.setPage(file.toURI().toURL());
							}
							catch(Exception e)
							{
								e.printStackTrace();
							}
						}
						
					}
				});
				gui.add(open , BorderLayout.NORTH);
				f.setContentPane(gui);
                f.pack();
                f.setSize(400 , 300);
                f.setLocationByPlatform(true);
                f.setVisible(true);
			}
		});
	}
*/
	
	/*public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				final JFrame f  = new JFrame();
				f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				final JFileChooser fileChooser = new JFileChooser();
				
				JPanel gui = new JPanel(new BorderLayout());
				final JEditorPane document = new JEditorPane();
				gui.add(new JScrollPane(document) , BorderLayout.CENTER);
			    
				File file = new File(args[0]);
							try
							{
								document.setPage(file.toURI().toURL());
							}
							catch(Exception e)
							{
								e.printStackTrace();
							}
				f.setContentPane(gui);
                f.pack();
                f.setSize(400 , 300);
                f.setLocationByPlatform(true);
                f.setVisible(true);
			}
		});
	} */
    public static void main(String[] args) {
    	//File selectedFile = null;
		
    	try {
    		//selectedFile = new File(args[0]);
    		//System.out.println("Starting....");
			//convertTextToPDF(selectedFile);
			convertTextToPDF(new File("C:\\Users\\soumyak051\\Desktop\\testFS.s2c"));
            //System.out.println(args[0]);
			//selectedFile.delete();
		} catch (Exception e) {
            //if(selectedFile != null) selectedFile.delete();
            
            
			SwingUtilities.invokeLater(new Runnable() {
				
				@Override
				public void run() {
					final JFrame f  = new JFrame();
					f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					
					JPanel gui = new JPanel(new BorderLayout());
					
					final JTextArea txtRea = new JTextArea();
					

					gui.add(new JScrollPane(txtRea) , BorderLayout.CENTER);
				    
					txtRea.setText(e.getMessage());
					
					f.setContentPane(gui);
	                f.pack();
	                f.setSize(400 , 300);
	                f.setLocationByPlatform(true);
	                f.setVisible(true);
				}
			});
			
		}
	}
	
	/*public static void main(String args[])
	{
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				final JFrame f  = new JFrame();
				f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
				JPanel gui = new JPanel(new BorderLayout());
				
				final JTextArea txtRea = new JTextArea();
				

				gui.add(new JScrollPane(txtRea) , BorderLayout.CENTER);
			    
				txtRea.setText("Hi there");
				
				f.setContentPane(gui);
                f.pack();
                f.setSize(400 , 300);
                f.setLocationByPlatform(true);
                f.setVisible(true);
			}
		});
	}*/
	
	public static void convertTextToPDF(File file) throws Exception {

	    BufferedReader br = null;

	    try {
            
	    	float header = Float.parseFloat(PWCViewerUtil.getProperty(PWCViewerConstant.PDF_HEADER_WIDTH));
	    	float footer = Float.parseFloat(PWCViewerUtil.getProperty(PWCViewerConstant.PDF_FOOTER_WIDTH));	
	    	float left   = Float.parseFloat(PWCViewerUtil.getProperty(PWCViewerConstant.PDF_LEFT_WIDTH));
	    	float right  = Float.parseFloat(PWCViewerUtil.getProperty(PWCViewerConstant.PDF_RIGHT_WIDTH));
	    	
	    	float font_size = Float.parseFloat(PWCViewerUtil.getProperty(PWCViewerConstant.PDF_FONT_SIZE));
	    	
	    	float leadingFixedSize = Float.parseFloat(PWCViewerUtil.getProperty(PWCViewerConstant.PDF_LEADING_FIXED_SIZE));
	    	float leadingMultiple = Float.parseFloat(PWCViewerUtil.getProperty(PWCViewerConstant.PDF_LEADING_MULTIPLE));
	    	
	        Document pdfDoc = new Document(PageSize.A4, left, right, header, footer);
	        String output_file = file.getPath().replace(".s2c", "dummy.pdf");
	        System.out.println("## writing to: " + output_file);
	        FileOutputStream oriOut = new FileOutputStream(output_file);
	        PdfWriter docWriter = PdfWriter.getInstance(pdfDoc, oriOut);
	       // PdfAction jAction = PdfAction.javaScript("this.print(true);\r", docWriter);
	       // docWriter.addJavaScript(jAction);
	        docWriter.setPdfVersion(PdfWriter.VERSION_1_7);;

	        pdfDoc.open();
	        PdfContentByte cb = docWriter.getDirectContent();

	        BaseFont courier = BaseFont.createFont(BaseFont.COURIER, BaseFont.CP1252, BaseFont.EMBEDDED);
	        Font myfont = new Font(courier);

	        myfont.setStyle(Font.NORMAL);
	        myfont.setSize(font_size);

	        //pdfDoc.add(new Paragraph("\n"));

	        if (file.exists()) {

	            br = new BufferedReader(new FileReader(file));
	            String strLineRaw;
	            int noOfCopies=0;
	            List<String> printStrList = new ArrayList<>();
	            boolean isPrintStart = false;
	            while ((strLineRaw = br.readLine()) != null) {
            	if(strLineRaw.startsWith("no.ofcopiesTCS"))
	            	{
	            		isPrintStart = true;
	            		noOfCopies = Integer.parseInt(strLineRaw.substring(14));
	            		continue;
	            	}
	            	
	            	if(isPrintStart)
	            	{
	            		printStrList.add(strLineRaw);
	            	}
	            	
	            }
	            
	            if(noOfCopies > 1)
	            {
	            	List<String> copyList = new ArrayList<>();
	            	for(int u = 1 ; u < noOfCopies ; u++)
	            	{
	            		for(String cpyStr : printStrList)
	            		{
	            			copyList.add(cpyStr);
	            		}
	            	}
	            	
	            	printStrList.addAll(copyList);
	            }
	            
	            boolean startNewDoc = false;
	            int startNewDocCount = 0;
	            for (String strLine : printStrList) {
                 //Count four blank line to start new page.
	        		if(startNewDoc)
	        		{
	        			if(startNewDocCount == 4)
	        			{
	        				pdfDoc.newPage();
		        			startNewDoc = false;
	        			}
	        			else
	        			{
	        			   startNewDocCount++;
		        		   continue;
	        			}
	        		}			
	            	if(strLine.contains("barcodeTCS"))
	            	{
	            		 Barcode128 code128 = new Barcode128();
	                     code128.setCode(strLine.substring(strLine.indexOf("barcodeTCS")+10));
	                     code128.setCodeType(Barcode128.CODE128);
	                     code128.setFont(null);
	                    // code128.setX(1.0f) ;
	                    // code128.setBarHeight(30);
	                     Image code128Image = code128.createImageWithBarcode(cb, null, null);
	                    // code128Image.setAbsolutePosition(i, strLine.indexOf("barcodetcs") + 10);
	                     code128Image.setWidthPercentage(200);
	                     code128Image.scalePercent(100);
	                     code128Image.setAlignment(1);
	                     pdfDoc.add(code128Image);

	            	}
	            	else
	            	{	
	            		Paragraph para;
	            		if(strLine != null && strLine.trim().equals("."))
	            		{
	            			para = new Paragraph("" + "\n", myfont);
	            		}
	            		else
	            		{	
		                    para = new Paragraph(strLine + "\n", myfont);
	            		}  
		                para.setLeading(leadingFixedSize, leadingMultiple);
		                para.setAlignment(Element.ALIGN_LEFT);
		                pdfDoc.add(para);
	            	}
	            	
	            	if(strLine != null && strLine.trim().equals("."))
	            	{
	            		startNewDoc=true;	
	            	}
	            }
	        } else {
	            System.out.println("no such file exists!");
	        }
	       // printPDFDocToPrinter(pdfDoc.);
	        
	        pdfDoc.close();
	        String output_file_Without_blank_page = output_file.replace("dummy.pdf", ".pdf");
	        removeBlankPdfPages(output_file , output_file_Without_blank_page);
	        new File(output_file).delete();
	        
	        
	        //printPDFWithPDFBOX(new File(output_file_Without_blank_page));
	        //new File(output_file_Without_blank_page).delete();
	    }

	    catch (Exception e) {
	        e.printStackTrace();
	        throw e;
	    } finally {
	        if (br != null) 
	            br.close();
	    }

	}
	
	public static void printPDFDocToPrinter(byte[] docByte) throws Exception
	{
		InputStream br = new ByteArrayInputStream(docByte);
		BufferedReader in = new BufferedReader(new InputStreamReader(br));
		String line;
		//if you use windows
		FileWriter out = new FileWriter("////IP Printer//printer name");
		//if you use linux you can try SMB:
		while((line = in.readLine()) != null)
		{  
		    System.out.println("line"+line);
		    out.write(line);
		    out.write(0x0D);  
		    out.write('\n');
		}
		out.close();
		in.close();
	}
	
	public static void printDOC(File fileToPrint) throws Exception {
		try
		{
		FileInputStream in = new FileInputStream(fileToPrint);
	    Doc doc = new SimpleDoc(in, DocFlavor.INPUT_STREAM.AUTOSENSE, null);
	    PrintService service = PrintServiceLookup.lookupDefaultPrintService();
	    service.createPrintJob().print(doc, null);
	    in.close();
		}
		catch(Exception e)
		{
			throw e;
		}
		finally {
			if(fileToPrint.delete())
			{
				System.out.println("File deleted successfully.");
			}
			else
			{
				System.out.println("File deletion failed." + fileToPrint.getAbsoluteFile());
			}
		}   
	}
	
   public static void printPDFWithPDFBOX(File fileToPrint) throws Exception
   {
	   
	   /*PrinterJob job = PrinterJob.getPrinterJob();
	   job.setPageable( new PDFPageable(document));
	   
	   //Define custom paper
	   Paper paper = new Paper();
	   paper.setSize(306, 396); //1/72 inch
	   paper.setImageableArea(0, 0, paper.getWidth(), paper.getHeight());//no mergin
	   
	   //custom page format
	   PageFormat pageFormat = new PageFormat();
	   pageFormat.setPaper(paper);
	   
	   //Override the page format
	   Book book = new Book();
	   
	   //Append all pages
	   book.append(new PDFPrintable(document), pageFormat , document.getNumberOfPages());
	   
	   
	   job.print();*/
	   
	   try
		{
		   PDDocument pdf = PDDocument.load(fileToPrint);
		   PrinterJob job = PrinterJob.getPrinterJob();
		   job.setPageable(new PDFPageable(pdf));
		   job.print();
		}
		catch(Exception e)
		{
			throw e;
		}
		finally {
			if(fileToPrint.delete())
			{
				System.out.println("File deleted successfully.");
			}
			else
			{
				System.out.println("File deletion failed." + fileToPrint.getAbsoluteFile());
			}
		}   
	   
   }
   
   // value where we can consider that this is a blank image
   // can be much higher or lower depending of what is considered as a blank page
   public static final int BLANK_THRESHOLD = 160;
   //Delete duplicate PDF page.
   public static void removeBlankPdfPages(String source, String destination)
	        throws IOException, DocumentException
	    {
	        PdfReader r = null;
	        RandomAccessSourceFactory rasf = null;
	        RandomAccessFileOrArray raf = null;
	        Document document = null;
	        PdfCopy writer = null;

	        try {
	            r = new PdfReader(source);
	            // deprecated
	            //    RandomAccessFileOrArray raf
	            //           = new RandomAccessFileOrArray(pdfSourceFile);
	            // itext 5.4.1
	            rasf = new RandomAccessSourceFactory();
	            raf = new RandomAccessFileOrArray(rasf.createBestSource(source));
	            document = new Document(r.getPageSizeWithRotation(1));
	            writer = new PdfCopy(document, new FileOutputStream(destination));
	            document.open();
	            PdfImportedPage page = null;

	            for (int i=1; i<=r.getNumberOfPages(); i++) {
	                // first check, examine the resource dictionary for /Font or
	                // /XObject keys.  If either are present -> not blank.
	                PdfDictionary pageDict = r.getPageN(i);
	                PdfDictionary resDict = (PdfDictionary) pageDict.get( PdfName.RESOURCES );
	                boolean noFontsOrImages = true;
	                if (resDict != null) {
	                  noFontsOrImages = resDict.get( PdfName.FONT ) == null &&
	                                    resDict.get( PdfName.XOBJECT ) == null;
	                }
	                System.out.println(i + " noFontsOrImages " + noFontsOrImages);

	                if (!noFontsOrImages) {
	                    byte bContent [] = r.getPageContent(i,raf);
	                    ByteArrayOutputStream bs = new ByteArrayOutputStream();
	                    bs.write(bContent);
	                    System.out.println
	                      (i + bs.size() + " > BLANK_THRESHOLD " +  (bs.size() > BLANK_THRESHOLD));
	                    if (bs.size() > BLANK_THRESHOLD) {
	                        page = writer.getImportedPage(r, i);
	                        writer.addPage(page);
	                    }
	                }
	            }
	        }
	        finally {
	            if (document != null) document.close();
	            if (writer != null) writer.close();
	            if (raf != null) raf.close();
	            if (r != null) r.close();
	        }
	    }
	 
}
