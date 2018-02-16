package st.jigahd.support.sql;

public class Configuration {

    private String host;
    private int port;
    private String user;
    private String database;
    private String password;
    private boolean autoCommit;

    public Configuration(String host, int port, String user, String database, String password) {
        this.host = host;
        this.port = port;
        this.user = user;
        this.database = database;
        this.password = password;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public String getUser() {
        return user;
    }

    public String getDatabase() {
        return database;
    }

    public String getPassword() {
        return password;
    }

    public Configuration setAutoCommit(boolean autoCommit) {
        this.autoCommit = autoCommit;
        return this;
    }

    public boolean isAutoCommit() {
        return autoCommit;
    }
}
