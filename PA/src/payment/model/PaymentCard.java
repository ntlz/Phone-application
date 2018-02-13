package payment.model;

/**
 * класс, содержащий информацию о заказчике
 */
public class PaymentCard {

    private String lastNumbers; //последние цифры номера карты

    public PaymentCard(String lastNumbers){
        this.lastNumbers = lastNumbers;
    }

    public String getLastNumbers() {
        return "**** **** **** " + lastNumbers;
    }

}
