package org.awesomebakery.utils;

import java.util.List;
import java.util.Vector;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;

public class JsonListHelper {
	public static <T> List<T> toObjectList(Class<T> clazz, ArrayNode arrayNode) {
		List<T> list = new Vector<>();
		for (JsonNode node : arrayNode) {
			try {
				@SuppressWarnings("unchecked")
				T o = (T) clazz.getMethod("fromJson", JsonNode.class).invoke(null, node);
				list.add(o);
			} catch (Throwable t) {
				throw new RuntimeException("failed to convert to json", t);
			}
		}
		return list;
	}
}
