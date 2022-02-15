package com.revature;

import com.google.errorprone.annotations.DoNotMock;
import com.revature.daos.ReimbursementDAO;
import com.revature.model.Reimbursement;
import com.revature.services.ReimbursementServices;

import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class ReimbursementServicesTest {

    @Mock
    static ReimbursementDAO rd;

    @InjectMocks
    private static ReimbursementServices rs;

    @Before
    public void initMocks() { MockitoAnnotations.openMocks(this); }

    @Test
    public void createReimbursementShouldReturnTrueOnSuccess() {

        Reimbursement reimbursement = new Reimbursement();

        // When DAO calls createReimbursement, force return "true" instead of running method
        Mockito.when(rd.createReimbursement(reimbursement)).thenReturn(true);

        // Run ReimbursementServices.createReimbursement()
        boolean test = rs.createReimbursement(reimbursement);

        // Checks if dao createReimbursement ran at all
        Mockito.verify(rd).createReimbursement(any());

        assertTrue(test);
    }

    @Test
    public void createReimbursementShouldReturnFalseOnFailure() {
        Reimbursement reimbursement = new Reimbursement();

        Mockito.when(rd.createReimbursement(reimbursement)).thenReturn(false);

        boolean test = rs.createReimbursement(reimbursement);

        Mockito.verify(rd).createReimbursement(any());

        assertFalse(test);
    }

    @Test
    public void getAllReimbursementsReturnsListOfReimbursements() {
        List<Reimbursement> lr = new ArrayList<>();
        Reimbursement reimbursement1 = new Reimbursement();
        Reimbursement reimbursement2 = new Reimbursement();

        lr.add(reimbursement1);
        lr.add(reimbursement2);

        Mockito.when(rd.getAllReimbursements()).thenReturn(lr);

        List<Reimbursement> test = rs.getAllReimbursements();

        Mockito.verify(rd).getAllReimbursements();

        assertEquals(lr, test);
    }

    @Test
    public void getReimbursementByIdReturnsReimbursement() {
        Reimbursement reimbursement = new Reimbursement();

        Mockito.when(rd.getReimbursementById(anyInt())).thenReturn(reimbursement);

        Reimbursement test = rs.getReimbursementById(2);

        Mockito.verify(rd).getReimbursementById(anyInt());

        assertEquals(reimbursement, test);

    }
}
