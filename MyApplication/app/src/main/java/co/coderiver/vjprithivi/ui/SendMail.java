package co.coderiver.vjprithivi.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.text.Html;
import android.widget.Toast;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Created by vjprithivi on 6/19/2016.
 */
public class SendMail extends AsyncTask<Void, Void, Void> {

    private final String htnltext = "<html><h1>heaDING </h1> What Type of studies:\" + e1 + '\\n' + '\\n' + \" Name of University or school:\" + e2 + '\\n'\n" +
            "                    + '\\n' + \"Town or city:\" + e3 + '\\n' + '\\n' + \"Postal code:\" + e4 + '\\n' + '\\n' + \"E-mail:\" + email +\n" +
            "                    '\\n' + '\\n' + \"Do you have an already acceptance letter:\"\n" +
            "                    + s1 + '\\n' + '\\n' + \"Do you have a Guarantor:\" + s2 +\n" +
            "                    '\\n' + '\\n' + \"Do you have a Bank acount:\" + s3 + '\\n' + \"Name\" + name + '\\n' + \"Date of Brith:\"\n" +
            "                    + Dateofbrith + '\\n' + \"Nationality:\" + nationality + '\\n' + \"Telephone:\" + mobile +\n" +
            "                    \"\\n\" + \"studies:\" + studies);\n";
    //Declaring Variables
    private Context context;
    private Session session;
    //Information to send email
    private String email;
    private String subject;
    private String Dateofbrith;
    private String name;
    private String mobile;
    private String e1;
    private String e2;
    private String e3;
    private String e4;
    private String s1;
    private String s2;
    private String s3;
    private String studies;
    private String nationality;
    //Progressdialog to show while sending email
    private ProgressDialog progressDialog;

    //Class Constructor
    public SendMail(Context context, String email, String subject, String e1, String e2, String e3, String e4, String s1, String s2, String s3, String name, String Dateofbrith, String nationality, String mobile, String studies) {
        //Initializing variables
        this.context = context;
        this.email = email;
        this.subject = subject;


        this.e1 = e1;
        this.e2 = e2;
        this.e3 = e3;
        this.e4 = e4;
        this.s1 = s1;
        this.s2 = s2;
        this.s3 = s3;
        this.studies = studies;


        this.name = name;
        this.Dateofbrith = Dateofbrith;
        this.nationality = nationality;
        this.mobile = mobile;


    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        //Showing progress dialog while sending email
        progressDialog = ProgressDialog.show(context, "Sending message", "Please wait...", false, false);
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        //Dismissing the progress dialog
        progressDialog.dismiss();
        //Showing a success message
        Toast.makeText(context, "Message Sent", Toast.LENGTH_LONG).show();
    }

    @Override
    protected Void doInBackground(Void... params) {
        //Creating properties
        Properties props = new Properties();

        //Configuring properties for gmail
        //If you are not using gmail you may need to change the values
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        //Creating a new session
        session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    //Authenticating the password
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(Config.EMAIL, Config.PASSWORD);
                    }
                });

        try {
            //Creating MimeMessage object
            MimeMessage mm = new MimeMessage(session);

            //Setting sender address
            mm.setFrom(new InternetAddress(Config.EMAIL));
            //Adding receiver
            mm.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
            //Adding subject
            mm.setSubject(subject);

            //Adding message
         mm.setText("What Type of studies:" + e1 + '\n' + '\n' + " Name of University or school:" + e2 + '\n'
                    + '\n' + "Town or city:" + e3 + '\n' + '\n' + "Postal code:" + e4 + '\n' + '\n' + "E-mail:" + email +
                    '\n' + '\n' + "Do you have an already acceptance letter:"
                    + s1 + '\n' + '\n' + "Do you have a Guarantor:" + s2 +
                    '\n' + '\n' + "Do you have a Bank acount:" + s3 + '\n' + "Name" + name + '\n' + "Date of Brith:"
                    + Dateofbrith + '\n' + "Nationality:" + nationality + '\n' + "Telephone:" + mobile +
                    "\n" + "studies:" + studies);

           //Sending email
            Transport.send(mm);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return null;
    }
}