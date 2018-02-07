package payment.model;

import common.model.Bill;

import java.util.Date;
import java.util.Vector;

/**
 * класс, содержащий информацию о карте плательщика
 */
public class PaymentUser {

    private String phoneNumber;               //номер телефона
    private String password;                    //пароль
    private String email;                       //почта
    private boolean isPhoneNumberValid;       //флаг проверен/не проверен номер телефона
    private boolean isEmailValid;               //флаг проверен/не проверен email
    private Vector<Bill> bills;

    public PaymentUser(String phoneNumber,
                       String password){
        this.phoneNumber = phoneNumber;
        this.password = password;       //добавить шифрование пароля !!!
        this.isPhoneNumberValid = false;
        this.isEmailValid = false;
        this.bills = new Vector<>(10);
    }

    public PaymentUser(String phoneNumber,
                       String password,
                       String email) {
        this.phoneNumber = phoneNumber;
        this.password = password;       //добавить шифрование пароля !!!
        this.email = email;             //вставить проверку email с помощью регулярок
        this.isPhoneNumberValid = false;
        this.isEmailValid = false;
        this.bills = new Vector<>(10);
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void addEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void changePhoneNumberValid(){
        if(!isPhoneNumberValid)
            isPhoneNumberValid = true;
    }

    public void changeEmailValid(){
        if(!isEmailValid)
            isEmailValid = true;
    }

    public void changePhoneNumber(String newPhoneNumber){
        phoneNumber = newPhoneNumber;
        isPhoneNumberValid = false;
    }

    public void changeEmail(String email){
        this.email = email;
        isEmailValid = false;
    }

    public void addBill(String idOfBill,
                        String description,
                        String nameOfSender,
                        Date dateOfSending,
                        Date dateOfPay,
                        Bill.Currency currency,
                        int sum){
        bills.add(new Bill(idOfBill, description, nameOfSender, dateOfSending, dateOfPay, Bill.Currency.RUBLE, sum));
    }

    public Vector<Bill> getBills(){
        return bills;
    }
}
