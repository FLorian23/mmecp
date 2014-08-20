/**
 * Created by Kevin van Bernum (bke) on 04.08.2014.
 * Copyright (c) 2014 Fraunhofer FOKUS
 */
package de.fhg.fokus.streetlife.mmecp;

import java.io.FileInputStream;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.List;

import javax.xml.transform.Source;

import org.dmg.pmml.*;
import org.xml.sax.InputSource;
import org.jpmml.model.*;

public class PmmlTest {

	public static void main(String[] args) {
		try {
			String path = PmmlTest.class.getClassLoader().getResource("example/modalChoice.pmml").getPath();
			Converter myConverter = new Converter();
			String rules = myConverter.convert(path);
			System.out.println(rules);
			//convert(path);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void convert(String pmmlPath) throws Exception {

		PMML pmml;
		InputStream is = new FileInputStream(pmmlPath);

		try {
			Source source = ImportFilter.apply(new InputSource(is));
			pmml = JAXBUtil.unmarshalPMML(source);
		} finally {
			is.close();
		}

		List<Model> models = pmml.getModels();

		for (Model model : models) {
			if (model instanceof TreeModel) {
				TreeModel treeModel = (TreeModel) model;
				format(treeModel.getNode(), "");
			}
		}
	}

	public static void format(Node node, String indent) {
		Predicate predicate = node.getPredicate();
		if (predicate == null) {
			throw new IllegalArgumentException("Missing predicate");
		}

		System.out.println(indent + "if(" + format(predicate) + "){");

		List<Node> children = node.getNodes();
		for (Node child : children) {
			format(child, indent + "\t");
		}

		if (node.getScore() != null) {
			StringBuffer scores = new StringBuffer();
			for (ScoreDistribution s : node.getScoreDistributions()) {
				DecimalFormat df = new DecimalFormat("##0.00");
				scores.append(s.getValue() + "=" + df.format(s.getConfidence() * 100) + "% ");
			}
			System.out.println(indent + "\t" + "return \"" + node.getScore() + "\"; // " + scores.toString());
		}

		System.out.println(indent + "}");
	}

	private static String format(Predicate predicate) {

		if (predicate instanceof SimplePredicate) {
			return format((SimplePredicate) predicate);
		} else if (predicate instanceof CompoundPredicate) {
			return format((CompoundPredicate) predicate);
		}

		if (predicate instanceof True) {
			return "true";
		} else if (predicate instanceof False) {
			return "false";
		}

		throw new IllegalArgumentException("Unsupported predicate " + predicate);
	}

	private static String format(SimplePredicate simplePredicate) {
		StringBuffer sb = new StringBuffer();

		sb.append((simplePredicate.getField()).getValue());
		sb.append(' ').append(format(simplePredicate.getOperator())).append(' ');
		sb.append(simplePredicate.getValue());

		return sb.toString();
	}

	private static String format(SimplePredicate.Operator operator) {

		switch (operator) {
		case EQUAL:
			return "==";
		case NOT_EQUAL:
			return "!=";
		case LESS_THAN:
			return "<";
		case LESS_OR_EQUAL:
			return "<=";
		case GREATER_THAN:
			return ">";
		case GREATER_OR_EQUAL:
			return ">=";
		default:
			throw new IllegalArgumentException();
		}
	}

	private static String format(CompoundPredicate compoundPredicate) {
		StringBuffer sb = new StringBuffer();

		List<Predicate> predicates = compoundPredicate.getPredicates();

		sb.append('(').append(format(predicates.get(0))).append(')');

		for (Predicate predicate : predicates.subList(1, predicates.size())) {
			sb.append(' ').append(format(compoundPredicate.getBooleanOperator())).append(' ');
			sb.append('(').append(format(predicate)).append(')');
		}

		return sb.toString();
	}

	private static String format(CompoundPredicate.BooleanOperator operator) {

		switch (operator) {
		case AND:
			return "&";
		case OR:
			return "|";
		case XOR:
			return "^";
		default:
			throw new IllegalArgumentException();
		}
	}

}
