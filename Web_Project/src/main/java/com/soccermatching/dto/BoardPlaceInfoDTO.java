package com.soccermatching.dto;

public class BoardPlaceInfoDTO {
	private int boardNumber;
	private String lat;
	private String lng;

	public int getBoardNumber() {
		return boardNumber;
	}

	public void setBoardNumber(int boardNumber) {
		this.boardNumber = boardNumber;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	@Override
	public String toString() {
		return "BoardPlaceInfoDTO [boardNumber=" + boardNumber + ", lat=" + lat + ", lng=" + lng + "]";
	}

}
