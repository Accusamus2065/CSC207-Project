package entity;

import data_access.DialogflowDAOImpl;
import org.junit.Test;

import java.io.IOException;

public class TestDialogflow {
    @Test
    public void testIntent() throws IOException {
        DialogflowDAOImpl dao = new DialogflowDAOImpl();
        String response = dao.detectIntent("I'm coughing a lot and I need a doctor");
        System.out.println(response);
    }
}
