package caster.demo.code.base;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Map;
import java.util.Set;

public class ServletUtils {

	public static void responseJson(HttpServletResponse response, String jsonString) {
		responseJson(response, jsonString, null);
	}

	public static void responseJson(HttpServletResponse response, String jsonString, String charset) {
		response.setHeader("Content-Type", "application/json" +
				(StrUtils.isBlank(charset) ? "" : ";charset=" + charset));
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write(jsonString);
			writer.flush();
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			StreamUtils.close(writer);
		}
	}

	public static boolean isMultipartRequest(HttpServletRequest request) {
		String contentType = request.getContentType();
		return contentType != null && contentType.toLowerCase().contains("multipart");
	}

	public static StringBuilder simpleSummary(HttpServletRequest request) {
		StringBuilder builder = new StringBuilder();

		builder.append(STRING_AT).append(BRACKETS_LEFT)
			.append(TimeUtils.format()).append(BRACKETS_RIGHT).append(BLANK_SPACE);

		builder.append(BRACKETS_LEFT).append(request.getRemoteAddr())
			.append(ENGLISH_COLON).append(request.getRemotePort())
			.append(BRACKETS_RIGHT).append(BLANK_SPACE);

		builder.append(request.getMethod()).append(BLANK_SPACE);

		Map<String, String[]> parameterMap = request.getParameterMap();
		if (parameterMap.entrySet().size() != 0) {
			builder.append(STRING_PARAMETER).append(BRACKETS_LEFT)
				.append(parameterSummary(parameterMap)).append(BRACKETS_RIGHT)
				.append(BLANK_SPACE);
		}

		Cookie[] cookies = request.getCookies();
		if (cookies != null && cookies.length > 0) {
			builder.append(STRING_COOKIE).append(BRACKETS_LEFT);
			for (Cookie cookie : cookies) {
				builder.append(cookie.getName()).append(CHARACTER_EQUAL)
					.append(cookie.getValue()).append(ENGLISH_COMMA);
			}
			builder.deleteCharAt(builder.length() - 1);
			builder.append(BRACKETS_RIGHT).append(BLANK_SPACE);
		}

		String contentType = request.getContentType();
		if (StrUtils.isNotBlank(contentType)) {
			builder.append(STRING_CONTENT_TYPE).append(BRACKETS_LEFT)
				.append(contentType).append(BRACKETS_RIGHT).append(BLANK_SPACE);
		}

		String characterEncoding = request.getCharacterEncoding();
		if (StrUtils.isNotBlank(characterEncoding)) {
			builder.append(STRING_ENCODE).append(BRACKETS_LEFT)
				.append(characterEncoding).append(BRACKETS_RIGHT).append(BLANK_SPACE);
		}

		builder.append(STRING_TO).append(BLANK_SPACE).append(BRACKETS_LEFT)
			.append(request.getRequestURL().toString()).append(BRACKETS_RIGHT);

		return builder;
	}

	public static StringBuilder heavySummary(HttpServletRequest request) {
		StringBuilder builder = new StringBuilder(StrUtils.ENDL)
			.append(ARROWHEAD_RIGHT).append(ARROWHEAD_RIGHT)
			.append(ARROWHEAD_RIGHT).append(ARROWHEAD_RIGHT).append(StrUtils.ENDL);

		builder.append(STRING_TIME).append(ENGLISH_COLON).append(BLANK_SPACE)
			.append(TimeUtils.format()).append(StrUtils.ENDL);

		builder.append(STRING_FROM).append(ENGLISH_COLON).append(BLANK_SPACE)
			.append(request.getRemoteAddr()).append(ENGLISH_COLON)
			.append(request.getRemotePort()).append(StrUtils.ENDL);

		builder.append(STRING_METHOD).append(ENGLISH_COLON).append(BLANK_SPACE)
			.append(request.getMethod()).append(StrUtils.ENDL);

		builder.append(STRING_TARGET).append(ENGLISH_COLON).append(BLANK_SPACE)
			.append(request.getRequestURL().toString()).append(StrUtils.ENDL);

		Map<String, String[]> parameterMap = request.getParameterMap();
		if (parameterMap.entrySet().size() != 0) {
			builder.append(STRING_PARAMETER_LOWER_CASE).append(ENGLISH_COLON).append(BLANK_SPACE);
			builder.append(parameterSummary(parameterMap)).append(StrUtils.ENDL);
		}

		String characterEncoding = request.getCharacterEncoding();
		if (StrUtils.isNotBlank(characterEncoding)) {
			builder.append(STRING_CHARACTER_ENCODING).append(ENGLISH_COLON).append(BLANK_SPACE)
				.append(characterEncoding).append(StrUtils.ENDL);
		}

		Enumeration<String> headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String headerName = headerNames.nextElement();
			builder.append(headerName).append(ENGLISH_COLON).append(BLANK_SPACE)
				.append(request.getHeader(headerName)).append(StrUtils.ENDL);
		}

		builder.append(ARROWHEAD_LEFT).append(ARROWHEAD_LEFT)
			.append(ARROWHEAD_LEFT).append(ARROWHEAD_LEFT).append(StrUtils.ENDL);

		return builder;
	}

	private static StringBuilder parameterSummary(Map<String, String[]> parameterMap) {
		StringBuilder builder = new StringBuilder();
		if (parameterMap == null) return builder;
		Set<Map.Entry<String, String[]>> entries = parameterMap.entrySet();
		for (Map.Entry<String, String[]> entry : entries) {
			String[] values = entry.getValue();
			if (values.length == 1) {
				builder.append(entry.getKey()).append(CHARACTER_EQUAL)
						.append(values[0]).append(ENGLISH_COMMA);
			} else {
				builder.append(entry.getKey()).append(BRACKETS_LEFT)
						.append(BRACKETS_RIGHT).append(CHARACTER_EQUAL).append(BRACES_LEFT);
				for (String value : values) {
					builder.append(value).append(ENGLISH_COMMA);
				}
				builder.deleteCharAt(builder.length() - 1)
						.append(BRACES_RIGHT).append(ENGLISH_COMMA);
			}
		}
		return builder.deleteCharAt(builder.length() - 1);
	}

	private static final String BRACKETS_LEFT = "[";
	private static final String BRACKETS_RIGHT = "]";
	private static final String BLANK_SPACE = " ";
	private static final String ENGLISH_COLON = ":";
	private static final String ENGLISH_COMMA = ",";
	private static final String CHARACTER_EQUAL = "=";
	private static final String BRACES_LEFT = "{";
	private static final String BRACES_RIGHT = "}";
	private static final String ARROWHEAD_LEFT = "<<====";
	private static final String ARROWHEAD_RIGHT = "====>>";
	private static final String STRING_TO = "To";
	private static final String STRING_AT = "At";
	private static final String STRING_CONTENT_TYPE = "ContentType";
	private static final String STRING_ENCODE = "Encode";
	private static final String STRING_PARAMETER = "Parameter";
	private static final String STRING_COOKIE = "Cookie";
	private static final String STRING_TIME = "$time";
	private static final String STRING_FROM = "$from";
	private static final String STRING_TARGET = "$target";
	private static final String STRING_METHOD = "$method";
	private static final String STRING_CHARACTER_ENCODING = "$character-encoding";
	private static final String STRING_PARAMETER_LOWER_CASE = "$parameter";

}
