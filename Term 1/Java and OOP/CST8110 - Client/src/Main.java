public class Main {
    public static void main(String[] args) {
        int      numberOfClients = 2;
        Client[] clients = new Client[numberOfClients];

        for (int counter = 0; counter < numberOfClients; counter++) {
            System.out.println("Enter information for Client: " + (counter + 1));
            Client client = new Client();
            client.keyboardClientInfo();
            clients[counter] = client;
        }

        System.out.println("List of Clients:");
        for (int counter = 0; counter < numberOfClients; counter++) {
            clients[counter].displayClientInfo();
        }

        System.out.println();
    }
}
