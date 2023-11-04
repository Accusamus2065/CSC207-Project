package data_access;

import entity.people.CommonPatient;
import entity.people.IPatient;
import entity.people.PatientUserFactory;
import org.junit.Test;


public class TestPatientDAOImpl {
    PatientDAOImpl patientDAOImpl = new PatientDAOImpl(new PatientUserFactory());

    @Test
    public void testSave() {
        IPatient patient = new CommonPatient("test", "test");
        patientDAOImpl.save(patient);
        assert patientDAOImpl.existsByName("test");
    }
}
