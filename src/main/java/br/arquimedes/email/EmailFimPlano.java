/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.arquimedes.email;

import br.arquimedes.model.Modulo;
import br.arquimedes.model.Plano;
import br.arquimedes.model.Usuario;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.*;

/**
 *
 * @author Usuario
 */
public class EmailFimPlano extends EmailConfig {

    static final String SUBJECT = "Renovação de plano";
    static String BODY = "";

    public EmailFimPlano(Usuario usuario, Modulo modulo, Plano plano) {

        TO = usuario.getEmail();
        BODY = String.join(
                System.getProperty("line.separator"),
                "<h1>Expiração de plano</h1>",
                "<p>Olá, " + usuario.getNomePessoa() + " estamos entrando em contato para notifiá-lo que em dois dias seu plano " + plano.getNomePlano() + " no moduo: " + modulo.getNome() + " irá expirar</p>",
                "<p>para sua comodidade, caso o mesmo não seja cancelado será re-cadastrado com as mesmas normas e clausas de contrato inicialmente acordadas</p>",
                "<p></p>",
                "<p>Para efetuar o cancelamento, logar-se no link abaixo:</p>",
                "<a href='http://localhost:8080/Arquimedes/Login.jsp'>Javamail Package</a>"      
        );
    }

    public void enviar() {

        // Create a Properties object to contain connection configuration information.
        Properties props = System.getProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.port", PORT);
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");

        // Create a Session object to represent a mail session with the specified properties. 
        Session session = Session.getInstance(props, new Authenticator(){
            protected PasswordAuthentication getPasswodAuthentication(){
                return new PasswordAuthentication(SMTP_USERNAME, SMTP_PASSWORD);
            }
        });

        // Create a message with the specified information. 
        MimeMessage msg = new MimeMessage(session);
        try {
            msg.setFrom(new InternetAddress(FROM, FROMNAME));

            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(TO));
            msg.setSubject(SUBJECT);
            msg.setContent(BODY, "text/html");

            Transport transport = session.getTransport();

            // Send the message.
            System.out.println("Sending...");

            // Connect to Amazon SES using the SMTP username and password you specified above.
            transport.connect(HOST, SMTP_USERNAME, SMTP_PASSWORD);

            // Send the email.
            transport.sendMessage(msg, msg.getAllRecipients());
            System.out.println("Email sent!");
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("The email was not sent.");
            System.out.println("Error message: " + ex.getMessage());
        }
    }
}