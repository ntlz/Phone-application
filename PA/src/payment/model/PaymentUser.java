package payment.model;

/**
 * класс, содержащий информацию о карте плательщика
 */
public class PaymentUser {

    private String numberOfPhone;               //номер телефона
    private String password;                    //пароль
    private String email;                       //почта
    private boolean isNumberOfPhoneValid;       //флаг проверен/не проверен номер телефона
    private boolean isEmailValid;               //флаг проверен/не проверен email

    public PaymentUser(String numberOfPhone,
                       String password){
        this.numberOfPhone = numberOfPhone;
        this.password = password;       //добавить шифрование пароля !!!
        this.isNumberOfPhoneValid = false;
        this.isEmailValid = false;
    }

    public PaymentUser(String numberOfPhone,
                       String password,
                       String email) {
        this.numberOfPhone = numberOfPhone;
        this.password = password;       //добавить шифрование пароля !!!
        this.email = email;             //вставить проверку email с помощью регулярок
        this.isNumberOfPhoneValid = false;
        this.isEmailValid = false;
    }

    public String getNumberOfPhone() {
        return numberOfPhone;
    }

    public void addEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void changeNumberOfPhoneValid(){
        if(!isNumberOfPhoneValid)
            isNumberOfPhoneValid = true;
    }

    public void changeEmailValid(){
        if(!isEmailValid)
            isEmailValid = true;
    }

    public void changePhone(String newPhone){
        numberOfPhone = newPhone;
        isNumberOfPhoneValid = false;
    }

    public void changeEmail(String email){
        this.email = email;
        isEmailValid = false;
    }
}
