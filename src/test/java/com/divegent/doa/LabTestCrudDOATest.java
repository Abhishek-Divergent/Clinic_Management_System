package com.divegent.doa;

class LabTestCrudDOATest {
	
//	
//	Connection connection=null;
//    H2DatabaseManager databaseManager=null;
//	Statement statement;
//	
//	@Before
//	private void TestSetup() throws SQLException {
//		ApplicationContext context = new ClassPathXmlApplicationContext(
//				"testconfig.xml");
//		databaseManager = (H2DatabaseManager) context.getBean("h2databaseid");
//		connection = databaseManager.connection();
//		statement = connection.createStatement();
//		statement.execute("drop table if exists labtest");
//		System.out.println("Table Deleted");
//		statement.executeUpdate("create table if not exists  labtest(labtest_id int auto_increment, labtest_name varchar(30), labtest_price int )");
//		System.out.println("Table created successfully...");
//		statement.executeUpdate(" insert into labtest values(1001,'Blood Test',45215)");
//		System.out.println("Data inserted successfully...");
//
//	}
//	
//	@Test
//	void testDelete() throws SQLException {
//		ApplicationContext context = new ClassPathXmlApplicationContext(
//				"testconfig.xml");
//		 LabTestCrudDOA crudDOA= (LabTestCrudDOA) context.getBean("labtestdoaid");
//		  assertEquals(1,crudDOA.delete(1001));
//	}
//
//	@Test
//	void testCreate() throws SQLException {
//		ApplicationContext context = new ClassPathXmlApplicationContext(
//				"testconfig.xml");
//		 LabTestCrudDOA crudDOA= (LabTestCrudDOA) context.getBean("labtestdoaid");
//		  assertEquals(1,crudDOA.create(1002,"sugar test" , 20015));
//	}
//
//	@Test
//	void testRead() throws SQLException {
//		ApplicationContext context = new ClassPathXmlApplicationContext(
//				"testconfig.xml");
//		 LabTestCrudDOA crudDOA= (LabTestCrudDOA) context.getBean("labtestdoaid");
//		 List<Map<Integer, String>> list =crudDOA.read();
//		 assertFalse(list.isEmpty());
// 	}
//
//	@Test
//	void testUpdate() throws SQLException {
//		ApplicationContext context = new ClassPathXmlApplicationContext(
//				"testconfig.xml");
//		 LabTestCrudDOA crudDOA= (LabTestCrudDOA) context.getBean("labtestdoaid");
//		  assertEquals(1,crudDOA.update(1001,"test karo", 1000));
//	}

}
