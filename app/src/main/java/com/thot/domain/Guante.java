package com.thot.domain;

import com.google.gson.Gson;

/**
 * Created by javikin on 3/18/17.
 */

public class Guante {

	String action;
	String word;

	public Guante() {
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public static Guante fromJson(String json){
		Gson gson = new Gson();
		return gson.fromJson(json, Guante.class);
	}
}
