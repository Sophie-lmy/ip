import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateTimeParser {
	private static final DateTimeFormatter INPUT_FORMAT =
			DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
	private static final DateTimeFormatter OUTPUT_FORMAT =
			DateTimeFormatter.ofPattern("MMM d yyyy HH:mm");

	/**
	 * Parses a date-time string to LocalDateTime.
	 *
	 * @param input the date-time string from user input
	 * @return a LocalDateTime object
	 * @throws IllegalArgumentException if the string format is invalid and give instructions
	 */
	public static LocalDateTime parse(String input) {
		try {
			return LocalDateTime.parse(input, INPUT_FORMAT);
		} catch (DateTimeParseException e) {
			throw new IllegalArgumentException(
					"Invalid date/time format. Please use yyyy-MM-dd HHmm, e.g. 2025-08-19 1600");
		}
	}

	/**
	 * Formats a LocalDateTime to output format
	 *
	 * @param dt the LocalDateTime object
	 * @return a formatted string for output
	 */
	public static String format(LocalDateTime dt) {
		return dt.format(OUTPUT_FORMAT);
	}
}
