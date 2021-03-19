package com.divergent.clinicmanagementsystem;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class AdminTest {
    Admin admin=new Admin();
	@Test
	void test() {
		assertEquals(true,admin.admin_Login());
	}
	@Test
	void test1() {
		assertEquals(true,admin.admin_pannel());
	}
}
