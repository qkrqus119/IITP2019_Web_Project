package com.soccermatching.dto;

public class ApiInfoDTO {
	private String apiName;
	private String apiKey;

	public String getApiName() {
		return apiName;
	}

	public void setApiName(String apiName) {
		this.apiName = apiName;
	}

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	@Override
	public String toString() {
		return "ApiInfoDTO [apiName=" + apiName + ", apiKey=" + apiKey + "]";
	}

}
