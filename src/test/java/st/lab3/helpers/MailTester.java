package st.lab3.helpers;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import st.lab3.helpers.DriverFactory;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public abstract class MailTester {

    protected static Lock sequential = new ReentrantLock();

    protected static final String PASSWORD_EMAIL = "mciBMg(4ItZ7";
    protected static final String RECIPIENT_EMAIL = "d32f123@mail.ru";
    protected static final String THEME_EMAIL = "some theme";
    protected static final String BODY_EMAIL = "hello there! how is it going";

    @BeforeEach
    protected void setUp() {
        sequential.lock();
    }

    @AfterEach
    protected void tearDown() {
        sequential.unlock();
    }

    @BeforeAll
    protected static void preAll() {
        DriverFactory.getDrivers();
    }

    @AfterAll
    protected static void tearAll() {
        DriverFactory.tearDown();
    }

}
