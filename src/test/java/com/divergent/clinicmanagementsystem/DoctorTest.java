package com.divergent.clinicmanagementsystem;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DoctorTest {

	@Test
	void test() {
		
		Doctor doctor= new Doctor();
		assertEquals(true , doctor.doctor_Login());
		
		
		
		//fail("Not yet implemented");
	}

}
