package entity;

import data_access.DialogflowDAOImpl;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class TestDialogflow {
    @Test
    public void testIntent() throws IOException {
        DialogflowDAOImpl dao = new DialogflowDAOImpl();
        List<Object> tuple = dao.detectIntent("I have joint pain");
        System.out.println(tuple.get(0));
        System.out.println(tuple.get(1));
    }


}
