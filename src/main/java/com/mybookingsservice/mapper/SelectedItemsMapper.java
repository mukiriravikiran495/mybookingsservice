package com.mybookingsservice.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.mybookingsservice.domain.SelectedItemsDTO;
import com.mybookingsservice.entity.SelectedItems;

@Mapper(componentModel = "spring")
public interface SelectedItemsMapper {
    SelectedItemsDTO toDto(SelectedItems entity);
    List<SelectedItemsDTO> toDtoList(List<SelectedItems> items);
    
    SelectedItems toEntity(SelectedItemsDTO dto);
    List<SelectedItems> toEntityList(List<SelectedItemsDTO> dtoList);
}