package iim.connection;

import ch.ethz.ssh2.ChannelCondition;
import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Properties;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Enables SSH Connection
 *
 * @author jplennis
 * @version 0.1
 */
public class SSHConnection {
    private static final String AUTH = "integra\n";
    private static final String OUTPUT_DESC = "Here is some information about the remote host: ";
    private static final String AUTH_FAIL = "Authentication failed.";
    private static final String USER = "server.user";
    private static final String PW = "server.password";
    private static final String FILENAME = System.getProperty("user.home") + "settings.config";
    private static final String OUTPUTFILE = System.getProperty("user.home") + "\\test.txt";
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
        conn.close();
    }

    public void connectionToSsh(String input) {

        for (String currentHost : this.hosts) {
            try (InputStream inputStream = new FileInputStream(FILENAME)) {
                Logger logger = Logger.getLogger(SSHConnection.class.getName());
                logger.setLevel(Level.INFO);
                logger.addHandler(new ConsoleHandler());
                Properties properties = new Properties();
                properties.load(inputStream);

                String authUser = properties.getProperty(USER);
                String authPw = properties.getProperty(PW);

                conn = new Connection(currentHost);
                conn.connect();
                boolean isAuthenticated = conn.authenticateWithPassword(authUser, authPw);
                if (!isAuthenticated) throw new IOException(AUTH_FAIL);
                sess = conn.openSession();
                sess.requestDumbPTY(); //sortierter Output für den Test
                sess.startShell();
                OutputStream os = sess.getStdin();
                Thread.sleep(5000);
                os.write(AUTH.getBytes());
                os.write(input.getBytes());

                sess.waitForCondition(ChannelCondition.STDOUT_DATA, 1000);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace(System.err);
            } finally {
                conn.close();
            }

        }

    }

    public void readOutput() throws IOException {
        Path file = Paths.get(OUTPUTFILE);

        System.out.println(OUTPUT_DESC); //Debug format
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
