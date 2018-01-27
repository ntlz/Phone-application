package payment.model;

/**
 * класс, содержащий информацию о заказчике
 */
public class PaymentCard {

    private String lastNumbers;

    public PaymentCard(String lastNumbers){
        this.lastNumbers = lastNumbers;
    }

    public String getLastNumbers() {
        return "**** **** **** " + lastNumbers;
    }

}
