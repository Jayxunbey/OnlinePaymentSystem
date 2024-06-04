package uz.pdp.online.onlinepayment.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uz.pdp.online.onlinepayment.common.exceptions.handling.CategoryExceptionHandler;
import uz.pdp.online.onlinepayment.common.exceptions.throwclasses.services.FieldsDataEmptyException;
import uz.pdp.online.onlinepayment.dto.signup.req.ServicesAddingReqDto;
import uz.pdp.online.onlinepayment.entity.inmongo.Field;
import uz.pdp.online.onlinepayment.repo.inmongo.ServiceRepository;

@Slf4j
@Service
@RequiredArgsConstructor

public class ServicesService {

    private final CategoryService categoryService;
    private final ServiceRepository serviceRepository;

    public void adding(ServicesAddingReqDto servicesAddingReqDto) {
        String categoryId = servicesAddingReqDto.getCategoryId();
        if (!categoryService.existsByNumber(categoryId)) {
            throw new CategoryExceptionHandler.CategoryNotFoundException(categoryId);
        }

        if (servicesAddingReqDto.getFields().isEmpty()) {
            throw new FieldsDataEmptyException();
        }

        var entity = getAsEntityFrom(servicesAddingReqDto);

        var save = serviceRepository.save(entity);

        log.info(String.valueOf(save));

    }

    private uz.pdp.online.onlinepayment.entity.inmongo.Service getAsEntityFrom(ServicesAddingReqDto servicesAddingReqDto) {
        return uz.pdp.online.onlinepayment.entity.inmongo.Service
                .builder()
                .name(servicesAddingReqDto.getName())
                .active(false)
                .categoryId(servicesAddingReqDto.getCategoryId())
                .cashback(servicesAddingReqDto.getCashback())
                .fee(servicesAddingReqDto.getFee())
                .requestAddress(servicesAddingReqDto.getRequestAddress())
                .fields(servicesAddingReqDto.getFields().toArray(i -> new Field[servicesAddingReqDto.getFields().size()]))
                .number(generateNumberForServiceSubId())
                .build();
    }

    private Integer generateNumberForServiceSubId() {

        long count = serviceRepository.count();
        return Integer.valueOf(String.valueOf(521000 + count));
    }
}
