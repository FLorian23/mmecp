/**
 * Created by Kevin van Bernum (bke) on 04.08.2014.
 * Copyright (c) 2014 Fraunhofer FOKUS
 */
package de.fhg.fokus.streetlife.mmecp;

public class PmmlTest {

	public static void main(String[] args) {
		String rules = "Something went wrong!";
		try {
			String schemaPath = PmmlTest.class.getClassLoader().getResource("example/carneades.rcs").getPath();
			String pmmlPath = PmmlTest.class.getClassLoader().getResource("example/modalChoice.pmml").getPath();
			Converter myConverter = new Converter(schemaPath);
			rules = myConverter.convert(new String[]{pmmlPath});
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(rules);
	}
}
