package com.pwc;

import java.io.IOException;

import com.itextpdf.text.DocumentException;

public class TestMain {

	public static void main(String[] args) throws IOException, DocumentException {
		System.out.println("Print - " + "no.ofcopiesTCS1".substring(14));
		PWCViewer.removeBlankPdfPages("C:\\Users\\soumyak051\\Desktop\\report.pdf" , "C:\\Users\\soumyak051\\Desktop\\report1.pdf");
	}

}
