package com.codeproj.recipesimplifierbase.common;


public class Constants {

	public static final String PARAMETER_STRING_SIZE_MORE_THEN_10 = "Lorem ipsum";
	public static final String PARAMETER_STRING_SIZE_MORE_THEN_100 = "Lorem ipsum dolor sit amet, consectetur "
							+ "adipiscing elit. Proin quis sem eget erat pharetra mattis sed. ";
	public static final String PARAMETER_STRING_SIZE_MORE_THEN_300 = "Lorem ipsum dolor sit amet, consectetur "
							+ "adipiscing elit. Aliquam nec blandit dolor. Aenean at volutpat ipsum, quis dignissim nisi. Ut "
							+ "sed arcu elementum, dignissim nisl a, adipiscing tortor. Nullam sit amet risus faucibus, luctus turpis ut, rhoncus massa. Sed mollis justo dapibus faucibus turpis duis. ";
	
	public static final String VALIDATION_ERR_MSG_ERROR_DURING_SENDING_REQUEST = "Hiba történt az adatok lekérése közben! Az adatok nem lettek elküldve, vagy a megadott azonosító nem érvényes.";
	public static final String VALIDATION_ERR_MSG_MISSING_PREREQUISITE = "Ez a tanítvány még nem rendelkezik a megfelelő előfeltételekkel: ";
	public static final String VALIDATION_ERR_MSG_TRAINING_TYPE_DOESNT_EXIST = "A tanfolyam típus nem létezik";
	public static final String VALIDATION_ERR_MSG_MANDATORY_PARAMETER = "Ezt a paramétert kötelező megadni: ";
	public static final String VALIDATION_ERR_MSG_PARAMETER_TOO_LONG = " túl hosszú. A maximum hosszúság: ";
	public static final String VALIDATION_ERR_MSG_EMAIL_INVALID = "Az email nem megfelelő formátumú";
	public static final String VALIDATION_DATE_NOT_VALID_1 = "A megadott idő: ";
	public static final String VALIDATION_DATE_NOT_VALID_2 = " nem lehet régebbi, mint ";
	public static final String VALIDATION_DATE_IN_THE_FUTURE_1 = "A megadott idő: ";
	public static final String VALIDATION_DATE_IN_THE_FUTURE_2 = " nem lehet a jövőben";
	public static final String VALIDATION_TRAINING_COMPLETION_DATE = "Tanfolyam elvégzési ideje";
	public static final String VALIDATION_ERR_MSG_REQUESTED_OBJECT_CANNOT_BE_FOUND = "A kért elem nem található";
	public static final String VALIDATION_ERR_MSG_TRAINING_COMPLETED_DATE_INVALID = "A megadott dátum érvénytelen";
	public static final String VALIDATION_ERR_MSG_GENERAL_ERROR = "A kért művelet közben szerver hiba történt!";
	public static final String VALIDATION_ERR_MSG_USER_TRAINING_COMPL_ALREADY_EXISTS = "A tanítvány már elvégezte ezt a tanfolyamot!";

}
