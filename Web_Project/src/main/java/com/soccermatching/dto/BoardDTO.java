package com.soccermatching.dto;

import java.time.LocalTime;
import java.util.Date;

public class BoardDTO {
	private int number;
	private int author;
	private Date gameDate;
	private LocalTime gameStartTime;
	private String gameType;
	private int gameGenderType;
	private String placeName;
	private String address;
	private String content;
	private Date registerDate;

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getAuthor() {
		return author;
	}

	public void setAuthor(int author) {
		this.author = author;
	}

	public Date getGameDate() {
		return gameDate;
	}

	public void setGameDate(Date gameDate) {
		this.gameDate = gameDate;
	}

	public LocalTime getGameStartTime() {
		return gameStartTime;
	}

	public void setGameStartTime(LocalTime gameStartTime) {
		this.gameStartTime = gameStartTime;
	}

	public String getGameType() {
		return gameType;
	}

	public void setGameType(String gameType) {
		this.gameType = gameType;
	}

	public int getGameGenderType() {
		return gameGenderType;
	}

	public void setGameGenderType(int gameGenderType) {
		this.gameGenderType = gameGenderType;
	}

	public String getPlaceName() {
		return placeName;
	}

	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	@Override
	public String toString() {
		return "BoardDTO [number=" + number + ", author=" + author + ", gameDate=" + gameDate + ", gameStartTime="
				+ gameStartTime + ", gameType=" + gameType + ", gameGenderType=" + gameGenderType + ", placeName="
				+ placeName + ", address=" + address + ", content=" + content + ", registerDate=" + registerDate + "]";
	}

}
