package com.tsena.mastermind.component;

import java.beans.PropertyEditorSupport;

import com.tsena.mastermind.constant.PegColor;

/**
 * Class used to PegColor bean converter used in REST requests
 * @author tsena
 *
 */
public class PegColorConverter extends PropertyEditorSupport{

	 public void setAsText(final String text) throws IllegalArgumentException {
	        setValue(PegColor.fromValue(text));
	    }

}