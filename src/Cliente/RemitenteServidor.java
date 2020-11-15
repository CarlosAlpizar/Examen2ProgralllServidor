package Cliente;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author Carlos Alpizar <carlosalpizarg@hotmail.com>
 */
public class RemitenteServidor {

    public static boolean enviar(String host, int port, String mensaje) {

        try {
            Socket socket = new Socket(host, port);
            DataOutputStream output = new DataOutputStream(socket.getOutputStream());
            output.writeUTF(mensaje);
            return true;
        } catch (IOException ex) {
            return false;
        }

    }
}
