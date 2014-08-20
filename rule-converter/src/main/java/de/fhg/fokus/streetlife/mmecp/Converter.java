/**
 * Created by Kevin van Bernum (bke) on 04.08.2014.
 * Copyright (c) 2014 Fraunhofer FOKUS
 */
package de.fhg.fokus.streetlife.mmecp;

import org.dmg.pmml.*;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.input.sax.XMLReaders;
import org.jpmml.model.ImportFilter;
import org.jpmml.model.JAXBUtil;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class Converter {

	private final Element schema;
	private Element nodeWrapper;
	private String targetName;

	public Converter(String schemaPath) throws IOException, SAXException, JDOMException {
		// parse rule converter schema
		File file = new File(schemaPath);
		SAXBuilder sax = new SAXBuilder(XMLReaders.DTDVALIDATING);
		Document doc = sax.build(file);
		schema = doc.getRootElement();
	}

	// ToDo: multiple pmml`s

	public String convert(String pmmlPath) throws Exception {

		PMML pmml;
		InputStream is = new FileInputStream(pmmlPath);

		try {
			Source source = ImportFilter.apply(new InputSource(is));
			pmml = JAXBUtil.unmarshalPMML(source);
		} finally {
			is.close();
		}

		StringBuffer knowledgeBase = new StringBuffer("");
		knowledgeBase.append(schema.getChild("head").getText());
		knowledgeBase.append(schema.getChild("body").getChild("mapText").getText());

		String placeholder = schema.getChild("body").getAttributeValue("ph_nodeWrapper");
		int ruleIndex = knowledgeBase.indexOf(placeholder);

		List<Model> models = pmml.getModels();
		for (Model model : models) {
			if (model instanceof TreeModel) {
				TreeModel treeModel = (TreeModel) model;
				targetName = model.getTargets().getTargets().get(0).getField().getValue();

				// get node wrapper for current tree model
				for (Element nw : schema.getChildren("nodeWrapper"))
					if (nw.getAttributeValue("model").contains(model.getModelName()))
						nodeWrapper = nw;

				// don't convert root
				for (Node child : treeModel.getNode().getNodes()) {
					knowledgeBase.insert(ruleIndex, format(child, ""));
					ruleIndex = knowledgeBase.indexOf(placeholder);
				}
			} else {
				throw new IllegalArgumentException("Can't convert the model (" + model.getModelName() +
												   "). Rule Converter coverts only tree models!");
			}
		}
		knowledgeBase.replace(ruleIndex, ruleIndex + placeholder.length(), "");

		return knowledgeBase.toString();
	}

	private String format(Node node, String result) {
		String output = "";

		// make premise for current node
		Predicate predicate = node.getPredicate();
		if (predicate == null) throw new IllegalArgumentException("Missing predicate");
		// ToDo: insert predicate in rule as defined in schema
		result += "\t" + format(predicate);

		// check child nodes
		List<Node> children = node.getNodes();
		if (!children.isEmpty()) {
			// have childs -> make more premises
			for (Node child : children) {
				output += format(child, result + "\n");
			}
		} else {
			// node is a leaf -> make conclusion
			// ToDo: make correct conclusion as defined in schema
			String conclusion = "pred in result";

			// make premise out of score
			// ToDo: make premise out of score with format(predicate)
			result += "\n\t" + targetName + " " + node.getScore();

			// make rule
			// ToDo: make rule as defined in schema
			output = "\nbegin rule\n\n" + "\t" + conclusion + "\n" + result + "\n\nend rule\n";
		}

		return output;
	}

	private String format(Predicate predicate) {

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

	private String format(SimplePredicate simplePredicate) {
		// ToDo: make predicate as defined in schema + check OTYPE of field
		StringBuffer sb = new StringBuffer();

		sb.append((simplePredicate.getField()).getValue());
		sb.append(' ').append(format(simplePredicate.getOperator())).append(' ');
		sb.append(simplePredicate.getValue());

		return sb.toString();
	}

	private String format(SimplePredicate.Operator operator) {

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

	private String format(CompoundPredicate compoundPredicate) {
		StringBuffer sb = new StringBuffer();

		List<Predicate> predicates = compoundPredicate.getPredicates();

		sb.append('(').append(format(predicates.get(0))).append(')');

		for (Predicate predicate : predicates.subList(1, predicates.size())) {
			sb.append(' ').append(format(compoundPredicate.getBooleanOperator())).append(' ');
			sb.append('(').append(format(predicate)).append(')');
		}

		return sb.toString();
	}

	private String format(CompoundPredicate.BooleanOperator operator) {

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
