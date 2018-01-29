package payment.model;

/**
 * класс, содержащий информацию о карте плательщика
 */
public class PaymentUser {

    private String phoneNumber;               //номер телефона
    private String password;                    //пароль
    private String email;                       //почта
    private boolean isPhoneNumberValid;       //флаг проверен/не проверен номер телефона
    private boolean isEmailValid;               //флаг проверен/не проверен email

    public PaymentUser(String phoneNumber,
                       String password){
        this.phoneNumber = phoneNumber;
        this.password = password;       //добавить шифрование пароля !!!
        this.isPhoneNumberValid = false;
        this.isEmailValid = false;
    }

    public PaymentUser(String phoneNumber,
                       String password,
                       String email) {
        this.phoneNumber = phoneNumber;
        this.password = password;       //добавить шифрование пароля !!!
        this.email = email;             //вставить проверку email с помощью регулярок
        this.isPhoneNumberValid = false;
        this.isEmailValid = false;
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

}
