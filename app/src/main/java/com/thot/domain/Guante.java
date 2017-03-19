package com.thot.domain;

import com.google.gson.Gson;

import java.util.HashMap;

/**
 * Created by javikin on 3/18/17.
 */

public class Guante {

	String content;
	HashMap<String, String> notes;

	public Guante() {
	}

	public Guante(String content) {
		this.content = content;
	}

	public Guante(String content, HashMap<String, String> notes) {
		this.content = content;
		this.notes = notes;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public HashMap<String, String> getNotes() {
		return notes;
	}

	public void setNotes(HashMap<String, String> notes) {
		this.notes = notes;
	}

	public static Guante fromJson(String json){
		Gson gson = new Gson();
		return gson.fromJson(json, Guante.class);
	}
}
