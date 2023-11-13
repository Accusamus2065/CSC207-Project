package entity;

import data_access.DialogflowDAOImpl;
import org.junit.Test;

import java.io.IOException;

public class TestDialogflow {
    @Test
    public void testIntent() throws IOException {
        DialogflowDAOImpl dao = new DialogflowDAOImpl();
        String[] arr = dao.detectIntent("I got a mole change on my back").split(",");
        String intent = arr[0];
        String response = arr[1];
        System.out.println(intent +":"+response);
    }
}
