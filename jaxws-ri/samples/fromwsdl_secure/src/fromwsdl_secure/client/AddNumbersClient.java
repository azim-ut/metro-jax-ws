package fromwsdl_secure.client;

public class AddNumbersClient {
    public static void main (String[] args) {
        try {


            //if there is a problem use debug property
            //System.setProperty("javax.net.debug", "all");

            fromwsdl_secure.client.AddNumbersPortType port = new fromwsdl_secure.client.AddNumbersService().getAddNumbersPort ();

            int number1 = 10;
            int number2 = 20;

            System.out.println("Invoking one-way operation. Nothing is returned from service.\n");
            port.oneWayInt(number1);

            System.out.printf ("Invoking addNumbers(%d, %d)\n", number1, number2);
            int result = port.addNumbers (number1, number2);
            System.out.printf ("The result of adding %d and %d is %d.\n\n", number1, number2, result);

            number1 = -10;
            System.out.printf ("Invoking addNumbers(%d, %d)\n", number1, number2);
            result = port.addNumbers (number1, number2);
            System.out.printf ("The result of adding %d and %d is %d.\n", number1, number2, result);
        } catch (AddNumbersFault_Exception ex) {
            System.out.printf ("Caught AddNumbersFault_Exception: %s\n", ex.getFaultInfo().getFaultInfo ());
        }
    }
}
