package com.xds.api.v1.mapper;

import com.xds.api.v1.model.PlateDTO;
import com.xds.domain.Plate;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PlateMapper {
    PlateMapper INSTANCE = Mappers.getMapper(PlateMapper.class);

    Plate plateDtoToPlate(PlateDTO plateDTO);

    List<Plate> plateDtosToPlates(List<PlateDTO> plateDTOS);
}
