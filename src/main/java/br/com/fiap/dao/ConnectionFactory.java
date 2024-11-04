package br.com.fiap.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
    private static ConnectionFactory instance;
    private Connection conexao;
    private String url;
    private String user;
    private String pass;
    private String driver;

    // Construtor com parâmetros
    private ConnectionFactory(String url, String user, String pass, String driver) {
        this.url = url;
        this.user = user;
        this.pass = pass;
        this.driver = driver;
    }

    public static  ConnectionFactory getInstance() {
        ConnectionFactory result = instance;
        if (result != null) {
            return result;
        }

        Properties prop = new Properties();  //Cria um objeto para Properties para carregar a propriedades do arquivo application.properties, que contém as config da conexao banco
        FileInputStream file = null; // carrega o arquivo application, que deve conter as chaves datasource

        try {
            file = new FileInputStream("./src/main/resources/application.properties");
            prop.load(file);

            String url = prop.getProperty("datasource.url");
            String user = prop.getProperty("datasource.username");
            String pass = prop.getProperty("datasource.password");
            String driver = prop.getProperty("datasource.driver-class-name");
            file.close();

            if (instance == null) {
                instance = new ConnectionFactory(url, user, pass, driver);
            }
            return  instance;


        } catch (FileNotFoundException e) {
            System.out.printf("Erro (FileNotFoundException): " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Erro (IOExeption): " + e.getMessage());
        }
        return  null;

    }
    public Connection getConexao() {
        try {
            if (this.conexao != null && !this.conexao.isClosed()) {
                return this.conexao;
            }

            if (this.getDriver() == null || this.getDriver().isEmpty()) {
                throw new ClassNotFoundException("Nome do driver nulo ou em branco");
            }

            if (this.getUrl() == null || this.getUrl().isEmpty()) {
                throw new IllegalArgumentException("URL de conexão nula ou em branco");
            }

            if (this.getUser() == null || this.getUser().isEmpty()) {
                throw new IllegalArgumentException("Usuário de conexão nulo ou em branco");
            }

            Class.forName(this.driver);
            this.conexao = DriverManager.getConnection(this.getUrl(), this.getUser(), this.getPass());
        } catch (SQLException e) {
            System.err.println("Erro de SQL: " + e.getMessage());
            System.exit(1);
        } catch (ClassNotFoundException e) {
            System.err.println("Erro ao carregar a classe do driver: " + e.getMessage());
            System.exit(1);
        }

        return conexao;
    }

    public String getUrl() {
        return url;
    }

    public String getUser() {
        return user;
    }

    public String getPass() {
        return pass;
    }

    public String getDriver() {
        return driver;
    }
}
