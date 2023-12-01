package entity;

import data_access.DialogflowDAOImpl;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class TestDialogflow {
    @Test
    public void testIntent() throws IOException {
        DialogflowDAOImpl dao = new DialogflowDAOImpl();
        String[] arr = dao.detectIntent("I have joint pain").split(",");
        String intent = arr[0];
        String response = arr[1];
//        List<String> ids = dao.getDoctors(intent);
        System.out.println(intent +":"+response);
//        for (String id: ids) {
//            System.out.println(id);
//        }
    }
}
