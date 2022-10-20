package peaksoft;

import peaksoft.models.Client;
import peaksoft.service.ClientService;


/**
 * Hello world!
 **/
public class App {
    public static void main( String[] args ) {
        Client client1 = new Client(
        "Argen","Abdymomunov","argen0407","argen@gmail.com");
        Client client2 = new Client(
        "Nurislam","Nurkanov","nuris1234","nuris@gmail.com");

        ClientService clientService = new ClientService();
//        clientService.register(client1);
//        clientService.register(client2);

        

//        clientService.getAllClient().forEach(System.out::println);
    }
}
