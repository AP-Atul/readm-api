package com.atul.readm.controller;

import java.util.Set;

import com.atul.readm.model.Chapter;
import com.atul.readm.model.Manga;

public class RClient {

	private RListener listener;

	public RClient(RListener listener) {
		this.listener = listener;
	}

	// genre can be null
	public void browse(int page, String genre) {
		new Thread() {
			@Override
			public void run() {
				listener.setMangas(RLoader.browse(page, genre));
			};
		}.start();
	}

	// search for manga by keyword
	public void search(String query) {
		new Thread() {

			@Override
			public void run() {
				listener.setMangas(RLoader.search(query));
			}
		}.start();
	}

	// get chapters
	public void chapters(Manga manga) {
		new Thread() {
			@Override
			public void run() {
				listener.setChapters(RLoader.getChapters(manga));
			};
		}.start();
	}

	// get pages
	public void pages(Chapter chapter) {
		new Thread() {
			@Override
			public void run() {
				listener.setPages(RLoader.getPages(chapter));
			}
		}.start();
	}

	// get all genres
	public Set<String> genres() {
		return RConstants.getGenres().keySet();
	}
}
