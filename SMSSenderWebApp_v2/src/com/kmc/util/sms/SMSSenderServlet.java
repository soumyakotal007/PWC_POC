package com.kmc.util.sms;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SMSSenderServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1994431805135944411L;

	private static final String USER_ID = "kmcit";
	private static final String PASSWORD = "ites@2";
	private static final String SENDER_ID = "KMCITS";
	private static final String SECURE_KEY = "e796da0a-6e52-4eb5-b25b-4008d397b6bd";
	
	/*public static void main(String[] args) {
		SMSServices smsServices = new SMSServices();
		String smsText = "hello world";
		String mobileNumber = "9007082882";
		String response = smsServices.sendSingleSMS(USER_ID, PASSWORD, smsText, SENDER_ID, mobileNumber, SECURE_KEY);
		System.out.println("Resonse Code: " + response);
	}*/
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		System.out.println("SMS Sending started after encoding");
		PrintWriter out = response.getWriter();
		
		String responseMessage = "-1";
		String mobileNumber = String.valueOf(request.getParameter("mobileNumber"));
		String smsText = String.valueOf(request.getParameter("smsText"));
		
		try {
			SMSServices smsServices = new SMSServices();
			responseMessage = smsServices.sendSingleSMS(USER_ID, PASSWORD, smsText, SENDER_ID, mobileNumber, SECURE_KEY);
			System.out.println("Response message: " + responseMessage);
		} catch (Exception ex) {
			ex.printStackTrace();
			responseMessage = "There were an error: " + ex.getMessage();
		} 
		
		out.println(responseMessage);
		out.close();
	}
}
