import model.entities.CarRental;
import model.entities.Vehicle;
import model.service.BrazilTaxService;
import model.service.RentalService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);

        Scanner sc = new Scanner(System.in);
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        System.out.println("Entre com os dados do aluguel: ");
        System.out.print("Modelo do carro: ");
        String carModel = sc.nextLine();
        System.out.println("Retirada (dd/MM/yyyy hh:mm) ");
        LocalDateTime start = LocalDateTime.parse(sc.next(), fmt);
        System.out.println("Retorno (dd/MM/yyyy hh:mm) ");
        LocalDateTime finish = LocalDateTime.parse(sc.next(), fmt);

        CarRental cr = new CarRental(start, finish, new Vehicle(carModel));

        System.out.print("Entre com o preço por hora");
        double pricePerHour = sc.nextDouble();
        System.out.print("Entre com preço por dia ");
        double pricePeDay = sc.nextDouble();

        RentalService rentalService = new RentalService(pricePeDay, pricePerHour, new BrazilTaxService());
        rentalService.processInvoice(cr);
        System.out.println("FATURA");
        System.out.println("Pagamento Basico: "+cr.getInvoice().getBasicPayment());
        System.out.println("Imposto : "+cr.getInvoice().getTax());
        System.out.println("Pagamento Total: "+cr.getInvoice().getTotalPayment());
        sc.close();

    }
}