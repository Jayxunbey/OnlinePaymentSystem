package uz.pdp.online.onlinepayment.mapper;

import org.mapstruct.*;
import uz.pdp.online.onlinepayment.dto.services.resp.ServiceRespForAllDto;
import uz.pdp.online.onlinepayment.entity.inmongo.Service;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ServiceMapper {
    Service toEntity(ServiceRespForAllDto serviceRespForAllDto);

    ServiceRespForAllDto toDto(Service service);

}