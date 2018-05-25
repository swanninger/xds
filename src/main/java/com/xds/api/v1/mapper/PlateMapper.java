package com.xds.api.v1.mapper;

import com.xds.api.v1.model.PlateDTO;
import com.xds.domain.Plate;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PlateMapper {
    PlateMapper INSTANCE = Mappers.getMapper(PlateMapper.class);

    Plate plateDtoToPlate(PlateDTO plateDTO);
}
