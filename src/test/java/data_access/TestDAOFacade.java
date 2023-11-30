package data_access;

import entity.people.CommonPatient;
import entity.people.IPatient;
import org.junit.Test;

public class TestDAOFacade {
    DAOFacade daoFacade = new DAOFacade();

    @Test
    public void testSavePatient() {
        IPatient patient = new CommonPatient("Test1", "123");
        daoFacade.save(patient);
        assert daoFacade.existsByName(false, "Test1");
    }
}
