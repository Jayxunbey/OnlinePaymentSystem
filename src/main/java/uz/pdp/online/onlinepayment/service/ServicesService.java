package uz.pdp.online.onlinepayment.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.pdp.online.onlinepayment.common.exceptions.handling.CategoryExceptionHandler;
import uz.pdp.online.onlinepayment.common.exceptions.throwclasses.services.FieldsDataEmptyException;
import uz.pdp.online.onlinepayment.common.exceptions.throwclasses.services.ServiceNotFoundException;
import uz.pdp.online.onlinepayment.dto.services.req.ChangeActiveReqDto;
import uz.pdp.online.onlinepayment.dto.signup.req.ServicesAddingReqDto;
import uz.pdp.online.onlinepayment.dto.signup.req.ServicesUpdateReqDto;
import uz.pdp.online.onlinepayment.entity.inmongo.Field;
import uz.pdp.online.onlinepayment.repo.inmongo.ServiceRepository;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class ServicesService {

    private final CategoryService categoryService;
    private final ServiceRepository serviceRepository;

    public void adding(ServicesAddingReqDto servicesAddingReqDto) {
        checkViaReqruments(servicesAddingReqDto);

        var entity = getAsEntityFrom(servicesAddingReqDto);

        var save = serviceRepository.save(entity);

        log.info(String.valueOf(save));

    }

    public void update(ServicesUpdateReqDto servicesUpdateReqDto) {

        if (!categoryService.existsByNumber(servicesUpdateReqDto.getCategoryId())) {
            throw new CategoryExceptionHandler.CategoryNotFoundException(servicesUpdateReqDto.getCategoryId());
        }

        if (servicesUpdateReqDto.getFields().isEmpty()) {
            throw new FieldsDataEmptyException();
        }

        var entitlyOptional = serviceRepository.findByNumber(Integer.valueOf(servicesUpdateReqDto.getNumber()));

        if (entitlyOptional.isEmpty()) {
            throw new ServiceNotFoundException();
        }

        var service = entitlyOptional.get();

        service.setName(servicesUpdateReqDto.getName());
        service.setFee(servicesUpdateReqDto.getFee());
        service.setCashback(servicesUpdateReqDto.getCashback());
        service.setFields(servicesUpdateReqDto.getFields().toArray(i -> new Field[servicesUpdateReqDto.getFields().size()]));
        service.setCategoryId(servicesUpdateReqDto.getCategoryId());
        service.setRequestAddress(servicesUpdateReqDto.getRequestAddress());

        var updated = serviceRepository.save(service);

        log.info(String.valueOf(updated));

    }

    private void checkViaReqruments(ServicesAddingReqDto servicesAddingReqDto) {
        if (!categoryService.existsByNumber(servicesAddingReqDto.getCategoryId())) {
            throw new CategoryExceptionHandler.CategoryNotFoundException(servicesAddingReqDto.getCategoryId());
        }

        if (servicesAddingReqDto.getFields().isEmpty()) {
            throw new FieldsDataEmptyException();
        }
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

    public List<uz.pdp.online.onlinepayment.entity.inmongo.Service> getAllByCategoryNumberAndActiveTrue(String categoryNumber) {
        return serviceRepository.findByCategoryIdAndActiveTrue(categoryNumber);
    }

    public void changeActive(ChangeActiveReqDto changeActiveReqDto) {

        var serviceOptional = serviceRepository.findByNumber(Integer.valueOf(changeActiveReqDto.getNumber()));
        if (serviceOptional.isEmpty()) {
            throw new ServiceNotFoundException();
        }
        var service = serviceOptional.get();
        service.setActive(changeActiveReqDto.isActive());
        serviceRepository.save(service);

    }

    public List<uz.pdp.online.onlinepayment.entity.inmongo.Service> getAllByCategoryNumberAll(String number) {
        return serviceRepository.findByCategoryId(number);
    }

    public void deleteService(String number) {
        var entityOptional = serviceRepository.findByNumber(Integer.valueOf(number));
        if (entityOptional.isEmpty()) {
            throw new ServiceNotFoundException();
        }

        var service = entityOptional.get();
        service.setActive(false);

        serviceRepository.save(service);

    }
}
