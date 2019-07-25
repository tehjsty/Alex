package iim.connection;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;
import iim.data.ApplicationData;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Properties;

/**
 * Enables SSH Connection
 *
 * @author jplennis
 * @version 0.1
 */
public class SSHConnection implements ApplicationData {
    private static final String auth = "integra\n";
    private static final String listName = "uname -a\n";
    private static final String outputDescr = "Here is some information about the remote host: ";
    private static final String authFail = "Authentication failed.";
    private static final String user = "server.user";
    private static final String pw = "server.password";
    private static final String failMessage = "authfail";
    private static final String fileName = System.getProperty("user.home") + "settings.config";
    private static final String outputFile = System.getProperty("user.home") + "\\test.txt";
    /* Authentification wit the VM user and password used in the company
     */
    private final List <String> hosts;
    private Session sess;
    private Connection conn;

    /**
     * Creates the SSH Connection to the targeted Virtual Machine.
     * The parameter(Ip-adress) will be saved in a List and transferred to a String,
     * to use it with the SSH-Library.
     * The reachable adresses will be used one after another to check for a valid VM.
     * The specific name of the VM will be printed out //TODO
     *
     * @param hosts
     */
    public SSHConnection(List <String> hosts) {
        this.hosts = hosts;
    }

    public void closeConnection() {
        sess.close();
        conn.close();

    }

    public void connectionToSsh(String input) {

        for (String currentHost : this.hosts) {
            try {
                Properties properties = new Properties();
                InputStream inputStream = new FileInputStream(fileName);
                properties.load(inputStream);

                String authUser = properties.getProperty(user);
                String authPw = properties.getProperty(pw);

                conn = new Connection(currentHost);
                conn.connect();
                boolean isAuthenticated = conn.authenticateWithPassword(authUser, authPw);
                if (!isAuthenticated) throw new IOException(authFail);
                sess = conn.openSession();
                sess.requestDumbPTY(); //sortierter Output für den Test
                sess.startShell();
                OutputStream os = sess.getStdin();
                os.write(auth.getBytes());
                os.write(input.getBytes());

            } catch (IOException e) {
                e.printStackTrace(System.err);
            }

        }

    }

    public void readOutput() throws IOException {
        Path file = Paths.get(outputFile);

        System.out.println(outputDescr); //Debug format
        InputStream stdout = new StreamGobbler(sess.getStdout());
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int read;
        String output = "";
        while ((read = stdout.read(buffer)) != -1) {
            result.write(buffer, 0, read);
            Files.write(file, buffer);

        }

    }
}
