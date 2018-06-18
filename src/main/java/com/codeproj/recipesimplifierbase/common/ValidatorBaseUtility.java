package com.codeproj.recipesimplifierbase.common;


import com.codeproj.recipesimplifierbase.common.exception.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

public class ValidatorBaseUtility {

	private static final Logger logger = LoggerFactory.getLogger(ValidationException.class);

	public static void mandatoryParameter(String name, Object object)
			throws ValidationException {
		if (object == null) {
			throw new ValidationException(Constants.VALIDATION_ERR_MSG_MANDATORY_PARAMETER + name);
		} else if (object instanceof String) {
			if (((String) object).trim().isEmpty()) {
				throw new ValidationException(Constants.VALIDATION_ERR_MSG_MANDATORY_PARAMETER + name);
			}
		}
	}

	public static void validateStringLength(String name, String strAttr,
			int maxLength) throws ValidationException {
		if (strAttr == null || "".equals(strAttr)) {
			return;
		}
		if (strAttr.length() > maxLength) {
			throw new ValidationException(name
					+ Constants.VALIDATION_ERR_MSG_PARAMETER_TOO_LONG + maxLength);
		}
	}

	public static String stripXSS(String value) {
		if (value != null) {

			// Avoid null characters
			value = value.replaceAll("", "");

			// Avoid anything between script tags
			Pattern scriptPattern = Pattern.compile("<script>(.*?)</script>",
					Pattern.CASE_INSENSITIVE);
			value = scriptPattern.matcher(value).replaceAll("");

			// Avoid anything in a src='...' type of expression
			scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\'(.*?)\\\'",
					Pattern.CASE_INSENSITIVE | Pattern.MULTILINE
							| Pattern.DOTALL);
			value = scriptPattern.matcher(value).replaceAll("");

			scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\"(.*?)\\\"",
					Pattern.CASE_INSENSITIVE | Pattern.MULTILINE
							| Pattern.DOTALL);
			value = scriptPattern.matcher(value).replaceAll("");

			// Remove any lonesome </script> tag
			scriptPattern = Pattern.compile("</script>",
					Pattern.CASE_INSENSITIVE);
			value = scriptPattern.matcher(value).replaceAll("");

			// Remove any lonesome <script ...> tag
			scriptPattern = Pattern.compile("<script(.*?)>",
					Pattern.CASE_INSENSITIVE | Pattern.MULTILINE
							| Pattern.DOTALL);
			value = scriptPattern.matcher(value).replaceAll("");

			// Avoid eval(...) expressions
			scriptPattern = Pattern.compile("eval\\((.*?)\\)",
					Pattern.CASE_INSENSITIVE | Pattern.MULTILINE
							| Pattern.DOTALL);
			value = scriptPattern.matcher(value).replaceAll("");

			// Avoid expression(...) expressions
			scriptPattern = Pattern.compile("expression\\((.*?)\\)",
					Pattern.CASE_INSENSITIVE | Pattern.MULTILINE
							| Pattern.DOTALL);
			value = scriptPattern.matcher(value).replaceAll("");

			// Avoid javascript:... expressions
			scriptPattern = Pattern.compile("javascript:",
					Pattern.CASE_INSENSITIVE);
			value = scriptPattern.matcher(value).replaceAll("");

			// Avoid vbscript:... expressions
			scriptPattern = Pattern.compile("vbscript:",
					Pattern.CASE_INSENSITIVE);
			value = scriptPattern.matcher(value).replaceAll("");

			// Avoid onload= expressions
			scriptPattern = Pattern.compile("onload(.*?)=",
					Pattern.CASE_INSENSITIVE | Pattern.MULTILINE
							| Pattern.DOTALL);
			value = scriptPattern.matcher(value).replaceAll("");
		}
		return value;
	}
	
	public static void entityIdValidator(Long id) throws ValidationException {
		if (id == null || id < 1) {
			logger.error("Id parameter: " + id + " is not valid. Cannot be null or less then 1");
			throw new ValidationException(Constants.VALIDATION_ERR_MSG_ERROR_DURING_SENDING_REQUEST);
		}
	}
	
//	public static void emailValidator(String email) throws ValidationException {
//		if (!isValidEmailAddress(email)) {
//			throw new ValidationException(Constants.VALIDATION_ERR_MSG_EMAIL_INVALID);
//		}
//	}

//	public static boolean isValidEmailAddress(String email) {
//		EmailValidator emailValidator= EmailValidator.getInstance();
//		return emailValidator.isValid(email);
//	}
}
