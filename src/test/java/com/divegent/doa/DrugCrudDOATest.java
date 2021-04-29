package com.divegent.doa;

class DrugCrudDOATest {
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
//		statement.execute("drop table if exists drugs");
//		System.out.println("Table Deleted");
//		statement.executeUpdate("create table if not exists  drugs(drugs_id int auto_increment, drugs_name varchar(30), drugs_description varchar(30) )");
//		System.out.println("Table created successfully...");
//		statement.executeUpdate(" insert into drugs values(2001,'peracetamol','fever')");
//	}
//	@Test
//	void testDelete() throws SQLException {
//		ApplicationContext context = new ClassPathXmlApplicationContext(
//				"testconfig.xml");
//		 DrugCrudDOA crudDOA= (DrugCrudDOA) context.getBean("drugsdoaid");
//		  assertEquals(1,crudDOA.delete(2001));
//	}
//
//	@Test
//	void testCreate() throws SQLException {
//		ApplicationContext context = new ClassPathXmlApplicationContext(
//				"testconfig.xml");
//		 DrugCrudDOA crudDOA= (DrugCrudDOA) context.getBean("drugsdoaid");
//		assertEquals(1,crudDOA.create(2002,"allegra","alergy"));
//	}
//
//	@Test
//	void testRead() throws SQLException {
//		ApplicationContext context = new ClassPathXmlApplicationContext(
//				"testconfig.xml");
//		 DrugCrudDOA crudDOA= (DrugCrudDOA) context.getBean("drugsdoaid");
//		 List<Map<Integer, String>> list =crudDOA.read();
//		 assertFalse(list.isEmpty());
//	}
//
//	@Test   
//	void testUpdate() throws SQLException {
//		ApplicationContext context = new ClassPathXmlApplicationContext(
//				"testconfig.xml");
//		 DrugCrudDOA crudDOA= (DrugCrudDOA) context.getBean("drugsdoaid");
//		 assertEquals(1,crudDOA.update(2001,"pata nhi","fever"));
//	}

}
