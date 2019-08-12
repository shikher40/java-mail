

/*Sending mail Using JavaMail to Yahoo and Gmail accounts
 * download the javamail jars from
http://java.sun.com/products/javamail/downloads/index.html and put them in
classpath and then run the program.
 
 */

package networks;

import java.io.File;
import java.security.Security;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class mytest{

private static final String SMTP_HOST_NAME = "smtp.gmail.com";
private static final String SMTP_PORT = "465";
private static final String emailMsgTxt = "Test Message Contents";
private static final String emailSubjectTxt = "A test from gmail";
private static  String emailFromAddress;
private static final String SSL_FACTORY =
"javax.net.ssl.SSLSocketFactory";
private static  ArrayList<String>  sendTo=new ArrayList<String>() ;
public static String sender,pass;
private static final String fileAttachment="D:\\hai.txt";
public static int l;
public static String s="";
public static String mailsubject="";
public static String messagebody="";
public static void main(String args[]) throws Exception {

	Scanner sc=new  Scanner(System.in);
	String con;
	
	System.out.println("Enter your Email :");
	sender=sc.next();
	emailFromAddress=sender;
	System.out.println("Enter your password :");
	pass=sc.next();
	con=sc.nextLine();
	System.out.println("Enter mail subject:");
	mailsubject=sc.nextLine();
	System.out.println("enter message :");
	messagebody=sc.nextLine();
	System.out.println("Enter receivers Email and enter send");
	while(!mytest.s.equals("send"))
	{
		mytest.s=sc.next();
		if(!mytest.s.equals("send"))
		mytest.sendTo.add(mytest.s);
	}
	
	
	
	
Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());


new mytest().sendSSLMessage(sendTo, emailSubjectTxt,
emailMsgTxt, emailFromAddress);
System.out.println("Sucessfully Sent mail to All Users");

}

public void sendSSLMessage(ArrayList<String> recipients, String subject,
String message, String from) throws MessagingException {
boolean debug = true;

Properties props = new Properties();
props.put("mail.smtp.host", SMTP_HOST_NAME);
props.put("mail.smtp.auth", "true");
props.put("mail.debug", "true");
props.put("mail.smtp.port", SMTP_PORT);
props.put("mail.smtp.socketFactory.port", SMTP_PORT);
props.put("mail.smtp.socketFactory.class", SSL_FACTORY);
props.put("mail.smtp.socketFactory.fallback", "false");

Session session = Session.getDefaultInstance(props,
new javax.mail.Authenticator() {

protected PasswordAuthentication getPasswordAuthentication() {
	
return new PasswordAuthentication(sender,pass);
}
});



MimeMessage message1 =
new MimeMessage(session);
message1.setFrom(
new InternetAddress(from));
for(int i=0;i<mytest.sendTo.size();i++)
{
message1.addRecipient(
Message.RecipientType.TO,
new InternetAddress(recipients.get(i)));
}

message1.setSubject(mailsubject);

// create the message part
MimeBodyPart messageBodyPart =
new MimeBodyPart();

//fill message
messageBodyPart.setText(messagebody);

Multipart multipart = new MimeMultipart();
multipart.addBodyPart(messageBodyPart);

// Part two is attachment
messageBodyPart = new MimeBodyPart();
DataSource source =
new FileDataSource(fileAttachment);
messageBodyPart.setDataHandler(
new DataHandler(source));
messageBodyPart.setFileName(fileAttachment);
multipart.addBodyPart(messageBodyPart);

// Put parts in messageMoh. Julahan Near Rajput Sabha Bhawan
message1.setContent(multipart);

// Send the message
Transport.send( message1 );
}
} 
