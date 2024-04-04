package com.thbs.ms.im.training.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseRequest {

	private String trackingHeader;
	
	public String getTrackingHeader() {
		return trackingHeader;
	}
	public void setTrackingHeader(String trackingHeader) {
		this.trackingHeader = trackingHeader;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	private String contentType;
	
	
	
}
