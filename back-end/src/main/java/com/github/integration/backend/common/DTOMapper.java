package com.github.integration.backend.common;

import com.github.integration.backend.common.entities.SearchResultDTO;
import com.github.integration.backend.controller.entities.ListRestResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DTOMapper {

    DTOMapper INSTANCE = Mappers.getMapper(DTOMapper.class);

    ListRestResponse searchResultToListRestResponse(SearchResultDTO searchResult);

}