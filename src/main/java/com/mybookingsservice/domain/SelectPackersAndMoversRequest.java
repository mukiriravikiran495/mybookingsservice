package com.mybookingsservice.domain;

import java.util.List;

public class SelectPackersAndMoversRequest {

	private MyBookingsDTO myBookingsDTO;
	private List<SelectedItemsDTO> selectedItemsDTO;
	public MyBookingsDTO getMyBookingsDTO() {
		return myBookingsDTO;
	}
	public void setMyBookingsDTO(MyBookingsDTO myBookingsDTO) {
		this.myBookingsDTO = myBookingsDTO;
	}
	public List<SelectedItemsDTO> getSelectedItemsDTO() {
		return selectedItemsDTO;
	}
	public void setSelectedItemsDTO(List<SelectedItemsDTO> selectedItemsDTO) {
		this.selectedItemsDTO = selectedItemsDTO;
	}
	
	
}
