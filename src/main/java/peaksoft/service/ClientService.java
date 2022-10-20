package peaksoft.service;

import peaksoft.exception.BadCredentialsException;
import peaksoft.exception.NotFoundException;
import peaksoft.models.Client;
import peaksoft.repository.ClientRepository;

import java.util.List;

public class ClientService implements AutoCloseable{
    private ClientRepository clientRepository = new ClientRepository();

    public void register(Client client) {
        Boolean exists = clientRepository.existsByEmail(client.getEmail());
        if (exists) {
            throw new IllegalStateException(
                    String.format("Illegal state exception!!!")
            );
        } else clientRepository.save(client);
    }

    public Boolean login(String email, String password) {
        Client client = clientRepository.findByEmail(email).orElseThrow(() -> new NotFoundException(
                String.format("Not found exception!")));
        if (password.equals(client.getPassword())) return true;
        else {
            throw new BadCredentialsException(
                    String.format("Bad credentials exception!!!")
            );
        }
    }

    public List<Client> getAllClient() {
        return clientRepository.getAllClient();
    }

    @Override
    public void close() throws Exception {
        clientRepository.close();
    }
}
