package co.coderiver.vjprithivi.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
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
 * Created by vjprithivi on 6/27/2016.
 */
public class bankmail extends AsyncTask<Void,Void,Void> {

    //Declaring Variables
    private Context context;
    private Session session;

    //Information to send email
    private String email;
    private String subject;
    private String y1;
    private String y2;



    private  String e1;
    private  String e2;
    private  String e3;

    private  String f1;
    private  String f2;
    private  String bankaccountyes;
    private  String y3;
    private  String y4;
    //Progressdialog to show while sending email
    private ProgressDialog progressDialog;

    //Class Constructor
    public bankmail(Context context, String email,String subject,String e1,String e2,String e3,String f1,String f2,String bankaccountyes,String y1,String y2,String y3,String y4){
        //Initializing variables
        this.context = context;

        this.email = email;
        this.subject = subject;

         this.f1=f1;

        this.f2=f2;
        this.e1 = e1;

        this.e2 = e2;
        this.e3 = e3;

        this.bankaccountyes=bankaccountyes;
        this.y1 = y1;
        this.y2 = y2;
        this.y3 = y3;
        this.y4 = y4;







    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        //Showing progress dialog while sending email
        progressDialog = ProgressDialog.show(context,"Sending message","Please wait...",false,false);
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        //Dismissing the progress dialog
        progressDialog.dismiss();
        //Showing a success message
        Toast.makeText(context,"Message Sent",Toast.LENGTH_LONG).show();
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
            mm.setText( "On what basis do you Choose a bank:"+f1+'\n'+'\n'+" What Type of Studies :"
                    +f2+'\n'+'\n'+"Name of School/University "+e1+'\n'+'\n'+"Town/City:"
                    +e2+'\n'+"postal code:"+e3+'\n'+"E-mail:"+email+'\n'+'\n'+"Do you have a Bank acount:"+bankaccountyes+
                    '\n'+"Name"+y1+'\n'+"Date of Brith:"+y2+'\n'
                    +"Nationality:"+y3+'\n'+"Telephone:"+y4+"\n");

            //Sending email
            Transport.send(mm);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return null;
    }
}