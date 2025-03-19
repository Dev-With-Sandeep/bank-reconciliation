package nyggs.accounts.reconciliation.util;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class PaymentTransactionIdsConverter implements AttributeConverter<Map<Long, Double>, String> {

	private static final String PAIR_SEPARATOR = ",";
	private static final String KEY_VALUE_SEPARATOR = ":";

	@Override
	public String convertToDatabaseColumn(Map<Long, Double> attribute) {
		try {
			if (attribute == null || attribute.isEmpty()) {
				return "";
			}

			return attribute.entrySet().stream().map(entry -> entry.getKey() + KEY_VALUE_SEPARATOR + entry.getValue())
					.collect(Collectors.joining(PAIR_SEPARATOR));
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Map<Long, Double> convertToEntityAttribute(String dbData) {
		try {
			if (dbData == null || dbData.isEmpty()) {
				return new HashMap<>();
			}

			return Stream.of(dbData.split(PAIR_SEPARATOR)).map(pair -> pair.split(KEY_VALUE_SEPARATOR)).collect(
					Collectors.toMap(keyValue -> Long.valueOf(keyValue[0]), keyValue -> Double.valueOf(keyValue[1])));
		} catch (Exception e) {

			return null;
		}
	}
}
