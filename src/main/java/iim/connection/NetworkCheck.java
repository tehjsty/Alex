package iim.connection;

import iim.data.RootLayout;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

/*
 * Überprüft das Netzwerk(lokal) nach dem vorgegebenen Adressbereich, welcher die VMWare-
 * Adressen bei uns darstellt. Nach erfolgreichem Ping werden die Adressen in einer List
 * gespeichert und an die SSHConnection-Klasse übergeben.
 *
 * @author jplennis
 *
 */

public class NetworkCheck extends RootLayout {
    private static final int START_IP = 2; //0 ist default
    private static final int MAX_IP = 10;



//    //Liste mit erfolgreichen Verbindungen
//    List <String> connectionValues = new ArrayList <>();

    //Ursprungs Liste mit erfolgreichen Pings
    private final List <String> ipList = new ArrayList <>();

    public void scanNetwork() {

        try {
            InetAddress address;
            final InetAddress virtualIp = InetAddress.getByAddress(new byte[]{(byte) 172, 16, 100, 1});

            // IPv4 usage
            byte[] ip = virtualIp.getAddress();

            for (int i = START_IP; i <= MAX_IP; ++i) {
                ip[3] = (byte) i;
                address = InetAddress.getByAddress(ip);
                if (address.isReachable(0)) {
                    this.ipList.add(address.getHostAddress()); //entferne forward Slash

//                      TO DO:
//                      Überlegung in neue Liste zu speichern, falls mehr als zwei VMs
//                      gefunden werden

                } else System.out.println(REQUEST + address);
            }     //Debug Ausgabe, wird später entfernt

        } catch (Exception e) {
            e.getMessage();

        }

    }

    public List <String> getIpList() {
        return ipList;
    }
}