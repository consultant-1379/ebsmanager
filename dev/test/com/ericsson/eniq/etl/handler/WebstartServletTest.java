package com.ericsson.eniq.etl.handler;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import org.junit.Before;
import org.junit.Test;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServlet;

import com.ericsson.eniq.common.testutilities.DatabaseTestUtils;
import com.ericsson.eniq.etl.handler.WebstartServlet;

import ssc.rockfactory.RockFactory;

public class WebstartServletTest extends HttpServlet{
	private RockFactory rock = null;
	private WebstartServlet testInstance = new WebstartServlet();
	Class classObj1 = testInstance.getClass();
	Field field1 ;
	Field field2;
	Method method;

	@Before
	public void setUp() throws Exception {
		rock = DatabaseTestUtils.getTestDbConnection();
	}
	
	@Test
	public void testcloseRockConnection() {
		try {
			methodCall(rock);
		} catch (Exception e) {
			e.getMessage();
		}
	}

	@Test
	public void testcloseRockConnectionFailure() {
		try {
			rock=null;
			methodCall(rock);
		} catch (Exception e) {
			e.getMessage();
		}
	}
	
	@Test
	public void closeReaderTest() {
		try {
		InputStreamReader isr = new InputStreamReader(System.in);
		
		BufferedReader templateFileReader = new BufferedReader(isr);
		method = WebstartServlet.class.getDeclaredMethod("closeReader", InputStreamReader.class,BufferedReader.class);
		method.setAccessible(true);
		method.invoke(testInstance,isr,templateFileReader);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void closeReaderTestCatch() {
		try {
		InputStreamReader isr = null;
		BufferedReader templateFileReader = null;
		method = WebstartServlet.class.getDeclaredMethod("closeReader", InputStreamReader.class,BufferedReader.class);
		method.setAccessible(true);
		method.invoke(testInstance,isr,templateFileReader);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void methodCall(RockFactory rock)
			throws Exception {
		field1 = classObj1.getDeclaredField("etlRockFactory");
		field1.setAccessible(true);
		field1.set(testInstance, rock);
		field2 = classObj1.getDeclaredField("dwhRockFactory");
		field2.setAccessible(true);
		field2.set(testInstance, rock);
		method = WebstartServlet.class.getDeclaredMethod("closeRockConnection", null);
		method.setAccessible(true);
		method.invoke(testInstance, null);
	}
	
	@Test
	public void testisTechpacksPropertyPlaceholder() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		method = WebstartServlet.class.getDeclaredMethod("isTechpacksPropertyPlaceholder", String.class);
		method.setAccessible(true);
		method.invoke(testInstance, "@@TECHPACKS@@");
	}
	

	@Test
	public void testisBaseTechpacksPropertyPlaceholder() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		method = WebstartServlet.class.getDeclaredMethod("isBaseTechpacksPropertyPlaceholder", String.class);
		method.setAccessible(true);
		method.invoke(testInstance, "@@BASETECHPACKS@@");
	}
	
	@Test
	public void testcreateTechpacksProperty() {
		try{
			method = WebstartServlet.class.getDeclaredMethod("createTechpacksProperty",null);
		method.setAccessible(true);
		method.invoke(testInstance, null);
		}catch(Exception e) {
			
		}
	}
}
